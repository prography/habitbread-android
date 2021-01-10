package com.example.newhabitbread.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newhabitbread.repository.HabitRepository
import com.example.newhabitbread.data.HabitListResponse
import com.example.newhabitbread.data.NewHabitReq
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Error

/*
ViewModel이 View에 사용되는 데이터를 요청하면 Repository는 요청한 데이터를 로컬이나 네트워크에서 적절히 선택하여 이를 전달한다.
ViewModel 클래스에서는 liveData 블록스 안에서 비동기적으로 repository를 통해 네트워크에서 데이터를 받는
앱을 사용하면서 장시간
 */
class HabitViewModel : ViewModel() {
    var rvData: MutableLiveData<HabitListResponse> = MutableLiveData()

    //모아보기
    fun getAllList() {


        GlobalScope.launch {
                try {
                    val habitList = HabitRepository().getHabitList()
                    rvData.postValue(habitList)
                } catch (err: Error) {
                    Log.e("HabitBread", err.printStackTrace().toString())
                }

        }
    }

    //이 함수는 MyHabits.kt 또는 HabitListAdapter안에서 처리하면 될 듯?
    /*fun getSpecificDayList(dayOfWeek: String) {

        GlobalScope.launch {


        }

    }
    */
    fun postHabit(body: NewHabitReq){
        GlobalScope.launch {
            try {
                val habitList = HabitRepository().postNewHabit(body)
                rvData.postValue(habitList)
            }catch (err: Error){
                Log.e("HabitBread", err.printStackTrace().toString())
            }
        }
    }





}