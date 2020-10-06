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
import kotlinx.android.synthetic.main.activity_item_details.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.cart_badge
import kotlinx.android.synthetic.main.activity_main.shopping_cart

class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mFirebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    var itemList : ArrayList<Item> ? = null

    private val sharedPrefFile = "kotlinsharedpreference"
    private lateinit var sharedPreferences: SharedPreferences
    private var sharedIdValue : Int = 0

    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mRecyclerView = findViewById(R.id.recyclerView)

        sharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        sharedIdValue = sharedPreferences.getInt(Utils.counter.toString(),0)
        checkCounter()

        shopping_cart.setOnClickListener {
            startActivity(Intent(applicationContext,CheckoutActivity::class.java))
        }

        itemList = arrayListOf()
        reference = mFirebaseDatabase.getReference("items")

        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    for (i in p0.children) {
                        val itm = i.getValue(Item::class.java)
                        itemList!!.add(itm!!)
                        shimmerFrameLayout.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                    }

                    val adapter = ItemsAdapter(applicationContext, itemList!!)
                    mRecyclerView.adapter = adapter
                }
            }
        })
    }

    private fun checkCounter(){
        val sharedIdValue = sharedPreferences.getInt(Utils.counter.toString(),0)
        if(sharedIdValue == 0){
            cart_badge.visibility = View.INVISIBLE
        }
        else if (sharedIdValue >= 1){
            cart_badge.text = sharedIdValue.toString()
            cart_badge.visibility = View.VISIBLE
        }
    }

    override fun onPause() {
        super.onPause()
        shimmerFrameLayout.stopShimmerAnimation()
    }


    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: Restarted")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Resumed")
        shimmerFrameLayout.startShimmerAnimation()
        checkCounter()
    }
}