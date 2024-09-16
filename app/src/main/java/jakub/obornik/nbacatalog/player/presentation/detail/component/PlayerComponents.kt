package jakub.obornik.nbacatalog.player.presentation.detail.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import jakub.obornik.nbacatalog.R
import jakub.obornik.nbacatalog.player.data.network.NBAPlayer
import jakub.obornik.nbacatalog.player.data.network.Team
import jakub.obornik.nbacatalog.player.presentation.list.component.ArrowIcon
import jakub.obornik.nbacatalog.player.presentation.list.component.InfoItem

@Composable
internal fun PlayerExtendedInfoCard(
    player: NBAPlayer,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            val notAvailable = stringResource(id = R.string.not_available)

            InfoItem(
                label = stringResource(id = R.string.player_position_label),
                value = player.position
            )
            InfoItem(
                label = stringResource(id = R.string.player_height_label),
                value = player.height
            )
            InfoItem(
                label = stringResource(id = R.string.player_weight_label),
                value = player.weight
            )
            InfoItem(
                label = stringResource(id = R.string.player_jersey_number_label),
                value = player.jerseyNumber
            )
            InfoItem(
                label = stringResource(id = R.string.player_college_label),
                value = player.college
            )
            InfoItem(
                label = stringResource(id = R.string.player_country_label),
                value = player.country
            )
            InfoItem(
                label = stringResource(id = R.string.player_draft_label),
                value = stringResource(
                    id = R.string.player_draft_label,
                    player.draftYear?.toString() ?: notAvailable,
                    player.draftRound?.toString() ?: notAvailable,
                    player.draftNumber?.toString() ?: notAvailable
                ).takeIf { player.draftAvailable }
            )
        }
    }
}

@Composable
internal fun TeamCard(
    team: Team,
    modifier: Modifier = Modifier,
    title: (@Composable () -> Unit)? = null,
    isExtended: Boolean = false,
    onTeamClick: ((Team) -> Unit)? = null,
    infoArrangement: Arrangement.Horizontal = Arrangement.Start,
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = onTeamClick != null) { onTeamClick?.invoke(team) }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                title?.invoke()
                if (isExtended) {
                    InfoItem(
                        label = stringResource(R.string.team_city_label),
                        value = team.city,
                        arrangement = infoArrangement,
                    )

                    InfoItem(
                        label = stringResource(R.string.team_abbreviation_label),
                        value = team.abbreviation,
                        arrangement = infoArrangement,
                    )
                }
                InfoItem(
                    label = stringResource(R.string.team_conference_label),
                    value = team.conference,
                    arrangement = infoArrangement,
                )

                InfoItem(
                    label = stringResource(R.string.team_division_label),
                    value = team.division,
                    arrangement = infoArrangement,
                )
            }

            onTeamClick?.let {
                ArrowIcon(contentDescription = stringResource(R.string.team_content_description_action))
            }
        }
    }
}


