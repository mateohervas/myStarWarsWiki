package com.shadows.core.mappers

import com.shadows.core.models.remote.RemoteFilm
import org.junit.Test


class FilmsMapperTest {

    @Test
    fun `given incomplete RemoteFilm map it to StarWarsFilm`() {
        val remoteFilm = RemoteFilm(null,null,null)
        val starWarsFilm = remoteFilm.toStarWarsFilm()
        assert((starWarsFilm.openingCrawl.isNotEmpty()&&starWarsFilm.title.isNotEmpty()&&starWarsFilm.releasedDate.isNotEmpty()))
    }

    @Test
    fun `given list of RemoteFilm return list of StarWarsFilm `() {
        val list = listOf(RemoteFilm(null,null,null))
        assert(!list.toSWFilmList().isNullOrEmpty())
    }
}