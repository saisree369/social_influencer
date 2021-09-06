/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.practice.socialinfluencerr

//import com.huawei.camera.camerakit.*
import android.app.AlertDialog
import android.content.Intent
import android.graphics.ImageFormat
import android.graphics.SurfaceTexture
import android.media.Image
import android.os.*
import android.util.Log
import android.util.Size
import android.view.Surface
import android.view.TextureView.SurfaceTextureListener
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.huawei.camera.camerakit.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import java.util.concurrent.Semaphore
import java.util.concurrent.TimeUnit

/**
 * CameraKitHdrCaptureActivity
 *
 */
class SampleCameraScreen : AppCompatActivity() {
    private val mPreviewSurfaceChangedDone = ConditionVariable()
    private var mTextureView: AutoFitTextureView? = null
    private var mButtonCaptureImage: Button? = null
    private var mPreviewSize: Size? = null
    private var mCaptureSize: Size? = null
    private var mFile: File? = null
    private var mCameraKit: CameraKit? = null

    @Mode.Type
    private val mCurrentModeType = Mode.Type.HDR_MODE
    private var mMode: Mode? = null
    private var mModeCharacteristics: ModeCharacteristics? = null
    private var modeConfigBuilder: ModeConfig.Builder? = null
    private var mCameraKitThread: HandlerThread? = null
    private var mCameraKitHandler: Handler? = null
    private val mCameraOpenCloseLock = Semaphore(1)
    private val mSurfaceTextureListener: SurfaceTextureListener = object : SurfaceTextureListener {
        override fun onSurfaceTextureAvailable(texture: SurfaceTexture, width: Int, height: Int) {
            mCameraKitHandler!!.post { createMode() }
        }

        override fun onSurfaceTextureSizeChanged(texture: SurfaceTexture, width: Int, height: Int) {
            mPreviewSurfaceChangedDone.open()
        }

        override fun onSurfaceTextureDestroyed(texture: SurfaceTexture): Boolean {
            return true
        }

        override fun onSurfaceTextureUpdated(texture: SurfaceTexture) {}
    }
    private val actionDataCallback: ActionDataCallback = object : ActionDataCallback() {
        override fun onImageAvailable(mode: Mode, @Type type: Int, image: Image) {
            Log.d(TAG, "onImageAvailable: save img")
            when (type) {
                Type.TAKE_PICTURE -> {
                    val buffer = image.planes[0].buffer
                    val bytes = ByteArray(buffer.remaining())
                    buffer[bytes]
                    var output: FileOutputStream? = null
                    try {
                        output = FileOutputStream(mFile)
                        output.write(bytes)
                    } catch (e: IOException) {
                        Log.e(TAG, "IOException when write in run")
                    } finally {
                        image.close()
                        if (output != null) {
                            try {
                                output.close()
                            } catch (e: IOException) {
                                Log.e(TAG, "IOException when close in run")
                            }
                        }
                    }
                }
                else -> {
                }
            }
        }
    }
    private val actionStateCallback: ActionStateCallback = object : ActionStateCallback() {
        override fun onPreview(mode: Mode, state: Int, result: PreviewResult?) {
            if (state == PreviewResult.State.PREVIEW_STARTED) {
                Log.i(TAG, "onPreview Started")
            }
        }

        override fun onTakePicture(mode: Mode, state: Int, result: TakePictureResult?) {
            when (state) {
                TakePictureResult.State.CAPTURE_STARTED -> Log.d(TAG, "onState: STATE_CAPTURE_STARTED")
                TakePictureResult.State.CAPTURE_COMPLETED -> {
                    Log.d(TAG, "onState: STATE_CAPTURE_COMPLETED")
                    showToast("take picture success! file=$mFile")
                    toRegister(mFile.toString())

//                    val _context = SampleCameraScreen()
//                    val i = Intent(_context, AdvertiserRegisterActivity::class.java)
//                    i.putExtra("path", mFile.toString())
//                    _context.startActivity(i)
                   // finish()
                }
                else -> {
                }
            }
        }
    }

    private val mModeStateCallback: ModeStateCallback = object : ModeStateCallback() {
        override fun onCreated(mode: Mode) {
            Log.d(TAG, "mModeStateCallback onModeOpened: ")
            mCameraOpenCloseLock.release()
            mMode = mode
            mModeCharacteristics = mode.modeCharacteristics
            modeConfigBuilder = mMode!!.modeConfigBuilder
            configMode()
        }

        override fun onCreateFailed(cameraId: String, modeType: Int, errorCode: Int) {
            Log.d(TAG,
                    "mModeStateCallback onCreateFailed with errorCode: $errorCode and with cameraId: $cameraId")
            mCameraOpenCloseLock.release()
        }

        override fun onConfigured(mode: Mode) {
            Log.d(TAG, "mModeStateCallback onModeActivated : ")
            mMode!!.startPreview()
            runOnUiThread { mButtonCaptureImage!!.isEnabled = true }
        }

        override fun onConfigureFailed(mode: Mode, errorCode: Int) {
            Log.d(TAG, "mModeStateCallback onConfigureFailed with cameraId: " + mode.cameraId)
            mCameraOpenCloseLock.release()
        }

        override fun onFatalError(mode: Mode, errorCode: Int) {
            Log.d(TAG, "mModeStateCallback onFatalError with errorCode: " + errorCode + " and with cameraId: "
                    + mode.cameraId)
            mCameraOpenCloseLock.release()
            finish()
        }

        override fun onReleased(mode: Mode) {
            Log.d(TAG, "mModeStateCallback onModeReleased: ")
            mCameraOpenCloseLock.release()
        }
    }

    private fun createMode() {
        Log.i(TAG, "createMode begin")
        mCameraKit = CameraKit.getInstance(this)
        if (mCameraKit == null) {
            Log.e(TAG, "This device does not support CameraKit！")
            showToast("CameraKit not exist or version not compatible")
            return
        }
        val cameraLists = mCameraKit!!.cameraIdList
        if (cameraLists != null && cameraLists.size > 0) {
            Log.i(TAG, "Try to use camera with id " + cameraLists[0])
            val modes = mCameraKit!!.getSupportedModes(cameraLists[0])
            if (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        !Arrays.stream(modes).anyMatch { i: Int -> i == mCurrentModeType }
                    } else {
                        TODO("VERSION.SDK_INT < N")
                    }) {
                Log.w(TAG, "Current mode is not supported in this device!")
                return
            }
            try {
                if (!mCameraOpenCloseLock.tryAcquire(2000, TimeUnit.MILLISECONDS)) {
                    throw RuntimeException("Time out waiting to lock camera opening.")
                }
                mCameraKit!!.createMode(cameraLists[0], mCurrentModeType, mModeStateCallback, mCameraKitHandler!!)
            } catch (e: InterruptedException) {
                throw RuntimeException("Interrupted while trying to lock camera opening.", e)
            }
        }
        Log.i(TAG, "createMode end")
    }

    private fun configMode() {
        Log.i(TAG, "configMode begin")
        val previewSizes = mModeCharacteristics!!.getSupportedPreviewSizes(SurfaceTexture::class.java)
        val captureSizes = mModeCharacteristics!!.getSupportedCaptureSizes(ImageFormat.JPEG)
        Log.d(TAG, "configMode: captureSizes = " + captureSizes.size + ";previewSizes=" + previewSizes.size)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mCaptureSize = captureSizes.stream().findFirst().orElse(Size(4000, 3000))
        }
        val tmpPreviewSize = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            previewSizes.stream().filter { size: Size -> Math.abs(1.0f * size.height / size.width - 1.0f * mCaptureSize!!.getHeight() / mCaptureSize!!.getWidth()) < 0.01 }.findFirst().get()
        } else {
            TODO("VERSION.SDK_INT < N")
        }
        Log.i(TAG, "configMode: mCaptureSize = $mCaptureSize;mPreviewSize=$mPreviewSize")
        runOnUiThread { mTextureView!!.setAspectRatio(tmpPreviewSize.height, tmpPreviewSize.width) }
        waitTextureViewSizeUpdate(tmpPreviewSize)
        val texture: SurfaceTexture? = mTextureView!!.getSurfaceTexture()
        if (texture == null) {
            Log.e(TAG, "configMode: texture=null!")
            return
        }
        texture.setDefaultBufferSize(mPreviewSize!!.width, mPreviewSize!!.height)
        val surface = Surface(texture)
        modeConfigBuilder!!.addPreviewSurface(surface).addCaptureImage(mCaptureSize!!, ImageFormat.JPEG)
        modeConfigBuilder!!.setDataCallback(actionDataCallback, mCameraKitHandler)
        modeConfigBuilder!!.setStateCallback(actionStateCallback, mCameraKitHandler)
        if (isModeExist) {
            mMode!!.configure()
        }
        Log.i(TAG, "configMode end")
    }

    @get:Synchronized
    private val isModeExist: Boolean
        private get() = if (mMode != null) {
            true
        } else {
            false
        }

    private fun waitTextureViewSizeUpdate(targetPreviewSize: Size) {
        if (mPreviewSize == null) {
            mPreviewSize = targetPreviewSize
            mPreviewSurfaceChangedDone.close()
            mPreviewSurfaceChangedDone.block(PREVIEW_SURFACE_READY_TIMEOUT)
        } else {
            if (targetPreviewSize.height * mPreviewSize!!.width
                    - targetPreviewSize.width * mPreviewSize!!.height == 0) {
                mPreviewSize = targetPreviewSize
            } else {
                mPreviewSize = targetPreviewSize
                mPreviewSurfaceChangedDone.close()
                mPreviewSurfaceChangedDone.block(PREVIEW_SURFACE_READY_TIMEOUT)
            }
        }
    }

    private fun captureImage() {
        Log.i(TAG, "captureImage begin")
        if (mMode != null) {
            mMode!!.setImageRotation(90)
            mFile = File(getExternalFilesDir(null), System.currentTimeMillis().toString() + "pic.jpg")
            mMode!!.takePicture()
        }
        Log.i(TAG, "captureImage end")
    }

    private fun showToast(text: String) {
        runOnUiThread { Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show() }
    }
    fun toRegister(path:String){
        val intent = Intent(this, AdvertiserRegisterActivity::class.java)
        intent.putExtra("filepath", path)
        startActivity(intent)
        finish()
    }

    protected override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        mButtonCaptureImage = findViewById(R.id.capture_image)
        mButtonCaptureImage!!.setOnClickListener { v: View? -> captureImage() }
        mTextureView = findViewById(R.id.texture)
    }

    override fun onStart() {
        Log.d(TAG, "onStart: ")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume: ")
        super.onResume()
        if (!PermissionHelper.hasPermission(this)) {
            PermissionHelper.requestPermission(this)
            return
        } else {
            if (!initCameraKit()) {
                showAlertWarning(getString(R.string.warning_str))
                return
            }
        }
        startBackgroundThread()
        if (mTextureView != null) {
            if (mTextureView!!.isAvailable()) {
                mTextureView!!.setSurfaceTextureListener(mSurfaceTextureListener)
                mCameraKitHandler!!.post { createMode() }
            } else {
                mTextureView!!.setSurfaceTextureListener(mSurfaceTextureListener)
            }
        }
    }

    private fun showAlertWarning(msg: String) {
        AlertDialog.Builder(this).setMessage(msg)
                .setTitle("warning:")
                .setCancelable(false)
                .setPositiveButton("OK") { dialog, which -> finish() }
                .show()
    }

    override fun onPause() {
        Log.d(TAG, "onPause: ")
        if (mMode != null) {
            mCameraKitHandler!!.post {
                mMode = try {
                    mCameraOpenCloseLock.acquire()
                    mMode!!.release()
                    null
                } catch (e: InterruptedException) {
                    throw RuntimeException("Interrupted while trying to lock camera closing.", e)
                } finally {
                    Log.d(TAG, "closeMode:")
                    mCameraOpenCloseLock.release()
                }
            }
        }
        super.onPause()
    }

    private fun initCameraKit(): Boolean {
        mCameraKit = CameraKit.getInstance(this)
        if (mCameraKit == null) {
            Log.e(TAG, "initCamerakit: this devices not support camerakit or not installed!")
            return false
        }
        return true
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
        stopBackgroundThread()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        Log.d(TAG, "onRequestPermissionsResult: ")
        if (!PermissionHelper.hasPermission(this)) {
            Toast.makeText(this, "This application needs camera permission.", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun startBackgroundThread() {
        Log.d(TAG, "startBackgroundThread")
        if (mCameraKitThread == null) {
            mCameraKitThread = HandlerThread("CameraBackground")
            mCameraKitThread!!.start()
            mCameraKitHandler = Handler(mCameraKitThread!!.looper)
            Log.d(TAG, "startBackgroundTThread: mCameraKitThread.getThreadId()=" + mCameraKitThread!!.threadId)
        }
    }

    private fun stopBackgroundThread() {
        Log.d(TAG, "stopBackgroundThread")
        if (mCameraKitThread != null) {
            mCameraKitThread!!.quitSafely()
            try {
                mCameraKitThread!!.join()
                mCameraKitThread = null
                mCameraKitHandler = null
            } catch (e: InterruptedException) {
                Log.e(TAG, "InterruptedException in stopBackgroundThread " + e.message)
            }
        }
    }

    companion object {
        private val TAG = CameraKit::class.java.simpleName
        private const val PREVIEW_SURFACE_READY_TIMEOUT = 5000L
    }
}