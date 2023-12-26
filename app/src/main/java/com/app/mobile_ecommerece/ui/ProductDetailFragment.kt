package com.app.mobile_ecommerece.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.mobile_ecommerece.R
import com.app.mobile_ecommerece.base.BaseFragment
import com.app.mobile_ecommerece.databinding.FragmentProductDetailBinding
import com.app.mobile_ecommerece.model.Request.CartRequest
import com.app.mobile_ecommerece.ui.adapter.ImageAdapter
import com.app.mobile_ecommerece.utils.Status
import com.app.mobile_ecommerece.viewmodels.ProductViewModel
import io.github.muddz.styleabletoast.StyleableToast


class ProductDetailFragment: BaseFragment<FragmentProductDetailBinding>(true) {

    private val args by navArgs<ProductDetailFragmentArgs>()
    private val prodcutViewModel: ProductViewModel by activityViewModels()
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProductDetailBinding {
        return FragmentProductDetailBinding.inflate(inflater, container, false)
    }

    private fun observerEvent() {
        registerAllExceptionEvent(prodcutViewModel, viewLifecycleOwner)
        registerObserverLoadingEvent(prodcutViewModel, viewLifecycleOwner)
        registerObserverNavigateEvent(prodcutViewModel, viewLifecycleOwner)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        observerEvent()
        if(args.productModel != null){
            binding.productData = args.productModel
        }
        setImageSlide()
//        binding.tvPrice.text = binding.productData!!.priceSale.toString() + "₫"
        val formattedPrice = String.format("%,d", binding.productData!!.priceSale) + "₫"

        binding.tvPrice.text = formattedPrice

        val controller = findNavController()
        binding.btnBack.setOnClickListener {
            navigateBack()
        }

        var itemQty: Int = binding.tvQuantity.text.toString().toInt();

        binding.btnPlusQuantity.setOnClickListener{
            itemQty = itemQty + 1
            binding.tvQuantity.text = itemQty.toString()
            val formattedPrice = String.format("%,d", (binding.productData!!.priceSale * itemQty)) + "₫"
            binding.tvPrice.text = formattedPrice
//            binding.tvPrice.text = (binding.productData!!.priceSale * itemQty).toString() + "₫"
        }

        binding.btnMinusQuantity.setOnClickListener{
            if(itemQty > 1){
                itemQty = itemQty - 1
            }
            binding.tvQuantity.text = itemQty.toString()
            val formattedPrice = String.format("%,d", (binding.productData!!.priceSale * itemQty)) + "₫"
            binding.tvPrice.text = formattedPrice
//            binding.tvPrice.text = (binding.productData!!.priceSale * itemQty).toString() + "₫"
        }

        binding.btnAddtoCart.setOnClickListener {
            if (!prodcutViewModel.checkIsLogin()){
//                navigateToPage(R.id.action_productDetailFragment_to_loginFragment)
                showNotify("Notification", "Please login", status = Status.WARNING)
            }
            else {
                val cartRequest = CartRequest(binding.productData!!._id, binding.tvQuantity.text.toString().toInt())
                prodcutViewModel.addtoCart(cartRequest)
                StyleableToast.makeText(requireContext(), "Add to Cart Successfully", Toast.LENGTH_LONG, R.style.SuccessToast).show()
            }
        }
        binding.btnCart.setOnClickListener {
            navigateToPage(R.id.action_productDetailFragment_to_cartFragment)
        }
    }

    fun setImageSlide() {
        val imageAdapter = ImageAdapter(binding.productData!!.images)
        binding.viewPagerItemDetails.adapter = imageAdapter
        binding.circleIndicator.setViewPager(binding.viewPagerItemDetails)
    }
}