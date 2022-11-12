package com.rubikans.challenge.ui.characterslist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rubikans.challenge.databinding.ItemCharacterListBinding
import com.rubikans.challenge.domain.model.Character
import com.rubikans.challenge.ui.base.BaseAdapter
import javax.inject.Inject

class CharacterAdapter @Inject constructor(
) : BaseAdapter<Character>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    override val differ = AsyncListDiffer(this, diffCallback)

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemCharacterListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterListBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<Character> {

        override fun bind(item: Character, position: Int) {
            binding.item = item
            binding.cardView.setOnClickListener { onItemClickListener(item) }
        }
    }
}