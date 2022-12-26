package com.omtaem.foodapp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.omtaem.foodapp.R
import com.omtaem.foodapp.databinding.FragmentDetailBinding
import com.omtaem.foodapp.domain.presentation.data.local.entity.FavoritesEntity
import dagger.hilt.android.AndroidEntryPoint
import org.jsoup.Jsoup


@AndroidEntryPoint
class DetailFragment : Fragment() {


    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val navigationArgs: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        bindUI()
        clickFavoriteButton()
        return binding.root
    }

    private fun getArgsResult() = navigationArgs.result

    private fun bindUI() {
        isFav()
        val result = navigationArgs.result
        binding.ivFood.load(result.image) {
            crossfade(600)
            error(R.drawable.ic_launcher_foreground)
        }
        binding.tvTitle.text = result.title
        binding.summaryTextView.text = Jsoup.parse(result.summary).text()
    }

    private fun isFav() {
        viewModel.isFav(getArgsResult())
        viewModel.favLiveData.observe(viewLifecycleOwner) {
            if (viewModel.favLiveData.value == true) {
                binding.imageButtonFav.setBackgroundResource(R.drawable.ic_favorite)
            } else {
                binding.imageButtonFav.setBackgroundResource(R.drawable.ic_favorite_outlined)
            }
        }
    }

    private fun clickFavoriteButton() = binding.imageButtonFav.setOnClickListener {
        if (viewModel.favLiveData.value == true) {
            viewModel.favLiveData.value = false
            viewModel.deleteFavoriteNews(getArgsResult())
            Toast.makeText(
                requireContext(),
                "Removed from favorite",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            viewModel.favLiveData.value = true
            Toast.makeText(
                requireContext(),
                "Added to favorite",
                Toast.LENGTH_SHORT
            ).show()
            viewModel.addFavoriteNews(FavoritesEntity(result = getArgsResult(), id = 0))
        }
    }
}