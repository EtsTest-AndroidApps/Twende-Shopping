package com.kanyideveloper.letsgoshopping


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var nFirebaseDatabase: FirebaseDatabase
    var itemList : ArrayList<Item> ? = null
    private var sharedIdValue : Int = 0
    private lateinit var reference: DatabaseReference
    private val TAG = "MainActivity"

    private val sharedPrefFile = "kotlinsharedpreference"
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nFirebaseDatabase = FirebaseDatabase.getInstance()
        mRecyclerView = findViewById(R.id.recyclerView)

        sharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        sharedIdValue = sharedPreferences.getInt(Utils.counter.toString(),0)

        cart_badge.text = sharedIdValue.toString()
        cart_badge.visibility = View.VISIBLE

        shopping_cart.setOnClickListener {
            startActivity(Intent(applicationContext,CheckoutActivity::class.java))
        }

        itemList = arrayListOf()
        reference = FirebaseDatabase.getInstance().getReference("items")


        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    for (i in p0.children) {
                        val itm = i.getValue(Item::class.java)
                        itemList!!.add(itm!!)
                        Log.d(TAG, "onDataChange: $itemList.toString()")
                    }

                    val adapter = ItemsAdapter(applicationContext, itemList!!)
                    mRecyclerView.adapter = adapter
                }
            }
        })
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: stoped")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: restarted")
        cart_badge.text = sharedIdValue.toString()
        cart_badge.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: resume")
    }
}