package com.app.mobile_ecommerece.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mobile_ecommerece.common.BindableAdapter
import com.app.mobile_ecommerece.databinding.ItemOrderBinding
import com.app.mobile_ecommerece.databinding.ItemProductOrderBinding
import com.app.mobile_ecommerece.model.CartModel
import com.app.mobile_ecommerece.model.OrderData
import com.app.mobile_ecommerece.model.OrderProductModel
import com.app.mobile_ecommerece.model.ProductCartModel
import com.squareup.picasso.Picasso

class ProductOrderAdapter(
    private val context: Context,
    private val onClick : (CartModel) -> Unit
) : RecyclerView.Adapter<ProductOrderAdapter.ProductOrderViewHolder>(), BindableAdapter<CartModel> {

    private var productList: List<CartModel> = listOf()
    inner class ProductOrderViewHolder(private val binding: ItemProductOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(productModel: CartModel) {
            binding.product = productModel
            binding.tvQuantity2.text = productModel.quantity.toString()
            Picasso.get().load(productModel.product!!.images[0].url).into(binding.shapeableImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductOrderViewHolder {
        val binding = ItemProductOrderBinding.inflate(LayoutInflater.from(context), parent,false)
        return ProductOrderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductOrderViewHolder, position: Int) {
        with(holder)  {
            this.bindData(productList[position])
        }
    }

    override fun setItems(items: List<CartModel>) {
        productList = items
        Log.d("SetItem icon adapter ", "")
        notifyDataSetChanged()
    }

}