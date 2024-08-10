package com.example.astrologermobileapp.introslide

import android.os.Parcel
import android.os.Parcelable

data class KundaliMatchingData(
    var boyDob: String = "",
    var boyTob: String = "",
    var boyTz: Double = 5.5,
    var boyLat: Double = 0.0,
    var boyLon: Double = 0.0,
    var girlDob: String = "",
    var girlTob: String = "",
    var girlTz: Double = 5.5,
    var girlLat: Double = 0.0,
    var girlLon: Double = 0.0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(boyDob)
        parcel.writeString(boyTob)
        parcel.writeDouble(boyTz)
        parcel.writeDouble(boyLat)
        parcel.writeDouble(boyLon)
        parcel.writeString(girlDob)
        parcel.writeString(girlTob)
        parcel.writeDouble(girlTz)
        parcel.writeDouble(girlLat)
        parcel.writeDouble(girlLon)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<KundaliMatchingData> {
        override fun createFromParcel(parcel: Parcel): KundaliMatchingData {
            return KundaliMatchingData(parcel)
        }

        override fun newArray(size: Int): Array<KundaliMatchingData?> {
            return arrayOfNulls(size)
        }
    }
}
