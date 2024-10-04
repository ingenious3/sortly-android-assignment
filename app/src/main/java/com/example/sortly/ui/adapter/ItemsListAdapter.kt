package com.example.sortly.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sortly.R
import com.example.sortly.data.model.Items
import com.example.sortly.databinding.ListItemBinding
import com.example.sortly.utils.getTotalPrice
import com.example.sortly.utils.setSpannableText

class ItemsListAdapter (private val context: Context, private var items: List<Items>, private val itemClick: (Items) -> Unit):
    RecyclerView.Adapter<ItemsListAdapter.ItemsViewHolder>() {

    companion object {
        const val ITEM = "item"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemsViewHolder(binding)
    }

    override fun onBindViewHolder(productsViewHolder: ItemsViewHolder, position: Int) {
        productsViewHolder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: List<Items>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ItemsViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(dataItem: Items) {
            binding.apply {
                tvItemName.text = dataItem.name
                tvSid.text = dataItem.sid
                tvItemType.text = dataItem.type
                if (dataItem.type == ITEM) {
                    tvItemQuantity.visibility = android.view.View.VISIBLE
                    tvItemPrice.visibility = android.view.View.VISIBLE
                    setSpannableText(tvItemQuantity,
                        context.getString(R.string.quntity), ContextCompat.getColor(context, R.color.grey),
                        dataItem.quantity ?: "", ContextCompat.getColor(context, R.color.black))

                    setSpannableText(tvItemPrice,
                        context.getString(R.string.total_price), ContextCompat.getColor(context, R.color.grey),
                        getTotalPrice(dataItem.quantity, dataItem.price), ContextCompat.getColor(context, R.color.black))

                } else {
                    tvItemQuantity.visibility = android.view.View.GONE
                    tvItemPrice.visibility = android.view.View.GONE
                }
                binding.root.setOnClickListener { itemClick(dataItem) }
            }
        }
    }

}