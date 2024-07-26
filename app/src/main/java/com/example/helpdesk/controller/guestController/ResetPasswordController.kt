package com.example.helpdesk.controller.guestController

import android.util.Patterns
import com.example.helpdesk.MainActivity
import com.example.helpdesk.R
import com.example.helpdesk.guestActivity.Helpdesk
import com.example.helpdesk.guestActivity.ResetPassword
import com.example.helpdesk.model.guestModel.ResetPasswordModel
import com.example.helpdesk.myClass.PopUpMSG
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordController(private var resetPasswordModel: ResetPasswordModel, var view: ResetPassword) {
    fun setView() {
        val text = resetPasswordModel.getTitle()
        view.setHeader(text)
    }
    fun backIcon() {
        view.startActivity(Helpdesk::class.java)
    }
    fun endIcon() {
        view.clearText("")
    }
    private fun buttonFinish(email: String) {
        if (!isEmailValid(email)) {
            view.setEmailHelperText(view.resources.getString(R.string.InvalidEmail))
        } else {
            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener{
                task ->
                if (task.isSuccessful) {
                    val title = view.resources.getString(R.string.ResetPassword)
                    val text = view.resources.getString(R.string.ResetLink)
                    PopUpMSG(view, title, text, MainActivity::class.java)
                } else {
                    val title = view.resources.getString(R.string.Error)
                    val text = view.resources.getString(R.string.ErrorMSG)
                    PopUpMSG(view, title, text)
                }
            }
        }
    }
    private fun isEmailValid(email: String?): Boolean { return Patterns.EMAIL_ADDRESS.matcher(email).matches() }
    fun buttonFinish(length: Int, email: String) {
        if(length > 0) {
            buttonFinish(email)
        } else {
            view.setEmailHelperText(view.resources.getString(R.string.Required))
        }
    }
}