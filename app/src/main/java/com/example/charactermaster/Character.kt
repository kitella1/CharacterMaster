package com.example.charactermaster

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable

data class Character(
    val charName: String = "",
    val charRace: String = "",
    val charClass: String = "",
    val charLevel: Int = 0,
    val profileImage: String = "") : Serializable {

    companion object {
        fun getCharacters(filename: String, context: Context): ArrayList<Character> {
            //create ArrayList of Character objects
            val characterList = ArrayList<Character>()

            try {
                //read json file
                val inputStream = context.assets.open(filename)
                val buffer = ByteArray(inputStream.available())
                inputStream.read(buffer)
                inputStream.close()

                //convert input to JSON
                val json = JSONObject(String(buffer, Charsets.UTF_8))
                val characters = json.getJSONArray("characters")

                //extract strings from the JSON objects
                //create new Character objects and add them to the List
                for (i in 0 until characters.length()) {
                    characterList.add(Character(
                        characters.getJSONObject(i).getString("charName"),
                        characters.getJSONObject(i).getString("charRace"),
                        characters.getJSONObject(i).getString("charClass"),
                        characters.getJSONObject(i).getInt("charLevel"),
                        characters.getJSONObject(i).getString("profileImage")))
                }

            } catch (e: JSONException) {
                e.printStackTrace()
            }

            //return the List of Character objects
            return characterList
        }
    }
}