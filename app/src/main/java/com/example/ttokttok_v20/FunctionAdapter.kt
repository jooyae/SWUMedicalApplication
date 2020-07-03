package com.example.ttokttok_v20

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.data_list_item_function.view.*
import java.util.*


class FunctionAdapter (val itemCheck: (Function) -> Unit)
    : RecyclerView.Adapter<FunctionAdapter.ViewHolder>() {
    private var items = ArrayList<Function>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val itemView: View = inflater.inflate(R.layout.data_list_item_function, viewGroup, false)
        return ViewHolder(itemView, itemCheck)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item: Function = items[position]
        viewHolder.setItem(item)
    }

    override fun getItemCount(): Int {
        return items.count()
    }


    fun setItems(items: ArrayList<Function>) {
        this.items = items
    }

    inner class ViewHolder(itemView: View, itemCheck: (Function) -> Unit)
        : RecyclerView.ViewHolder(itemView) {
        fun setItem(item: Function) {
            itemView.icon_name.text = item.name
            itemView.icon_view.setImageResource(item.imageRes)
            itemView.setOnClickListener { itemCheck(item) }
        }
    }
}