package com.shadows.mystarwarswiki.ui.views.search

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.shadows.core.models.local.Resource
import com.shadows.core.models.local.StarWarsCharacter
import com.shadows.mystarwarswiki.R
import com.shadows.mystarwarswiki.databinding.ActivitySearchBinding
import com.shadows.mystarwarswiki.ui.adapters.search.CharacterClickListener
import com.shadows.mystarwarswiki.ui.views.binding.viewBinding
import com.shadows.mystarwarswiki.ui.views.characterdetail.CharacterDetailActivity
import com.shadows.mystarwarswiki.ui.views.favorites.FavoriteCharactersActivity
import com.shadows.mystarwarswiki.ui.adapters.search.SearchResultsAdapter
import com.shadows.mystarwarswiki.utils.*
import com.shadows.mystarwarswiki.ui.viewmodels.CharacterSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity(), CharacterClickListener {

    private val binding by viewBinding(ActivitySearchBinding::inflate)
    private val viewModel: CharacterSearchViewModel by viewModels()
    private val charactersAdapter by lazy {
        SearchResultsAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpRecyclerView()
        searchCharacterByName()
        observeForSearchResults()
        goToFavorites()
    }

    private fun searchCharacterByName() =
        binding.svCharacter.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    this@SearchActivity.showShortToast(getString(R.string.hint_empty_search))
                } else {
                    viewModel.getStarWarsCharacter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

    //method that will observe the results obtained by the API and show an error,
    // show the results or show the loading bar depending on the event
    private fun observeForSearchResults() = viewModel.characters.observe(this, {
        when (it) {
            is Resource.Error -> {
                showError(it.error.stringResource)
            }
            is Resource.Loading -> {
                isLoading(it.isLoading)
            }
            is Resource.Success -> {
                showResults(it.data)
            }
        }
    })

    //method that populates the adapter and hides the no results layout
    private fun showResults(list: List<StarWarsCharacter>) {
        binding.llNoResults.show(list.isEmpty())
        charactersAdapter.submitList(list)
        if (list.isEmpty()) {
            binding.tvNoResults.text = getString(R.string.no_results)
        }
    }

    //method that shows the error dialog
    private fun showError(stringResource: Int) {
        this.showDialog(getString(stringResource)) {
            val query = binding.svCharacter.query.toString()
            viewModel.getStarWarsCharacter(query)
        }
    }

    //method that shows the loading animation, hides the no results layout and blocks the touch of the user
    private fun isLoading(flag: Boolean) {
        binding.llNoResults.show(!flag)
        binding.loader.clLoader.show(flag)
        this.blockTouch(flag)
    }

    private fun setUpRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        binding.rvSearchResults.apply {
            adapter = charactersAdapter
            layoutManager = linearLayoutManager
        }
    }

    private fun goToFavorites() = binding.fltFavorites.setOnClickListener {
        val intent = Intent(this, FavoriteCharactersActivity::class.java)
        startActivity(intent)
    }

    override fun goToCharacterDetailActivity(starWarsCharacter: StarWarsCharacter) {
        val intent = Intent(this, CharacterDetailActivity::class.java).apply {
            putExtra(STAR_WARS_CHARACTER, starWarsCharacter)
        }
        startActivity(intent)
    }
}