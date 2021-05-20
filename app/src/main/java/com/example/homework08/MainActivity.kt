package com.example.homework08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework08.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val carsList = mutableListOf<Car>()

    lateinit var adapter: CarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
        init()
    }

    private fun setData() {
        repeat(2) {
            carsList.add(Car("Nissan", "Skyline"))
            carsList.add(Car("Porsche", "Taycan"))
            carsList.add(Car("Mitsubishi", "Lancer", R.mipmap.ic_car))
            carsList.add(Car("Mercedes", "S-Class"))
            carsList.add(Car("Mercedes", "S-Class", R.mipmap.ic_car))
            carsList.add(Car("Toyota", "Supra", R.mipmap.ic_car))
            carsList.add(Car("Ferrari", "Enzo"))
            carsList.add(Car("Mercedes", "S-Class", R.mipmap.ic_car))
        }
    }

    private fun init() {
        adapter = CarAdapter(carsList, object : CarItemListener {
            override fun carItemLongClick(position: Int) {
                carsList.removeAt(position)
                adapter.notifyItemRemoved(position)
            }

        })
        binding.rvCars.layoutManager = GridLayoutManager(this, 2)
        binding.rvCars.adapter = adapter
    }
}