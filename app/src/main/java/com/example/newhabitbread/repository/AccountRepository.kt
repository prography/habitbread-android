package com.exmaple.newhabitbread.repository

import android.util.Log
import com.example.newhabitbread.api.ServerImpl
import com.example.newhabitbread.data.AccountResponse
import com.example.newhabitbread.data.UserInfoRequest
import com.example.newhabitbread.data.UserInfoResponse
import com.example.newhabitbread.data.BaseResponse

import kotlinx.coroutines.runBlocking
import retrofit2.await

class AccountRepository {
    private val habitBreadAPI = ServerImpl.APIService
    private var account : AccountResponse? = null;
    private var baseResponse : BaseResponse = BaseResponse("");
    private var userInfo : UserInfoResponse? = null;

    fun getUserInfo() : AccountResponse {
        runBlocking {
            val request = habitBreadAPI.getUserInfo()
            val response = request.await();
            Log.d("HabitBread", response.toString())
            account = response
        }
        return account!!;
    }


    fun updateUserNickname(nickname: String) : UserInfoResponse {
        runBlocking {
            val request = habitBreadAPI.updateNickname(UserInfoRequest.NicknameRequest(nickname))
            val response = request.await()
            userInfo = response
        }
        return userInfo!!
    }

    fun updateFcmToken(fcmToken: String) : UserInfoResponse {
        runBlocking {
            val request = habitBreadAPI.patchFcmToken(UserInfoRequest.FcmTokenRequest(fcmToken))
            val response = request.await()
            userInfo = response
        }
        return userInfo!!
    }
}