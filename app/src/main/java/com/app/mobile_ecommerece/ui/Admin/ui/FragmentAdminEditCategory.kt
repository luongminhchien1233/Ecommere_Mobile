package com.app.mobile_ecommerece.ui.Admin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentCreateCategoryAdminBinding
import com.app.mobile_ecommerece.model.CategoryModel
import com.app.mobile_ecommerece.model.Request.CreateCategoryRequest
import com.app.mobile_ecommerece.model.RoomModel
import com.app.mobile_ecommerece.ui.Admin.adapter.CategoryAdminAdapter
import com.app.mobile_ecommerece.ui.OrderDetailFragmentArgs
import com.app.mobile_ecommerece.viewmodels.AdminViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentAdminEditCategory : BaseFragment<FragmentCreateCategoryAdminBinding>(true) {
    private val adminViewModel: AdminViewModel by activityViewModels()
    private val args by navArgs<FragmentAdminEditCategoryArgs>()
    private lateinit var roomAdapter: ArrayAdapter<String>
    private var roomData: List<RoomModel> = listOf()
    var roomId = ""
    var categoryId = ""
    var nameCate = ""
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCreateCategoryAdminBinding {
        return FragmentCreateCategoryAdminBinding.inflate(inflater, container, false)
    }

    private fun setupRecycleViewLayout() {
        roomAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line)
        binding.autoCompleteTxtRoom.setAdapter(roomAdapter)
        binding.autoCompleteTxtRoom.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val itemSelect = parent.getItemAtPosition(position)
            for(item in roomData) {
                if (item.nameRoom == itemSelect) {
                    roomId = item._id
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
        binding.imageButton.setOnClickListener {
            val action: NavDirections = FragmentAdminEditCategoryDirections.actionAdminEditCategorytFragmentToAdminCategorytFragment()
            navigateAction(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.adminViewModel = adminViewModel
        observerEvent()
        setUpNavigate()
        setupRecycleViewLayout()
        adminViewModel.getALlRoom()
        adminViewModel.roomsData.observe(viewLifecycleOwner) { newItem ->
            val itemNames = newItem.map { it.nameRoom }
            roomAdapter.clear()
            roomAdapter.addAll(itemNames)
            roomAdapter.notifyDataSetChanged()
            roomData = newItem
        }
        //Create Mode
        if(args.categoryModel == null){
            binding.btnEditSave.text = "Create"
            binding.btnEditSave.setOnClickListener {
                CreateCategory()
            }
            binding.btnDelete.visibility = View.GONE
        }
        else{
            binding.btnEditSave.text = "Save"
            categoryId = args.categoryModel?._id.toString()
            binding.btnEditSave.setOnClickListener {
                UpdateCategory()
            }
            binding.btnDelete.setOnClickListener {
                DeleteCategory()
            }
            binding.etCategory.hint = args.categoryModel?.nameCate.toString()
            nameCate = args.categoryModel?.nameCate.toString()
            binding.btnDelete.visibility = View.VISIBLE
        }
        val controller = findNavController()
    }

    fun CreateCategory(){

        val request = CreateCategoryRequest(binding.etCategory.text.toString(), roomId)
        if(roomId != ""){
            adminViewModel.createCategory(request)
        }
    }

    fun UpdateCategory(){
        var text = binding.etCategory.text.toString()
        if(text == ""){
            text = nameCate.toString()
        }
        val request = CreateCategoryRequest(text, roomId)
        if(roomId != ""){
            adminViewModel.updateCategory(categoryId, request)
        }
    }

    fun DeleteCategory(){
        adminViewModel.deleteCategory(categoryId)
    }
}