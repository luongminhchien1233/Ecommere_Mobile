package com.app.mobile_ecommerece.ui.Admin.ui

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
import com.app.mobile_ecommerece.databinding.FragmentUserinfoAdminBinding
import com.app.mobile_ecommerece.model.Request.UpdateRoleRequest
import com.app.mobile_ecommerece.ui.OrderDetailFragmentArgs
import com.app.mobile_ecommerece.viewmodels.AdminViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentAdminUserInfo : BaseFragment<FragmentUserinfoAdminBinding>(true) {
    private val args by navArgs<FragmentAdminUserInfoArgs>()
    private val adminViewModel: AdminViewModel by activityViewModels()
    private lateinit var roleAdapter: ArrayAdapter<String>
    private var roleData: List<String> = listOf("customer", "staff")
    var role = ""
    var userId = ""
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentUserinfoAdminBinding {
        return FragmentUserinfoAdminBinding.inflate(inflater, container, false)
    }

    private fun setupRecycleViewLayout() {

    }

    private fun observerEvent() {
        registerAllExceptionEvent(adminViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(adminViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(adminViewModel, viewLifecycleOwner)
    }

    private fun setUpNavigate(){
        binding.ibBack.setOnClickListener {
            val action: NavDirections = FragmentAdminUserInfoDirections.actionAdminUserInfoFragmentToAdminUserFragment()
            navigateAction(action)
//            navigateBack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        observerEvent()
        setUpNavigate()
        setupRecycleViewLayout()
        if(args.userModel != null){
            binding.userData = args.userModel
        }
        userId = args.userModel?._id.toString()
        role = args.userModel?.role.toString()
        if(adminViewModel.isAdmin() == true){
            binding.Layout5.visibility = View.VISIBLE
            roleAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, roleData)
            binding.autoCompleteTxtRole.setAdapter(roleAdapter)
            binding.autoCompleteTxtRole.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                val itemSelect = parent.getItemAtPosition(position)
                role = itemSelect.toString()
            }
            binding.btnEditSave.setOnClickListener {
                Update()
            }
        }
        else{
            binding.Layout5.visibility = View.GONE
        }
        val controller = findNavController()
    }

    fun Update(){
        if(role != ""){
            val request = UpdateRoleRequest(role)
            adminViewModel.updateRole(request, userId)
        }
    }
}