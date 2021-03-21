package com.shadows.mystarwarswiki.ui.views.favorites

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.shadows.core.models.local.Resource
import com.shadows.core.models.local.StarWarsCharacter
import com.shadows.mystarwarswiki.R
import com.shadows.mystarwarswiki.databinding.ActivityFavoriteCharactersBinding
import com.shadows.mystarwarswiki.ui.views.binding.viewBinding
import com.shadows.mystarwarswiki.ui.views.characterdetail.CharacterDetailActivity
import com.shadows.mystarwarswiki.ui.adapters.search.CharacterClickListener
import com.shadows.mystarwarswiki.ui.adapters.search.SearchResultsAdapter
import com.shadows.mystarwarswiki.utils.STAR_WARS_CHARACTER
import com.shadows.mystarwarswiki.utils.blockTouch
import com.shadows.mystarwarswiki.utils.show
import com.shadows.mystarwarswiki.utils.showDialog
import com.shadows.mystarwarswiki.ui.viewmodels.FavoriteCharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

//this activity is in charge of displaying the favorite characters saved by the user
@AndroidEntryPoint
class FavoriteCharactersActivity : AppCompatActivity(), CharacterClickListener {

    private val binding by viewBinding(ActivityFavoriteCharactersBinding::inflate)
    private val viewModel: FavoriteCharactersViewModel by viewModels()
    private val charactersAdapter by lazy {
        SearchResultsAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()
        setUpRecyclerView()
        observeForFavoriteCharacters()
        noResultsClick()

    }

    private fun setToolbar() = binding.toolbar.apply {
        btnBack.setOnClickListener {
            onBackPressed()
        }
        tvCharacterName.text = getString(R.string.your_favorites)
        tglFavorite.visibility = View.INVISIBLE
    }

    override fun onResume() {
        super.onResume()
        getFavoriteCharacters()
    }

    private fun getFavoriteCharacters() = viewModel.getFavoriteCharacters()

    private fun observeForFavoriteCharacters() = viewModel.favoriteCharacters.observe(this, {
        when (it) {
            is Resource.Error -> showError(it.error.stringResource)
            is Resource.Loading -> isLoading(it.isLoading)
            is Resource.Success -> showResults(it.data)
        }
    })

    //method that populates the adapter and hides the no results layout
    private fun showResults(list: List<StarWarsCharacter>) {
        binding.llNoResults.show(list.isEmpty())
        charactersAdapter.submitList(list)
    }

    //method that shows the error dialog
    private fun showError(stringResource: Int) {
        this.showDialog(getString(stringResource)) {
            getFavoriteCharacters()
        }
    }

    //method that shows the loading animation, hides the no results layout and blocks the touch of the user
    private fun isLoading(flag: Boolean) {
        binding.llNoResults.show(!flag)
        binding.loader.clLoader.show(flag)
        this.blockTouch(flag)
    }

    private fun noResultsClick() = binding.llNoResults.setOnClickListener {
        onBackPressed()
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

    override fun goToCharacterDetailActivity(starWarsCharacter: StarWarsCharacter) {
        val intent = Intent(this, CharacterDetailActivity::class.java).apply {
            putExtra(STAR_WARS_CHARACTER, starWarsCharacter)
        }
        startActivity(intent)
    }


}