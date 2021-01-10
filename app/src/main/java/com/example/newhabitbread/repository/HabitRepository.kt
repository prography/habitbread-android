package com.example.newhabitbread.repository

import com.example.newhabitbread.api.HabitBreadAPI
import com.example.newhabitbread.data.HabitListResponse
import com.example.newhabitbread.data.NewHabitReq
import kotlinx.coroutines.runBlocking
import retrofit2.await

/*
 Repository를 인터페이스로 만들어 OOP적 특성 활

 */
import com.example.newhabitbread.api.ServerImpl
import com.example.newhabitbread.data.RankResponse

class HabitRepository {
    val TAG :String? = "HabitBread"
    private val habitBreadAPI: HabitBreadAPI = ServerImpl.APIService
    var allRanks : RankResponse? = null
    private lateinit var allHabitListData: HabitListResponse


    //아래 함수는 Habit 관련
    fun getHabitList(): HabitListResponse{

        runBlocking {
            val request = habitBreadAPI.getAllHabitLists()
            val response = request.await()
            allHabitListData = response


        }
        return allHabitListData
    }

    fun createHabit(body: NewHabitReq): HabitListResponse{

        runBlocking {
            val postRequest =habitBreadAPI.postNewHabit(body)

            val postResponse=postRequest.await()
//postResponse는 postRequest가 완료될 때까지 기다린다.
            val getRequest =habitBreadAPI.getAllHabitLists()

            val getResponse=getRequest.await()

            allHabitListData=getResponse

        }
        return allHabitListData
    }
    fun postNewHabit(body : NewHabitReq): HabitListResponse{
        runBlocking {
            val postRequest = habitBreadAPI.postNewHabit(body)
            val postResponse = postRequest.await()
            val getRequest = habitBreadAPI.getAllHabitLists()
            val getResponse = getRequest.await()
            allHabitListData = getResponse
        }
        return allHabitListData
    }



}