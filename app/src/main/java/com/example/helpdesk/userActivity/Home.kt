package com.example.helpdesk.userActivity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.helpdesk.R
import com.example.helpdesk.controller.userController.HomeController
import com.example.helpdesk.guestActivity.Helpdesk
import com.example.helpdesk.model.userModel.HomeModel

class Home : AppCompatActivity() {
    private lateinit var homeModel: HomeModel
    private lateinit var homeController: HomeController
    private lateinit var backIcon: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()
    }
    private fun init() {
        homeModel = ViewModelProvider(this)[HomeModel::class.java]
        homeController = HomeController(homeModel, this)
        backIcon = findViewById<ImageView>(R.id.back_icon)
        backIcon.setImageResource(R.drawable.logout_icon)
        setBackIcon()
    }
    private fun setBackIcon() {
        backIcon.setOnClickListener {
            onBackPressed()
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        val builder = AlertDialog.Builder(this)
        builder.setTitle(resources.getString(R.string.SignOut))
            .setMessage(resources.getString(R.string.AreYouSure)).setCancelable(true).setPositiveButton(resources.getString(R.string.Yes)) { _, _ ->
                homeController.signOut()
                startActivity(Intent(this, Helpdesk::class.java))
                finish()
            }.setNegativeButton(resources.getString(R.string.No)) { _, _ -> }.show()
    }
}