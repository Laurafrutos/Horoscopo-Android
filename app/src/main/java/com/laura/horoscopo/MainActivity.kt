package com.laura.horoscopo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var horoscopeList: List<Horoscope> = Horoscope.getAll()

    lateinit var adapter: HoroscopeAdapter

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        recyclerView = findViewById(R.id.recyclerView)

        adapter = HoroscopeAdapter(horoscopeList,{ position ->

            val horoscope = horoscopeList[position]
            Toast.makeText(this,horoscope.id, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("HoroscopE_ID", horoscope.id)

            startActivity(intent)
        })


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)

        val searchMenuItem = menu.findItem(R.id.search_menu)
        val searchView = searchMenuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                horoscopeList = Horoscope.getAll().filter {
                    getString(it.name).search(newText) ||
                            getString(it.dates).search(newText)
                }
                adapter.updateData(horoscopeList)
                return true
            }
        })

        return true
    }
}