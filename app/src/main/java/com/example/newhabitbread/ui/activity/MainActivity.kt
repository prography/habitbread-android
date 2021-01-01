package com.example.newhabitbread.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newhabitbread.R
import com.example.newhabitbread.api.FirebaseAPI
import com.example.newhabitbread.base.BaseApplication

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(BaseApplication.preferences.isTokenRegistered){
        }

    }

    override fun onResume(){

        super.onResume()
    }
}