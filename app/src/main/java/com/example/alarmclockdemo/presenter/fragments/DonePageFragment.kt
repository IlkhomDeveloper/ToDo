package com.example.alarmclockdemo.presenter.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alarmclockdemo.R
import com.example.alarmclockdemo.databinding.DoneFragmentBinding
import com.example.alarmclockdemo.presenter.adapters.DoneAlarmsAdapter
import com.example.alarmclockdemo.presenter.viewModels.DoneViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DonePageFragment : DaggerFragment(R.layout.done_fragment) {
    @Inject
    lateinit var viewModel: DoneViewModel

    @Inject
    lateinit var adapter: DoneAlarmsAdapter

    lateinit var binding: DoneFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = DoneFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        binding.recycle.adapter = adapter
        binding.recycle.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getDoneAlarms().observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        binding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.allTodo -> {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.main_activity, AllPageFragment()).commit()
                }
                R.id.inProgressToDo -> {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.main_activity, InProgressFragment()).commit()
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}