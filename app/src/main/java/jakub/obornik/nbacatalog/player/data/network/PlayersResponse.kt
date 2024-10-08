package jakub.obornik.nbacatalog.player.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayersResponse(
    @SerialName("data")
    val players: List<NBAPlayer>,
    @SerialName("meta")
    val pageData: PageData
)