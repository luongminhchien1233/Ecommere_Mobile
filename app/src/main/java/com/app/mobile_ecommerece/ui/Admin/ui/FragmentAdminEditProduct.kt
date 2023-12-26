package com.app.mobile_ecommerece.ui.Admin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.mobile_ecommerece.R
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentEditProductAdminBinding
import com.app.mobile_ecommerece.model.CategoryModel
import com.app.mobile_ecommerece.model.Request.ProductUpdateRequest
import com.app.mobile_ecommerece.model.Request.SpecRequest
import com.app.mobile_ecommerece.model.RoomModel
import com.app.mobile_ecommerece.viewmodels.AdminViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.github.muddz.styleabletoast.StyleableToast

@AndroidEntryPoint
class FragmentAdminEditProduct : BaseFragment<FragmentEditProductAdminBinding>(true) {
    private val adminViewModel: AdminViewModel by activityViewModels()
    private val args by navArgs<FragmentAdminEditProductArgs>()
    private lateinit var roomAdapter: ArrayAdapter<String>
    private var roomData: List<RoomModel> = listOf()
    private lateinit var categoryAdapter: ArrayAdapter<String>
    private var categoryData: List<CategoryModel> = listOf()
    var roomId = ""
    var categoryId = ""
    var productId = ""
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEditProductAdminBinding {
        return FragmentEditProductAdminBinding.inflate(inflater, container, false)
    }

    private fun setupRecycleViewLayout() {
        roomAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line)
        binding.autoCompleteTxtRoom.setAdapter(roomAdapter)
        binding.autoCompleteTxtRoom.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val itemSelect = parent.getItemAtPosition(position)
            for(item in roomData) {
                if (item.nameRoom == itemSelect) {
                    roomId = item._id
                    adminViewModel.getCategoryByRoom(roomId)
                }
            }
        }
        categoryAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line)
        binding.autoCompleteTxtCategory.setAdapter(categoryAdapter)
        binding.autoCompleteTxtCategory.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val itemSelect = parent.getItemAtPosition(position)
            for(item in categoryData) {
                if (item.nameCate == itemSelect) {
                    categoryId = item._id
                }
            }
        }
    }

    private fun observerEvent() {
        registerAllExceptionEvent(adminViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(adminViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(adminViewModel, viewLifecycleOwner)
    }

    private fun setUpNavigate(){
        binding.imageButton2.setOnClickListener {
            val action: NavDirections = FragmentAdminEditProductDirections.actionAdminEditProductFragmentToAdminProductFragment()
            navigateAction(action)
        }
        binding.btnEditSave.setOnClickListener {
            updateProduct()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.adminViewModel = adminViewModel
        observerEvent()
        setUpNavigate()
        setupRecycleViewLayout()
        if(args.productModel != null){
            binding.product = args.productModel
            productId = args.productModel!!._id
            roomId = args.productModel!!.room._id
            categoryId = args.productModel!!.category._id
            binding.checkBox.isChecked = args.productModel!!.enable
            binding.etDimensions.setText(args.productModel!!.specs[0].v)
            binding.etCollection.setText(args.productModel!!.specs[1].v)
            binding.etMaterials.setText(args.productModel!!.specs[2].v)
            adminViewModel.getInfoProduct(args.productModel!!.room._id)
            adminViewModel.roomsData.observe(viewLifecycleOwner) { newItem ->
                val itemNames = newItem.map { it.nameRoom }
                roomAdapter.clear()
                roomAdapter.addAll(itemNames)
                roomAdapter.notifyDataSetChanged()
                roomData = newItem
            }
            adminViewModel.categoriesData.observe(viewLifecycleOwner) { newItem ->
                val itemNames = newItem.map { it.nameCate }
                categoryAdapter.clear()
                categoryAdapter.addAll(itemNames)
                categoryAdapter.notifyDataSetChanged()
                categoryData = newItem
            }
            adminViewModel.isUpdateSuccess.observe(viewLifecycleOwner) { value ->
                if(value == true){
                    StyleableToast.makeText(requireContext(), "Update Successfully!", Toast.LENGTH_LONG, R.style.SuccessToast).show()
                }
            }
            adminViewModel.isUpdateFail.observe(viewLifecycleOwner) { value ->
                if(value == true){
                    StyleableToast.makeText(requireContext(), "Update Fail, Please Try Again!", Toast.LENGTH_LONG, R.style.ErrorToast).show()
                }
            }
        }
        val controller = findNavController()
    }

    fun updateProduct(){
        var code = binding.etCode.text.toString()
        var name = binding.etName.text.toString()
        var desc = binding.etDescription.text.toString()
        var shortDesc = binding.etShortDescription.text.toString()
        var price = binding.etPrice.text.toString().toInt()
        var sale = binding.etSale.text.toString().toInt()
        var quantity = binding.etQuantity.text.toString().toInt()
        var dimention = SpecRequest("Dimensions", binding.etDimensions.text.toString())
        var collection = SpecRequest("Collection", binding.etCollection.text.toString())
        var materials = SpecRequest("Materials", binding.etMaterials.text.toString())
        var specs: List<SpecRequest> = listOf(dimention, collection, materials)
        var enable = binding.checkBox.isChecked
        val request = ProductUpdateRequest(
            code, name, desc, shortDesc, categoryId, roomId, price, sale, quantity, specs, enable
        )
        adminViewModel.updateProduct(request, productId)
    }
}