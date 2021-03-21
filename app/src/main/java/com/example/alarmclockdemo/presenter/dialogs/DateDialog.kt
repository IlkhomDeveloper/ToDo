package com.example.alarmclockdemo.presenter.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DateDialog : DialogFragment(), DatePickerDialog.OnDateSetListener {
    private var listenerText : ((String) -> Unit) ?= null
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        //DatePickerDialog(requireContext(),this@DateDialog, 2000, 20, 10)
        val listener = DatePickerDialog.OnDateSetListener { _, y, m, d ->
            if (m.toString().length == 1 && d.toString().length == 1){
                listenerText?.invoke("$y-0${m+1}-0$d")
            }
            else if (m.toString().length == 1 && d.toString().length == 2){
                if (m < 9){
                    listenerText?.invoke("$y-0${m+1}-$d")
                }
                else{
                    listenerText?.invoke("$y-${m+1}-$d")
                }
            }
            else if (m.toString().length == 2 && d.toString().length == 1){
                listenerText?.invoke("$y-${m+1}-0$d")
            }
            else{
                listenerText?.invoke("$y-${m+1}-$d")
            }
        }

        return DatePickerDialog(requireContext(),listener,year,month,day)
    }

    fun setListener(f : (String) -> Unit){
        listenerText = f
    }


}