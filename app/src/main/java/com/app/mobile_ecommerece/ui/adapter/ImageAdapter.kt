package com.app.mobile_ecommerece.ui.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.app.mobile_ecommerece.R
import com.app.mobile_ecommerece.model.ImageModel
import com.app.mobile_ecommerece.ui.adapter.ImageAdapter.Page2ViewHolder
import com.denzcoskun.imageslider.adapters.ViewPagerAdapter
import com.squareup.picasso.Picasso


class ImageAdapter(
    private val imageList: List<ImageModel>
) : RecyclerView.Adapter<ImageAdapter.Page2ViewHolder>(){
    inner class Page2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.imgSlideItem)

        init {
            itemImage.setOnClickListener {
                val position = adapterPosition
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Page2ViewHolder {
        return Page2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.images_slide_itemdetails, parent, false))
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: Page2ViewHolder, position: Int) {
        Picasso.get().load(imageList[position].url).into(holder.itemImage)
    }


}