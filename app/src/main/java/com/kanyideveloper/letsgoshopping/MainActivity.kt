package com.kanyideveloper.letsgoshopping

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mFirebase = FirebaseDatabase.getInstance().reference

        // It is a class provide by the FirebaseUI to make a query in the database to fetch appropriate data
        val options: FirebaseRecyclerOptions<Item> = FirebaseRecyclerOptions.Builder<Item>()
                .setQuery(mFirebase, Item::class.java)
                .build()

        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        val cart : ImageView = findViewById(R.id.shopping_cart)

        recyclerView.adapter = ItemsAdapter(applicationContext,options)

        cart.setOnClickListener {
            startActivity(Intent(applicationContext, CheckoutActivity::class.java))
        }
    }
}


