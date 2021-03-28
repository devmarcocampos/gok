package com.example.gok.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.gok.R
import com.example.gok.data.model.Product
import com.squareup.picasso.Picasso

class RecyclerProductsAdapter(
    var productsList: ArrayList<Product>
) : RecyclerView.Adapter<RecyclerProductsAdapter.ProductsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_products, parent, false)
        )
    }

    override fun getItemCount(): Int = productsList.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(productsList[position])
    }

    class ProductsViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ImageView>(R.id.imageViewProduct)

        fun bind(listItem: Product) {
            val picasso = Picasso.Builder(view.context).listener { _, _, exception ->
                exception?.printStackTrace()
                println("Picasso loading failed : ${exception?.message}")
                imageView.setImageResource(R.drawable.img_nao_encontrada)
            }.build()

            picasso
                .load(listItem.imageURL)
                .into(imageView)
        }
    }
}