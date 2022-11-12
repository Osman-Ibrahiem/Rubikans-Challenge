package com.rubikans.challenge.ui.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rubikans.challenge.databinding.ItemSettingListBinding
import com.rubikans.challenge.domain.model.SettingType
import com.rubikans.challenge.domain.model.Settings
import com.rubikans.challenge.ui.base.BaseAdapter
import javax.inject.Inject

class SettingsAdapter @Inject constructor() : BaseAdapter<Settings>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Settings>() {
        override fun areItemsTheSame(oldItem: Settings, newItem: Settings): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Settings, newItem: Settings): Boolean {
            return oldItem == newItem
        }
    }

    override val differ = AsyncListDiffer(this, diffCallback)

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemSettingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingsViewHolder(binding)
    }

    inner class SettingsViewHolder(private val binding: ItemSettingListBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<Settings> {
        override fun bind(item: Settings, position: Int) {
            binding.apply {
                textViewSettingName.text = item.settingLabel
                when (item.type) {
                    SettingType.TEXT -> {
                        textViewValue.apply {
                            text = item.settingValue
                            isVisible = true
                            setOnClickListener {
                                setClickListener(item)
                            }
                        }

                        switchValue.isVisible = false
                    }
                    SettingType.EMPTY -> {
                        cardView.setOnClickListener {
                            setClickListener(item)
                        }
                    }
                    SettingType.SWITCH -> {
                        switchValue.apply {
                            isVisible = true
                            isChecked = item.selectedValue
                            setOnCheckedChangeListener { _, isChecked ->
                                item.selectedValue = isChecked
                                setClickListener(item)
                            }
                        }
                        textViewValue.isVisible = false
                    }
                }
            }
        }

        private fun setClickListener(settings: Settings) {
            onItemClickListener(settings)
        }
    }
}