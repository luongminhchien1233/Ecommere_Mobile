package com.app.mobile_ecommerece.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mobile_ecommerece.common.BindableAdapter
import com.app.mobile_ecommerece.databinding.ItemRoomBinding
import com.app.mobile_ecommerece.model.RoomModel
import com.squareup.picasso.Picasso

class RoomAdapter(
    private val context: Context,
    private val onClick : (RoomModel) -> Unit
) : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>(), BindableAdapter<RoomModel> {

    private var roomList: List<RoomModel> = listOf()
    inner class RoomViewHolder(private val binding: ItemRoomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(roomModel: RoomModel) {
            binding.tvRoomName.text = roomModel.nameRoom
            if(roomModel.icUrl.isNotEmpty()) {
                Picasso.get().load(roomModel.icUrl).into(binding.roomIc)
            }
            binding.layoutRoomItem.setOnClickListener { onClick(roomModel) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val binding = ItemRoomBinding.inflate(LayoutInflater.from(context), parent,false)
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
}