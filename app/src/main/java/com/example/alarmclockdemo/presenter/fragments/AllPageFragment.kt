package com.example.alarmclockdemo.presenter.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alarmclockdemo.R
import com.example.alarmclockdemo.databinding.AllFragmentBinding
import com.example.alarmclockdemo.presenter.adapters.AllAlarmsAdapter
import com.example.alarmclockdemo.presenter.viewModels.AllAlarmViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AllPageFragment : DaggerFragment(R.layout.all_fragment) {
    @Inject
    lateinit var viewModel: AllAlarmViewModel

    @Inject
    lateinit var adapter: AllAlarmsAdapter

    lateinit var binding: AllFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = AllFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        binding.recycle.adapter = adapter
        binding.recycle.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getAllAlarms().observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
        binding.bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.doneTodo ->{parentFragmentManager.beginTransaction().replace(R.id.main_activity,DonePageFragment()).commit()}
                R.id.inProgressToDo ->{parentFragmentManager.beginTransaction().replace(R.id.main_activity,InProgressFragment()).commit()}
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}