package com.app.mobile_ecommerece.ui.Admin.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mobile_ecommerece.common.BindableAdapter
import com.app.mobile_ecommerece.databinding.ItemAdminCategoryBinding
import com.app.mobile_ecommerece.databinding.ItemAdminRoomBinding
import com.app.mobile_ecommerece.model.CategoryModel
import com.app.mobile_ecommerece.model.RoomModel

class RoomAdminAdapter(
    private val context: Context,
    private val onClick : (RoomModel) -> Unit
) : RecyclerView.Adapter<RoomAdminAdapter.RoomViewHolder>(),
    BindableAdapter<RoomModel> {

    private var roomList: List<RoomModel> = listOf()
    inner class RoomViewHolder(private val binding: ItemAdminRoomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(roomModel: RoomModel) {
            binding.tvNameItem.text = roomModel.nameRoom
            binding.LayoutItem.setOnClickListener { onClick(roomModel) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val binding = ItemAdminRoomBinding.inflate(LayoutInflater.from(context), parent,false)
        return RoomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        with(holder)  {
            this.bindData(roomList[position])
        }
    }

    override fun setItems(items: List<RoomModel>) {
        roomList = items
        Log.d("SetItem icon adapter ", "")
        notifyDataSetChanged()
    }

    fun setFilterList(items: List<RoomModel>){
        this.roomList = items as ArrayList<RoomModel>
        notifyDataSetChanged()
    }

}