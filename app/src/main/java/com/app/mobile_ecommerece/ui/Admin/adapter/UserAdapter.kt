package com.app.mobile_ecommerece.ui.Admin.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mobile_ecommerece.common.BindableAdapter
import com.app.mobile_ecommerece.databinding.ItemAdminOrderBinding
import com.app.mobile_ecommerece.databinding.ItemAdminUserBinding
import com.app.mobile_ecommerece.model.OrderData
import com.app.mobile_ecommerece.model.UserAdminDataJson

class UserAdapter(
    private val context: Context,
    private val onClick : (UserAdminDataJson) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(), BindableAdapter<UserAdminDataJson> {

    private var userList: List<UserAdminDataJson> = listOf()
    inner class UserViewHolder(private val binding: ItemAdminUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(userModel: UserAdminDataJson) {
            binding.userData = userModel
            binding.btnViewDetailOrder.setOnClickListener { onClick(userModel) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemAdminUserBinding.inflate(LayoutInflater.from(context), parent,false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        with(holder)  {
            this.bindData(userList[position])
        }
    }

    override fun setItems(items: List<UserAdminDataJson>) {
        userList = items
        Log.d("SetItem icon adapter ", "")
        notifyDataSetChanged()
    }

    fun setFilterList(items: List<OrderData>){
        this.userList = items as ArrayList<UserAdminDataJson>
        notifyDataSetChanged()
    }
}