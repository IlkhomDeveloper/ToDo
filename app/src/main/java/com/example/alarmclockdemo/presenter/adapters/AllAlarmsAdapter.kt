package com.example.alarmclockdemo.presenter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmclockdemo.R
import com.example.alarmclockdemo.data.vo.AlarmVo
import com.example.alarmclockdemo.databinding.ItemAllAlarmBinding
import com.example.alarmclockdemo.databinding.ItemDoneAlarmBinding
import com.example.alarmclockdemo.presenter.utils.Messages
import com.example.alarmclockdemo.presenter.utils.fromDateToString
import com.example.alarmclockdemo.presenter.utils.fromTimeToString

class AllAlarmsAdapter() : RecyclerView.Adapter<AllAlarmsAdapter.VH>() {
    private lateinit var binding: ItemAllAlarmBinding
    private val doneAlarms = ArrayList<AlarmVo>()

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {
            itemView.apply {
                binding = ItemAllAlarmBinding.bind(itemView)
                val data = doneAlarms[adapterPosition]
                binding.itemDate.text = fromDateToString(data.date)
                binding.itemTime.text = fromTimeToString(data.date)
                if (data.status == 2){
                    binding.itemText.text = Messages.DONE
                }
                else{
                    binding.itemText.text = Messages.IN_PROGRESS
                }
                binding.itemTitle.text = data.name
            }
        }
    }

    fun submitList(ls:List<AlarmVo>){
        doneAlarms.clear()
        doneAlarms.addAll(ls)
        notifyItemRangeInserted(0,ls.size-1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(LayoutInflater.from(parent.context).inflate(R.layout.item_done_alarm,parent,false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()

    override fun getItemCount(): Int = doneAlarms.size
}