package com.kanyideveloper.letsgoshopping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class CheckoutActivity : AppCompatActivity() {

    private lateinit var mDatabase: FirebaseDatabase
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mReference: DatabaseReference
    private var cartList : ArrayList<CartItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        mDatabase = FirebaseDatabase.getInstance()
        mRecyclerView = findViewById(R.id.cart_recycler)

        cartList = arrayListOf()
        mReference = mDatabase.getReference("cart_items")

        mReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        val item = i.getValue(CartItem::class.java)
                        cartList!!.add(item!!)
                    }

                    //Initialise my adapter
                    mRecyclerView.adapter = CartItemsAdapter(applicationContext, cartList!!)
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}