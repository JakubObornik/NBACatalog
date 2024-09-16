package jakub.obornik.nbacatalog.player.domain


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerResponse(
    @SerialName("data")
    val player: NBAPlayer,
)