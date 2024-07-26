package com.example.helpdesk.controller.userController

import com.example.helpdesk.model.userModel.HomeModel
import com.example.helpdesk.userActivity.Home

class HomeController(var homeModel: HomeModel, var view: Home) {
    fun signOut() {
        if (homeModel.getAuth().currentUser != null) {
            homeModel.getAuth().signOut()
        }
    }
}