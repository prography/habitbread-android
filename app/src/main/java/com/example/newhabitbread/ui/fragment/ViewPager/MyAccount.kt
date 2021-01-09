package com.example.newhabitbread.ui.fragment.ViewPager

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.newhabitbread.R
import com.example.newhabitbread.viewmodel.AccountViewModel
import androidx.lifecycle.Observer
import com.example.newhabitbread.base.BaseApplication
import kotlinx.android.synthetic.main.fragment_account.*

class MyAccount: Fragment() {
    private val accountViewModel : AccountViewModel by viewModels()
    private var userNickname: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        accountViewModel.accountData.observe(viewLifecycleOwner, Observer {
            userNickname = it.accountName
            textview_profile_nickname.text = userNickname
            progress_exp.progress = it.percent
            textview_progress_exp.text = getString(R.string.percentage, it.percent);
            if (it.percent <= 55) {
                textview_progress_exp.setTextColor(Color.parseColor("#80553615"))
            } else {
                textview_progress_exp.setTextColor(Color.parseColor("#FFFFFF"))
            }
            textview_account_exp.text = it.userExp.toString()
            textview_bread_num.text = it.totalItemCount.toString()
            textview_current_bread_num.text = getString(R.string.currentBreadNum, it.totalItemCount)
        })
        setOnClickListener()
        setOnToggleListener()
        switch_alarm.isChecked = BaseApplication.preferences.isTokenRegistered
    }

    private fun setOnToggleListener() {
        switch_alarm.setOnCheckedChangeListener { _, isChecked ->
            BaseApplication.preferences.isTokenRegistered = isChecked
            if (isChecked) {
//                PushUtils().register()
            } else {
//                PushUtils().unregister()
            }
        }
    }

    override fun onResume() {
        super.onResume()
//        accountViewModel.getUserInfo()
    }
    private fun setNickNameButton() {
        imageButton_change_nickname.setOnClickListener {
            val dialogEditText = getDialogEditText()
            val dialog = AlertDialog.Builder(requireContext())
                .setTitle("닉네임을 변경합니다")
                .setView(dialogEditText)
                .setPositiveButton("변경!") { _: DialogInterface, _: Int ->
                    if (dialogEditText.length() < 0) {
                        Toast.makeText(requireContext(), "닉네임 길이는 1글자 이상이여야 합니다~", Toast.LENGTH_SHORT).show()
                    } else {
//                        accountViewModel.updateUserNickname(dialogEditText.text.toString())
                        accountViewModel.userInfoData.observe(viewLifecycleOwner, Observer {
                            textview_profile_nickname.text = it.userName
                        })
                    }
                }.setNegativeButton("취소!") {
                        dialogInterface, _ -> dialogInterface.cancel()
                }
            dialog.create().show()
        }
    }


    private fun getDialogEditText() : EditText {
        val et = EditText(requireContext())
        et.setText(userNickname)
        return et
    }

    private fun setOnClickListener() {
        imageButton_change_nickname.setOnClickListener {
            setNickNameButton()
        }
        /*
        imageButton_delete_account.setOnClickListener {
            deleteAccount()
        }
        imageButton_logout.setOnClickListener {
            signOut()
        }
        */
        imageButton_change_info.setOnClickListener {
            showNotReadyToast()
        }
    }

    private fun showNotReadyToast() {
        Toast.makeText(this.context, "아직 준비중인 기능입니다.", Toast.LENGTH_SHORT).show()
    }

//todo: 계정 삭제와 로그아웃 구현
    private fun backToLogin() {
        Log.d("HabitBread", "Back To Login")
 //todo 메인페이지로의 navControl 설정
     }
}