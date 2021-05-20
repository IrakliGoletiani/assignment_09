package com.example.homework08

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework08.databinding.ItemLayoutBinding
import com.example.homework08.databinding.ItemWithImageLayoutBinding

class CarAdapter(
    private val carsList: MutableList<Car>,
    private val carItemListener: CarItemListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val CAR_WITH_IMAGE = 1
        private const val CAR_WITHOUT_IMAGE = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == CAR_WITH_IMAGE) {
            val binding = ItemWithImageLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            CarsWithImageViewHolder(binding)
        } else {
            val binding =
                ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            CarsViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CarsViewHolder -> holder.onBind()
            is CarsWithImageViewHolder -> holder.onBind()
        }
    }

    override fun getItemCount() = carsList.size

    inner class CarsViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root), View.OnLongClickListener {

        fun onBind() {
            val modelWithoutImage = carsList[adapterPosition]
            binding.tvBrand.text = modelWithoutImage.brand
            binding.tvModel.text = modelWithoutImage.model

            binding.root.setOnLongClickListener(this)
        }

        override fun onLongClick(v: View?): Boolean {
            carItemListener.carItemLongClick(adapterPosition)
            return true
        }


    }

    inner class CarsWithImageViewHolder(private val binding: ItemWithImageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnLongClickListener {
        fun onBind() {
            val modelWithImage = carsList[adapterPosition]
            binding.tvBrand.text = modelWithImage.brand
            binding.tvModel.text = modelWithImage.model
            binding.ivCar.setImageResource(modelWithImage.image ?: R.mipmap.ic_car)

            binding.root.setOnLongClickListener(this)
        }

        override fun onLongClick(v: View?): Boolean {
           carItemListener.carItemLongClick(adapterPosition)
           return true
        }
    }

    override fun getItemViewType(position: Int): Int {
        val model = carsList[position]
        return if (model.image == null)
            CAR_WITHOUT_IMAGE
        else
            CAR_WITH_IMAGE
    }
}