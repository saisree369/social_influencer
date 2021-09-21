package com.practice.socialinfluencerr

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil


import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.ViewDataBinding
import com.practice.socialinfluencerr.databinding.SubProfileBinding;



class ProfilesAdapter() : RecyclerView.Adapter<ProfilesAdapter.ProfileViewHolder>(), Parcelable {

    private var profiles: List<Inf_Profile>? = null

    constructor(parcel: Parcel) : this() {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProfileViewHolder(
          DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.sub_profile,
            parent,
            false
        )
    )

    override fun getItemCount() = profiles?.size ?: 0

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        profiles?.let {

            holder.binding.profile = it[position]
            holder.binding.executePendingBindings()
        }
    }

    fun setProfiles(profiles: List<Inf_Profile>) {
        this.profiles = profiles
        notifyDataSetChanged()
    }

    inner class ProfileViewHolder(var binding: SubProfileBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProfilesAdapter> {
        override fun createFromParcel(parcel: Parcel): ProfilesAdapter {
            return ProfilesAdapter(parcel)
        }

        override fun newArray(size: Int): Array<ProfilesAdapter?> {
            return arrayOfNulls(size)
        }
    }

}