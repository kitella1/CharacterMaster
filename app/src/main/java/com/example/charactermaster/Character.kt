package com.example.charactermaster

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(
    var charName: String? = "",
    val profileImage: String? = "",
    val charClass: String? = "",
    var charLevel: Int = 0,
    var charXP: Int = 0,
    var charAlignment: String? = "",
    var charRace: String? = "",
    val background: String? = "",
    val appearance: String? = "",
    val traits: String? = "",
    val ideals: String? = "",
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
            parcel.writeString(ideals)
            parcel.writeString(bonds)
            parcel.writeString(flaws)
        }
        override fun create(parcel: Parcel): Character {
            return Character(parcel)
        }
    }
}
