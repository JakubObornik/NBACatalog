package jakub.obornik.nbacatalog.team.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakub.obornik.nbacatalog.core.presentation.UiState
import jakub.obornik.nbacatalog.player.domain.Team
import jakub.obornik.nbacatalog.team.domain.usecase.GetTeamUseCaseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class TeamViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTeam: GetTeamUseCaseUseCase,
) : ViewModel() {

    private val teamId: Int? = savedStateHandle["teamId"]
    internal val uiState = MutableStateFlow(UiState<Team>())

    init {
        fetch()
    }

    private fun fetch() {
        viewModelScope.launch {
            uiState.value = uiState.value.copy(loading = true)
            uiState.value = try {
                val team = getTeam(requireNotNull(teamId))
                uiState.value.copy(loading = false, content = team)
            } catch (e: Throwable) {
                uiState.value.copy(loading = false, error = e)
            }
        }
    }

    fun onRetry() {
        fetch()
    }
}