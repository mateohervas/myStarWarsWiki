package com.shadows.mystarwarswiki.ui.views.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shadows.mystarwarswiki.databinding.FragmentOpeningCrawlBinding
import com.shadows.mystarwarswiki.ui.views.binding.viewBinding

//this BottomSheetDialogFragment displays the opening crawl and resembles the beloved opening crawls of the star wars movies
class OpeningCrawlFragment(private val title: String, private val openingCrawl: String) :
    BottomSheetDialogFragment() {

    private val binding by viewBinding(FragmentOpeningCrawlBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val storesBinding = FragmentOpeningCrawlBinding.inflate(inflater,container,false)
        return storesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvFilmTitle.text = title
            tvOpeningCrawl.text = openingCrawl
        }

    }
}