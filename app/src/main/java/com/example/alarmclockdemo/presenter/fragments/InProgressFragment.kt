package com.example.alarmclockdemo.presenter.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alarmclockdemo.R
import com.example.alarmclockdemo.data.vo.AlarmVo
import com.example.alarmclockdemo.databinding.MainScreenBinding
import com.example.alarmclockdemo.presenter.adapters.InProgressAdapter
import com.example.alarmclockdemo.presenter.utils.Type
import com.example.alarmclockdemo.presenter.viewModels.InProgressViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class InProgressFragment : DaggerFragment(R.layout.main_screen) {

    @Inject
    lateinit var viewModel:InProgressViewModel

    @Inject
    lateinit var adapter : InProgressAdapter

    private val insertObserver = Observer<AlarmVo> { adapter.insert(it) }
    private val updateObserver = Observer<AlarmVo> { adapter.update(it) }
    private val deleteObserver = Observer<AlarmVo> { adapter.delete(it) }
    private val checkObserver = Observer<String> { showToast(it) }

    private lateinit var binding: MainScreenBinding
    private var ls = ArrayList<AlarmVo>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainScreenBinding.bind(view)

        viewModel.insertLiveDate.observe(viewLifecycleOwner, insertObserver)
        viewModel.liveDataUpdate.observe(viewLifecycleOwner, updateObserver)
        viewModel.liveDataDelete.observe(viewLifecycleOwner, deleteObserver)
        viewModel.liveDataCheck.observe(viewLifecycleOwner, checkObserver)
        checkChanges()

        binding.recycleList.adapter = adapter
        binding.recycleList.layoutManager = LinearLayoutManager(requireContext())

        binding.addAlarm.setOnClickListener {
            findNavController().navigate(InProgressFragmentDirections.actionMainPageFragmentToShowDialogAdd())
        }

        adapter.setUpdateListener {
            findNavController().navigate(InProgressFragmentDirections.actionMainPageFragmentToShowDialogUpdate(it))
        }

        adapter.clickDoneListener {
            it.status = Type.DONE
            viewModel.updateAlarm(it)
        }

        viewModel.getAlarms().observe(viewLifecycleOwner) {
            adapter.submitList(it)
            ls = ArrayList(it)
        }

        adapter.setDeleteListener {
            val dialog = AlertDialog.Builder(requireContext())
            dialog
                .setTitle("DELETE ALARM")
                .setMessage("Are you sure you want to delete the alarm")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes") { _, _ ->
                    viewModel.deleteAlarm(it)
                    adapter.delete(it)
                }.create().show()
        }
        binding.navigationBottom.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.allTodo ->{parentFragmentManager.beginTransaction().replace(R.id.main_activity,AllPageFragment()).addToBackStack("All").commit()}
                R.id.doneTodo ->{parentFragmentManager.beginTransaction().replace(R.id.main_activity,DonePageFragment()).addToBackStack("Done").commit()}
            }
            return@setOnNavigationItemSelectedListener true
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun checkChanges(){
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<AlarmVo>("UPDATED_ALARM")?.observe(viewLifecycleOwner,{
            viewModel.updateAlarm(it)
        })
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<AlarmVo>("ADDED_ALARM")?.observe(viewLifecycleOwner){
            viewModel.insertAlarm(it)
        }
    }
}