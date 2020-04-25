package com.example.charactermaster

import android.content.Context
import android.content.SharedPreferences

class Storage(val context: Context, val prefName: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    fun saveCharacter(char: Character) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        //parse as JSON
        editor.putString("charName", char.charName)
        /*editor.putString("charClass", char.charClass)*/
        editor.putInt("charLevel", char.charLevel)
        editor.putInt("charXP", char.charXP)
        editor.putString("charAlignment", char.charAlignment)
        editor.putString("charRace", char.charRace)
        editor.putString("background", char.background)
        editor.putString("appearance", char.appearance)
        editor.putString("traits", char.traits)
        editor.putString("ideals", char.ideals)
        editor.putString("bonds", char.bonds)
        editor.putString("flaws", char.flaws)

        editor!!.commit()
    }

    /*fun loadCharacters(): ArrayList<Character> {
        return characters
    }*/

    fun clear() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.clear()
        editor.commit()
    }
}