package com.jihan.shohid.retrofit

import android.os.Parcel
import android.os.Parcelable

data class Shohid(
    val age: String,
    val birth_place: String,
    val date_of_death: String,
    val description: String,
    val dob: String,
    val en_age: String,
    val en_birth_place: String,
    val en_date_of_death: String,
    val en_description: String,
    val en_dob: String,
    val en_name: String,
    val en_occupation: String,
    val en_personal_life: String,
    val en_reason: String,
    val id: String,
    val name: String,
    val occupation: String,
    val personal_life: String,
    val reason: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        (parcel.readValue(Any::class.java.classLoader) ?: "").toString(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        (parcel.readValue(Any::class.java.classLoader) ?: "").toString(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(age)
        parcel.writeString(birth_place)
        parcel.writeString(date_of_death)
        parcel.writeString(description)
        parcel.writeValue(dob)
        parcel.writeString(en_age)
        parcel.writeString(en_birth_place)
        parcel.writeString(en_date_of_death)
        parcel.writeString(en_description)
        parcel.writeValue(en_dob)
        parcel.writeString(en_name)
        parcel.writeString(en_occupation)
        parcel.writeString(en_personal_life)
        parcel.writeString(en_reason)
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(occupation)
        parcel.writeString(personal_life)
        parcel.writeString(reason)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Shohid> {
        override fun createFromParcel(parcel: Parcel): Shohid {
            return Shohid(parcel)
        }

        override fun newArray(size: Int): Array<Shohid?> {
            return arrayOfNulls(size)
        }
    }
}
