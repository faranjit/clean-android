package com.faranjit.clean.features.spotify.playlists

import android.view.LayoutInflater
import android.view.ViewGroup
import com.faranjit.clean.core.ui.adapter.BaseRecyclerAdapter
import com.faranjit.clean.core.ui.adapter.BaseViewHolder
import com.faranjit.clean.databinding.ListPlaylistItemBinding
import com.faranjit.clean.domain.spotify.model.SpotifyPlaylistItem

/**
 * Created by Bulent Turkmen on 1.04.2020.
 */
class SpotifyPlaylistAdapter(
    items: List<SpotifyPlaylistItem>,
    clickListener: ((SpotifyPlaylistItem, SpotifyPlaylistViewHolder) -> Unit)? = null
) : BaseRecyclerAdapter<SpotifyPlaylistItem, SpotifyPlaylistViewHolder>(
    items,
    clickListener
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotifyPlaylistViewHolder =
        SpotifyPlaylistViewHolder(
            ListPlaylistItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
}

class SpotifyPlaylistViewHolder(private val binding: ListPlaylistItemBinding) :
    BaseViewHolder<SpotifyPlaylistItem>(binding) {

    override fun bind(item: SpotifyPlaylistItem) {
        binding.playlist = item
    }
}