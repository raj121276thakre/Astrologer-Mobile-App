package com.example.astrologermobileapp.introslide

import android.os.Parcel
import android.os.Parcelable

data class SlideModel(
    val imageRes: Int,
    val title: String,
    var inputText: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(imageRes)
        parcel.writeString(title)
        parcel.writeString(inputText)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<SlideModel> {
        override fun createFromParcel(parcel: Parcel): SlideModel {
            return SlideModel(parcel)
        }

        override fun newArray(size: Int): Array<SlideModel?> {
            return arrayOfNulls(size)
        }
    }
}

