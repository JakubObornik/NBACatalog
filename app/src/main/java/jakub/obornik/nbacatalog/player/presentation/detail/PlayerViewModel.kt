package jakub.obornik.nbacatalog.player.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakub.obornik.nbacatalog.core.presentation.UiState
import jakub.obornik.nbacatalog.player.data.network.NBAPlayer
import jakub.obornik.nbacatalog.player.domain.usecase.GetPlayerUseCaseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPlayer: GetPlayerUseCaseUseCase,
) : ViewModel() {

    private val playerId: Int? = savedStateHandle["playerId"]
    internal val uiState = MutableStateFlow(UiState<NBAPlayer>())

    init {
        fetch()
    }

    private fun fetch() {
        viewModelScope.launch {
            uiState.value = uiState.value.copy(loading = true)
            uiState.value = try {
                val player = getPlayer(requireNotNull(playerId))
                uiState.value.copy(loading = false, content = player)
            } catch (e: Throwable) {
                uiState.value.copy(loading = false, error = e)
            }
        }
    }

    fun onRetry() {
        fetch()
    }
}