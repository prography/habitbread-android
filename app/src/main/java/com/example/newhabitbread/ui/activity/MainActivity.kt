package com.example.newhabitbread.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newhabitbread.R
import com.example.newhabitbread.api.FirebaseAPI
import com.example.newhabitbread.base.BaseApplication
import com.example.newhabitbread.util.PushUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(BaseApplication.preferences.isTokenRegistered){
            //todo code related with FireBaseToken Push Service
        }

    }

    override fun onResume(){

        super.onResume()
    }
}