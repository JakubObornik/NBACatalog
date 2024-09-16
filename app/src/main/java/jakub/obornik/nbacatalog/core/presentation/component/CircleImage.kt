package jakub.obornik.nbacatalog.core.presentation.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
internal fun CircleImage(url: String, contentDescription: String, modifier: Modifier = Modifier) {
    Card(
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = modifier,
    ) {
        GlideImage(
            model = url,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop
        )
    }
}