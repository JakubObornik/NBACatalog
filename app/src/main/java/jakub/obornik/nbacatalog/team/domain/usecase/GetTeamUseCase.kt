package jakub.obornik.nbacatalog.team.domain.usecase

import jakub.obornik.nbacatalog.core.network.BallDontLieApiService
import javax.inject.Inject

internal class GetTeamUseCaseUseCase @Inject constructor(
    private val apiService: BallDontLieApiService,
) {

    suspend operator fun invoke(id: Int) = apiService.getTeam(id).team
}
