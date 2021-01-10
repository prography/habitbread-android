package com.example.newhabitbread.util

import android.content.Context
import com.example.newhabitbread.base.BaseApplication
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task


class AccountUtils(context: Context) {
    private val googleIdTokenAddress = "191839451290-81q6qni5lt1s5nad9lfrhahabjtrp2pa.apps.googleusercontent.com"

    var googleSignInClient : GoogleSignInClient
    init {
        val gso = GoogleSignInOptions.Builder()
            .requestIdToken(googleIdTokenAddress)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(context, gso)
    }

    fun signOut() : Task<Void> {
        BaseApplication.preferences.clearPreferences()
        return googleSignInClient.signOut()
    }

    fun revokeAccess() : Task<Void> {
        BaseApplication.preferences.clearPreferences()
        return googleSignInClient.revokeAccess()
    }

    fun isAlreadyLoggedIn() : Boolean {
        return (!BaseApplication.preferences.googleIdToken.isNullOrEmpty() &&
                GoogleSignIn.getLastSignedInAccount(googleSignInClient.applicationContext) != null)
        //단말에서 마지막으로 로그인한 구글 계정을 저장.
    }
}