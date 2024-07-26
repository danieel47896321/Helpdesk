package com.example.helpdesk.model.guestModel

import androidx.lifecycle.ViewModel
import com.example.helpdesk.R
import com.google.firebase.auth.FirebaseAuth

class HelpdeskModel : ViewModel() {
    private val titles = intArrayOf(R.string.SignIn, R.string.CreateAccount)
    private val firebaseAuth = FirebaseAuth.getInstance()
    fun getTitles(): IntArray { return titles }
    fun getAuth(): FirebaseAuth { return firebaseAuth }
}