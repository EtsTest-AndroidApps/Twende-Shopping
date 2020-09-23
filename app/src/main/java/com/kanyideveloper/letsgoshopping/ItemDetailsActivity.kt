package com.kanyideveloper.letsgoshopping

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_item_details.*
import kotlin.math.roundToInt

class ItemDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)




        val itemImage = intent.getStringExtra("ITEM_IMAGE")
        val item_Name = intent.getStringExtra("ITEM_NAME")
        val item_Price = intent.getStringExtra("ITEM_PRICE")
        val item_OldPrice = intent.getStringExtra("ITEM_OLD_PRICE" )

        itemName.text = item_Name
        itemPrice.text = "KSh ${item_Price.toString()}"
        itemOldPrice.text = "KSh ${item_OldPrice.toString()}"
        itemOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        percentCut.text = "${(calculatePercentageOff(item_Price!!,item_OldPrice!!))}% Off"
    }

    fun calculatePercentageOff(new: String, old:String) : Int{
        val newValue = new.toDouble()
        val oldValue = old.toDouble()

        var x = (((oldValue-newValue)/oldValue)*100)
        return x.roundToInt()
    }
}