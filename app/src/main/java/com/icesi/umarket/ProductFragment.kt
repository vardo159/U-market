package com.icesi.umarket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.icesi.umarket.databinding.FragmentProductBinding
import com.icesi.umarket.model.Order
import com.icesi.umarket.model.Product
import com.icesi.umarket.model.ShoppingCar

class ProductFragment : Fragment() {
    private lateinit var _binding: FragmentProductBinding
    private val binding get() = _binding!!
    lateinit var product: Product
    lateinit var shoppingCar: ShoppingCar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductBinding.inflate(inflater,container,false)

        _binding.productName.text = product.name
        _binding.productInfo.text = product.description
        _binding.priceProduct.text = "$" + product.price.toString()
        loadImage()

        _binding.moreText.setOnClickListener {
            changeAmountText(true)
        }

        _binding.lessText.setOnClickListener {
            changeAmountText(false)
        }

        _binding.pedirBtn.setOnClickListener {
            shoppingCar = ShoppingCar("Valentina","3186775051")
            shoppingCar.orders.add(Order(1, "Pan"))
            shoppingCar.orders.add(Order(4, "Hamburguesa"))
            shoppingCar.orders.add(Order(3, "Pizza"))
            shoppingCar.orders.add(Order(5, "Arroz"))
            shoppingCar.orders.add(Order(2, "Pan de bono"))
            startActivity(shoppingCar.sendMessage())
        }
        // Inflate the layout for this fragment
        return _binding.root
    }

    fun changeAmountText(state: Boolean){
        var amount = Integer.parseInt(_binding.amountText.text.toString())
        if(state){
            amount++
        }else if(amount>1){
            amount--
        }
        _binding.amountText.text = ""+amount

    }

    fun loadImage(){
        if (product.imageID != "") {
            Firebase.storage.reference.child("product-images")
                .child(product.imageID!!).downloadUrl
                .addOnSuccessListener {
                    Glide.with(_binding.productImg).load(it).into(_binding.productImg)
                }
        }
    }

    companion object {
        fun newInstance() = ProductFragment()
    }
}