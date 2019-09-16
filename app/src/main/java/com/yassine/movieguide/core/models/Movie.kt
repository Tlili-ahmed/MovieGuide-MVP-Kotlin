package com.yassine.movieguide.core.models

import android.os.Parcel
import android.os.Parcelable

data class Movie(var id: Int, var title: String?, var year: String?, var image: String?, var description: String?, var trailerUrl: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(year)
        parcel.writeString(image)
        parcel.writeString(description)
        parcel.writeString(trailerUrl)
    }

    override fun describeContents(): Int {
        return hashCode()
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}