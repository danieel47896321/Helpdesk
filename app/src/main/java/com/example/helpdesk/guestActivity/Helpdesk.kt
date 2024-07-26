package com.example.helpdesk.guestActivity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.helpdesk.R
import com.example.helpdesk.controller.guestController.HelpdeskController
import com.example.helpdesk.model.guestModel.HelpdeskModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class Helpdesk : AppCompatActivity() {
    private lateinit var helpdeskModel: HelpdeskModel
    private lateinit var helpdeskController: HelpdeskController
    private lateinit var title: TextView
    private lateinit var backIcon: ImageView
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_helpdesk)
        init()
    }
    private fun init() {
        helpdeskModel = ViewModelProvider(this)[HelpdeskModel::class.java]
        helpdeskController = HelpdeskController(helpdeskModel, this)
        title = findViewById<TextView>(R.id.title)
        backIcon = findViewById<ImageView>(R.id.back_icon)
        backIcon.visibility = View.GONE
        title.visibility = View.INVISIBLE
        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.viewPager2)
        helpdeskController.setView()
    }
    fun setPager(pagerAdapter: HelpdeskController.ViewPagerAdapter, titles: Array<String?>) {
        viewPager2.adapter = pagerAdapter
        TabLayoutMediator(tabLayout, viewPager2) { tab: TabLayout.Tab, position: Int -> tab.text = titles[position] }.attach()
    }

}