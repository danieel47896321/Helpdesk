package com.example.helpdesk.controller.guestController

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.helpdesk.guestActivity.Helpdesk
import com.example.helpdesk.guestFragments.CreateAccount
import com.example.helpdesk.guestFragments.SignIn
import com.example.helpdesk.model.guestModel.HelpdeskModel

class HelpdeskController (private var helpdeskModel: HelpdeskModel, private var view: Helpdesk) {
    fun setView(){
        view.setPager(ViewPagerAdapter(view, helpdeskModel.getTitles()), getTitles())
    }
    private fun getTitles(): Array<String?> {
        val titleSize = helpdeskModel.getTitles().size
        val titles = arrayOfNulls<String>(titleSize)
        for(i in 0 until titleSize)
            titles[i] = view.resources.getString(helpdeskModel.getTitles()[i])
        return titles
    }
    class ViewPagerAdapter(fragmentActivity: FragmentActivity, private val titles: IntArray): FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return SignIn()
                1 -> return CreateAccount()
            }
            return SignIn()
        }
        override fun getItemCount(): Int { return titles.size }
    }
}