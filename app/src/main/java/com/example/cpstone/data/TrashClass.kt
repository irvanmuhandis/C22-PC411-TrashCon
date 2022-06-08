package com.example.cpstone.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class TrashClass(
    var name: String,
    var photo: Int,
    var description: String,
    var process: String
):Parcelable

data class HeaderTutor(
    var text :String,
    var photo : Int
)

data class ImageUpload(
    var photo: Int,
    var date: Date,
    var name: String,
    var result : String
)
