package com.example.charactermaster

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.charactermaster.Fragments.*

class FragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment
    {
        return when (position) {
            0 -> FragGeneral()
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