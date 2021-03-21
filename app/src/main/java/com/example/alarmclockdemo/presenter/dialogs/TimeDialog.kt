package com.example.alarmclockdemo.presenter.dialogs

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*
import kotlin.math.min

class TimeDialog : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    private val calendar = Calendar.getInstance()
    private var listener: ((String) -> Unit)? = null

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var h = calendar.get(Calendar.HOUR_OF_DAY)
        var m = calendar.get(Calendar.MINUTE)
        var l = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            if (hourOfDay.toString().length == 1 && minute.toString().length == 1){
                listener?.invoke("0$hourOfDay:0$minute")
            }
            else if (hourOfDay.toString().length == 1 && minute.toString().length == 2){
                listener?.invoke("0$hourOfDay:${minute}")
            }
            else if (hourOfDay.toString().length == 2 && minute.toString().length == 1){
                listener?.invoke("$hourOfDay:0$minute")
            }
            else{
                listener?.invoke("$hourOfDay:$minute")
            }
        }
        return TimePickerDialog(requireContext(), l, h, m, true)
    }

    fun setListener(f: (String) -> Unit) {
        listener = f
    }
}