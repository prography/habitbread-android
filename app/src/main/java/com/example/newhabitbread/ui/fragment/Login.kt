package com.example.newhabitbread.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newhabitbread.R
import com.example.newhabitbread.api.FirebaseAPI
import com.example.newhabitbread.api.ServerImpl
import com.example.newhabitbread.base.BaseApplication
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.Api
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Response
import com.google.android.gms.tasks.Task
import com.habitbread.main.data.GoogleOAuthRequest
import com.habitbread.main.data.GoogleOAuthResponse
import retrofit2.Call
import retrofit2.Callback

class Login : Fragment() {
    private val loginTag="HabitBread"

    private lateinit var clinet: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        return inflater.inflate(R.layout.fragment_login, container,false)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>){
        try{
            val account=completedTask.getResult(ApiException::class.java)
            if(account!=null){
                Log.d(loginTag,"handleSignInResult:"+account.idToken)

                sendGoogleOauth(account.idToken)
            }else{

                Log.d(loginTag,"handleSignInResult" + " no Account")
            }
        }catch(e:ApiException){

            Log.w("HabitBread","signInResult:failed code="+e.statusCode)

        }
    }



    private fun sendGoogleOauth(idToken: String?) {
        val tempRequest=GoogleOAuthRequest(idToken!!)

        val habitBreadApi=ServerImpl.APIService

        val call: Call<GoogleOAuthResponse> =habitBreadApi.serverLoginWithGoogle(tempRequest)
        //retrofit callback usage
        call.enqueue(object: Callback<GoogleOAuthResponse?> {
            override fun onResponse(
                call: Call<GoogleOAuthResponse?>,
                response: retrofit2.Response<GoogleOAuthResponse?>
            ) {
                val googleOauthResponse = response.body()!!

                if(googleOauthResponse.idToken != null){
                    Log.d("login success",googleOauthResponse.idToken)
                    //신규 유저와 기존 유저 구분하는 로직
                    BaseApplication.preferences.googleIdToken=googleOauthResponse.idToken
                    FirebaseAPI().sendRegistrationToServer(BaseApplication.preferences.FCMToken.toString())
                }
           }

            override fun onFailure(call: Call<GoogleOAuthResponse?>, t: Throwable) {
                Log.d("HabitBread","Error in Server google oauth")
                t.printStackTrace()

            }
        })
    }

}

