package com.rubikans.challenge.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.rubikans.challenge.databinding.ItemCharacterListBinding
import com.rubikans.challenge.domain.model.Character
import com.rubikans.challenge.ui.base.BaseAdapter
import javax.inject.Inject

class CharacterAdapter @Inject constructor(
    private val glide: RequestManager,
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
            binding.apply {
                textViewCharacterName.text = item.fullName
                textViewEmail.text = item.email
                glide.load(item.avatar).into(imageViewCharacter)
                cardView.setOnClickListener {
                    onItemClickListener(item)
                }
            }
        }
    }
}