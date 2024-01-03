package com.app.mobile_ecommerece.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentStoreBinding
import com.app.mobile_ecommerece.model.CategoryModel
import com.app.mobile_ecommerece.model.OrderData
import com.app.mobile_ecommerece.model.ProductModel
import com.app.mobile_ecommerece.model.RoomModel
import com.app.mobile_ecommerece.ui.adapter.CategoryAdapter
import com.app.mobile_ecommerece.ui.adapter.ProductAdapter
import com.app.mobile_ecommerece.viewmodels.StoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class StoreFragment : BaseFragment<FragmentStoreBinding>(false) {
    private val storeViewModel: StoreViewModel by activityViewModels()
    private val categoryAdapter: CategoryAdapter by lazy{
        CategoryAdapter(requireContext(), onCateIconClick)
    }

    private val productAdapter: ProductAdapter by lazy{
        ProductAdapter(requireContext(), onProductItemClick)
    }
    private val args by navArgs<StoreFragmentArgs>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStoreBinding {
        return FragmentStoreBinding.inflate(inflater, container, false)
    }

    private fun setupRecycleViewLayout() {
        binding.rvCategoryStore.adapter = categoryAdapter
        binding.rvCategoryStore.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.rvListItem.adapter = productAdapter
        binding.rvListItem.layoutManager = GridLayoutManager(context, 2)
    }

    private fun observerEvent() {
        registerAllExceptionEvent(storeViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(storeViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(storeViewModel, viewLifecycleOwner)
    }

    private fun setUpNavigate(){
        binding.btnchooseRoom.setOnClickListener {
            val action: NavDirections = StoreFragmentDirections.actionStoreFragmentToChooseRoomFragment()
            navigateAction(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.storeViewModel = storeViewModel
        observerEvent()
        setUpNavigate()
        setupRecycleViewLayout()
        storeViewModel.getALlProduct()
        storeViewModel.getALlCategories()
        if(args.roomId!!.isEmpty()){

        }
        else{
            val roomId : String = args.roomId!!
            storeViewModel.getProductByRoom(roomId)
            storeViewModel.getCategoryByRoom(roomId)
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterList(newText.lowercase(Locale.ROOT))
                return false
            }
        })
        val controller = findNavController()
    }

    private val onProductItemClick: (ProductModel) -> Unit = {
        val action: NavDirections = StoreFragmentDirections.actionStoreFragmentToProductDetailFragment(it)
        navigateAction(action)
    }

    private val onCateIconClick: (CategoryModel) -> Unit = {
        storeViewModel.getProductByCategory(it._id)
    }
    private fun filterList(newText: String) {
        val neworderList: ArrayList<ProductModel> = ArrayList()
        storeViewModel.productsData.value!!.forEach { item ->
            if (item.name.lowercase(Locale.ROOT).contains(newText)) {
                neworderList.add(item)
            }
        }
        if (neworderList.isEmpty()) {

        } else {
            productAdapter.setFilterList(neworderList)
        }
    }
}