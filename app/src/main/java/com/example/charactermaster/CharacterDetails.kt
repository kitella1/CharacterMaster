package com.example.charactermaster

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.charactermaster.Fragments.FragGeneral
import kotlinx.android.synthetic.main.activity_character_details.*

class CharacterDetails : AppCompatActivity() {
    var editable: Boolean = false
    var character: Character? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        fragmentAdapter.putCharacter(intent.getParcelableExtra<Character>("EXTRA_CHAR"))
        viewPager?.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewPager)

        editable = intent.getBooleanExtra("EXTRA_EDIT", false)
        /*character = intent.getParcelableExtra<Character>("EXTRA_CHAR")*/

        if (editable)
        {
            fab.setImageDrawable(Drawable.createFromPath("@drawable/ic_edit"))
        }
        else
        {
            fab.setImageDrawable(Drawable.createFromPath("@drawable/ic_edit"))
        }

        /*Log.i("LOG_CHARACTER", character?.background)*/
    }


}