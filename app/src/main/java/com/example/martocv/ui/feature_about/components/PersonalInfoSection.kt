package com.example.martocv.ui.feature_about.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.martocv.R
import com.example.martocv.data.models.PersonalInfo


@Composable
fun PersonalInfoSection(
    personalInfo: PersonalInfo,
    topBarScrollFraction: Float
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.TopStart
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Photo de profil",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .align(Alignment.TopEnd)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 32.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = personalInfo.name,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = personalInfo.title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            CollapsingTextContainer(
                text = personalInfo.summary,
                style = MaterialTheme.typography.bodyLarge,
                scrollFraction = topBarScrollFraction,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    val selector = Intent(Intent.ACTION_SENDTO)
                    selector.data = "mailto:".toUri()

                    val intent = Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_EMAIL, arrayOf("marchal.tommy@gmail.com"))
                        putExtra(Intent.EXTRA_SUBJECT, "À propose de votre CV !")

                    }
                    intent.selector = selector

                    context.startActivity(
                        Intent.createChooser(
                            intent,
                            "Choisissez votre client mail préféré :"
                        )
                    )
                }) {
                Icon(
                    painter = painterResource(R.drawable.mail_square),
                    modifier = Modifier.size(32.dp),
                    contentDescription = "Email",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("marchal.tommy@gmail.com")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        "https://www.linkedin.com/in/marchal-t/".toUri()
                    )
                    context.startActivity(intent, null)
                }) {
                Icon(
                    painter = painterResource(R.drawable.linkedin),
                    modifier = Modifier.size(32.dp),
                    contentDescription = "Linkedin",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("linkedin.com/in/marchal-t/")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    val intent =
                        Intent(Intent.ACTION_VIEW, "https://github.com/MarchalTommy".toUri())
                    context.startActivity(intent, null)
                }) {
                Icon(
                    painter = painterResource(R.drawable.github),
                    modifier = Modifier.size(32.dp),
                    contentDescription = "Email",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("github.com/MarchalTommy")
            }
        }
    }
}
