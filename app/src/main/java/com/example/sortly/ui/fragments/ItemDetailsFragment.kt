package com.example.sortly.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sortly.R
import com.example.sortly.data.model.Items
import com.example.sortly.databinding.FragmentDetailsBinding
import com.example.sortly.utils.formatDateTime
import com.example.sortly.utils.getTotalPrice
import com.example.sortly.utils.setSpannableText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailsFragment: Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val ITEM = "item"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item: Items? = arguments?.getParcelable(ITEM)
        item?.let {
            updateUi(it)
        }
    }

    private fun updateUi(item: Items) {
        with(binding) {
            tvItemName.text = item.name

            setSpannableText(tvItemType,
                getString(R.string.type), resources.getColor(R.color.black),
                item.type ?: "", resources.getColor(R.color.grey))

            setSpannableText(tvSid,
                getString(R.string.sid), resources.getColor(R.color.black),
                item.sid ?: "", resources.getColor(R.color.grey))

            if (!item.quantity.isNullOrEmpty() && item.type == ITEM) {
                setSpannableText(tvItemQuantity,
                    getString(R.string.quntity), resources.getColor(R.color.black),
                    item.quantity, resources.getColor(R.color.grey))
            } else {
                tvItemQuantity.visibility = View.GONE
            }

            if (!item.price.isNullOrEmpty() && item.type == ITEM) {
                setSpannableText(tvItemPrice,
                    getString(R.string.total_price), resources.getColor(R.color.black),
                    getTotalPrice(item.quantity, item.price), resources.getColor(R.color.grey))
            } else {
                tvItemPrice.visibility = View.GONE
            }

            if (!item.notes.isNullOrEmpty()) {
                setSpannableText(tvNotes,
                    getString(R.string.notes), resources.getColor(R.color.black),
                    item.notes, resources.getColor(R.color.grey))
            } else {
                tvNotes.visibility = View.GONE
            }

            if (!item.createdAt.isNullOrEmpty()) {
                setSpannableText(tvCreatedDate,
                    getString(R.string.date), resources.getColor(R.color.black),
                    formatDateTime(item.createdAt), resources.getColor(R.color.grey))
            } else {
                tvCreatedDate.visibility = View.GONE
            }

            if (!item.tagNames.isNullOrEmpty()) {
                setSpannableText(tvTags,
                    getString(R.string.tags), resources.getColor(R.color.black),
                    item.tagNames.joinToString(","), resources.getColor(R.color.grey))
            } else {
                tvTags.visibility = View.GONE
            }

        }
    }

}