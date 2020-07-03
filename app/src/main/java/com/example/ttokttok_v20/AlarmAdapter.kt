package com.example.ttokttok_v20

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.data_list_item_alarm.view.*
import java.util.*


class AlarmAdapter (val itemCheck: (Alarm) -> Unit)
    : RecyclerView.Adapter<AlarmAdapter.ViewHolder>() {
    private var items = ArrayList<Alarm>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val itemView: View = inflater.inflate(R.layout.data_list_item_alarm, viewGroup, false)
        return ViewHolder(itemView, itemCheck)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item: Alarm = items[position]
        viewHolder.setItem(item)
    }

    override fun getItemCount(): Int {
        return items.count()
    }


    fun setItems(items: ArrayList<Alarm>) {
        this.items = items
    }

    inner class ViewHolder(itemView: View, itemCheck: (Alarm) -> Unit)
        : RecyclerView.ViewHolder(itemView) {
        fun setItem(item: Alarm) {
            itemView.icon_view.setImageResource(item.photo)
            itemView.setOnClickListener { itemCheck(item) }
        }
    }
}