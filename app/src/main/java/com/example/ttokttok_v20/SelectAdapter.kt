package com.example.ttokttok_v20

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.data_list_item_brand.view.*
import kotlinx.android.synthetic.main.data_list_item_select.view.*
import java.util.*

class SelectAdapter (val context: Context, val itemCheck: (Select) -> Unit)
    : RecyclerView.Adapter<SelectAdapter.ViewHolder>() {
    private var items = ArrayList<Select>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val itemView: View = inflater.inflate(R.layout.data_list_item_select, viewGroup, false)
        return ViewHolder(itemView, itemCheck)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item: Select = items[position]
        viewHolder.setItem(item)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    fun setItems(items: ArrayList<Select>) {
        this.items = items
    }

    fun addItem(item: Select) {
        this.items.add(item)
    }

    inner class ViewHolder(itemView: View, itemCheck: (Select) -> Unit)
        : RecyclerView.ViewHolder(itemView) {
        fun setItem(item: Select) {
            val resourceId = context.resources.getIdentifier(
                item.photo,
                "drawable",
                context.packageName
            )
            if (resourceId in 0..1) {
                itemView.img_select?.setImageResource(R.mipmap.ic_launcher)
            }
            else {
                itemView.img_select?.setImageResource(resourceId)
            }
            itemView.tv_selectName.text = item.name
            itemView.setOnClickListener() { itemCheck(item) }
        }
    }
}