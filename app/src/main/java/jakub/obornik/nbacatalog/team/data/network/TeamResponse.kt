package jakub.obornik.nbacatalog.team.data.network

import jakub.obornik.nbacatalog.player.data.network.Team
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamResponse(
    @SerialName("data")
    val team: Team,
)