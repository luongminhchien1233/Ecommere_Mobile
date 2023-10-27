package com.app.mobile_ecommerece.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mobile_ecommerece.common.BindableAdapter
import com.app.mobile_ecommerece.databinding.ItemCategoryBinding
import com.app.mobile_ecommerece.model.CategoryModel
import com.squareup.picasso.Picasso

class CategoryAdapter(
    private val context: Context,
    private val onClick : (CategoryModel) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(), BindableAdapter<CategoryModel> {

    private var categoryList: List<CategoryModel> = listOf()
    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(categoryModel: CategoryModel) {
            binding.tvCategoryName.text = categoryModel.nameCate
            Picasso.get().load(categoryModel.icUrl).into(binding.categoryIc)
            binding.layoutCategoryItem.setOnClickListener { onClick(categoryModel) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(context), parent,false)
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
}