package com.icesi.umarket.model.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.icesi.umarket.consumer.ConsumerMainOverviewFragment
import com.icesi.umarket.R
import com.icesi.umarket.model.Product
import com.icesi.umarket.model.holders.ProductViewHolder
import java.io.File

class ProductAdapter: RecyclerView.Adapter<ProductViewHolder>() {

    private val products = ArrayList<Product>()
    lateinit var onProductObserver: ConsumerMainOverviewFragment.SellerObserver

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.product_row,parent,false)
        val productViewHolder = ProductViewHolder(view)
        productViewHolder.onProductObserver = onProductObserver
        return productViewHolder
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val productn = products[position]

        holder.bindProduct(productn)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun clear() {
        val size = products.size
        products.clear()
        notifyItemRangeRemoved(0,size)
    }

    fun addProduct(product: Product){
        products.add(product)
        notifyItemInserted(products.size-1)
    }
}