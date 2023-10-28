package com.app.mobile_ecommerece.ui

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentProductDetailBinding
import com.app.mobile_ecommerece.ui.adapter.ImageAdapter


class ProductDetailFragment: BaseFragment<FragmentProductDetailBinding>(true) {

    private val args by navArgs<ProductDetailFragmentArgs>()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProductDetailBinding {
        return FragmentProductDetailBinding.inflate(inflater, container, false)
    }

    private fun observerEvent() {
//        registerAllExceptionEvent(homeViewModel, viewLifecycleOwner)
//        registerObserverLoadingEvent(homeViewModel, viewLifecycleOwner)
//        registerObserverNavigateEvent(homeViewModel, viewLifecycleOwner)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        observerEvent()
        if(args.productModel != null){
            binding.productData = args.productModel
        }
        setImageSlide()
        binding.tvPrice.text = binding.productData!!.price.toString()
        val controller = findNavController()
        binding.btnBack.setOnClickListener {
            navigateBack()
        }
    }

    fun setImageSlide() {
        val imageAdapter = ImageAdapter(binding.productData!!.images)
        binding.viewPagerItemDetails.adapter = imageAdapter
        binding.circleIndicator.setViewPager(binding.viewPagerItemDetails)
    }
}