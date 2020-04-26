package com.example.charactermaster

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(
    var name: String? = "",
    val image: String? = "",
    val charClass: String? = "",
    var level: Int = 0,
    var xp: Int = 0,
    var alignment: String? = "",
    var race: String? = "",
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
            parcel.writeString(name)
            parcel.writeString(image)
            parcel.writeString(charClass)
            parcel.writeInt(level)
            parcel.writeInt(xp)
            parcel.writeString(alignment)
            parcel.writeString(race)
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
