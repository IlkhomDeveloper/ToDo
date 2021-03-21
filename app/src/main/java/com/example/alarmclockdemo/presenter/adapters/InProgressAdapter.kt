package com.example.alarmclockdemo.presenter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import com.example.alarmclockdemo.R
import com.example.alarmclockdemo.data.vo.AlarmVo
import com.example.alarmclockdemo.databinding.ItemInProgressBinding
import com.example.alarmclockdemo.presenter.utils.fromDateToString
import com.example.alarmclockdemo.presenter.utils.fromTimeToString

class InProgressAdapter() : RecyclerView.Adapter<InProgressAdapter.VH>() {
    private lateinit var binding : ItemInProgressBinding
    private var listenerUpdate : ((AlarmVo) -> Unit) ?= null
    private var listenerDelete : ((AlarmVo) -> Unit) ?= null
    private var listenerDone : ((AlarmVo) -> Unit) ?= null

    private var callback = object : SortedListAdapterCallback<AlarmVo>(this) {

        override fun compare(o1: AlarmVo, o2: AlarmVo): Int = o1.date.compareTo(o2.date)

        override fun areContentsTheSame(oldItem: AlarmVo, newItem: AlarmVo): Boolean = oldItem.name == newItem.name && oldItem.date == newItem.date

        override fun areItemsTheSame(item1: AlarmVo, item2: AlarmVo): Boolean = item1.date == item2.date

    }

    private var sortedList = SortedList(AlarmVo::class.java, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(LayoutInflater.from(parent.context).inflate(R.layout.item_in_progress, parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()

    override fun getItemCount(): Int = sortedList.size()

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {
            itemView.apply {
                binding = ItemInProgressBinding.bind(itemView)
                val alarm = sortedList[adapterPosition]
                binding.itemTitle.text = alarm.name
                binding.itemDate.text = fromDateToString(alarm.date)
                binding.itemTime.text = fromTimeToString(alarm.date)
                binding.itemDelete.setOnClickListener { listenerDelete?.invoke(alarm) }
                binding.itemSwitch.setOnClickListener { listenerDone?.invoke(alarm) }
                setOnClickListener { listenerUpdate?.invoke(alarm) }
            }
        }
    }

    fun submitList(list: List<AlarmVo>){
        sortedList.beginBatchedUpdates()
        sortedList.addAll(list)
        sortedList.endBatchedUpdates()
    }

    fun insert(alarmVo: AlarmVo){
        sortedList.add(alarmVo)
    }

    fun update(alarmVo: AlarmVo) = (0 until this.sortedList.size()).forEach { i ->
        val id = sortedList[i].id
        if(alarmVo.id == id){
            if (alarmVo.status == 1){
                sortedList.updateItemAt(i,alarmVo)
            }
            else{
                delete(alarmVo)
            }
        }
    }

    fun delete(alarmVo: AlarmVo){
        sortedList.remove(alarmVo)
    }

    fun setUpdateListener(f : (AlarmVo) -> Unit){
        listenerUpdate = f
    }

    fun setDeleteListener(f : (AlarmVo) -> Unit){
        listenerDelete = f
    }

    fun clickDoneListener(f : (AlarmVo) ->Unit){
        listenerDone = f
    }
}