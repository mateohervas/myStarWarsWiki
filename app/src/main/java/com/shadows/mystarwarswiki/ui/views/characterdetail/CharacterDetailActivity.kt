package com.shadows.mystarwarswiki.ui.views.characterdetail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.shadows.core.models.local.*
import com.shadows.core.utils.NOT_AVAILABLE
import com.shadows.mystarwarswiki.R
import com.shadows.mystarwarswiki.databinding.ActivityCharacterDetailBinding
import com.shadows.mystarwarswiki.ui.views.binding.viewBinding
import com.shadows.mystarwarswiki.ui.adapters.films.CharacterFilmsAdapter
import com.shadows.mystarwarswiki.ui.adapters.films.FilmClickLister
import com.shadows.mystarwarswiki.utils.*
import com.shadows.mystarwarswiki.ui.viewmodels.CharactersDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailActivity : AppCompatActivity(), FilmClickLister {

    private val binding by viewBinding(ActivityCharacterDetailBinding::inflate)
    private val viewModel: CharactersDetailViewModel by viewModels()
    private lateinit var starWarsCharacter: StarWarsCharacter
    private val species: String by lazy {
        if (starWarsCharacter.species.isNotEmpty()) starWarsCharacter.species.first() else ""
    }
    private val planet: String by lazy {
        if (starWarsCharacter.homeWorld == NOT_AVAILABLE) "" else starWarsCharacter.homeWorld
    }
    private val filmsList by lazy {
        if (starWarsCharacter.films.isNotEmpty()) starWarsCharacter.films else listOf("http")
    }
    private val filmsAdapter by lazy {
        CharacterFilmsAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        goBack()
        starWarsCharacter = intent.getParcelableExtra(STAR_WARS_CHARACTER)

        favoriteButtonBehavior()
        checkIfIsFavorite()
        observeForFavoriteChanges()

        setUpRecyclerView()
        setCharacterPersonalInfo()

        getDemographicInformation()
        observeForDemographicDetails()

    }

    private fun getDemographicInformation() =
        viewModel.getDemographicInformation(species, planet, filmsList)

    private fun checkIfIsFavorite() = viewModel.checkIfIsFavorite(starWarsCharacter.url)


    private fun setCharacterPersonalInfo() = binding.apply {
        toolbar.tvCharacterName.text = starWarsCharacter.name
        tvBithYearValue.text = starWarsCharacter.birthYear
        val heightInCm = starWarsCharacter.heightInCm
        val heightInInches = starWarsCharacter.heightInInches
        tvHeightValue.text = getString(R.string.height_cm_in, heightInCm, heightInInches)
    }


    private fun goBack() = binding.toolbar.btnBack.setOnClickListener {
        onBackPressed()
    }


    private fun observeForDemographicDetails() = viewModel.demographicDetails.observe(this, {
        when (it) {
            is Resource.Error -> showError(it.error.stringResource)
            is Resource.Loading -> isLoading(it.isLoading)
            is Resource.Success -> {
                if (it.data.species != null) {
                    setSpeciesView(it.data.species!!)
                }
                if (it.data.planet != null) {
                    setPlanetView(it.data.planet!!)
                }
                if (it.data.films.isNotEmpty()) {
                    filmsAdapter.submitList(it.data.films)
                }
            }
        }
    })

    private fun observeForFavoriteChanges() = viewModel.favoriteCharacter.observe(this, {
        when (it) {
            is Resource.Error -> showError(it.error.stringResource)
            is Resource.Loading -> isLoading(it.isLoading)
            is Resource.Success -> checkToggle(it.data)
        }
    })

    private fun checkToggle(check: Boolean) {
        binding.toolbar.tglFavorite.isChecked = check
    }

    private fun favoriteButtonBehavior() =
        binding.toolbar.tglFavorite.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                viewModel.saveFavoriteCharacter(starWarsCharacter)
            } else {
                viewModel.deleteFavoriteCharacter(starWarsCharacter)
            }
        }


    private fun isLoading(flag: Boolean) {
        binding.loader.clLoader.show(flag)
        this.blockTouch(flag)
    }

    private fun showError(stringResource: Int) {
        this.showDialog(getString(stringResource)) {
            viewModel.getDemographicInformation(species, planet, filmsList)
        }

    }

    private fun setSpeciesView(starWarsSpecies: StarWarsSpecies) = binding.apply {
        tvSpeciesNameValue.text = starWarsSpecies.name
        tvSpeciesLanguageValue.text = starWarsSpecies.language
    }


    private fun setPlanetView(starWarsPlanet: StarWarsPlanet) = binding.apply {
        tvPlanetNameValue.text = starWarsPlanet.name
        tvPlanetPopulationValue.text = starWarsPlanet.population
    }


    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        binding.rvFilms.apply {
            this.layoutManager = layoutManager
            this.adapter = filmsAdapter
        }
    }

    override fun onFilmClickListener(starWarsFilm: StarWarsFilm) {
        val openingCrawlFragment =
            OpeningCrawlFragment(starWarsFilm.title, starWarsFilm.openingCrawl)
        openingCrawlFragment.show(supportFragmentManager, starWarsFilm.title)
    }
}