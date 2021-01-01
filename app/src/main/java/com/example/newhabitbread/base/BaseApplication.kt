package com.example.newhabitbread.base

import android.app.Application
import com.example.newhabitbread.util.SharedPreferences
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase


class BaseApplication: Application() {
    companion object{
        lateinit var preferences: SharedPreferences
        lateinit var firebaseAnalytics: FirebaseAnalytics

    }
    override fun onCreate(){
        super.onCreate()
        preferences=SharedPreferences(applicationContext)
        firebaseAnalytics= Firebase.analytics

    }

}