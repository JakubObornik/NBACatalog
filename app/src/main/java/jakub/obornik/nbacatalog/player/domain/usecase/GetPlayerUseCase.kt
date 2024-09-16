package jakub.obornik.nbacatalog.player.domain.usecase

import jakub.obornik.nbacatalog.core.network.BallDontLieApiService
import javax.inject.Inject

class GetPlayerUseCaseUseCase @Inject constructor(
    private val apiService: BallDontLieApiService,
) {

    suspend operator fun invoke(id: Int) = apiService.getPlayer(id).player
}
