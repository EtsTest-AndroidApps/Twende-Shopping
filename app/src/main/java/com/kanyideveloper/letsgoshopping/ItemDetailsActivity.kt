package com.kanyideveloper.letsgoshopping

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_item_details.*
import kotlin.math.roundToInt

class ItemDetailsActivity : AppCompatActivity() {

    private val sharedPrefFile = "kotlinsharedpreference"


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)

        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.reference

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        val image = intent.getStringExtra("ITEM_IMAGE")
        val name = intent.getStringExtra("ITEM_NAME")
        val price = intent.getStringExtra("ITEM_PRICE")
        val oldPrice = intent.getStringExtra("ITEM_OLD_PRICE")

        Glide.with(applicationContext)
                .load(image)
                .into(itemImage)

        itemName.text = name
        itemPrice.text = "KSh $price"
        itemOldPrice.text = "KSh $oldPrice"
        itemOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        percentCut.text = "-${(calculatePercentageOff(price!!, oldPrice!!))}% Off"

        add_to_cart.setOnClickListener {
            val cartItems = CartItem(image.toString(), name.toString(), price)
            myRef.child("cart_items").push().setValue(cartItems)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt(Utils.counter.toString(), sharedPreferences.getInt(Utils.counter.toString(),0)+1)
            editor.apply()
            editor.commit()
            val sharedIdValue = sharedPreferences.getInt(Utils.counter.toString(),0)
            cart_badge.text = sharedIdValue.toString()
        }
    }

    private fun calculatePercentageOff(new: String, old: String) : Int{
        val newValue = new.toDouble()
        val oldValue = old.toDouble()

        val x = (((oldValue-newValue)/oldValue)*100)
        return x.roundToInt()
    }

}
