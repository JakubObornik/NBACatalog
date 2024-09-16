package jakub.obornik.nbacatalog.core.network

import jakub.obornik.nbacatalog.player.domain.PlayerResponse
import jakub.obornik.nbacatalog.player.domain.PlayersResponse
import jakub.obornik.nbacatalog.team.domain.TeamResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BallDontLieApiService {

    @GET("players")
    suspend fun getPlayers(
        @Query("cursor") cursor: Int?,
        @Query("per_page") perPage: Int
    ): PlayersResponse

    @GET("players/{$ID}")
    suspend fun getPlayer(
        @Path(ID) id: Int
    ): PlayerResponse

    @GET("teams/{$ID}")
    suspend fun getTeam(
        @Path(ID) id: Int
    ): TeamResponse

    companion object {
        const val ID = "player_id"
    }
}