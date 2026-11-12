package com.example.floodshield1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.floodshield1.ui.theme.FLOODSHIELD1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FLOODSHIELD1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF070F1E)
                ) {
                    // Full Frontend Navigation State
                    var currentScreen by remember { mutableStateOf("welcome") }

                    when (currentScreen) {
                        "welcome" -> WelcomeScreen(onOpenDashboard = { currentScreen = "dashboard" })
                        "dashboard" -> DashboardScreen(
                            onNavigate = { destination -> currentScreen = destination },
                            onLogout = { currentScreen = "welcome" }
                        )
                        "sos" -> SosScreen(onBack = { currentScreen = "dashboard" })
                        "report" -> ReportScreen(onBack = { currentScreen = "dashboard" })
                        "map" -> MapScreen(onBack = { currentScreen = "dashboard" })
                    }
                }
            }
        }
    }
}

// ================= 1. WELCOME SCREEN =================
@Composable
fun WelcomeScreen(onOpenDashboard: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF0B132B), Color(0xFF040810))))
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .border(1.dp, Color(0xFF00E5FF).copy(alpha = 0.5f), RoundedCornerShape(50.dp))
                    .background(Color(0xFF00E5FF).copy(alpha = 0.1f))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(text = "South Gujarat Early Warning Network", color = Color(0xFF00E5FF), fontSize = 12.sp, fontWeight = FontWeight.Medium)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .clip(RoundedCornerShape(28.dp))
                        .background(Color(0xFF101C38))
                        .border(1.5.dp, Color(0xFF2979FF), RoundedCornerShape(28.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🛡️", fontSize = 48.sp)
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Flood", color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Shield", color = Color(0xFF00E5FF), fontSize = 36.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "AI-Driven Disaster Risk Intelligence", color = Color.Gray, fontSize = 13.sp)

                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF101C38))
                        .border(1.dp, Color(0xFF1E293B), RoundedCornerShape(10.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(text = "📍 Surat • Navsari • Valsad • Bharuch", color = Color.LightGray, fontSize = 11.sp)
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = onOpenDashboard,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2979FF))
                ) {
                    Text(text = "Open Control Dashboard  >", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "GSDMA & ISRO Satellite Data Integration", color = Color.Gray, fontSize = 10.sp)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

// ================= 2. DASHBOARD SCREEN (WITH REGIONAL CARDS) =================
@Composable
fun DashboardScreen(onNavigate: (String) -> Unit, onLogout: () -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF070F1E))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Top Bar
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "FloodShield Control", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Region: South Gujarat Zone", color = Color(0xFF00E5FF), fontSize = 12.sp)
                }
                Button(
                    onClick = onLogout,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2A1215)),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(text = "Logout", color = Color.Red, fontSize = 11.sp)
                }
            }
        }

        // Overview Alert Status Banner
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF101C38))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "SYSTEM STATUS", color = Color.Gray, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        Text(text = "NORMAL", color = Color(0xFF00E676), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = "Tapi River Basin Stable", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Ukai Dam Outflow: 25,000 Cusecs. Weather radar clear across all districts.", color = Color.LightGray, fontSize = 11.sp)
                }
            }
        }

        // Quick Actions Grid Header
        item {
            Text(text = "Emergency & Monitoring Tools", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }

        // Quick Actions Grid
        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ActionCard(title = "Live Map & Radar", icon = "🗺️", modifier = Modifier.weight(1f)) { onNavigate("map") }
                ActionCard(title = "Report Flood", icon = "📢", modifier = Modifier.weight(1f)) { onNavigate("report") }
            }
        }
        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ActionCard(title = "Weather Feed", icon = "🌦️", modifier = Modifier.weight(1f)) { onNavigate("map") }
                ActionCard(title = "Emergency SOS", icon = "🚨", modifier = Modifier.weight(1f)) { onNavigate("sos") }
            }
        }

        // District Status Header
        item {
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "District-Wise Water Levels", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }

        // District Cards
        item { DistrictStatusCard(district = "Surat (Tapi River)", level = "18.5 ft", status = "Safe", color = Color(0xFF00E676)) }
        item { DistrictStatusCard(district = "Navsari (Purna River)", level = "12.0 ft", status = "Safe", color = Color(0xFF00E676)) }
        item { DistrictStatusCard(district = "Valsad (Ambika River)", level = "14.2 ft", status = "Moderate", color = Color(0xFFFFB300)) }
        item { DistrictStatusCard(district = "Bharuch (Narmada Basin)", level = "22.1 ft", status = "Safe", color = Color(0xFF00E676)) }

        item { Spacer(modifier = Modifier.height(20.dp)) }
    }
}

@Composable
fun ActionCard(title: String, icon: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = modifier.height(85.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF101C38))
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = icon, fontSize = 22.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = title, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun DistrictStatusCard(district: String, level: String, status: String, color: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF101C38))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = district, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "Water Level: $level", color = Color.Gray, fontSize = 11.sp)
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(color.copy(alpha = 0.15f))
                    .border(1.dp, color.copy(alpha = 0.4f), RoundedCornerShape(50.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(text = status, color = color, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// ================= 3. EMERGENCY SOS SCREEN =================
@Composable
fun SosScreen(onBack: () -> Unit) {
    var sentState by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFF070F1E)).padding(16.dp)
    ) {
        Button(onClick = onBack, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF101C38))) {
            Text("< Back to Dashboard", color = Color.White)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "🚨 Emergency SOS Dispatch", color = Color.Red, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Direct alert linkage to Surat & South Gujarat Disaster Control Room.", color = Color.LightGray, fontSize = 13.sp)

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFF101C38)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "📍 GPS Coordinates Captured", color = Color(0xFF00E5FF), fontSize = 13.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Lat: 21.1702° N, Lng: 72.8311° E", color = Color.White, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Accuracy: High (Within 5 meters)", color = Color.Gray, fontSize = 11.sp)
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { sentState = true },
            modifier = Modifier.fillMaxWidth().height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = if (sentState) "🚨 SOS ALERT BROADCASTED!" else "DISPATCH EMERGENCY SOS", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }

        if (sentState) {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0D2818)),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Success! Rescue team notified with your live coordinates.",
                    color = Color(0xFF00E676),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(14.dp)
                )
            }
        }
    }
}

// ================= 4. REPORT FLOOD SCREEN =================
@Composable
fun ReportScreen(onBack: () -> Unit) {
    var waterLevel by remember { mutableStateOf("") }
    var locationInput by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var submitted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFF070F1E)).padding(16.dp)
    ) {
        Button(onClick = onBack, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF101C38))) {
            Text("< Back to Dashboard", color = Color.White)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "📢 Report Flood Incident", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = locationInput,
            onValueChange = { locationInput = it },
            label = { Text("Location / Area (e.g., Adajan, Surat)") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFF00E5FF), unfocusedBorderColor = Color.Gray)
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = waterLevel,
            onValueChange = { waterLevel = it },
            label = { Text("Estimated Water Level (e.g., 2.5 feet)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description / Details") },
            modifier = Modifier.fillMaxWidth().height(100.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { submitted = true },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2979FF)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Submit Local Report", color = Color.White, fontWeight = FontWeight.Bold)
        }

        if (submitted) {
            Spacer(modifier = Modifier.height(14.dp))
            Text(text = "✓ Report logged successfully! Thank you for helping the network.", color = Color(0xFF00E676), fontSize = 12.sp)
        }
    }
}

// ================= 5. LIVE MAP SCREEN =================
@Composable
fun MapScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFF070F1E)).padding(16.dp)
    ) {
        Button(onClick = onBack, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF101C38))) {
            Text("< Back to Dashboard", color = Color.White)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "🗺️ Live Risk Map & Weather Radar", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
                .background(Color(0xFF101C38), RoundedCornerShape(16.dp))
                .border(1.dp, Color(0xFF1E293B), RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "🛰️ Live GIS / Satellite Feed Active", color = Color(0xFF00E5FF), fontSize = 13.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "South Gujarat River Network Monitored", color = Color.Gray, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "• Tapi Basin (Surat): Normal\n• Purna Basin (Navsari): Normal\n• Ambika Basin (Valsad): Watch", color = Color.LightGray, fontSize = 11.sp)
            }
        }
    }
}
// ================= PREVIEW =================
@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun FullAppPreview() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF070F1E)) {
        DashboardScreen(onNavigate = {}, onLogout = {})
    }
}