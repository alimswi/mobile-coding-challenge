package com.alimswi.matic_challenge.Model

import com.google.gson.annotations.SerializedName

data class Owner (@SerializedName("login") var OwnerName:String?,
             @SerializedName("avatar_url") var OwnerImageUrl:String?)