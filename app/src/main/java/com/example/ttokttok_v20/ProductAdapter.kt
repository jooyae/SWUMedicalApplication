package com.example.ttokttok_v20

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.data_list_item_product.view.*
import java.util.*

class ProductAdapter (val context: Context, val itemCheck: (Product) -> Unit)
    : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private var items = ArrayList<Product>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val itemView: View = inflater.inflate(R.layout.data_list_item_product, viewGroup, false)
        return ViewHolder(itemView, itemCheck)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item: Product = items[position]
        viewHolder.setItem(item)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    fun setItems(items: ArrayList<Product>) {
        this.items = items
    }

    inner class ViewHolder(itemView: View, itemCheck: (Product) -> Unit)
        : RecyclerView.ViewHolder(itemView) {
        fun setItem(item: Product) {
            val resourceId = context.resources.getIdentifier(
                item.reviewAvatar,
                "drawable",
                context.packageName
            )
            if (resourceId in 0..1) {
                itemView.iv_reviewAvater?.setImageResource(R.mipmap.ic_launcher)
            }
            else {
                itemView.iv_reviewAvater?.setImageResource(resourceId)
            }
            itemView.tv_reviewNickname.text = item.nickname
            itemView.tv_reviewRatingStar.text=item.ratingStar
            itemView.tv_reviewContent.text = item.review

            itemView.setOnClickListener() { itemCheck(item) }
        }
    }
}