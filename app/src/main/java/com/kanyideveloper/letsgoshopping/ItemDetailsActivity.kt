package com.kanyideveloper.letsgoshopping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ItemDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)

        val oldPrice: TextView = findViewById(R.id.itemOldPrice)
    }
}