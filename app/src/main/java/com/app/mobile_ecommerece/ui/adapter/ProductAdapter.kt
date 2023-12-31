package com.app.mobile_ecommerece.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mobile_ecommerece.common.BindableAdapter
import com.app.mobile_ecommerece.databinding.ItemProductBinding
import com.app.mobile_ecommerece.model.OrderData
import com.app.mobile_ecommerece.model.ProductModel
import com.squareup.picasso.Picasso

class ProductAdapter(
    private val context: Context,
    private val onClick : (ProductModel) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(), BindableAdapter<ProductModel> {

    private var productList: List<ProductModel> = listOf()
    inner class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(productModel: ProductModel) {
            binding.product = productModel
            Picasso.get().load(productModel.images[0].url).into(binding.imgItemList)
            val formattedNumber = String.format("%,d", productModel.priceSale)
            binding.tvPriceItemList.text = formattedNumber + "₫"
            binding.LayoutItem.setOnClickListener { onClick(productModel) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(context), parent,false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        with(holder)  {
            this.bindData(productList[position])
        }
    }

    override fun setItems(items: List<ProductModel>) {
        productList = items
        Log.d("SetItem pd adapter ", "")
        notifyDataSetChanged()
    }

    fun setFilterList(items: List<ProductModel>){
        this.productList = items as ArrayList<ProductModel>
        notifyDataSetChanged()
    }

}