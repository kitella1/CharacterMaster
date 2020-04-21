package com.example.charactermaster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_character_details.*

class CharacterDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        viewPager?.adapter = fragmentAdapter

        tabs.setupWithViewPager(viewPager)
    }
}