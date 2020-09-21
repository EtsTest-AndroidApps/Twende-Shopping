package com.kanyideveloper.letsgoshopping

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)

        recyclerView.adapter = ItemsAdapter(applicationContext).apply { addItemList(getMockItems()) }

    }

    fun getMockItems(): List<Item> {

        return listOf(
                Item(resources.getDrawable(R.drawable.ajab),"Ajab Maize Flour 2Kg",120),
                Item(resources.getDrawable(R.drawable.chain),"Lavari - Stainless Steel Heavy Curb Chain Necklace",250),
                Item(resources.getDrawable(R.drawable.crisps),"Walkers Ready Salted Crisps 32.5 g (48 Packs)",100),
                Item(resources.getDrawable(R.drawable.cups),"Blue rim Ocean Wave White ceramic cups & saucers x 4",1040),
                Item(resources.getDrawable(R.drawable.fridge),"Euhomy Mini Fridge with Freezer, 3.2 Cu.Ft Compact Refrige",120000),
                Item(resources.getDrawable(R.drawable.head_cap),"Promotional Men's Head Cap",250),
                Item(resources.getDrawable(R.drawable.jogoo),"Jogoo Maize Meal 2KG",120),
                Item(resources.getDrawable(R.drawable.laptop),"Lenovo V15 Intel",70000),
                Item(resources.getDrawable(R.drawable.plates),"Amuse- Professional Gourmet Porcelain Dinner Plate",840),
                Item(resources.getDrawable(R.drawable.printer),"Canon TS3150",65000),
                Item(resources.getDrawable(R.drawable.ream_paper),"China Ream Paper China, China Ream Paper China Manufa",470),
               /* Item(resources.getDrawable(R.drawable.shirt),"Men's Equator Shirt",670),*/
                Item(resources.getDrawable(R.drawable.shoe_1),"Nike's Adapt BB",4500),
                Item(resources.getDrawable(R.drawable.shoe_2),"Men Steel Toe Trainers Lightweight Work ",1200),
                Item(resources.getDrawable(R.drawable.soda),"Coke Soda - 1.25 Litres",107),
                Item(resources.getDrawable(R.drawable.stapler),"Swingline Stapler",437),
                Item(resources.getDrawable(R.drawable.t_shirt),"Round-necked T-shirt Slim Fit",999),
                Item(resources.getDrawable(R.drawable.tecno_camon_15),"Tecno Camon 15",17000),
                Item(resources.getDrawable(R.drawable.tecno_spark_5),"Tecno Spark 5",13000),
                Item(resources.getDrawable(R.drawable.trouser),"Moss Covent Garden Tailored Fit Houndstooth Trouser",1200),
                Item(resources.getDrawable(R.drawable.tv),"X80H Series 4K Ultra HD LCD TV | Sony Asia Pacific",120000),
        )
    }

}


