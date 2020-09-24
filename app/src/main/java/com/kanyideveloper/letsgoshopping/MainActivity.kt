package com.kanyideveloper.letsgoshopping


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query


class MainActivity : AppCompatActivity() {


    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var adapter: ItemsAdapter
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("items")
        mRecyclerView = findViewById(R.id.recyclerView)

        val query: Query = FirebaseDatabase.getInstance()
                .reference
                .child("items")

        val options: FirebaseRecyclerOptions<Item> = FirebaseRecyclerOptions.Builder<Item>()
                .setQuery(query, Item::class.java)
                .build()
        Log.d(TAG, "onCreate: nnn ${options.toString()}")
        adapter = ItemsAdapter(applicationContext,options)

        mRecyclerView.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
            adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
            adapter.stopListening()
    }

}