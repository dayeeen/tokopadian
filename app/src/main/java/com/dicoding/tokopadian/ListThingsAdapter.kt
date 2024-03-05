package com.dicoding.tokopadian

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListThingsAdapter(private val listThings: ArrayList<Things>) : RecyclerView.Adapter<ListThingsAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_things, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, seller, photo, price, rating, description) = listThings[position]
        Glide.with(holder.itemView.context)
            .load(photo) // URL Gambar
            .into(holder.imgPhoto) // imageView mana yang akan diterapkan
        holder.tvName.text = name
        holder.tvSeller.text = seller
        holder.tvRating.text = rating
        holder.tvPrice.text = price
        holder.tvDescription.text = description

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra(DetailActivity.EXTRA_NAME, name)
            intentDetail.putExtra(DetailActivity.EXTRA_PHOTO, photo)
            intentDetail.putExtra(DetailActivity.EXTRA_SELLER, seller)
            intentDetail.putExtra(DetailActivity.EXTRA_PRICE, price)
            intentDetail.putExtra(DetailActivity.EXTRA_RATING, rating)
            intentDetail.putExtra(DetailActivity.EXTRA_DESCRIPTION, description)
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = listThings.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvSeller: TextView = itemView.findViewById(R.id.tv_seller)
        val tvRating: TextView = itemView.findViewById(R.id.tv_rating)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_price)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Things)
    }

}