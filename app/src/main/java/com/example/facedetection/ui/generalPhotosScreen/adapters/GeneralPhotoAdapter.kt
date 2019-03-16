package com.example.facedetection.ui.generalPhotosScreen.adapters

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.facedetection.R
import com.example.facedetection.data.local.model.IImageObj
import com.example.facedetection.ui.base.BaseHolder
import com.example.facedetection.ui.base.IAdapter
import com.example.facedetection.utils.diff.MyDiff

class GeneralPhotoAdapter : IAdapter<List<IImageObj>>, RecyclerView.Adapter<GeneralPhotoAdapter.ViewHolder>() {

    private var content = mutableListOf<IImageObj>()

    override fun setContent(content: List<IImageObj>) {
        val diff = MyDiff(this.content, content)
        val result = DiffUtil.calculateDiff(diff, true)
        this.content = content.toMutableList()
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_general_photo_layout, parent, false)
        )

    override fun getItemCount(): Int = content.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(content[position])
    }

    class ViewHolder(private val view: View) : BaseHolder<IImageObj>(view) {
        override fun onBind(item: IImageObj) {
            val imageView = view.findViewById<ImageView>(R.id.iv_general_photo)

            Glide.with(view)
                .load(item.getImagePath())
//                .apply(RequestOptions().override(150, 150))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .placeholder(R.drawable.ic_empty_folder)
                .error(R.drawable.ic_empty_folder)
                .into(imageView)
        }
    }
}