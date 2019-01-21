package com.sivianes.kotlinkleankode.domain.model

import android.os.Parcel
import android.os.Parcelable

class POTD() : Parcelable {

    lateinit var date : String
    lateinit var explanation : String
    lateinit var hdurl : String
    lateinit var media_type : String
    lateinit var service_version : String
    lateinit var title : String
    lateinit var url : String

    constructor(parcel: Parcel) : this() {
        date = parcel.readString()
        explanation = parcel.readString()
        hdurl = parcel.readString()
        media_type = parcel.readString()
        service_version = parcel.readString()
        title = parcel.readString()
        url = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(explanation)
        parcel.writeString(hdurl)
        parcel.writeString(media_type)
        parcel.writeString(service_version)
        parcel.writeString(title)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<POTD> {
        override fun createFromParcel(parcel: Parcel): POTD {
            return POTD(parcel)
        }

        override fun newArray(size: Int): Array<POTD?> {
            return arrayOfNulls(size)
        }
    }


}