package com.example.timereminderapp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScreen()
                }
            }
        }
    }
}


@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Color(0xFF0D0D0D),   // Near-black background
            surface    = Color(0xFF1A1A1A),   // Slightly lighter surface
            onBackground = Color(0xFFF0EDE6)  // Off-white text on dark background
        ),
        content = content
    )
}


@Composable
fun AppScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 20.dp)
    ) {
        DateHeader()

        // ── The divider line below the date ──
        HorizontalDivider(
            modifier  = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            thickness = 2.dp,
            color     = Color(0xFF2A2A2A)
        )

        // ── Spacer pushes everything below it to the bottom ──
        Spacer(modifier = Modifier.weight(1f))

        // ── The bottom navigation bar ──
        BottomNavigationBar()
    }
}

// ─────────────────────────────────────────────
// THE DATE HEADER COMPOSABLE
// Reads the current date from the phone and
// displays it as:  5 | Jun | 2025
// Each segment has a different visual weight
// so it's easy to scan at a glance.
// ─────────────────────────────────────────────
@Composable
fun DateHeader() {
    val today = LocalDate.now()

    // Formats directly to "7 Apr 2025"
    val formattedDate = today.format(DateTimeFormatter.ofPattern("d MMM yyyy"))

    Text(
        text = formattedDate,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = Color(0xFFF0EDE6),   // Off-white text
        fontSize = 28.sp,            // Uniform size across the whole string
        fontWeight = FontWeight.Bold // Uniform bold weight across the whole string
    )
}

// ─────────────────────────────────────────────
// BOTTOM NAVIGATION BAR
// Auto-adjusts items using Row weights.
// ─────────────────────────────────────────────
@Composable
fun BottomNavigationBar() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // ── The separator line above the nav bar ──
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 2.dp,
            color = Color(0xFF2A2A2A)
        )

        // ── The buttons row ──
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp) // Fixed side padding (~0.3 cm)
                .padding(vertical = 16.dp),  // Gives space from the line and bottom of the screen
            horizontalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            NavButton(icon = Icons.Outlined.Home, modifier = Modifier.weight(1f))
            NavButton(icon = Icons.Outlined.DateRange, modifier = Modifier.weight(1f))
            NavButton(icon = Icons.Outlined.Settings, modifier = Modifier.weight(1f))
        }
    }
}
// ─────────────────────────────────────────────
// INDIVIDUAL NAVIGATION BUTTON
// Square, outlined, rounded corners.
// ─────────────────────────────────────────────
@Composable
fun NavButton(icon: ImageVector, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .aspectRatio(1f) // Forces the box to be a perfect square
            .border(
                width = 2.dp,
                color = Color(0xFF2A2A2A), // Matches the divider color theme
                shape = RoundedCornerShape(16.dp) // rounded corners
            )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF9A9A9A), // Light grey for the icons
            modifier = Modifier.padding(16.dp)
        )
    }
}