package com.example.newhabitbread.ui.fragment.ViewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newhabitbread.R
import com.example.newhabitbread.adapter.HabitListAdapter
import com.example.newhabitbread.viewmodel.HabitViewModel
import com.example.newhabitbread.util.DateCalculation
import com.habitbread.main.data.NewHabitReq
import kotlinx.android.synthetic.main.fragment_my_habits.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MyHabits : Fragment() {

    private lateinit var recyclerviewHabitList: RecyclerView
    private lateinit var recyclerviewAdapter: HabitListAdapter
    private lateinit var habitListAdapter: HabitListAdapter
    private val habitViewModel: HabitViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //todo: fragement_my_habits 레이아웃 파일 디자인 수정

        val view= inflater.inflate(R.layout.fragment_my_habits, container, false)
        recyclerviewHabitList = view.findViewById(R.id.recyclerView_habitlist)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        habitViewModel.getAllList()
        initRecyclerView()


        habitViewModel.rvData.observe(viewLifecycleOwner, Observer {
            textView_announcement.text = it.comment
            val sortedList = DateCalculation().habitListSorting(it.habits)
            if (it.habits.size >= 10) {

            }
            habitListAdapter.setAdapterData(sortedList)
        })
        //ui 디자인 바뀌면서 제거된 Line
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onResume() {
        super.onResume()
        habitViewModel.getAllList()
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    private fun initRecyclerView() {
        recyclerviewAdapter = HabitListAdapter(context)
        recyclerviewHabitList.adapter = recyclerviewAdapter
        recyclerviewHabitList.layoutManager = LinearLayoutManager(context)
    }




    //todo:  EventBus 관련해서 습관 post하는 것 Repository와 엮어서 코드 추가
}