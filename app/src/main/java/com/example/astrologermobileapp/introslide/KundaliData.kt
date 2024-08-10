package com.example.astrologermobileapp.introslide

import android.os.Parcel
import android.os.Parcelable

data class KundaliData(
    var name: String = "",
    var dob: String = "",
    var tob: String = "",
    var lat: Double = 0.0,
    var lon: Double = 0.0,
    var tz: Double = 5.5
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(dob)
        parcel.writeString(tob)
        parcel.writeDouble(lat)
        parcel.writeDouble(lon)
        parcel.writeDouble(tz)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<KundaliData> {
        override fun createFromParcel(parcel: Parcel): KundaliData {
            return KundaliData(parcel)
        }

        override fun newArray(size: Int): Array<KundaliData?> {
            return arrayOfNulls(size)
        }
    }
}
