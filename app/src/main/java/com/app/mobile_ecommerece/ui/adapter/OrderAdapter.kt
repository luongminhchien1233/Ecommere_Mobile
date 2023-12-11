package com.app.mobile_ecommerece.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mobile_ecommerece.common.BindableAdapter
import com.app.mobile_ecommerece.databinding.ItemCategoryBinding
import com.app.mobile_ecommerece.databinding.ItemOrderBinding
import com.app.mobile_ecommerece.model.CategoryModel
import com.app.mobile_ecommerece.model.OrderData
import com.squareup.picasso.Picasso

class OrderAdapter(
    private val context: Context,
    private val onClick : (OrderData) -> Unit
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>(), BindableAdapter<OrderData> {

    private var orderList: List<OrderData> = listOf()
    inner class OrderViewHolder(private val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(orderModel: OrderData) {
            binding.order = orderModel
            binding.btnViewDetailOrder.setOnClickListener { onClick(orderModel) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(context), parent,false)
        return OrderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        with(holder)  {
            this.bindData(orderList[position])
        }
    }

    override fun setItems(items: List<OrderData>) {
        orderList = items
        Log.d("SetItem icon adapter ", "")
        notifyDataSetChanged()
    }

}