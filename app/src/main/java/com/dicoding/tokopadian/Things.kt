package com.dicoding.tokopadian

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Things(
    var name: String,
    var photo: String,
    var price: String,
    var seller: String,
    var rating: String,
    var description: String,
) : Parcelable
