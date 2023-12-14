package com.app.mobile_ecommerece.ui.Admin.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mobile_ecommerece.common.BindableAdapter
import com.app.mobile_ecommerece.databinding.ItemAdminCategoryBinding
import com.app.mobile_ecommerece.databinding.ItemCategoryBinding
import com.app.mobile_ecommerece.model.CategoryModel

class CategoryAdminAdapter(
    private val context: Context,
    private val onClick : (CategoryModel) -> Unit
) : RecyclerView.Adapter<CategoryAdminAdapter.CategoryViewHolder>(), BindableAdapter<CategoryModel> {

    private var categoryList: List<CategoryModel> = listOf()
    inner class CategoryViewHolder(private val binding: ItemAdminCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(categoryModel: CategoryModel) {
            binding.tvNameItem.text = categoryModel.nameCate
            binding.LayoutItem.setOnClickListener { onClick(categoryModel) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemAdminCategoryBinding.inflate(LayoutInflater.from(context), parent,false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        with(holder)  {
            this.bindData(categoryList[position])
        }
    }

    override fun setItems(items: List<CategoryModel>) {
        categoryList = items
        Log.d("SetItem icon adapter ", "")
        notifyDataSetChanged()
    }

    fun setFilterList(items: List<CategoryModel>){
        this.categoryList = items as ArrayList<CategoryModel>
        notifyDataSetChanged()
    }

}