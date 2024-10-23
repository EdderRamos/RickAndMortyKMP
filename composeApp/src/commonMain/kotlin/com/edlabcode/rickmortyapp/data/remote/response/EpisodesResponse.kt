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
            characters = characters.map { url -> url.substringAfterLast("/") },
            season = season,
            videoURL = getVideoUrlFromSeason(season)
        )
    }

    private fun getVideoUrlFromSeason(season: SeasonEpisode): String {
        return when (season) {
            SEASON_1 -> "https://www.youtube.com/watch?v=8BEzj2kRjO8"
            SEASON_2 -> "https://www.youtube.com/watch?v=_IZfO_LfK5Q"
            SEASON_3 -> "https://www.youtube.com/watch?v=rLyOul8kau0"
            SEASON_4 -> "https://www.youtube.com/watch?v=hl1U0bxTHbY"
            SEASON_5 -> "https://www.youtube.com/watch?v=qbHYYXj2gMc"
            SEASON_6 -> "https://www.youtube.com/watch?v=jerFRSQW9g8"
            SEASON_7 -> "https://www.youtube.com/watch?v=PkZtVBNkmso"
            UNKNOWN -> "https://www.youtube.com/watch?v=8BEzj2kRjO8"
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