package com.example.martocv.ui.feature_about.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Constraints
import kotlin.math.roundToInt

@Composable
fun ShrinkableText(
    text: String,
    style: TextStyle,
    scrollFraction: Float,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current

    Layout(
        content = {
            Text(
                text = text,
                style = style,
                maxLines = Int.MAX_VALUE
            )
        },
        modifier = modifier // Appliquer les modificateurs externes (ex: padding) ici
    ) { measurables, constraints ->
        // On s'attend à un seul enfant : le Text
        val textMeasurable = measurables.firstOrNull() ?: return@Layout layout(0,0) {}

        // 1. Mesurer le Text avec hauteur INFINIE pour obtenir sa hauteur désirée complète
        val textPlaceable = textMeasurable.measure(
            constraints.copy(minHeight = 0, maxHeight = Constraints.Infinity)
        )
        val desiredHeightPx = textPlaceable.height
        val textWidthPx = textPlaceable.width

        // 2. Calculer la hauteur CIBLE du Layout basée sur la fraction
        val targetHeightPx = (desiredHeightPx * scrollFraction).coerceAtLeast(0f).roundToInt()

        // 3. Définir la taille de ce Layout et placer l'enfant
        layout(textWidthPx, targetHeightPx) {
            // Placer le Text mesuré en utilisant une couche graphique pour le scaling/clipping
            textPlaceable.placeWithLayer(
                x = 0,
                y = 0, // Placer en haut à gauche
            ) {
                this.scaleY = scrollFraction
                this.transformOrigin = TransformOrigin(0.5f, 0f) // Rétrécir vers le haut
                this.clip = true // Activer le clipping aux bords de la couche (qui a targetHeightPx)
            }
        }
    }
}

// --- Usage ---
// ShrinkingText(
//     text = personalInfo.summary,
//     style = MaterialTheme.typography.bodyLarge, // Style qui applique la Google Font
//     scrollFraction = topBarScrollFraction,      // Votre état de fraction
//     modifier = Modifier.padding(bottom = 16.dp) // Padding appliqué au Layout externe
// )