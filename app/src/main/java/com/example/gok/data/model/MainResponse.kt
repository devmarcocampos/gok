package com.example.gok.data.model

import com.google.gson.annotations.SerializedName

data class MainResponse (
    @SerializedName("spotlight")
    var spotlight: ArrayList<Spotlight>,
    @SerializedName("products")
    var products: ArrayList<Product>,
    @SerializedName("cash")
    var cash: Cash
)

data class Spotlight (
    @SerializedName("name")
    var name: String,
    @SerializedName("bannerURL")
    var bannerURL: String,
    @SerializedName("description")
    var description: String
)

data class Product (
    @SerializedName("name")
    var name: String,
    @SerializedName("imageURL")
    var imageURL: String,
    @SerializedName("description")
    var description: String
)

data class Cash (
    @SerializedName("title")
    var title: String,
    @SerializedName("bannerURL")
    var bannerURL: String,
    @SerializedName("description")
    var description: String
)