package com.example.helpdesk.controller

import com.example.helpdesk.MainActivity
import com.example.helpdesk.model.MainModel
import com.example.helpdesk.myClass.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class MainController(var mainModel: MainModel, var view: MainActivity) {
    fun checkCurrentUser(){
        if (mainModel.getAuth().currentUser != null && mainModel.getAuth().currentUser!!.isEmailVerified) {
            mainModel.getData().child(mainModel.getAuth().currentUser!!.uid).addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)
                    if (user != null) {
                        view.homePage(user)
                    } else {
                        view.helpDeskPage()
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    view.helpDeskPage()
                }
            })
        } else {
            view.helpDeskPage()
        }
    }
}