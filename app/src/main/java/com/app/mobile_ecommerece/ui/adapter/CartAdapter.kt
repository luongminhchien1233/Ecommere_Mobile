package com.app.mobile_ecommerece.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mobile_ecommerece.common.BindableAdapter
import com.app.mobile_ecommerece.databinding.ItemCartBinding
import com.app.mobile_ecommerece.model.CartModel
import com.squareup.picasso.Picasso

class CartAdapter(
    private val context: Context,
    private val onPlusClick : (CartModel) -> Unit,
    private val onMinusClick : (CartModel) -> Unit,
    private val onDeleteClick : (CartModel) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>(), BindableAdapter<CartModel> {

    private var cartList: List<CartModel> = listOf()
    inner class CartViewHolder(private val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(cartModel: CartModel) {
            binding.cartItem = cartModel
            Picasso.get().load(cartModel.product.images[0].url).into(binding.shapeableImageView)
            val formattedNumber = String.format("%,d", cartModel.totalPriceItem)
            binding.tvPricePopular.text = formattedNumber + "₫"
            binding.tvQuantity.text = cartModel.quantity.toString()
            binding.btnPlusQuantity.setOnClickListener { onPlusClick(cartModel) }
            binding.btnMinusQuantity.setOnClickListener { onMinusClick(cartModel) }
            binding.btnDeleteItemCart.setOnClickListener { onDeleteClick(cartModel) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(context), parent,false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        with(holder)  {
            this.bindData(cartList[position])
        }
    }

    override fun setItems(items: List<CartModel>) {
        cartList = items
        Log.d("SetItem pd adapter ", "")
        notifyDataSetChanged()
    }
    fun clear(){
        cartList = listOf()
        notifyDataSetChanged()
    }

}