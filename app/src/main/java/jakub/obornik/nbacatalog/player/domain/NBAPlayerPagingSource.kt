package jakub.obornik.nbacatalog.player.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import jakub.obornik.nbacatalog.core.network.BallDontLieApiService
import javax.inject.Inject

/**
 * A custom [PagingSource] for fetching NBA players from a remote API.
 *
 * @property apiService The API service that provides NBA player data.
 *
 * @constructor Injects the required API service for dependency injection.
 */
class NBAPlayerPagingSource @Inject constructor(
    private val apiService: BallDontLieApiService,
) : PagingSource<Int, NBAPlayer>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NBAPlayer> {
        val cursor = params.key // cursor is the "key" for pagination
        return try {
            // Fetch data from the API
            val response = apiService.getPlayers(cursor = cursor, perPage = params.loadSize)
            val players = response.players

            LoadResult.Page(
                data = players,
                prevKey = null, // No need for a prevKey in cursor-based pagination
                nextKey = response.pageData.nextCursor // Use next cursor from the API response
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    // No need to manage the refresh key in cursor-based pagination
    override fun getRefreshKey(state: PagingState<Int, NBAPlayer>) = null
}