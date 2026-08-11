package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.local.HairstyleCatalog
import com.example.data.local.ProductCatalog
import com.example.data.model.RoutineStep
import com.example.ui.components.HairstyleCard
import com.example.ui.components.HoldShineIndicator
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.GoldPrimary
import com.example.ui.viewmodel.AppNavTab
import com.example.ui.viewmodel.MainViewModel

@Composable
fun StylingRoutineScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val routineProductsEntities by viewModel.routineProducts.collectAsStateWithLifecycle()
    val savedHairstylesEntities by viewModel.savedHairstyles.collectAsStateWithLifecycle()
    val selectedFaceShape by viewModel.selectedFaceShape.collectAsStateWithLifecycle()

    val completedSteps = remember { mutableStateMapOf<Int, Boolean>() }

    val defaultRoutineSteps = listOf(
        RoutineStep(
            stepNumber = 1,
            stage = "Stage 1: Pre-Styling & Root Prep",
            title = "Apply Foundation Pre-Styler to Damp Hair",
            description = "Towel dry hair until slightly damp (70%). Spritz Sea Salt Spray or apply Volumizing Mousse directly to the roots for lift and grip.",
            suggestedProduct = "Oceanic Texturizing Sea Salt Spray",
            durationSeconds = 60
        ),
        RoutineStep(
            stepNumber = 2,
            stage = "Stage 2: Directional Blow Dry",
            title = "Blow Dry in Desired Direction",
            description = "Use a vent brush or fingers while blow drying on medium heat, lifting the roots upward to establish the baseline volume for your ${selectedFaceShape.displayName} face shape.",
            suggestedProduct = "Round Ceramic or Vent Brush",
            durationSeconds = 180
        ),
        RoutineStep(
            stepNumber = 3,
            stage = "Stage 3: Main Styler Application",
            title = "Warm & Distribute Main Styler",
            description = "Rub a dime-sized amount of Matte Clay or Paste vigorously between hands until clear. Work from back to front, ensuring even coverage from roots to tips.",
            suggestedProduct = "SculptCraft High-Hold Matte Clay",
            durationSeconds = 90
        ),
        RoutineStep(
            stepNumber = 4,
            stage = "Stage 4: Texture & Lock",
            title = "Detail Texture & Lock In Place",
            description = "Piece out fringe sections or layer ends. Lightly tap Texture Powder onto roots or mist flexible finishing spray to resist humidity all day.",
            suggestedProduct = "MicroDust Instant Volumizing Powder",
            durationSeconds = 45
        )
    )

    val progress = completedSteps.count { it.value } / defaultRoutineSteps.size.toFloat()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("styling_routine_screen"),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header Card with Progress Bar
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Daily Styling Master Routine",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Optimized for ${selectedFaceShape.displayName} proportions",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (progress == 1f) EmeraldSuccess.copy(alpha = 0.2f) else GoldPrimary.copy(alpha = 0.2f)
                        ) {
                            Text(
                                text = "${completedSteps.count { it.value }}/${defaultRoutineSteps.size} Done",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (progress == 1f) EmeraldSuccess else GoldPrimary,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = if (progress == 1f) EmeraldSuccess else GoldPrimary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                }
            }
        }

        // Daily Routine Steps List
        item {
            Text(
                text = "Styling Sequence",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        items(defaultRoutineSteps) { step ->
            val isDone = completedSteps[step.stepNumber] == true
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { completedSteps[step.stepNumber] = !isDone },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isDone) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f) else MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(
                    1.dp,
                    if (isDone) EmeraldSuccess.copy(alpha = 0.4f) else MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(if (isDone) EmeraldSuccess else MaterialTheme.colorScheme.surfaceVariant),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (isDone) Icons.Default.Check else Icons.Default.RadioButtonUnchecked,
                            contentDescription = "Toggle completion",
                            tint = if (isDone) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = step.stage.uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = if (isDone) EmeraldSuccess else GoldPrimary
                        )

                        Text(
                            text = step.title,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = step.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                            modifier = Modifier.padding(top = 4.dp)
                        ) {
                            Text(
                                text = "Recommended: ${step.suggestedProduct}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        }

        // Active Product Stack Section
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(imageVector = Icons.Default.Spa, contentDescription = null, tint = GoldPrimary, modifier = Modifier.size(20.dp))
                    Text(
                        text = "My Active Product Stack (${routineProductsEntities.size})",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = "+ Add More",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = GoldPrimary,
                    modifier = Modifier
                        .clickable { viewModel.setTab(AppNavTab.PRODUCTS) }
                        .padding(4.dp)
                )
            }
        }

        if (routineProductsEntities.isEmpty()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "No products in your daily routine yet",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Browse recommended styling products and tap 'Add to Routine'.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Button(
                            onClick = { viewModel.setTab(AppNavTab.PRODUCTS) },
                            colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary, contentColor = Color(0xFF261A00)),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Explore Products", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        } else {
            items(routineProductsEntities) { itemEntity ->
                val product = ProductCatalog.getProductById(itemEntity.productId)
                product?.let { prod ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.selectProductForDetails(prod) },
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
                                    text = prod.category.displayName.uppercase(),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color(prod.category.badgeColorHex),
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = prod.name,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "${prod.brandType} • Hold: ${prod.holdLevel}/5",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            IconButton(onClick = { viewModel.toggleProductInRoutine(prod) }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Remove from routine",
                                    tint = MaterialTheme.colorScheme.error.copy(alpha = 0.7f)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Saved Hairstyles Wishlist Section
        if (savedHairstylesEntities.isNotEmpty()) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Bookmark, contentDescription = null, tint = GoldPrimary, modifier = Modifier.size(20.dp))
                        Text(
                            text = "Saved Hairstyles Wishlist (${savedHairstylesEntities.size})",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            items(savedHairstylesEntities) { itemEntity ->
                val hairstyle = HairstyleCatalog.getHairstyleById(itemEntity.hairstyleId)
                hairstyle?.let { style ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.selectHairstyleForDetails(style) },
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
                                    text = style.category.uppercase(),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = GoldPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = style.name,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "${style.hairLength} • ${style.targetGender} • ${style.difficulty}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            IconButton(onClick = { viewModel.toggleSaveHairstyle(style) }) {
                                Icon(
                                    imageVector = Icons.Default.Bookmark,
                                    contentDescription = "Saved",
                                    tint = GoldPrimary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
