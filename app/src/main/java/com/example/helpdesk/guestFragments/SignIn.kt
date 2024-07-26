package com.example.helpdesk.guestFragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.helpdesk.MainActivity
import com.example.helpdesk.R
import com.example.helpdesk.controller.guestController.SignInController
import com.example.helpdesk.guestActivity.ResetPassword
import com.example.helpdesk.model.guestModel.SignInModel
import com.example.helpdesk.myClass.PopUpMSG
import com.example.helpdesk.userActivity.Home
import com.google.android.material.textfield.TextInputLayout

class SignIn : Fragment() {
    private lateinit var signInModel: SignInModel
    private lateinit var signInController: SignInController
    private lateinit var resetPassword: TextView
    private lateinit var textInputLayoutEmail: TextInputLayout
    private lateinit var textInputLayoutPassword:TextInputLayout
    private lateinit var buttonSignIn: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var myView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        myView = inflater.inflate(R.layout.fragment_sign_in, container, false)
        signInModel = ViewModelProvider(this)[SignInModel::class.java]
        signInController = SignInController(signInModel, this)
        textInputLayoutEmail = myView.findViewById<TextInputLayout>(R.id.textInputLayoutEmail)
        textInputLayoutPassword = myView.findViewById<TextInputLayout>(R.id.textInputLayoutPassword)
        buttonSignIn = myView.findViewById<Button>(R.id.buttonSignIn)
        resetPassword = myView.findViewById<TextView>(R.id.resetPassword)
        progressBar = myView.findViewById<ProgressBar>(R.id.progressBar)
        endIcon()
        signInCheck()
        resetPasswordButton()
        return myView
    }
    @SuppressLint("SetTextI18n")
    private fun endIcon() {
        textInputLayoutEmail.setEndIconOnClickListener {
            textInputLayoutEmail.helperText = ""
            textInputLayoutEmail.editText?.setText("")
        }
    }
    private fun signInCheck() {
        buttonSignIn.setOnClickListener {
            signInController.buttonSignIn(
                textInputLayoutEmail.editText?.text.toString(),
                textInputLayoutPassword.editText?.text.toString()
            )
        }
    }
    fun moveToHome() {
        startActivity(Intent(activity, Home::class.java))
        activity?.finish()
    }
    private fun resetPasswordButton() {
        resetPassword.setOnClickListener {
            startActivity(Intent(activity, ResetPassword::class.java))
            activity?.finish()
        }
    }
    fun displayMessage(title: String, text: String, dest: Class<MainActivity>?) {
        if (dest == null) {
            PopUpMSG(myView.context, title, text)
        } else {
            PopUpMSG(myView.context, title, text, dest)
        }
    }
    fun setProgressBar(view: Int) {
        progressBar.visibility = view
    }
    fun setEmailHelper(text: String) {
        textInputLayoutEmail.helperText = text
    }
    fun setPasswordHelper(text: String) {
        textInputLayoutPassword.helperText = text
    }
}