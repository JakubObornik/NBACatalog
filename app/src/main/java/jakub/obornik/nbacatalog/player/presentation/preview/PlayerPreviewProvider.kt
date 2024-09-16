package jakub.obornik.nbacatalog.player.presentation.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import jakub.obornik.nbacatalog.player.domain.NBAPlayer
import jakub.obornik.nbacatalog.player.domain.Team

internal class PlayerPreviewProvider : PreviewParameterProvider<NBAPlayer> {

    override val values: Sequence<NBAPlayer>
        get() = sequenceOf(
            NBAPlayer(
                id = 2544,
                firstName = "LeBron",
                lastName = "James",
                position = "Forward",
                height = "6'9\"",
                weight = "250 lbs",
                jerseyNumber = "6",
                college = null,
                country = "USA",
                draftYear = 2003,
                draftRound = 1,
                draftNumber = 1,
                team = Team(
                    id = 1610612747,
                    conference = "Western",
                    division = "Pacific",
                    city = "Los Angeles",
                    name = "Lakers",
                    fullName = "Los Angeles Lakers",
                    abbreviation = "LAL"
                )
            ),
        )
}
