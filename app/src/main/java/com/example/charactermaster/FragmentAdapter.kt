package com.example.charactermaster

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.charactermaster.Fragments.*


class FragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    var character: Character? = null

    fun putCharacter(char : Character?) {
      character = char
    }

    override fun getItem(position: Int): Fragment
    {
        return when (position) {
            0 -> {
                val fragGen = FragGeneral()
                val bundle = Bundle()
                if (character != null) {
                    bundle.putParcelable("character", character)
                    fragGen.arguments = bundle
                    /*fragGen.arguments?.putBundle("character", bundle)*/
                }
                return fragGen

            }
            1 -> FragChar()
            2 -> FragAbilities()
            3 -> FragAttacks()
            4 -> FragSpells()
            else -> return FragInventory()
        }
    }

    override fun getCount(): Int {
        return 6
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "General"
            1 -> "Character"
            2 -> "Abilities"
            3 -> "Attacks"
            4 -> "Spells"
            else -> return "Inventory"
        }
    }
}