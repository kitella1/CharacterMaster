package com.example.charactermaster

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_grimoire.*
import org.json.JSONException
import org.json.JSONObject

class GrimoireActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grimoire)

        val characterList = getCharacters("characters.json", this)

        //link to recycler view
        recyclerView.adapter = CharacterAdapter(this, characterList).apply {
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        }
    }

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
                    characters.getJSONObject(i).getString("profileImage"),
                    characters.getJSONObject(i).getString("charClass"),
                    characters.getJSONObject(i).getInt("charLevel"),
                    characters.getJSONObject(i).getInt("charXP"),
                    characters.getJSONObject(i).getString("charAlignment"),
                    characters.getJSONObject(i).getString("charRace"),
                    characters.getJSONObject(i).getString("background"),
                    characters.getJSONObject(i).getString("appearance"),
                    characters.getJSONObject(i).getString("traits"),
                    characters.getJSONObject(i).getString("ideas"),
                    characters.getJSONObject(i).getString("bonds"),
                    characters.getJSONObject(i).getString("flaws")
                ))
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        //return the List of Character objects
        return characterList
    }

    fun fabAddCharacter(view: View) {
        val newCharIntent = Intent(this, CharacterDetails::class.java)
        startActivity(newCharIntent)
    }
}