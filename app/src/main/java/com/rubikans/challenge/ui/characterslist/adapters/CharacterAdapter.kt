package com.rubikans.challenge.ui.characterslist.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rubikans.challenge.databinding.ItemCharacterListBinding
import com.rubikans.challenge.databinding.ItemFooterBinding
import com.rubikans.challenge.domain.model.Character
import com.rubikans.challenge.ui.base.BaseAdapter
import javax.inject.Inject

class CharacterAdapter @Inject constructor(
) : BaseAdapter<Character>() {

    companion object {
        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_FOOTER = 1
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    override val differ = AsyncListDiffer(this, diffCallback)

    override fun getItemViewType(position: Int): Int {
        return if (list[position].id in listOf(-1, -2)) VIEW_TYPE_FOOTER else VIEW_TYPE_ITEM
    }

    fun showLoading() {
        list = (list + Character(id = -1))
    }

    fun hideLoading() {
        list = (list - Character(id = -1))
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_ITEM) {
            val binding = ItemCharacterListBinding.inflate(inflater, parent, false)
            CharacterViewHolder(binding)
        } else {
            val binding = ItemFooterBinding.inflate(inflater, parent, false)
            FooterViewHolder(binding)
        }
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterListBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<Character> {

        override fun bind(item: Character, position: Int) {
            binding.item = item
            binding.cardView.setOnClickListener { onItemClickListener(item) }
        }
    }

    inner class FooterViewHolder(private val binding: ItemFooterBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<Character> {

        override fun bind(item: Character, position: Int) {
            when (item.id) {
                -1 -> {
                    binding.progress.isVisible = true
                    binding.textError.isVisible = false
                    binding.btnRetry.isVisible = false
                }
                -2 -> {
                    binding.progress.isVisible = false
                    binding.textError.isVisible = true
                    binding.btnRetry.isVisible = true
                }
                else -> {
                    binding.progress.isVisible = false
                    binding.textError.isVisible = false
                    binding.btnRetry.isVisible = false
                }
            }
        }
    }
}