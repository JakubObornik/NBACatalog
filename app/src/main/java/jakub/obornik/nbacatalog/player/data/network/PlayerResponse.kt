package jakub.obornik.nbacatalog.player.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerResponse(
    @SerialName("data")
    val player: NBAPlayer,
)