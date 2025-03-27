package com.example.martocv.ui.feature_about.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Constraints
import kotlin.math.roundToInt

@Composable
fun CollapsingTextContainer( // Renommé pour plus de clarté
    text: String,
    style: TextStyle,
    scrollFraction: Float, // Doit aller de 1.0 (étendu) à 0.0 (réduit)
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current

    Layout(
        content = {
            Text(
                text = text,
                style = style,
                maxLines = Int.MAX_VALUE // Mesure complète nécessaire
            )
        },
        // Important: Clipper le conteneur Layout lui-même
        modifier = modifier.clipToBounds()
    ) { measurables, constraints ->
        val textMeasurable = measurables.firstOrNull() ?: return@Layout layout(0, 0) {}

        // 1. Mesurer le Text avec hauteur infinie
        val textPlaceable = textMeasurable.measure(
            constraints.copy(minHeight = 0, maxHeight = Constraints.Infinity)
        )
        // Utiliser Float pour les calculs avec la fraction
        val desiredHeightPx = textPlaceable.height.toFloat()
        val textWidthPx = textPlaceable.width

        // 2. Calculer la hauteur cible du conteneur Layout
        val targetHeightPx = (desiredHeightPx * scrollFraction).coerceAtLeast(0f)
        val targetHeightInt = targetHeightPx.roundToInt()

        // 3. Calculer le décalage Y pour que le texte glisse vers le haut
        // Quand fraction = 1.0, offset = 0
        // Quand fraction = 0.0, offset = -desiredHeightPx
        val offsetY = -(desiredHeightPx * (1f - scrollFraction)).roundToInt()

        // 4. Définir la taille du Layout et placer le contenu
        layout(textWidthPx, targetHeightInt) {
            // Placer le Text à sa taille réelle, mais décalé verticalement
            textPlaceable.placeRelative(
                x = 0,
                y = offsetY
            )
            // Pas besoin de placeWithLayer car on ne scale plus
            // Le clipping est géré par les limites du Layout + .clipToBounds()
        }
    }
}

// --- Usage ---
// CollapsingTextContainer(
//     text = personalInfo.summary,
//     style = MaterialTheme.typography.bodyLarge,
//     scrollFraction = topBarScrollFraction, // Assurez-vous qu'il va de 1.0 à 0.0
//     modifier = Modifier.padding(bottom = 16.dp)
// )