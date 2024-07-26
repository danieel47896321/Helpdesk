package com.example.helpdesk.model.guestModel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInModel: ViewModel() {
    private val firebaseDatabase = FirebaseDatabase.getInstance("https://helpdesk-ddefa-default-rtdb.europe-west1.firebasedatabase.app")
    private val databaseReference = firebaseDatabase.reference.child("Users")
    private var firebaseAuth = FirebaseAuth.getInstance()
    fun getAuth(): FirebaseAuth { return firebaseAuth}
    fun getData(): DatabaseReference { return databaseReference }
}