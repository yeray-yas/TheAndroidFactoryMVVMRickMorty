package com.example.theandroidfactorymvvmrickmorty.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.theandroidfactorymvvmrickmorty.R
import com.example.theandroidfactorymvvmrickmorty.data.model.Character
import com.example.theandroidfactorymvvmrickmorty.databinding.ModelCharacterDetailBinding
import com.squareup.picasso.Picasso

class MainAdapter(private val charactersList: List<Character>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ModelCharacterDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(charactersList[position])
    }

    override fun getItemCount(): Int = charactersList.size

    inner class MainViewHolder(private val binding: ModelCharacterDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(character: Character) {
            val (image, gender) = addText(character)
            addGenderIcon(character, gender)
            addImage(character, image)
        }

        private fun addImage(character: Character, image: ImageView) {
            Picasso.get().load(character.image).into(image)
        }

        private fun addGenderIcon(character: Character, gender: ImageView) {
            if (character.gender.equals("male", true)) {
                gender.setImageResource(R.drawable.ic_male_24)
            } else {
                gender.setImageResource(R.drawable.ic_female_24)
            }
        }

        private fun addText(character: Character): Pair<ImageView, ImageView> {
            with(binding) {
                nameTextView.text = character.name
                aliveTextView.text = character.status
                originTextView.text = character.origin.name
                speciesTextView.text = character.species
                return Pair(headerImageView, genderImageView)
            }
        }
    }
}
