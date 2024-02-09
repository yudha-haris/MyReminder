package com.example.myreminder.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myreminder.R
import com.example.myreminder.design_system.components.atom.PrimaryButton
import com.example.myreminder.design_system.ui.theme.Typography
import com.example.myreminder.design_system.ui.theme.blue900

@Preview()
@Composable
fun OnboardingScreen(
    onStart: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(red = 44, green = 96, blue = 168),
                        Color.White,
                        Color.White
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier.align(alignment = Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                painter = painterResource(id = R.drawable.baseline_android_24),
                contentDescription = "App Logo",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 48.dp
                    )
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Welcome to Blue App",
                style = Typography.titleLarge,
                color = blue900
            )
            Box(modifier = Modifier.size(16.dp))
            Text(
                text = "Super App made for carving what I learn so far",
                style = Typography.bodyMedium,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(48.dp))
            PrimaryButton(
                onClick = { onStart() },
                text = "Mulai",
                modifier = Modifier.padding(24.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}