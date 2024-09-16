package jakub.obornik.nbacatalog.player.domain

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NBAPlayer(
    @SerialName("id") val id: Int,
    @SerialName("first_name") val firstName: String?,
    @SerialName("last_name") val lastName: String?,
    @SerialName("position") val position: String?,
    @SerialName("height") val height: String?,
    @SerialName("weight") val weight: String?,
    @SerialName("jersey_number") val jerseyNumber: String?,
    @SerialName("college") val college: String?,
    @SerialName("country") val country: String?,
    @SerialName("draft_year") val draftYear: Int?,
    @SerialName("draft_round") val draftRound: Int?,
    @SerialName("draft_number") val draftNumber: Int?,
    @SerialName("team") val team: Team?
) {
    @IgnoredOnParcel
    val placeholderImageUrl = "https://via.placeholder.com/150"
    val draftAvailable = draftYear != null && draftRound != null && draftNumber != null
}

@Serializable
data class Team(
    @SerialName("id") val id: Int?,
    @SerialName("conference") val conference: String?,
    @SerialName("division") val division: String?,
    @SerialName("city") val city: String?,
    @SerialName("name") val name: String?,
    @SerialName("full_name") val fullName: String?,
    @SerialName("abbreviation") val abbreviation: String?,
){
    @IgnoredOnParcel
    val placeholderImageUrl = "https://via.placeholder.com/150"
}