package com.example.helpdesk.model.guestModel

import androidx.lifecycle.ViewModel
import com.example.helpdesk.R
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordModel: ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val title: Int = R.string.ResetPassword
    fun getTitle(): Int { return title }
    fun getAuth(): FirebaseAuth { return firebaseAuth}
}