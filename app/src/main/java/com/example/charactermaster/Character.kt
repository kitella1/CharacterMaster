package com.example.charactermaster

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize
import org.json.JSONException
import org.json.JSONObject

@Parcelize
data class Character(
    val charName: String? = "",
    val profileImage: String? = "",
    val charClass: String? = "",
    val charLevel: Int = 0,
    val charXP: Int = 0,
    val charAlignment: String? = "",
    val charRace: String? = "",
    val background: String? = "",
    val appearance: String? = "",
    val traits: String? = "",
    val ideas: String? = "",
    val bonds: String? = "",
    val flaws: String? = ""
) : Parcelable {
    constructor(parcel: Parcel): this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun describeContents(): Int {
        return 0
    }

    companion object : Parceler<Character> {
        override fun Character.write(parcel: Parcel, flags: Int) {
            parcel.writeString(charName)
            parcel.writeString(profileImage)
            parcel.writeString(charClass)
            parcel.writeInt(charLevel)
            parcel.writeInt(charXP)
            parcel.writeString(charAlignment)
            parcel.writeString(charRace)
            parcel.writeString(background)
            parcel.writeString(appearance)
            parcel.writeString(traits)
            parcel.writeString(ideas)
            parcel.writeString(bonds)
            parcel.writeString(flaws)
        }
        override fun create(parcel: Parcel): Character {
            return Character(parcel)
        }
        /*override fun newArray(size: Int): Array<Character?>? {
            return arrayOfNulls<Character>(size)
        }*/
    }
}
