package com.app.mobile_ecommerece.ui.Admin.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mobile_ecommerece.common.BindableAdapter
import com.app.mobile_ecommerece.databinding.ItemAdminOrderBinding
import com.app.mobile_ecommerece.databinding.ItemAdminProductBinding
import com.app.mobile_ecommerece.model.OrderData
import com.app.mobile_ecommerece.model.ProductAdminModel
import com.squareup.picasso.Picasso

class ProductAdminAdapter(
    private val context: Context,
    private val onClick : (ProductAdminModel) -> Unit
) : RecyclerView.Adapter<ProductAdminAdapter.ProductViewHolder>(), BindableAdapter<ProductAdminModel> {

    private var productsList: List<ProductAdminModel> = listOf()
    inner class ProductViewHolder(private val binding: ItemAdminProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(productModel: ProductAdminModel) {
            binding.tvCateRoom.text = productModel.category.nameCate + ", " + productModel.room.nameRoom
            binding.tvName.text = productModel.name
            binding.tvPrice.text = productModel.price.toString()
            if(productModel.enable == true){
                binding.tvEnable.text = "Enable"
            }
            else{
                binding.tvEnable.text = "Disable"
            }

            Picasso.get().load(productModel.images[0].url).into(binding.imgItemList)

            binding.btnEdit.setOnClickListener { onClick(productModel) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemAdminProductBinding.inflate(LayoutInflater.from(context), parent,false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        with(holder)  {
            this.bindData(productsList[position])
        }
    }

    override fun setItems(items: List<ProductAdminModel>) {
        productsList = items
        Log.d("SetItem icon adapter ", "")
        notifyDataSetChanged()
    }

    fun setFilterList(items: List<ProductAdminModel>){
        this.productsList = items as ArrayList<ProductAdminModel>
        notifyDataSetChanged()
    }
}