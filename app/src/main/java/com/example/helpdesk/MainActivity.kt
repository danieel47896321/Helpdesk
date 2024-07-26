package com.example.helpdesk

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.helpdesk.controller.MainController
import com.example.helpdesk.guestActivity.Helpdesk
import com.example.helpdesk.model.MainModel
import com.example.helpdesk.myClass.User
import com.example.helpdesk.userActivity.Home

class MainActivity : AppCompatActivity() {
    private lateinit var mainController: MainController
    private lateinit var mainModel: MainModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init() {
        mainModel = ViewModelProvider(this)[MainModel::class.java]
        mainController = MainController(mainModel, this)
        mainController.checkCurrentUser()
    }
    fun helpDeskPage() {
        startActivity(Intent(this, Helpdesk::class.java))
        finish()
    }
    fun homePage(user: User) {
        val intent = Intent(this, Home::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
        finish()
    }
}