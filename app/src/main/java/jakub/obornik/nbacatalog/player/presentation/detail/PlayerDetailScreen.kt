package jakub.obornik.nbacatalog.player.presentation.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import jakub.obornik.nbacatalog.R
import jakub.obornik.nbacatalog.core.presentation.component.CircleImage
import jakub.obornik.nbacatalog.core.presentation.component.ErrorScreen
import jakub.obornik.nbacatalog.core.presentation.component.TitleName
import jakub.obornik.nbacatalog.player.domain.NBAPlayer
import jakub.obornik.nbacatalog.player.domain.Team
import jakub.obornik.nbacatalog.player.presentation.detail.component.PlayerExtendedInfoCard
import jakub.obornik.nbacatalog.player.presentation.detail.component.TeamCard
import jakub.obornik.nbacatalog.player.presentation.preview.PlayerPreviewProvider
import jakub.obornik.nbacatalog.ui.theme.NBACatalogTheme

@Composable
fun PlayerDetailScreen(
    onTeamClick: (Team) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PlayerViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val player = uiState.content
    val error = uiState.error

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        when {
            uiState.loading -> CircularProgressIndicator()

            player != null -> PlayerDetail(player = player, onTeamClick = onTeamClick)

            error != null -> ErrorScreen(error = error, onRetry = viewModel::onRetry)
        }
    }
}

@Composable
private fun PlayerDetail(
    player: NBAPlayer,
    onTeamClick: (Team) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        CircleImage(
            url = player.placeholderImageUrl,
            contentDescription = "${player.firstName} ${player.lastName}",
            modifier = Modifier.size(150.dp),
        )

        TitleName("${player.firstName} ${player.lastName}")

        PlayerExtendedInfoCard(player)

        player.team?.let {
            TeamCard(
                team = it,
                title = {
                    Text(
                        text = stringResource(
                            R.string.team_title_format,
                            it.fullName ?: it.name.orEmpty()
                        ),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                onTeamClick = onTeamClick,
            )
        }
    }
}

@Preview(
    name = "Player Detail - Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun PreviewPlayerDetailFetchedScreenLight(
    @PreviewParameter(PlayerPreviewProvider::class) player: NBAPlayer,
) {
    NBACatalogTheme {
        PlayerDetail(player, {})
    }
}

@Preview(
    name = "Player Detail - Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun PreviewPlayerDetailFetchedScreenDark(
    @PreviewParameter(PlayerPreviewProvider::class) player: NBAPlayer,
) {
    NBACatalogTheme {
        PlayerDetail(player, {})
    }
}
