package com.example.cpstone.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp
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
@Parcelize
data class ImageUpload(
    var photo: String ?= "",
    var indexClass: Int ?= 0,
    var timestamp: Long?=0,
    var result: String? = ""
):Parcelable
