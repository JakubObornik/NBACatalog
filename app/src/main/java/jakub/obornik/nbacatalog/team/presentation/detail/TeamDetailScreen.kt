package jakub.obornik.nbacatalog.team.presentation.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import jakub.obornik.nbacatalog.core.presentation.component.CircleImage
import jakub.obornik.nbacatalog.core.presentation.component.ErrorScreen
import jakub.obornik.nbacatalog.core.presentation.component.TitleName
import jakub.obornik.nbacatalog.player.data.network.NBAPlayer
import jakub.obornik.nbacatalog.player.data.network.Team
import jakub.obornik.nbacatalog.player.presentation.detail.component.TeamCard
import jakub.obornik.nbacatalog.player.presentation.preview.PlayerPreviewProvider
import jakub.obornik.nbacatalog.ui.theme.NBACatalogTheme

@Composable
internal fun TeamDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: TeamViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val team = uiState.content
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

            team != null -> TeamDetail(team = team)

            error != null -> ErrorScreen(error, onRetry = viewModel::onRetry)
        }
    }
}

@Composable
private fun TeamDetail(
    team: Team,
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
            url = team.placeholderImageUrl,
            contentDescription = team.name.orEmpty(),
            modifier = Modifier.size(150.dp)
        )

        TitleName(team.fullName.orEmpty())

        TeamCard(
            team = team,
            isExtended = true,
            infoArrangement = Arrangement.SpaceBetween,
        )
    }
}

@Preview(
    name = "Team Detail - Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun PreviewTeamDetailFetchedScreenLight(
    @PreviewParameter(PlayerPreviewProvider::class) player: NBAPlayer,
) {
    NBACatalogTheme {
        TeamDetail(requireNotNull(player.team))
    }
}

@Preview(
    name = "Team Detail - Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewTeamDetailFetchedScreenDark(
    @PreviewParameter(PlayerPreviewProvider::class) player: NBAPlayer,
) {
    NBACatalogTheme {
        TeamDetail(requireNotNull(player.team))
    }
}
