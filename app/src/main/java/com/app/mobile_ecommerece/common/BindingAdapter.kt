package com.app.e_commerce_app.common

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.*
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Picasso.get().load(url).into(view)
    }
}

@BindingAdapter("imageSlideList")
fun setImageList(view: ImageSlider, list: ArrayList<SlideModel>?) {
    if (!list.isNullOrEmpty())
        view.setImageList(list, ScaleTypes.CENTER_INSIDE)
}

@BindingAdapter("items")
fun <T> setItems(recyclerView: RecyclerView, items: List<T>?) {
    if (items == null) return
    val adapter = recyclerView.adapter as? BindableAdapter<T>
    adapter?.setItems(items)
}

interface BindableAdapter<T> {
    fun setItems(items: List<T>)
}

//@BindingAdapter("numberValue")
//fun setNumberValue(textView: TextView, value: Long?) {
//    var number = value
//    if (number == null)
//        number = 0
//    number.let {
////        val formattedValue = Utils.formatNumber(number)
//        textView.text = Utils.formatNumber(number)
//    }
//}
//
//
//@BindingAdapter("numberValue")
//fun setNumberValue(textView: TextView, value: Int?) {
//    var number = value
//    if (number == null)
//        number = 0
//    number.let {
////        val formattedValue = Utils.formatNumber(number)
//        textView.text = number.toString()
//    }
//}
//
//
//@BindingAdapter("editTextValue")
//fun setEditTextValue(editText: EditText, value: String?) {
//    value?.let {
//        editText.setText(it)
//    }
//}