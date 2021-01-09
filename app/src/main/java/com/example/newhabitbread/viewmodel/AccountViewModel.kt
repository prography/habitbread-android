package com.example.newhabitbread.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.habitbread.main.data.AccountResponse
import com.habitbread.main.data.UserInfoResponse

class AccountViewModel: ViewModel() {
    val accountData: MutableLiveData<AccountResponse> = MutableLiveData()
    val userInfoData: MutableLiveData<UserInfoResponse> = MutableLiveData()

}
