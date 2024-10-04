package com.example.sortly.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sortly.R
import com.example.sortly.data.model.Items
import com.example.sortly.databinding.FragmentListBinding
import com.example.sortly.ui.UiStates
import com.example.sortly.ui.adapter.ItemsListAdapter
import com.example.sortly.viewmodel.ItemsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemsListFragment : Fragment() {

    private val viewModel: ItemsViewModel by viewModels()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        setUpObservers()
        viewModel.fetchItems()
    }

    private fun initUi() {
        val clickListener: (Items) -> Unit = { item: Items ->
            navigateToDetails(item)
        }
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ItemsListAdapter(context = context, items = listOf(), itemClick = clickListener)
        }
    }

    private fun setUpObservers() {
        viewModel.items.observe(viewLifecycleOwner) {
            when(it) {
                is UiStates.Loading -> {
                    if (!viewModel.isDataCached()) {
                        showLoader(true)
                        showErrorMessage(errorMessage = "", shouldShowError = false)
                        updateList(emptyList())
                    }
                }
                is UiStates.Error -> {
                    if (!viewModel.isDataCached()) {
                        showLoader(false)
                        showErrorMessage(errorMessage = it.errorMessage, shouldShowError = true)
                        updateList(emptyList())
                    }
                }
                is UiStates.Success -> {
                    showLoader(false)
                    showErrorMessage(errorMessage = "", shouldShowError = false)
                    updateList(it.data)
                }
            }
        }
    }

    private fun navigateToDetails(item: Items) {
        findNavController().navigate(
            R.id.action_itemList_to_itemDetails,
            Bundle().apply {
                putParcelable("item", item)
            }
        )
    }

    private fun updateList(data: List<Items>) {
        binding.apply {
            (rvProducts.adapter as? ItemsListAdapter)?.updateItems(data)
        }
    }

    private fun showErrorMessage(errorMessage: String, shouldShowError: Boolean) {
        binding.tvErrorMessage.text = errorMessage
        binding.tvErrorMessage.visibility =  if (shouldShowError) { View.VISIBLE } else { View.GONE }
    }

    private fun showLoader(shouldShowLoader: Boolean) {
        binding.loading.visibility = if (shouldShowLoader) { View.VISIBLE } else { View.GONE }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}