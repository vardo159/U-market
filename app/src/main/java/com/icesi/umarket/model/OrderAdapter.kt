package com.icesi.umarket.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.icesi.umarket.ConsumerMainOverviewFragment
import com.icesi.umarket.R
import java.io.File

class OrderAdapter: RecyclerView.Adapter<OrderViewHolder>()  {

    private val orders = ArrayList<Order>()
    var showNoOrder: Boolean = true
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.order_row,parent,false)
        val orderViewHolder = OrderViewHolder(view)
        return orderViewHolder
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.bindOrder(order)
    }

    fun showOrders() {
        showNoOrder = false
    }

    override fun getItemCount(): Int {
        return orders.size
    }


    fun clear() {
        val size = orders.size
        orders.clear()
        notifyItemRangeRemoved(0,size)
    }

    fun addOrder(order: Order){
        orders.add(order)
        notifyItemInserted(orders.size-1)
    }

    private fun setImageBitmap(image: String?): Bitmap? {
        val file = image?.let { File(it) }
        val bitmap = BitmapFactory.decodeFile(file?.path)
        val thumbnail = Bitmap.createScaledBitmap(bitmap, bitmap.width/4, bitmap.height/4, true)
        return thumbnail
    }
}
