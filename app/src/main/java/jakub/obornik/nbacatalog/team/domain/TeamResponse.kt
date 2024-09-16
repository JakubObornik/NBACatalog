package jakub.obornik.nbacatalog.team.domain


import jakub.obornik.nbacatalog.player.domain.Team
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamResponse(
    @SerialName("data")
    val team: Team,
)