package com.example.alarmclockdemo.presenter.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alarmclockdemo.app.App
import com.example.alarmclockdemo.data.vo.AlarmVo
import com.example.alarmclockdemo.databinding.AddAlarmBinding
import com.example.alarmclockdemo.presenter.utils.Constants
import com.example.alarmclockdemo.presenter.utils.Type
import com.example.alarmclockdemo.presenter.viewModels.ViewModelAddDialog
import java.text.SimpleDateFormat

class ShowDialogAdd() : DialogFragment() {
    private lateinit var date: String
    private lateinit var binding: AddAlarmBinding
    private lateinit var viewModel: ViewModelAddDialog
    private val observerError = Observer<String> { showToast(it) }
    private var listener: ((AlarmVo) -> Unit)? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddAlarmBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ViewModelAddDialog::class.java]
        setUpClickListeners()

        viewModel.liveDataError.observe(viewLifecycleOwner, observerError)
        if (viewModel.listener == null) {
            viewModel.listener = listener
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setUpClickListeners() {
        binding.dialogAlarmDate.setOnClickListener {
            val dateDialog = DateDialog()
            dateDialog.setListener {
                binding.dialogAlarmDate.text = it
            }
            dateDialog.show(childFragmentManager,"DATE")
        }

        binding.dialogAlarmTime.setOnClickListener {
             val dialogTime = TimeDialog()
             dialogTime.setListener {
                 binding.dialogAlarmTime.text = it
             }
             dialogTime.show(childFragmentManager,"TIME")
        }
        binding.dialogAlarmAdd.setOnClickListener {
            var title = binding.dialogAlarmName.text.toString().replace("\\s+".toRegex(), " ")
            var date = binding.dialogAlarmDate.text.toString()
            var time = binding.dialogAlarmTime.text.toString()
            var dateFull = "$date $time"

            if (viewModel.checkDate(title, date, time)) {
                var a = AlarmVo(0, title, SimpleDateFormat(Constants.yyyy_MM_dd__HH_mm).parse(dateFull),Type.IN_PROGRESS)
                findNavController().previousBackStackEntry?.savedStateHandle?.set("ADDED_ALARM", a)
                dismiss()
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(App.instance, message, Toast.LENGTH_LONG).show()
    }
}