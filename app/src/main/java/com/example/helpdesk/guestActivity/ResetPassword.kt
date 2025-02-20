package com.example.helpdesk.guestActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.helpdesk.MainActivity
import com.example.helpdesk.R
import com.example.helpdesk.controller.guestController.ResetPasswordController
import com.example.helpdesk.model.guestModel.ResetPasswordModel
import com.google.android.material.textfield.TextInputLayout

class ResetPassword : AppCompatActivity() {
    private lateinit var resetPasswordController: ResetPasswordController
    private lateinit var resetPasswordModel: ResetPasswordModel
    private lateinit var buttonFinish: Button
    private lateinit var textInputLayoutEmail: TextInputLayout
    private lateinit var backIcon: ImageView
    private lateinit var title: TextView
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        init()
    }
    private fun init() {
        resetPasswordModel = ViewModelProvider(this)[ResetPasswordModel::class.java]
        resetPasswordController = ResetPasswordController(resetPasswordModel, this)
        backIcon = findViewById<ImageView>(R.id.back_icon)
        title = findViewById<TextView>(R.id.title)
        buttonFinish = findViewById<Button>(R.id.buttonFinish)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        textInputLayoutEmail = findViewById<TextInputLayout>(R.id.textInputLayoutEmail)
        resetPasswordController.setView()
        backIcon()
        endIcon()
        buttonFinish()
    }
    fun setHeader(text: Int) {
        title.setText(text)
    }
    fun startActivity(destination: Class<*>) {
        startActivity(Intent(this, destination))
        finish()
    }
    private fun backIcon() {
        backIcon.setOnClickListener {
            resetPasswordController.backIcon()
        }
    }
    private fun endIcon() {
        textInputLayoutEmail.setEndIconOnClickListener {
            resetPasswordController.endIcon()
        }
    }
    fun clearText(text: String) {
        textInputLayoutEmail.helperText = text
        textInputLayoutEmail.editText!!.setText(text)
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(MainActivity::class.java)
    }
    fun setProgressBar(view: Int) {
        progressBar.visibility = view
    }
    private fun buttonFinish() {
        buttonFinish.setOnClickListener {
            resetPasswordController.buttonFinish(textInputLayoutEmail.editText?.text?.length!!, textInputLayoutEmail.editText!!.text.toString())
        }
    }
    fun setEmailHelperText(text: String) {
        textInputLayoutEmail.helperText = text
    }
}