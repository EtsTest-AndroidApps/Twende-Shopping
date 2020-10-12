package com.kanyideveloper.letsgoshopping

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.cart_items_row.*

class CheckoutActivity : AppCompatActivity(), ItemClickListener {

    private lateinit var mDatabase: FirebaseDatabase
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mReference: DatabaseReference
    private var cartList : ArrayList<CartItem>? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        mDatabase = FirebaseDatabase.getInstance()
        mRecyclerView = findViewById(R.id.cart_recycler)

        cartList = arrayListOf()
        mReference = mDatabase.getReference("cart_items")

        mReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot : DataSnapshot) {
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val item = i.getValue(CartItem::class.java)
                        cartList!!.add(item!!)
                    }

                    var sum = 0.0
                    var vat = 0.0
                    var total = 0.0
                    for (itm : CartItem in cartList!!) {
                        sum += itm.itemPrice.toDouble()
                        vat += itm.vat.toDouble()
                        total = sum + vat
                    }
                    subtotal_value.text = sum.toString()
                    vat_value.text = vat.toString()

                    total_value.text = total.toString()


                    //Initialise my adapter
                    mRecyclerView.adapter = CartItemsAdapter(applicationContext, cartList!!,this@CheckoutActivity)
                }
            }

            override fun onCancelled(error : DatabaseError) {
            }
        })
    }

    override fun increaseToCart(item : CartItem, position : Int) {
        Toast.makeText(applicationContext,"Add Button",Toast.LENGTH_SHORT).show()
    }

    override fun decreaseFromCart(item : CartItem, position : Int) {
        Toast.makeText(applicationContext,"Minus Button",Toast.LENGTH_SHORT).show()
    }

    override fun deleteFromCart(item : CartItem, position : Int) {
        Toast.makeText(applicationContext,"Delete Button",Toast.LENGTH_SHORT).show()
    }
}