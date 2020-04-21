package com.example.charactermaster

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.charactermaster.Fragments.FragAbilities
import com.example.charactermaster.Fragments.FragChar
import com.example.charactermaster.Fragments.FragGeneral

class FragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment
    {
        return when (position) {
            0 -> FragGeneral()
            1 -> FragChar()
            else -> return FragAbilities()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "General"
            1 -> "Character"
            else -> {
                return "Abilities"
            }
        }
    }
}