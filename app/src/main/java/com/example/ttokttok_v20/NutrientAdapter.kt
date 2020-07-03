package com.example.ttokttok_v20

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.data_list_item_nutrient.view.*
import java.util.*

class NutrientAdapter (val context: Context, val itemCheck: (Nutrient) -> Unit)
    : RecyclerView.Adapter<NutrientAdapter.ViewHolder>() {
    private var items = ArrayList<Nutrient>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val itemView: View = inflater.inflate(R.layout.data_list_item_nutrient, viewGroup, false)
        return ViewHolder(itemView, itemCheck)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item: Nutrient = items[position]
        viewHolder.setItem(item)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    fun setItems(items: ArrayList<Nutrient>) {
        this.items = items
    }

    fun addItem(item : Nutrient) {
        this.items.add(item)
    }

    inner class ViewHolder(itemView: View, itemCheck: (Nutrient) -> Unit)
        : RecyclerView.ViewHolder(itemView) {
        fun setItem(item: Nutrient) {

            itemView.tv_nutrientName.text = item.name
            itemView.setOnClickListener() { itemCheck(item) }
        }
    }
}