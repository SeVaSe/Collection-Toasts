package com.example.collectiontoasts

import android.os.Parcel
import android.os.Parcelable

class MyToast(val toastName: String, val toastDesc: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun toString(): String {
        return "Toast Name: $toastName, Description: $toastDesc"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(toastName)
        parcel.writeString(toastDesc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyToast> {
        override fun createFromParcel(parcel: Parcel): MyToast {
            return MyToast(parcel)
        }

        override fun newArray(size: Int): Array<MyToast?> {
            return arrayOfNulls(size)
        }
    }
}