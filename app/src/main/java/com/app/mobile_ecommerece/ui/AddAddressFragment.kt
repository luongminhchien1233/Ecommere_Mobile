package com.app.mobile_ecommerece.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentAddAddressBinding
import com.app.mobile_ecommerece.model.Request.AddressRequest
import com.app.mobile_ecommerece.model.ThirdParties.DisctrictModel
import com.app.mobile_ecommerece.model.ThirdParties.ProvinceModel
import com.app.mobile_ecommerece.model.ThirdParties.TownModel
import com.app.mobile_ecommerece.viewmodels.AddressViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddAddressFragment : BaseFragment<FragmentAddAddressBinding>(true) {
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
    ): FragmentAddAddressBinding {
        return FragmentAddAddressBinding.inflate(inflater, container, false)
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
        binding.btnAdd.setOnClickListener {
            AddAddress()
        }
    }

    private fun setUpDropdown(){
        provineAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line)
        binding.autoCompleteTxtProvince.setAdapter(provineAdapter)

        disctrictAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line)
        binding.autoCompleteTxtDisctrict.setAdapter(disctrictAdapter)

        townAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line)
        binding.autoCompleteTxtTown.setAdapter(townAdapter)

        binding.autoCompleteTxtProvince.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val itemSelect = parent.getItemAtPosition(position)
            for(item in provinceData) {
                if (item.name == itemSelect) {
                    addressViewModel.getDisctrict(item.code)
                    binding.autoCompleteTxtDisctrict.isFocusable = true
                }
            }
            provinceText = itemSelect.toString()
        }
        binding.autoCompleteTxtDisctrict.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val itemSelect = parent.getItemAtPosition(position)
            for(item in disctrictData) {
                if (item.name == itemSelect) {
                    addressViewModel.getTown(item.code)
                    binding.autoCompleteTxtTown.isFocusable = true
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
        binding.addressViewModel = addressViewModel
        setUpNavigate()
        setUpDropdown()
        addressViewModel.provincesData.observe(viewLifecycleOwner) { newItem ->
            val itemNames = newItem.map { it.name }
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
        addressViewModel.getProvince()
        binding.autoCompleteTxtDisctrict.isFocusable = false;
        binding.autoCompleteTxtTown.isFocusable = false;
        val controller = findNavController()
    }

    private  fun AddAddress(){
        val addressRequest = AddressRequest(binding.etAddressName.text.toString(), provinceText, disctrictText, townText, binding.etNote.text.toString(), binding.cbDefault.isChecked)

        addressViewModel.addAddress(addressRequest)
    }

}