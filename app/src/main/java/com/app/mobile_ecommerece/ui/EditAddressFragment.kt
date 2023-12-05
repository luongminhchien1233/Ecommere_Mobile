package com.app.mobile_ecommerece.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentAddAddressBinding
import com.app.mobile_ecommerece.databinding.FragmentEditAddressBinding
import com.app.mobile_ecommerece.model.Request.AddressRequest
import com.app.mobile_ecommerece.model.ThirdParties.DisctrictModel
import com.app.mobile_ecommerece.model.ThirdParties.ProvinceModel
import com.app.mobile_ecommerece.model.ThirdParties.TownModel
import com.app.mobile_ecommerece.viewmodels.AddressViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditAddressFragment : BaseFragment<FragmentEditAddressBinding>(true) {
    private val args by navArgs<EditAddressFragmentArgs>()
    private val addressViewModel: AddressViewModel by activityViewModels()
    private lateinit var provineAdapter: ArrayAdapter<String>
    private var provinceData: List<ProvinceModel> = listOf()
    private lateinit var disctrictAdapter: ArrayAdapter<String>
    private var disctrictData: List<DisctrictModel> = listOf()
    private lateinit var townAdapter: ArrayAdapter<String>
    private var townData: List<TownModel> = listOf()
    var provinceText = ""
    var disctrictText = ""
    var townText = ""
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEditAddressBinding {
        return FragmentEditAddressBinding.inflate(inflater, container, false)
    }

    private fun observerEvent() {
        registerAllExceptionEvent(addressViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(addressViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(addressViewModel, viewLifecycleOwner)
    }



    private fun setUpNavigate(){
        binding.btnBack.setOnClickListener {
            navigateBack()
        }
        binding.btnUpdate.setOnClickListener {
            UpdateAddress()
        }
        binding.btnDelete.setOnClickListener {
            DeleteAddress()
        }
    }

    private fun setUpDropdown(){
        provineAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line)
        binding.autoCompleteTxtProvince.setAdapter(provineAdapter)

        disctrictAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line)
        binding.autoCompleteTxtDisctrict.setAdapter(disctrictAdapter)

        townAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line)
        binding.autoCompleteTxtTown.setAdapter(townAdapter)

        binding.autoCompleteTxtProvince.setOnClickListener {
            addressViewModel.getProvince()
        }

        binding.autoCompleteTxtProvince.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val itemSelect = parent.getItemAtPosition(position)
            for(item in provinceData) {
                if (item.name == itemSelect) {
                    addressViewModel.getDisctrict(item.code)
                }
            }
            provinceText = itemSelect.toString()
        }
        binding.autoCompleteTxtDisctrict.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val itemSelect = parent.getItemAtPosition(position)
            for(item in disctrictData) {
                if (item.name == itemSelect) {
                    addressViewModel.getTown(item.code)
                }
            }
            disctrictText = itemSelect.toString()
        }
        binding.autoCompleteTxtTown.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val itemSelect = parent.getItemAtPosition(position)
            townText = itemSelect.toString()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerEvent()
        binding.lifecycleOwner = viewLifecycleOwner
        setUpNavigate()
        setUpDropdown()
        val controller = findNavController()
        addressViewModel.provincesData.observe(viewLifecycleOwner) { newItem ->
            val itemNames = newItem.map { it.name }
            binding.autoCompleteTxtProvince.text.clear()
            provineAdapter.clear()
            provineAdapter.addAll(itemNames)
            provineAdapter.notifyDataSetChanged()
            provinceData = newItem
        }
        addressViewModel.districtsData.observe(viewLifecycleOwner) { Disctrict ->
            val itemNames = Disctrict.map { it.name }
            disctrictAdapter.clear()
            disctrictAdapter.addAll(itemNames)
            disctrictAdapter.notifyDataSetChanged()
            disctrictData = Disctrict
        }
        addressViewModel.townsData.observe(viewLifecycleOwner) { newItem ->
            val itemNames = newItem.map { it.name }
            townAdapter.clear()
            townAdapter.addAll(itemNames)
            townAdapter.notifyDataSetChanged()
            townData = newItem
        }
        if(args.addressModel != null){
            binding.addressData = args.addressModel
            binding.cbDefault.isChecked = args.addressModel!!.default
            provinceText = args.addressModel!!.province
            disctrictText = args.addressModel!!.district
            townText = args.addressModel!!.ward
            args!!.addressModel?.let { addressViewModel.getAllInfo(it) }
        }
    }

    private  fun DeleteAddress(){
        if(args.addressModel != null){
            if(args.addressModel?.default == false){
                addressViewModel.deleteAddress(args.addressModel!!._id)
            }
        }
    }

    private  fun UpdateAddress(){
        val addressRequest = AddressRequest(binding.etAddressName.text.toString(), provinceText, disctrictText, townText, binding.etNote.text.toString(), binding.cbDefault.isChecked)
        args!!.addressModel?.let { addressViewModel.updateAddress(it!!._id, addressRequest) }
    }

}