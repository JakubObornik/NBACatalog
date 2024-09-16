package jakub.obornik.nbacatalog.core.presentation

internal data class UiState<ContentType>(
    val loading: Boolean = true,
    val content: ContentType? = null,
    val error: Throwable? = null,
)