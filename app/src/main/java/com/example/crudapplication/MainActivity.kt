package com.example.crudapplication

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapplication.api.ApiConfig
import com.example.crudapplication.databinding.ActivityMainBinding
import com.example.crudapplication.model.ResponseGetKategori
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val rvKategori = findViewById<RecyclerView>(R.id.rv_kategori)

        ApiConfig.getService().getKategori().enqueue(object : Callback<ResponseGetKategori> {
            override fun onResponse(call: Call<ResponseGetKategori>, response: Response<ResponseGetKategori>) {
                if (response.isSuccessful){
                    val postResponse = response.body()
                    val dataKategori = postResponse?.data
                    val kategoriAdapter = KategoriAdapter(dataKategori)
                    rvKategori.apply{
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        setHasFixedSize(true)
                        kategoriAdapter.notifyDataSetChanged()
                        adapter = kategoriAdapter
                    }
                }
            }

            override fun onFailure(call: Call<ResponseGetKategori>, t: Throwable) {
                Log.d("MainActivity","Error di " + t.localizedMessage)
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}