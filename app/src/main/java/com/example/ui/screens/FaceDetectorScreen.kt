package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.local.FaceShapeAnalyzer
import com.example.data.model.FaceShape
import com.example.ui.components.FaceOutlineOverlay
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.SlateSecondary
import com.example.ui.viewmodel.AppNavTab
import com.example.ui.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun FaceDetectorScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    var detectorMode by remember { mutableIntStateOf(0) } // 0: Diagnostic Quiz, 1: Landmark Alignment
    val quizAnswers by viewModel.quizAnswers.collectAsStateWithLifecycle()
    val latestAnalysis by viewModel.latestAnalysis.collectAsStateWithLifecycle()
    val scanHistory by viewModel.scanHistory.collectAsStateWithLifecycle()
    val selectedFaceShape by viewModel.selectedFaceShape.collectAsStateWithLifecycle()

    // Landmark measurement sliders
    var faceLengthRatio by remember { mutableFloatStateOf(1.4f) }
    var cheekboneWidthRatio by remember { mutableFloatStateOf(1.0f) }
    var foreheadWidthRatio by remember { mutableFloatStateOf(1.1f) }
    var jawlineLengthRatio by remember { mutableFloatStateOf(0.9f) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("face_detector_screen"),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Mode Selector Tab Row
        item {
            TabRow(
                selectedTabIndex = detectorMode,
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                contentColor = GoldPrimary,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[detectorMode]),
                        color = GoldPrimary
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Tab(
                    selected = detectorMode == 0,
                    onClick = { detectorMode = 0 },
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Psychology, contentDescription = null, modifier = Modifier.size(18.dp))
                            Text("Diagnostic Quiz", fontWeight = FontWeight.Bold)
                        }
                    },
                    modifier = Modifier.testTag("tab_diagnostic_quiz")
                )
                Tab(
                    selected = detectorMode == 1,
                    onClick = { detectorMode = 1 },
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Straighten, contentDescription = null, modifier = Modifier.size(18.dp))
                            Text("Face Landmark Ratios", fontWeight = FontWeight.Bold)
                        }
                    },
                    modifier = Modifier.testTag("tab_landmark_ratios")
                )
            }
        }

        // Mode 0: Interactive Diagnostic Quiz
        if (detectorMode == 0) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Face Shape Assessment Questions",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        // Question 1: Length vs Width
                        QuizQuestionSection(
                            title = "1. Face Length vs Width Proportion",
                            subtitle = "Look in a mirror: Is your face noticeably taller than it is wide?",
                            options = listOf(
                                "EQUAL" to "Equal (Width ~ Length)",
                                "LONGER" to "Slightly Longer (1.3x - 1.5x)",
                                "MUCH_LONGER" to "Noticeably Longer (1.7x+)"
                            ),
                            selectedOption = quizAnswers.lengthVsWidth,
                            onOptionSelected = {
                                viewModel.updateQuizAnswers(quizAnswers.copy(lengthVsWidth = it))
                            }
                        )

                        // Question 2: Widest Feature
                        QuizQuestionSection(
                            title = "2. What is the widest part of your face?",
                            subtitle = "Measure horizontally across your face.",
                            options = listOf(
                                "FOREHEAD" to "Forehead & Temples",
                                "CHEEKBONES" to "Prominent Cheekbones",
                                "JAW" to "Broad Jawline",
                                "EQUAL" to "Uniform Width All Over"
                            ),
                            selectedOption = quizAnswers.widestPart,
                            onOptionSelected = {
                                viewModel.updateQuizAnswers(quizAnswers.copy(widestPart = it))
                            }
                        )

                        // Question 3: Jawline Contour
                        QuizQuestionSection(
                            title = "3. Shape of your Jawline and Chin",
                            subtitle = "Examine your jaw angle from ears down to the chin.",
                            options = listOf(
                                "ROUNDED" to "Soft & Curved",
                                "SQUARE" to "Sharp & Angular (90°)",
                                "POINTED" to "Pointed & Delicate (V-shape)",
                                "TAPERED" to "Gently Tapered Oval"
                            ),
                            selectedOption = quizAnswers.jawShape,
                            onOptionSelected = {
                                viewModel.updateQuizAnswers(quizAnswers.copy(jawShape = it))
                            }
                        )

                        // Question 4: Forehead Dimension
                        QuizQuestionSection(
                            title = "4. Forehead Width",
                            subtitle = "Width across your hairline.",
                            options = listOf(
                                "WIDE" to "Broad & Expansive",
                                "MEDIUM" to "Medium Proportional",
                                "NARROW" to "Narrow at Temples"
                            ),
                            selectedOption = quizAnswers.foreheadWidth,
                            onOptionSelected = {
                                viewModel.updateQuizAnswers(quizAnswers.copy(foreheadWidth = it))
                            }
                        )

                        Button(
                            onClick = { viewModel.submitQuizAnalysis() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("submit_quiz_btn"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary, contentColor = Color(0xFF261A00))
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = null)
                                Text("Calculate My Face Shape", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            }
                        }
                    }
                }
            }
        }

        // Mode 1: Landmark Measurement Sliders & Overlay Preview
        if (detectorMode == 1) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Text(
                            text = "Facial Geometry Landmark Guide",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        // Visual Alignment Canvas Frame
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1.2f)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f))
                                .border(1.dp, GoldPrimary.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                        ) {
                            FaceOutlineOverlay(
                                faceShape = selectedFaceShape,
                                guideColor = GoldPrimary,
                                modifier = Modifier.fillMaxSize()
                            )

                            Surface(
                                modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .padding(top = 10.dp),
                                shape = RoundedCornerShape(12.dp),
                                color = Color.Black.copy(alpha = 0.6f)
                            ) {
                                Text(
                                    text = "Current Overlay: ${selectedFaceShape.displayName}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = GoldPrimary,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                        }

                        // Sliders for Precision Measurements
                        MeasurementSliderItem(
                            label = "Face Length Proportion",
                            value = faceLengthRatio,
                            range = 0.9f..2.0f,
                            displayValue = "${String.format("%.2f", faceLengthRatio)}x",
                            onValueChange = { faceLengthRatio = it }
                        )

                        MeasurementSliderItem(
                            label = "Cheekbone Width Proportion",
                            value = cheekboneWidthRatio,
                            range = 0.8f..1.6f,
                            displayValue = "${String.format("%.2f", cheekboneWidthRatio)}x",
                            onValueChange = { cheekboneWidthRatio = it }
                        )

                        MeasurementSliderItem(
                            label = "Forehead Width Proportion",
                            value = foreheadWidthRatio,
                            range = 0.7f..1.5f,
                            displayValue = "${String.format("%.2f", foreheadWidthRatio)}x",
                            onValueChange = { foreheadWidthRatio = it }
                        )

                        MeasurementSliderItem(
                            label = "Jawline Width Proportion",
                            value = jawlineLengthRatio,
                            range = 0.6f..1.4f,
                            displayValue = "${String.format("%.2f", jawlineLengthRatio)}x",
                            onValueChange = { jawlineLengthRatio = it }
                        )

                        Button(
                            onClick = {
                                viewModel.submitMeasurementAnalysis(
                                    faceLength = faceLengthRatio,
                                    cheekboneWidth = cheekboneWidthRatio,
                                    foreheadWidth = foreheadWidthRatio,
                                    jawlineLength = jawlineLengthRatio
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("analyze_landmarks_btn"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary, contentColor = Color(0xFF261A00))
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(imageVector = Icons.Default.Tune, contentDescription = null)
                                Text("Analyze Facial Ratios", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            }
                        }
                    }
                }
            }
        }

        // Analysis Result Card (if analysis has been completed)
        latestAnalysis?.let { analysis ->
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("analysis_result_card"),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                    ),
                    border = BorderStroke(1.5.dp, GoldPrimary)
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Success",
                                    tint = EmeraldSuccess,
                                    modifier = Modifier.size(24.dp)
                                )
                                Text(
                                    text = "${analysis.detectedShape.displayName} Face Shape",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = GoldPrimary.copy(alpha = 0.2f),
                                border = BorderStroke(1.dp, GoldPrimary)
                            ) {
                                Text(
                                    text = "${analysis.confidence}% Match",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = GoldPrimary,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }

                        Text(
                            text = analysis.summary,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        // Key takeaways checklist
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            analysis.keyTakeaways.forEach { takeaway ->
                                Row(
                                    verticalAlignment = Alignment.Top,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Lightbulb,
                                        contentDescription = null,
                                        tint = GoldPrimary,
                                        modifier = Modifier
                                            .size(16.dp)
                                            .padding(top = 2.dp)
                                    )
                                    Text(
                                        text = takeaway,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }

                        // Apply & Explore Hairstyles CTA
                        Button(
                            onClick = {
                                viewModel.selectFaceShape(analysis.detectedShape)
                                viewModel.setTab(AppNavTab.HAIRSTYLES)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 6.dp)
                                .testTag("view_recommended_hairstyles_btn"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GoldPrimary,
                                contentColor = Color(0xFF261A00)
                            )
                        ) {
                            Text(
                                text = "View Hairstyles for ${analysis.detectedShape.displayName} Face",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        // Scan History Section
        if (scanHistory.isNotEmpty()) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(imageVector = Icons.Default.History, contentDescription = null, tint = GoldPrimary, modifier = Modifier.size(20.dp))
                        Text(
                            text = "Saved Face Analyses",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            items(scanHistory) { scan ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "${scan.faceShapeName} (${scan.confidence}% confidence)",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Ratio: ${String.format("%.2f", scan.lengthToWidthRatio)} • ${scan.notes}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = SimpleDateFormat("MMM dd, yyyy - hh:mm a", Locale.getDefault()).format(Date(scan.scanDate)),
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                            )
                        }

                        IconButton(onClick = { viewModel.deleteScanHistory(scan.id) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete scan",
                                tint = MaterialTheme.colorScheme.error.copy(alpha = 0.7f)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun QuizQuestionSection(
    title: String,
    subtitle: String,
    options: List<Pair<String, String>>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
        Text(text = subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            options.forEach { (key, label) ->
                val isSelected = key == selectedOption
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                    border = BorderStroke(
                        1.dp,
                        if (isSelected) GoldPrimary else MaterialTheme.colorScheme.surfaceVariant
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onOptionSelected(key) }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(18.dp)
                                .clip(CircleShape)
                                .background(if (isSelected) GoldPrimary else Color.Transparent)
                                .border(2.dp, if (isSelected) GoldPrimary else MaterialTheme.colorScheme.onSurfaceVariant, CircleShape)
                        )
                        Text(
                            text = label,
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MeasurementSliderItem(
    label: String,
    value: Float,
    range: ClosedFloatingPointRange<Float>,
    displayValue: String,
    onValueChange: (Float) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = label, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface)
            Text(text = displayValue, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = GoldPrimary)
        }
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = range,
            colors = SliderDefaults.colors(
                thumbColor = GoldPrimary,
                activeTrackColor = GoldPrimary,
                inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        )
    }
}
