package com.rubikans.challenge.ui.characterslist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rubikans.challenge.R
import com.rubikans.challenge.databinding.FragmentCharacterListBinding
import com.rubikans.challenge.domain.model.Character
import com.rubikans.challenge.extension.onQueryTextChanged
import com.rubikans.challenge.presentation.viewmodel.CharactersListViewModel
import com.rubikans.challenge.ui.base.BaseFragment
import com.rubikans.challenge.ui.characterslist.adapters.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListFragment :
    BaseFragment<FragmentCharacterListBinding, CharactersListViewModel>() {

    override val layoutId: Int = R.layout.fragment_character_list
    override val viewModel: CharactersListViewModel by viewModels()

    @Inject
    lateinit var characterAdapter: CharacterAdapter

    var searchView: SearchView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        installFilterMenu()

        initRecyclerView()
        characterAdapter.setItemClickListener { character ->
            findNavController().navigate(CharacterListFragmentDirections.actionToCharacterDetails(
                characterId = character.id
            ))
        }

        observeResults()
    }

    private fun initRecyclerView() = viewBinding.recyclerViewCharacters.apply {
        adapter = characterAdapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        layoutManager = linearLayoutManager
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!viewModel.isLoading) {
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == characterAdapter.itemCount - 1) {
                        viewModel.nextPage()
                    }
                }
            }
        })
    }

    private fun observeResults() {
        viewModel.loading.observe(viewLifecycleOwner) { loading: Boolean? ->
            handleLoading(loading == true && viewModel.pageNum == 1)
            if (loading == true && viewModel.pageNum != 1) {
                characterAdapter.showLoading()
            } else {
                characterAdapter.hideLoading()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { throwable: Throwable? ->
            throwable ?: return@observe

            val error = throwable.message ?: "Error"
            Timber.e(error)
            handleErrorMessage(error)


//            viewBinding.recyclerViewCharacters.isVisible = false
        }

        viewModel.characterList.observe(viewLifecycleOwner) { characters: List<Character>? ->
//            viewBinding.recyclerViewCharacters.isVisible = true
            characterAdapter.fullList = characters ?: ArrayList()
        }
    }

    private fun installFilterMenu() {
        activity?.addMenuProvider(menuProvider)

        viewModel.searchQuery.observe(viewLifecycleOwner) {
            characterAdapter.filter = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        searchView?.let {
            if (!it.isIconified) {
                it.isIconified = true
            }
            it.setOnQueryTextListener(null)
        }
        activity?.removeMenuProvider(menuProvider)
    }

    private val menuProvider = object : MenuProvider {

        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.search_menu, menu)

            val searchItem: MenuItem? = menu.findItem(R.id.action_search)
            searchView = searchItem?.actionView as? SearchView

            val pendingQuery = viewModel.searchQuery.value
            if (pendingQuery != null && pendingQuery.isNotEmpty()) {
                searchItem?.expandActionView()
                searchView?.setQuery(pendingQuery, false)
            }

            searchView?.onQueryTextChanged {
                viewModel.searchQuery.value = it
            }

        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            return false
        }

    }
}