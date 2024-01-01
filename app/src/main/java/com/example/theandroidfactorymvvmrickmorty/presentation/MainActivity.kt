package com.example.theandroidfactorymvvmrickmorty.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theandroidfactorymvvmrickmorty.data.model.Character
import com.example.theandroidfactorymvvmrickmorty.databinding.ActivityMainBinding
import com.example.theandroidfactorymvvmrickmorty.utils.ScreenState
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val viewModel: CharacterListViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeCharactersLiveData()
    }

    private fun observeCharactersLiveData() {
        viewModel.charactersLiveData.observe(this) { state ->
            handleCharactersState(state)
        }
    }

    private fun handleCharactersState(state: ScreenState<List<Character>?>) {
        when (state) {
            is ScreenState.Error -> showErrorState(state.message)
            is ScreenState.Loading -> showLoadingState()
            is ScreenState.Success -> showSuccessState(state.data)
        }
    }

    private fun showErrorState(errorMessage: String?) {
        binding.progressBar.visibility = View.GONE
        errorMessage?.let {
            val rootView = binding.progressBar.rootView
            Snackbar.make(rootView, errorMessage, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun showLoadingState() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showSuccessState(characters: List<Character>?) {
        binding.progressBar.visibility = View.GONE
        characters?.let { displayCharactersRecyclerView(it) }
    }

    private fun displayCharactersRecyclerView(characters: List<Character>) {
        val adapter = MainAdapter(characters)
        val recyclerView = binding.charactersRv
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
