package com.example.newhabitbread.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newhabitbread.data.AccountResponse
import com.example.newhabitbread.data.UserInfoResponse

class AccountViewModel: ViewModel() {
    val accountData: MutableLiveData<AccountResponse> = MutableLiveData()
    val userInfoData: MutableLiveData<UserInfoResponse> = MutableLiveData()

}
