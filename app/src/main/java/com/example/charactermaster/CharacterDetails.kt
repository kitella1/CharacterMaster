package com.example.charactermaster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_character_details.*

class CharacterDetails : AppCompatActivity() {
    var editable: Boolean = false
    var character: Character? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        viewPager?.adapter = fragmentAdapter

        editable = intent.getBooleanExtra("EXTRA_EDIT", false)

        character = intent.getParcelableExtra<Character>("EXTRA_CHAR")

        /*Log.i("LOG_CHARACTER", character?.background)*/

        TODO()
        /*https://stackoverflow.com/questions/46050185/android-pass-data-from-activity-to-fragment-in-viewpager*/
        tabs.setupWithViewPager(viewPager)
    }


}