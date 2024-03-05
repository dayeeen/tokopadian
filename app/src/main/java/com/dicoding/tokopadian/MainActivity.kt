package com.dicoding.tokopadian

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.tokopadian.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var rvThings: RecyclerView
    private lateinit var binding: ActivityMainBinding

    private val list = ArrayList<Things>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvThings = findViewById(R.id.rv_things)
        rvThings.setHasFixedSize(true)

        list.addAll(listThings)
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            R.id.action_list -> {
                rvThings.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                // Mengatur GridLayoutManager dengan 2 kolom dan orientasi vertical
                val layoutManager = GridLayoutManager(this, 2)
                rvThings.layoutManager = layoutManager
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val listThings: ArrayList<Things>
        get() {
            val dataName = resources.getStringArray(R.array.data_name)
            val dataSeller = resources.getStringArray(R.array.data_seller)
            val dataPrice = resources.getStringArray(R.array.data_price)
            val dataPhoto = resources.getStringArray(R.array.data_photo)
            val dataRating = resources.getStringArray(R.array.data_rating)
            val dataDescription = resources.getStringArray(R.array.data_description)
            val listThings = ArrayList<Things>()
            for (i in dataName.indices) {
                val things = Things(dataName[i], dataSeller[i], dataPhoto[i], dataPrice[i], dataRating[i], dataDescription[i] )
                listThings.add(things)
            }
            return listThings
        }

    private fun showSelectedHero(things: Things) {
        Toast.makeText(this, "Kamu memilih " + things.name, Toast.LENGTH_SHORT).show()
    }

    private fun showRecyclerList() {
        rvThings.layoutManager = LinearLayoutManager(this)
        val listThingsAdapter = ListThingsAdapter(list)
        rvThings.adapter = listThingsAdapter
        listThingsAdapter.setOnItemClickCallback(object : ListThingsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Things) {
                showSelectedHero(data)
            }
        })
    }
}
