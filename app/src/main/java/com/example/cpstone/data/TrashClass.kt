package com.example.cpstone.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

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
