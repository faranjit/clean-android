package com.faranjit.clean.features.spotify.tracks

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.faranjit.clean.R
import com.faranjit.clean.core.ui.BaseActivity
import com.faranjit.clean.core.ui.RecyclerScrollListener
import com.faranjit.clean.core.ui.observeLiveData
import com.faranjit.clean.core.ui.visible
import com.faranjit.clean.databinding.ActivitySpotifyTracksBinding
import com.faranjit.clean.domain.spotify.model.SpotifyTrack
import com.faranjit.clean.view.setImageUrl
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_spotify_tracks.*
import javax.inject.Inject
import kotlin.math.abs

class SpotifyTracksActivity : BaseActivity<SpotifyTracksViewModel, ActivitySpotifyTracksBinding>() {

    companion object {
        const val KEY_ID = "playlist_id"
        const val KEY_TITLE = "playlist_title"
        const val KEY_IMAGE = "playlist_image_url"

        fun newIntent(
            context: Context,
            playlistId: String,
            title: String,
            imageUrl: String?
        ): Intent {
            val bundle = Bundle()
            bundle.putString(KEY_ID, playlistId)
            bundle.putString(KEY_TITLE, title)
            bundle.putString(KEY_IMAGE, imageUrl)

            return Intent(context, SpotifyTracksActivity::class.java).apply {
                putExtras(bundle)
            }
        }
    }

    @Inject
    lateinit var viewModel: SpotifyTracksViewModel

    private lateinit var scrollListener: RecyclerScrollListener

    private var playlistId: String = ""
    private var appBarExpanded = true

    private val spotifyTracksAdapter = SpotifyTracksAdapter(emptyList())

    override fun provideLayoutId(): Int = R.layout.activity_spotify_tracks

    override fun provideViewModel(): SpotifyTracksViewModel = viewModel

    override fun showLoading(visible: Boolean) = spinner.visible(visible)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent.getStringExtra(KEY_IMAGE)?.let { url ->
            imgPlaylist.transitionName = url
            imgPlaylist.setImageUrl(url)
        }

        playlistId = intent.getStringExtra(KEY_ID) ?: ""

        viewDataBinding.viewModel = viewModel
        viewDataBinding.toolbarLayout.title = intent.getStringExtra(KEY_TITLE)
        viewDataBinding.recyclerSpotifyTracks.adapter = spotifyTracksAdapter

        val navResourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (navResourceId > 0) {
            val params: CoordinatorLayout.LayoutParams =
                viewDataBinding.recyclerSpotifyTracks.layoutParams as CoordinatorLayout.LayoutParams
            params.bottomMargin = resources.getDimensionPixelSize(navResourceId)
        }

        viewDataBinding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            if (abs(verticalOffset) > 200) {
                appBarExpanded = false;
                invalidateOptionsMenu();
            } else {
                appBarExpanded = true;
                invalidateOptionsMenu();
            }
        })

        initScrollListener()

        viewModel.getTracks(playlistId)
    }

    override fun initObservers() {
        super.initObservers()

        viewModel.run {
            observeLiveData(hasNext) {
                if (!it) {
                    scrollListener.setOnLoadMoreListener { return@setOnLoadMoreListener }
                }
            }

            observeLiveData(spotifyTracks) {
                scrollListener.setLoaded()
                it.getContentIfNotHandled()?.let { tracks ->
                    submitList(tracks)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initScrollListener() {
        val layoutManager = viewDataBinding.recyclerSpotifyTracks.layoutManager
        layoutManager?.let {
            scrollListener = RecyclerScrollListener(it).apply {
                setOnLoadMoreListener {
                    viewModel.getTracks(playlistId)
                }
            }

            viewDataBinding.recyclerSpotifyTracks.addOnScrollListener(scrollListener)
        }
    }

    private fun submitList(items: List<SpotifyTrack>) {
        spotifyTracksAdapter.addItems(items)
    }
}