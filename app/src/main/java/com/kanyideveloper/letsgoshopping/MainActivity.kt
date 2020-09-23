package com.kanyideveloper.letsgoshopping

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        val cart : ImageView = findViewById(R.id.shopping_cart)

        recyclerView.adapter = ItemsAdapter(applicationContext).apply { addItemList(getMockItems()) }

        cart.setOnClickListener {
            startActivity(Intent(applicationContext,ItemDetailsActivity::class.java))
        }

    }

    fun getMockItems(): List<Item> {

        return listOf(
                Item(resources.getDrawable(R.drawable.ajab),"Ajab Maize Flour 2Kg",120,150),
                Item(resources.getDrawable(R.drawable.chain),"Lavari - Stainless Steel Heavy Curb Chain Necklace",250,330),
                Item(resources.getDrawable(R.drawable.crisps),"Walkers Ready Salted Crisps 32.5 g (48 Packs)",100,120),
                Item(resources.getDrawable(R.drawable.cups),"Blue rim Ocean Wave White ceramic cups & saucers x 4",1040,1500),
                Item(resources.getDrawable(R.drawable.fridge),"Euhomy Mini Fridge with Freezer, 3.2 Cu.Ft Compact Refrige",120000,150000),
                Item(resources.getDrawable(R.drawable.head_cap),"Promotional Men's Head Cap",250,337),
                Item(resources.getDrawable(R.drawable.jogoo),"Jogoo Maize Meal 2KG",120,149),
                Item(resources.getDrawable(R.drawable.laptop),"Lenovo V15 Intel",70000,99999),
                Item(resources.getDrawable(R.drawable.plates),"Amuse- Professional Gourmet Porcelain Dinner Plate",840,1000),
                Item(resources.getDrawable(R.drawable.printer),"Canon TS3150",65000,76000),
                Item(resources.getDrawable(R.drawable.ream_paper),"China Ream Paper China, China Ream Paper China Manufa",470,530),
               /* Item(resources.getDrawable(R.drawable.shirt),"Men's Equator Shirt",670),*/
                Item(resources.getDrawable(R.drawable.shoe_1),"Nike's Adapt BB",4500,5999),
                Item(resources.getDrawable(R.drawable.shoe_2),"Men Steel Toe Trainers Lightweight Work ",1200,1499),
                Item(resources.getDrawable(R.drawable.soda),"Coke Soda - 1.25 Litres",107,149),
                Item(resources.getDrawable(R.drawable.stapler),"Swingline Stapler",437,500),
                Item(resources.getDrawable(R.drawable.t_shirt),"Round-necked T-shirt Slim Fit",999,1400),
                Item(resources.getDrawable(R.drawable.tecno_camon_15),"Tecno Camon 15",17000,20999),
                Item(resources.getDrawable(R.drawable.tecno_spark_5),"Tecno Spark 5",13000,14999),
                Item(resources.getDrawable(R.drawable.trouser),"Moss Covent Garden Tailored Fit Houndstooth Trouser",1200,1600),
                Item(resources.getDrawable(R.drawable.tv),"X80H Series 4K Ultra HD LCD TV | Sony Asia Pacific",120000,149999),
        )
    }

}


