@file:OptIn(ExperimentalGlideComposeApi::class)

package jakub.obornik.nbacatalog.player.presentation.list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import jakub.obornik.nbacatalog.R
import jakub.obornik.nbacatalog.core.presentation.component.CircleImage
import jakub.obornik.nbacatalog.player.data.network.NBAPlayer

@Composable
internal fun PlayerItem(
    player: NBAPlayer,
    modifier: Modifier = Modifier,
    onPlayerClick: (NBAPlayer) -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onPlayerClick(player) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PlayerInfoSection(
                player = player,
                modifier = Modifier.weight(1f),
            )
            ArrowIcon(stringResource(R.string.players_content_description_action))
        }
    }
}

@Composable
internal fun PlayerInfoSection(player: NBAPlayer, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        CircleImage(
            url = player.placeholderImageUrl,
            contentDescription = "${player.firstName} ${player.lastName}",
            modifier = Modifier.size(64.dp),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            PlayerName(name = "${player.firstName} ${player.lastName}")
            Spacer(modifier = Modifier.height(4.dp))
            PlayerDetails(
                position = player.position,
                teamName = player.team?.name
            )
        }
    }
}

@Composable
internal fun PlayerName(name: String) {
    Text(
        text = name,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
internal fun PlayerDetails(position: String?, teamName: String?) {
    val notAvailable = stringResource(id = R.string.not_available)
    Text(
        text = stringResource(
            R.string.players_label_info_format,
            stringResource(R.string.player_position_label), position ?: notAvailable
        ),
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.secondary
    )
    Text(
        text = stringResource(
            R.string.players_label_info_format,
            stringResource(R.string.player_team_label), teamName ?: notAvailable
        ),
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.secondary
    )
}

@Composable
internal fun ArrowIcon(contentDescription: String? = null) {
    Icon(
        imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
        contentDescription = contentDescription,
        modifier = Modifier.size(24.dp),
        tint = MaterialTheme.colorScheme.primary
    )
}

@Composable
internal fun InfoItem(
    label: String,
    value: String?,
    modifier: Modifier = Modifier,
    arrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = arrangement
    ) {
        Text(
            text = stringResource(R.string.label_format, label),
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.width(4.dp))
        Text(text = value ?: stringResource(id = R.string.not_available))
    }
}
