package jakub.obornik.nbacatalog.player.presentation.list

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import jakub.obornik.nbacatalog.R
import jakub.obornik.nbacatalog.core.presentation.component.ErrorScreen
import jakub.obornik.nbacatalog.player.data.network.NBAPlayer
import jakub.obornik.nbacatalog.player.presentation.list.component.PlayerItem
import jakub.obornik.nbacatalog.player.presentation.preview.PlayerPreviewProvider
import jakub.obornik.nbacatalog.ui.theme.NBACatalogTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PlayerListScreen(
    onPlayerClick: (NBAPlayer) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PlayersViewModel = hiltViewModel(),
) {
    val playerList = viewModel.players.collectAsLazyPagingItems()
    val listState = rememberLazyListState()
    val initialLoading = playerList.loadState.let {
        it.refresh is LoadState.Loading && playerList.itemCount == 0
    }
    val initialError = playerList.loadState.refresh as? LoadState.Error
    val pageError = playerList.loadState.append as? LoadState.Error
    val context = LocalContext.current

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            initialLoading -> CircularProgressIndicator()
            initialError != null -> ErrorScreen(
                Throwable(initialError.error),
                onRetry = playerList::retry,
            )

            else -> LazyColumn(
                state = listState,
            ) {
                items(playerList.itemCount) { index ->
                    val player = playerList[index]
                    if (player != null) {
                        Column {
                            PlayerItem(
                                player = player,
                                onPlayerClick = onPlayerClick,
                            )
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(pageError) {
        if (pageError != null) {
            Toast.makeText(
                context,
                R.string.players_fetch_page_error,
                Toast.LENGTH_SHORT,
            ).show()
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collectLatest { lastVisibleItemIndex ->
                if (lastVisibleItemIndex == playerList.itemCount - 1) {
                    playerList.retry()
                }
            }
    }
}

@Preview(
    name = "NBA Player Item - Light Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun PreviewNBAPlayerItemLight(
    @PreviewParameter(PlayerPreviewProvider::class) player: NBAPlayer,
) {
    NBACatalogTheme {
        PlayerItem(
            player = player,
        )
    }
}

@Preview(
    name = "NBA Player Item - Dark Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewNBAPlayerItemDark(
    @PreviewParameter(PlayerPreviewProvider::class) player: NBAPlayer,
) {
    NBACatalogTheme {
        PlayerItem(
            player = player,
        )
    }
}
