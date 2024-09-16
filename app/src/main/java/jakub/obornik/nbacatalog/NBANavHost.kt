package jakub.obornik.nbacatalog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import jakub.obornik.nbacatalog.Navigation.ARG_PLAYER_ID
import jakub.obornik.nbacatalog.Navigation.ARG_TEAM_ID
import jakub.obornik.nbacatalog.Navigation.ROUTE_PLAYERS
import jakub.obornik.nbacatalog.Navigation.ROUTE_TEAMS
import jakub.obornik.nbacatalog.player.presentation.detail.PlayerDetailScreen
import jakub.obornik.nbacatalog.player.presentation.list.PlayerListScreen
import jakub.obornik.nbacatalog.team.presentation.detail.TeamDetailScreen

@Composable
fun NBANavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_PLAYERS,
        modifier = modifier,
    ) {
        composable(ROUTE_PLAYERS) {
            PlayerListScreen({
                navController.navigate("$ROUTE_PLAYERS/${it.id}")
            })
        }
        composable(
            "$ROUTE_PLAYERS/{$ARG_PLAYER_ID}",
            arguments = listOf(navArgument(ARG_PLAYER_ID) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val playerId = backStackEntry.arguments?.getInt(ARG_PLAYER_ID)
            playerId?.let {
                PlayerDetailScreen({
                    navController.navigate("$ROUTE_TEAMS/${it.id}")
                })
            }
        }
        composable(
            "$ROUTE_TEAMS/{$ARG_TEAM_ID}",
            arguments = listOf(navArgument(ARG_TEAM_ID) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val teamId = backStackEntry.arguments?.getInt(ARG_TEAM_ID)
            teamId?.let {
                TeamDetailScreen()
            }
        }
    }
}

object Navigation {
    const val ROUTE_PLAYERS = "players"
    const val ROUTE_TEAMS = "teams"

    const val ARG_PLAYER_ID = "playerId"
    const val ARG_TEAM_ID = "teamId"
}