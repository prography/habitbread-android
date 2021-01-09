package com.example.newhabitbread.ui.fragment.ViewPager

import android.accounts.Account
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.newhabitbread.R
import kotlinx.android.synthetic.main.fragment_viewpager.*
import kotlinx.android.synthetic.main.fragment_viewpager.view.*

class ViewPager : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_viewpager, container, false)

        val fragmentList = arrayListOf(
            MyHabits(),
            Ranking(),
            MyAccount()
        )

        val adapter = PagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        view.main_viewpager.adapter = adapter
        view.main_viewpager.registerOnPageChangeCallback(PageChangeCallback())
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        main_bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.myHabits -> setPageIndex(1)
                R.id.bakery -> setPageIndex(2)
                R.id.habitCreatePage -> setPageIndex(3)
                R.id.ranking -> setPageIndex(4)
                R.id.account -> setPageIndex(5)

                else -> setPageIndex(0)
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun setPageIndex(index: Int) {
        main_viewpager.currentItem = index
    }

    private inner class PagerAdapter(list: ArrayList<Fragment>, fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {
        private val fragmentList = list

        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }
    }

    private inner class PageChangeCallback : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            main_bottom_navigation.selectedItemId = when (position) {
                2-> R.id.bakery
                3-> R.id.bakery

                4 -> R.id.ranking
                5 -> R.id.account

                else -> R.id.myHabits
            }
        }
    }
}