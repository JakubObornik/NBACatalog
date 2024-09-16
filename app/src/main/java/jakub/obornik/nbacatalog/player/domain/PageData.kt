package jakub.obornik.nbacatalog.player.domain


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageData(
    @SerialName("next_cursor")
    val nextCursor: Int,
    @SerialName("per_page")
    val perPage: Int
)