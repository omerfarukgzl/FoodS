package com.omtaem.foodapp.domain.presentation.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.omtaem.foodapp.R
import com.omtaem.foodapp.data.local.entity.FavoritesEntity
import com.omtaem.foodapp.databinding.ItemFavoriteFoodBinding

class FavoriteFoodsAdapter(

) : RecyclerView.Adapter<FavoriteFoodsAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: ItemFavoriteFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            favorite: FavoritesEntity,
        ) = binding.apply {
            binding.recipeImageView.load(favorite.result.image) {
                crossfade(600)
                error(R.drawable.ic_launcher_foreground)
            }
            titleTextView.text = favorite.result.title
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<FavoritesEntity>() {
        override fun areItemsTheSame(oldItem: FavoritesEntity, newItem: FavoritesEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: FavoritesEntity,
            newItem: FavoritesEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val diffList = AsyncListDiffer(this, diffUtil)

    var favorites: List<FavoritesEntity>
        get() = diffList.currentList
        set(value) = diffList.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemFavoriteFoodBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(favorites[position])
    }

    override fun getItemCount() = favorites.size
}