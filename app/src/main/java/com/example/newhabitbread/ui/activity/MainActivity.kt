package com.example.newhabitbread.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.newhabitbread.R
import com.example.newhabitbread.api.FirebaseAPI
import com.example.newhabitbread.base.BaseApplication

class MainActivity : AppCompatActivity() {
    final val TAG: String ="MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(BaseApplication.preferences.isTokenRegistered){

        }

    }

    override fun onResume(){

        super.onResume()
        Log.d(TAG," OnResume호출됨 ")
    }
//onStop() 콜백이 언제 호출되는지보고 싶어서 추가한 코드
    override fun onStop(){
        super.onStop()
        Log.d(TAG," OnStop호출됨 ")

    }



}