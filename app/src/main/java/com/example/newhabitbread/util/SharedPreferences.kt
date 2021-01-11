package com.example.newhabitbread.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(context: Context) {
//    need multiple shared preference files identified by name,첫 파라미터로 파일 이름을 넘긴
    private val preferenceFileName=""
    private val preferencesGoogleIdToken="googleIdToken"
    private val preferencesFCMToken="FCMToken"
    private val preferences: SharedPreferences? =context.getSharedPreferences(preferenceFileName,0)

//get 실행 시 저장된 값을 반환하며 default값은 ""
//set(value)실행 시 value로 값을 대체한 후에 저장
    var googleIdToken: String?
        get()=preferences!!.getString(preferencesGoogleIdToken,"")
        set(value)=preferences!!.edit().putString(preferencesGoogleIdToken,value).apply()



    var FCMToken: String?
        get()= preferences!!.getString(preferencesFCMToken,"")
        set(value)= preferences!!.edit().putString(preferencesFCMToken,value).apply()
    //store registraion id in shared pre and start app check wehther null or not

    var isTokenRegistered: Boolean
//        get()=ipreferences!!.getBoolean(preferencesFCMToken,true) !is Boolean){
//             true
//        }else{
//             preferences!!.getBoolean(preferencesFCMToken, true)
//        }
//        set(value)=preferences!!.edit().putBoolean(preferencesFCMToken, value).apply()
        get() = preferences!!.getBoolean(preferencesFCMToken, true)
        set(value) = preferences!!.edit().putBoolean(preferencesFCMToken, value).apply()

    fun clearPreferences(){
        preferences!!.edit().clear().apply();
    }


}