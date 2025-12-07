package com.example.axis.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.axis.R

class SettingsAdapter(
    private val settings: List<SettingItem>,
    private val onSettingClick: (SettingItem) -> Unit
) : RecyclerView.Adapter<SettingsAdapter.SettingViewHolder>() {

    data class SettingItem(
        val title: String,
        val description: String?,
        val type: SettingType
    )

    enum class SettingType {
        GRID_SIZE,
        ICON_SIZE,
        SHOW_LABELS,
        DRAWER_MODE,
        APPEARANCE,
        GESTURES,
        PROFILE,
        BACKUP_RESTORE,
        CLOUD_SYNC,
        NOTIFICATIONS,
        PRIVACY_SECURITY,
        ABOUT,
        ICON_PACKS,
        WALLPAPERS
    }

    inner class SettingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.settingTitle)
        val descText: TextView = view.findViewById(R.id.settingDescription)

        fun bind(item: SettingItem) {
            titleText.text = item.title
            if (item.description != null) {
                descText.text = item.description
                descText.visibility = View.VISIBLE
            } else {
                descText.visibility = View.GONE
            }

            itemView.setOnClickListener {
                onSettingClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_setting, parent, false)
        return SettingViewHolder(view)
    }

    override fun onBindViewHolder(holder: SettingViewHolder, position: Int) {
        holder.bind(settings[position])
    }

    override fun getItemCount(): Int = settings.size
}
