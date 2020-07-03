package com.example.ttokttok_v20

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.data_list_item_brand.view.*
import java.util.*

class BrandAdapter (val context: Context, val itemCheck: (Brand) -> Unit)
    : RecyclerView.Adapter<BrandAdapter.ViewHolder>() {
    private var items = ArrayList<Brand>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val itemView: View = inflater.inflate(R.layout.data_list_item_brand, viewGroup, false)
        return ViewHolder(itemView, itemCheck)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item: Brand = items[position]
        viewHolder.setItem(item)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    fun setItems(items: ArrayList<Brand>) {
        this.items = items
    }

    fun addItem(item : Brand) {
        this.items.add(item)
    }

    inner class ViewHolder(itemView: View, itemCheck: (Brand) -> Unit)
        : RecyclerView.ViewHolder(itemView) {
        fun setItem(item: Brand) {
            itemView.tv_brandName.text = item.name
            itemView.setOnClickListener() { itemCheck(item) }
        }
    }
}