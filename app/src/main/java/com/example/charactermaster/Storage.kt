package com.example.charactermaster

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import java.io.ByteArrayOutputStream


class Storage(val context: Context, val prefName: String = "") {
    val contextKey = "CHARACTER_MASTER"
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(contextKey, Context.MODE_PRIVATE)

    fun saveCharacter(char: Character) {
        val prefsEdit: SharedPreferences.Editor = sharedPreferences.edit()
        prefsEdit.putString(char.name, char.name)
        prefsEdit.commit()


        val charPrefs: SharedPreferences = context.getSharedPreferences(char.name, Context.MODE_PRIVATE)
        val charEdit: SharedPreferences.Editor = charPrefs.edit()
        charEdit.putString("name", char.name)
        charEdit.putString("image", "jester")
        /*editor.putString("charClass", char.charClass)*/
        charEdit.putInt("level", char.level)
        charEdit.putInt("xp", char.xp)
        charEdit.putString("alignment", char.alignment)
        charEdit.putString("race", char.race)
        charEdit.putString("background", char.background)
        charEdit.putString("appearance", char.appearance)
        charEdit.putString("traits", char.traits)
        charEdit.putString("ideals", char.ideals)
        charEdit.putString("bonds", char.bonds)
        charEdit.putString("flaws", char.flaws)
        charEdit.commit()
    }

    fun loadCharacters(): ArrayList<Character>{
        val entries: Map<String, *> = sharedPreferences.all
        val chars = arrayListOf<Character>()

        for ((key, value) in entries.entries) {
            val charPrefs: SharedPreferences = context.getSharedPreferences(value.toString(), Context.MODE_PRIVATE)
            val c = Character(
                charPrefs.getString("name", ""),
                "",
                "",
                charPrefs.getInt("level", 1),
                charPrefs.getInt("xp", 0),
                charPrefs.getString("alignment", ""),
                charPrefs.getString("race", ""),
                charPrefs.getString("background", ""),
                charPrefs.getString("appearance", ""),
                charPrefs.getString("traits", ""),
                charPrefs.getString("ideals", ""),
                charPrefs.getString("bonds", ""),
                charPrefs.getString("flaws", "")
            )
            Log.i("Character found", c.toString())
            chars.add(c)
        }

        return chars
    }

    fun clear() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.clear()
        editor.commit()
    }
}