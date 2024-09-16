package jakub.obornik.nbacatalog.player.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import jakub.obornik.nbacatalog.player.domain.NBAPlayer
import jakub.obornik.nbacatalog.player.domain.NBAPlayerPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case class responsible for providing paginated data of NBA players.
 *
 * @property pagingSource The data source responsible for fetching paginated NBA player data.
 *
 * @constructor Injects the required paging source for dependency injection.
 */
class GetPlayersUseCaseUseCase @Inject constructor(
    private val pagingSource: NBAPlayerPagingSource,
) {

    /**
     * Invokes the use case to fetch paginated NBA players.
     *
     * @return [Flow] of [PagingData] containing the paginated NBA players.
     */
    operator fun invoke(): Flow<PagingData<NBAPlayer>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { pagingSource }
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 35
    }
}
