package com.faranjit.clean.features.spotify.tracks

import android.view.LayoutInflater
import android.view.ViewGroup
import com.faranjit.clean.core.ui.adapter.BaseRecyclerAdapter
import com.faranjit.clean.core.ui.adapter.BaseViewHolder
import com.faranjit.clean.databinding.ListTrackItemBinding
import com.faranjit.clean.domain.spotify.model.SpotifyTrack

/**
 * Created by Bulent Turkmen on 6.04.2020.
 */
class SpotifyTracksAdapter(
    items: List<SpotifyTrack>
) : BaseRecyclerAdapter<SpotifyTrack, SpotifyTrackViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotifyTrackViewHolder =
        SpotifyTrackViewHolder(
            ListTrackItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
}

class SpotifyTrackViewHolder(private val binding: ListTrackItemBinding) :
    BaseViewHolder<SpotifyTrack>(binding) {

    override fun bind(item: SpotifyTrack) {
        binding.track = item
        binding.txtTrackArtists.text = item.artists.joinToString {
            it.name
        }
    }
}