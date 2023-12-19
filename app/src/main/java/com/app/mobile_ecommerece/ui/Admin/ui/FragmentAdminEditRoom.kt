package com.app.mobile_ecommerece.ui.Admin.ui

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentCreateCategoryAdminBinding
import com.app.mobile_ecommerece.databinding.FragmentCreateRoomAdminBinding
import com.app.mobile_ecommerece.model.Request.CreateCategoryRequest
import com.app.mobile_ecommerece.model.Request.CreateRoomRequest
import com.app.mobile_ecommerece.model.RoomModel
import com.app.mobile_ecommerece.viewmodels.AdminViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentAdminEditRoom : BaseFragment<FragmentCreateRoomAdminBinding>(true) {
    private val adminViewModel: AdminViewModel by activityViewModels()
    private val args by navArgs<FragmentAdminEditRoomArgs>()
    var roomId = ""
    var nameRoom = ""
    var icUrl = ""
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCreateRoomAdminBinding {
        return FragmentCreateRoomAdminBinding.inflate(inflater, container, false)
    }

    private fun setupRecycleViewLayout() {

    }

    private fun observerEvent() {
        registerAllExceptionEvent(adminViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(adminViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(adminViewModel, viewLifecycleOwner)
    }

    private fun setUpNavigate(){
        binding.imageButton.setOnClickListener {
            val action: NavDirections = FragmentAdminEditRoomDirections.actionAdminEditRoomFragmentToAdminRoomFragment()
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
        //Create Mode
        if(args.roomModel == null){
            binding.btnEditSave.text = "Create"
            binding.btnEditSave.setOnClickListener {
                CreateRoom()
            }
            binding.btnDelete.visibility = View.GONE
        }
        else{
            binding.btnEditSave.text = "Save"
            roomId = args.roomModel?._id.toString()
            binding.btnEditSave.setOnClickListener {
                UpdateRoom()
            }
            binding.btnDelete.setOnClickListener {
                DeleteRoom()
            }
            binding.etRoom.hint = args.roomModel?.nameRoom.toString()
            nameRoom = args.roomModel?.nameRoom.toString()
            binding.etIcon.hint = args.roomModel?.icUrl.toString()
            icUrl = args.roomModel?.icUrl.toString()
            binding.btnDelete.visibility = View.VISIBLE
        }
        val controller = findNavController()
    }

    fun CreateRoom(){
        var name = binding.etRoom.text.toString()
        var icUrl = binding.etIcon.text.toString()
        if(name != ""){
            val request = CreateRoomRequest(name, icUrl)
            adminViewModel.createRoom(request)
        }
    }

    fun UpdateRoom(){
        var text = binding.etRoom.text.toString()
        if(text == ""){
            text = nameRoom.toString()
        }
        var ic = binding.etIcon.text.toString()
        if(ic == ""){
            ic = icUrl.toString()
        }
        val request = CreateRoomRequest(text, ic)
        if(roomId != ""){
            adminViewModel.updateRoom(roomId, request)
        }
    }

    fun DeleteRoom(){
        adminViewModel.deleteRoom(roomId)
    }
}