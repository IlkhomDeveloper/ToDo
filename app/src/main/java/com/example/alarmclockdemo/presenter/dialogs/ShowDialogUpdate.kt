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
import androidx.navigation.fragment.navArgs
import com.example.alarmclockdemo.app.App
import com.example.alarmclockdemo.data.vo.AlarmVo
import com.example.alarmclockdemo.databinding.UpdateAlarmBinding
import com.example.alarmclockdemo.presenter.utils.Constants
import com.example.alarmclockdemo.presenter.utils.Type
import com.example.alarmclockdemo.presenter.viewModels.ViewModelUpdateDialog
import java.text.SimpleDateFormat

class ShowDialogUpdate() : DialogFragment() {

    private lateinit var binding : UpdateAlarmBinding
    private var alarmVo: AlarmVo? = null
    private var listener: ((AlarmVo) -> Unit)? = null
    private lateinit var viewModel: ViewModelUpdateDialog
    private var observerError = Observer<String>{showToast(it)}

    private val args by navArgs<ShowDialogUpdateArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UpdateAlarmBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        alarmVo = args.alarmVo
        viewModel = ViewModelProvider(this)[ViewModelUpdateDialog::class.java]
        setUpClickListeners()
        viewModel.liveDataError.observe(viewLifecycleOwner,observerError)
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
        binding.dialogAlarmName.setText(alarmVo?.name)
        binding.dialogAlarmDate.text = SimpleDateFormat(Constants.yyyy_MM_dd).format(alarmVo?.date)
        binding.dialogAlarmTime.text = SimpleDateFormat(Constants.HH_mm).format(alarmVo?.date)


        binding.dialogAlarmDate.setOnClickListener {
            val dateDialog = DateDialog()
            dateDialog.setListener {
                binding.dialogAlarmDate.text = it
            }
            dateDialog.show(childFragmentManager, "DATE")
        }

        binding.dialogAlarmTime.setOnClickListener {
            val dialogTime = TimeDialog()
            dialogTime.setListener {
                binding.dialogAlarmTime.text = it
            }
            dialogTime.show(childFragmentManager, "TIME")
        }
        binding.dialogAlarmAdd.setOnClickListener {
            var title = binding.dialogAlarmName.text.toString().replace("\\s+".toRegex(), " ")
            var date = binding.dialogAlarmDate.text.toString()
            var time = binding.dialogAlarmTime.text.toString()
            var dateFull = "$date $time"

            if (viewModel.checkDate(title, date, time)) {
                var a = AlarmVo(alarmVo?.id!!, title, SimpleDateFormat(Constants.yyyy_MM_dd__HH_mm).parse(dateFull),Type.IN_PROGRESS)
                findNavController().previousBackStackEntry?.savedStateHandle?.set("UPDATED_ALARM",a)
                dismiss()
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(App.instance, message, Toast.LENGTH_LONG).show()
    }
}