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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.Brush
import androidx.compose.material.icons.filled.Add

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

        // ── This Box pushes the nav bar down and holds the floating buttons ──
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FloatingAddButton()
            }
        }

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
            .aspectRatio(1f)
            .background(Color(0xFF0D0D0D), RoundedCornerShape(16.dp))
            // ── Deeper, brighter stacked borders for a visible gradient ──
            .border(16.dp, Color(0xFF2A2A2A).copy(alpha = 0.2f), RoundedCornerShape(16.dp)) // Deepest reach
            .border(8.dp,  Color(0xFF2A2A2A).copy(alpha = 0.5f), RoundedCornerShape(16.dp)) // Mid-transition
            .border(2.dp,  Color(0xFF2A2A2A), RoundedCornerShape(16.dp))                    // Sharp outer edge
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF9A9A9A),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun FloatingAddButton() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(50.dp)
            .background(Color(0xFF0D0D0D), RoundedCornerShape(16.dp))
            // ── Deeper, brighter stacked borders for a visible gradient ──
            .border(16.dp, Color(0xFF2A2A2A).copy(alpha = 0.2f), RoundedCornerShape(16.dp))
            .border(8.dp,  Color(0xFF2A2A2A).copy(alpha = 0.5f), RoundedCornerShape(16.dp))
            .border(2.dp,  Color(0xFF2A2A2A), RoundedCornerShape(16.dp))
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Reminder",
            tint = Color(0xFF9A9A9A),
            modifier = Modifier.size(28.dp)
        )
    }
}