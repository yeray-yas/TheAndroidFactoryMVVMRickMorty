package com.example.theandroidfactorymvvmrickmorty.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.theandroidfactorymvvmrickmorty.R
import com.example.theandroidfactorymvvmrickmorty.data.model.GetCharacterByIdResponse
import com.example.theandroidfactorymvvmrickmorty.data.remote.NetworkLayer.apiClient
import com.example.theandroidfactorymvvmrickmorty.data.remote.NetworkLayer.apiService
import com.example.theandroidfactorymvvmrickmorty.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.characterByIdLiveData.observe(this) { response ->
            if (response == null) {
                Toast.makeText(
                    this@MainActivity,
                    "Unsuccessful network call!!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }

       /*     with(binding) {
                nameTextView.text = response.name
                aliveTextView.text = response.status
                speciesTextView.text = response.species
                originTextView.text = response.origin.name

                if (response.gender.equals("male", true)) {
                    binding.genderImageView.setImageResource(R.drawable.ic_male_24)
                }else{
                    binding.genderImageView.setImageResource(R.drawable.ic_female_24)
                }
            }


            Picasso.get().load(response.image).into(binding.headerImageView)*/
        }
    }
}
