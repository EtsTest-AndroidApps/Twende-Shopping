package com.kanyideveloper.letsgoshopping

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_item_details.*
import kotlin.math.roundToInt

class ItemDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)

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
        percentCut.text = "${(calculatePercentageOff(price!!,oldPrice!!))}% Off"
    }

    private fun calculatePercentageOff(new: String, old:String) : Int{
        val newValue = new.toDouble()
        val oldValue = old.toDouble()

        val x = (((oldValue-newValue)/oldValue)*100)
        return x.roundToInt()
    }
}