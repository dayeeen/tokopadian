package com.dicoding.tokopadian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val actionBar = supportActionBar
        actionBar?.title = "Detail Barang"
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val imgPhoto: ImageView = findViewById(R.id.img_item_photo)
        val tvName: TextView = findViewById(R.id.tv_item_name)
        val tvSeller: TextView = findViewById(R.id.tv_seller)
        val tvRating: TextView = findViewById(R.id.tv_rating)
        val tvPrice: TextView = findViewById(R.id.tv_price)
        val tvDescription: TextView = findViewById(R.id.tv_item_description)

        val name  = intent.getStringExtra(EXTRA_NAME)
        val photo = intent.getStringExtra(EXTRA_PHOTO)
        val price = intent.getStringExtra(EXTRA_PRICE)
        val rating = intent.getStringExtra(EXTRA_RATING)
        val desc = intent.getStringExtra(EXTRA_DESCRIPTION)
        val seller = intent.getStringExtra(EXTRA_SELLER)

        tvName.text = name
        Glide.with(this)
            .load(photo)
            .apply(RequestOptions())
            .into(imgPhoto)
        tvSeller.text = seller
        tvRating.text = rating
        tvPrice.text = price
        tvDescription.text = desc
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_PHOTO = "extra_photo"
        const val EXTRA_PRICE = "extra_price"
        const val EXTRA_RATING = "extra_rating"
        const val EXTRA_SELLER = "extra_seller"
        const val EXTRA_DESCRIPTION = "extra_description"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
