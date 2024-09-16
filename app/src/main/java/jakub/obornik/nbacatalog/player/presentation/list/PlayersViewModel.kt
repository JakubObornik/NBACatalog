package jakub.obornik.nbacatalog.player.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import jakub.obornik.nbacatalog.player.data.network.NBAPlayer
import jakub.obornik.nbacatalog.player.domain.usecase.GetPlayersUseCaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(getPlayers: GetPlayersUseCaseUseCase) : ViewModel() {
    val players: Flow<PagingData<NBAPlayer>> = getPlayers()
        .cachedIn(viewModelScope)
}