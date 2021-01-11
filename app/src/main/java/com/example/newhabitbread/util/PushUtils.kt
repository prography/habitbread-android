package com.example.newhabitbread.util

import android.util.Log
import com.example.newhabitbread.api.FirebaseAPI
import com.exmaple.newhabitbread.repository.AccountRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import java.lang.Exception

class PushUtils {

    fun register(){
        getFCMCurrentToken()
        FirebaseMessaging.getInstance()

    }

    private fun getFCMCurrentToken(){
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener(OnCompleteListener { task ->
            if(!task.isSuccessful){
                Log.w("FCMCurrentTokenTest", "getFCMCurrentToken failed", task.exception)
                return@OnCompleteListener
            }

            // This is a new Token(Instance ID)
            val token = task.result?.token.toString()
            FirebaseAPI().sendRegistrationToServer(token)
            Log.d("FCM_Token", token)

            FirebaseAPI().sendRegistrationToServer(token)
        })

    }

    fun unregister() {
        FirebaseMessaging.getInstance().isAutoInitEnabled = false
        Thread {
            try {
                FirebaseInstanceId.getInstance().deleteInstanceId()
                val userInfoResponse = AccountRepository().updateFcmToken("")
                Log.d("UserInfoResult", userInfoResponse.toString())
            } catch (e : Exception) {
                e.printStackTrace()
            }
        }
    }

}