package com.app.mobile_ecommerece.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.app.mobile_ecommerece.common.BindableAdapter
import com.app.mobile_ecommerece.databinding.ItemAddressBinding
import com.app.mobile_ecommerece.databinding.ItemAddressOrderBinding
import com.app.mobile_ecommerece.model.AddressModel

class AddressChooseAdapter(
    private val context: Context,
    private val onClick : (AddressModel) -> Unit,
) : RecyclerView.Adapter<AddressChooseAdapter.AddressViewHolder>(), BindableAdapter<AddressModel> {

    private var addressList: List<AddressModel> = listOf()
    inner class AddressViewHolder(private val binding: ItemAddressOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(addressModel: AddressModel) {
            binding.radioButton.setOnClickListener { onClick(addressModel) }
            binding.tvNameAddress.text = addressModel.nameAddress
            binding.tvNote.text = addressModel.note
            binding.tvDefault.isVisible = addressModel.default
            binding.tvDiaChi.text = addressModel.ward + "," + addressModel.district + "," + addressModel.province
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val binding = ItemAddressOrderBinding.inflate(LayoutInflater.from(context), parent,false)
        return AddressViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return addressList.size
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        with(holder)  {
            this.bindData(addressList[position])
        }
    }

    override fun setItems(items: List<AddressModel>) {
        addressList = items
        Log.d("SetItem pd adapter ", "")
        notifyDataSetChanged()
    }
}