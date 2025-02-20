package com.example.helpdesk.controller.guestController

import android.util.Patterns
import android.view.View
import com.example.helpdesk.R
import com.example.helpdesk.guestFragments.SignIn
import com.example.helpdesk.model.guestModel.SignInModel
import com.example.helpdesk.myClass.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.messaging.FirebaseMessaging

class SignInController(private var signInModel: SignInModel, var view: SignIn) {
    fun buttonSignIn(email: String, password: String) {
        if (checkInput(email, password)) {
            view.setProgressBar(View.VISIBLE)
            signInModel.getAuth().signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (signInModel.getAuth().currentUser!!.isEmailVerified) {
                        signInModel.getData().child(signInModel.getAuth().currentUser!!.uid).addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                view.setProgressBar(View.GONE)
                                val user = snapshot.getValue(User::class.java)
                                if (user != null) {
                                    if(user.token == "Token") {
                                        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                                            if (task.result != null) {
                                                val token: String = task.result
                                                user.token = token
                                                val firebaseDatabase = FirebaseDatabase.getInstance("https://videomeeting-86807-default-rtdb.europe-west1.firebasedatabase.app")
                                                val databaseReference = firebaseDatabase.reference.child("Users")
                                                databaseReference.child(user.uid).child("token").setValue(token)
                                                view.setProgressBar(View.GONE)
                                                view.moveToHome()
                                            } else {
                                                view.setProgressBar(View.GONE)
                                            }
                                        }
                                    } else {
                                        view.setProgressBar(View.GONE)
                                        view.moveToHome()
                                    }
                                }
                            }
                            override fun onCancelled(error: DatabaseError) {
                                view.setProgressBar(View.GONE)
                            }
                        })
                    } else {
                        view.displayMessage(view.resources.getString(R.string.SignIn), view.resources.getString(R.string.CheckEmailVerify), null)
                        view.setProgressBar(View.GONE)
                    }
                } else {
                    signInModel.getAuth().fetchSignInMethodsForEmail(email).addOnCompleteListener { emailExistTask ->
                        if (emailExistTask.isSuccessful) {
                            if (emailExistTask.result.signInMethods!!.isEmpty()) {
                                view.setProgressBar(View.GONE)
                                view.setEmailHelper(view.resources.getString(R.string.EmailNotExist))
                            } else {
                                view.setProgressBar(View.GONE)
                                view.setEmailHelper("")
                                view.setPasswordHelper(view.resources.getString(R.string.WrongPassword))
                            }
                        } else {
                            view.setProgressBar(View.GONE)
                            view.displayMessage(view.resources.getString(R.string.Error), view.resources.getString(R.string.ErrorMSG), null)
                        }
                    }
                }
            }
        }
    }
    private fun checkInput(email: String, password: String): Boolean {
        var flag = true
        if (email.isEmpty()) {
            view.setEmailHelper(view.resources.getString(R.string.Required))
            flag = false
        } else if (!isEmailValid(email)) {
            view.setEmailHelper(view.resources.getString(R.string.InvalidEmail))
            flag = false
        } else {
            view.setEmailHelper("")
        }

        if (password.isEmpty()) {
            view.setPasswordHelper(view.resources.getString(R.string.Required))
            flag = false
        } else if (password.length < 6){
            view.setPasswordHelper(view.resources.getString(R.string.MinimumChars))
            flag = false
        } else {
            view.setPasswordHelper("")
        }
        return flag
    }
    private fun isEmailValid(email: String?): Boolean { return Patterns.EMAIL_ADDRESS.matcher(email).matches() }
}