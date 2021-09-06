package com.practice.socialinfluencerr


import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*


internal object PermissionHelper {
    const val REQUEST_CODE_ASK_PERMISSIONS = 1
    private val PERMISSIONS_ARRAY = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.ACCESS_FINE_LOCATION)
    private val permissionsList: MutableList<String> = ArrayList(PERMISSIONS_ARRAY.size)
    fun hasPermission(activity: Activity?): Boolean {
        for (permission in PERMISSIONS_ARRAY) {
            if (ContextCompat.checkSelfPermission(activity!!, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    fun requestPermission(activity: Activity?) {
        for (permission in PERMISSIONS_ARRAY) {
            if (ContextCompat.checkSelfPermission(activity!!, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission)
            }
        }
        ActivityCompat.requestPermissions(activity!!, permissionsList.toTypedArray(),
                REQUEST_CODE_ASK_PERMISSIONS)
    }
}
