package com.estudo.threadbootcamp

import com.estudo.model.AstroPeople
import com.google.gson.annotations.SerializedName

data class AstroResult(
    @SerializedName("message")
    val message: String,
    @SerializedName("number")
    val number: Int,
    @SerializedName("people")
    val people: List<AstroPeople>
)
