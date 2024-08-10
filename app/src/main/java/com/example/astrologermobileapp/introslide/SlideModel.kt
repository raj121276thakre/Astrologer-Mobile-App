package com.example.astrologermobileapp.introslide

import android.os.Parcel
import android.os.Parcelable

data class SlideModel(
    val imageResId: Int, // If you have an image associated with each slide
    val title1: String,
    val title2: String
)
 : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(imageResId)
        parcel.writeString(title1)
        parcel.writeString(title2)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SlideModel> {
        override fun createFromParcel(parcel: Parcel): SlideModel {
            return SlideModel(parcel)
        }

        override fun newArray(size: Int): Array<SlideModel?> {
            return arrayOfNulls(size)
        }
    }
}
