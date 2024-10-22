package com.edlabcode.rickmortyapp.data.remote.response

import com.edlabcode.rickmortyapp.domain.model.EpisodeModel
import com.edlabcode.rickmortyapp.domain.model.SeasonEpisode
import com.edlabcode.rickmortyapp.domain.model.SeasonEpisode.SEASON_1
import com.edlabcode.rickmortyapp.domain.model.SeasonEpisode.SEASON_2
import com.edlabcode.rickmortyapp.domain.model.SeasonEpisode.SEASON_3
import com.edlabcode.rickmortyapp.domain.model.SeasonEpisode.SEASON_4
import com.edlabcode.rickmortyapp.domain.model.SeasonEpisode.SEASON_5
import com.edlabcode.rickmortyapp.domain.model.SeasonEpisode.SEASON_6
import com.edlabcode.rickmortyapp.domain.model.SeasonEpisode.SEASON_7
import com.edlabcode.rickmortyapp.domain.model.SeasonEpisode.UNKNOWN
import kotlinx.serialization.Serializable

@Serializable
data class EpisodesResponse(
    val id: Int,
    val name: String,
    val episode: String,
    val characters: List<String>
) {
    fun toDomain(): EpisodeModel {
        val season = getSeasonFromEpisodeCode(episode)
        return EpisodeModel(
            id = id,
            name = name,
            episode = episode,
            characters = characters.map { url -> url.substringAfter("/") },
            season = season,
            videoURL = getVideoUrlFromSeason(season)
        )
    }

    private fun getVideoUrlFromSeason(season: SeasonEpisode): String {
        return when (season) {
            SEASON_1 -> "https://youtu.be/Kvrt8_b6xsY?si=8wRqXChkLuegCyCN"
            SEASON_2 -> "https://youtu.be/Kvrt8_b6xsY?si=8wRqXChkLuegCyCN"
            SEASON_3 -> "https://youtu.be/Kvrt8_b6xsY?si=8wRqXChkLuegCyCN"
            SEASON_4 -> "https://youtu.be/Kvrt8_b6xsY?si=8wRqXChkLuegCyCN"
            SEASON_5 -> "https://youtu.be/Kvrt8_b6xsY?si=8wRqXChkLuegCyCN"
            SEASON_6 -> "https://youtu.be/Kvrt8_b6xsY?si=8wRqXChkLuegCyCN"
            SEASON_7 -> "https://youtu.be/Kvrt8_b6xsY?si=8wRqXChkLuegCyCN"
            UNKNOWN -> "https://youtu.be/Kvrt8_b6xsY?si=8wRqXChkLuegCyCN"
        }

    }

    private fun getSeasonFromEpisodeCode(episode: String): SeasonEpisode {
        return when {
            episode.startsWith("S01") -> SEASON_1
            episode.startsWith("S02") -> SEASON_2
            episode.startsWith("S03") -> SEASON_3
            episode.startsWith("S04") -> SEASON_4
            episode.startsWith("S05") -> SEASON_5
            episode.startsWith("S06") -> SEASON_6
            episode.startsWith("S07") -> SEASON_7
            else -> UNKNOWN
        }
    }
}