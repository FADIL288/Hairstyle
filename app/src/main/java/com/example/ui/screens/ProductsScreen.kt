package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.local.ProductCatalog
import com.example.data.model.FaceShape
import com.example.data.model.ProductCategory
import com.example.data.model.StylingProduct
import com.example.ui.components.FaceShapeBadge
import com.example.ui.components.HoldShineIndicator
import com.example.ui.components.ProductCard
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.GoldPrimary
import com.example.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ProductsScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val selectedFaceShape by viewModel.selectedFaceShape.collectAsStateWithLifecycle()
    val categoryFilter by viewModel.productCategoryFilter.collectAsStateWithLifecycle()
    val savedProducts by viewModel.savedProducts.collectAsStateWithLifecycle()
    val selectedProductDetail by viewModel.selectedProduct.collectAsStateWithLifecycle()

    val allProducts = ProductCatalog.products.filter { product ->
        val matchesCategory = categoryFilter == null || product.category == categoryFilter
        matchesCategory
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("products_screen"),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Header Info
        item {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "Styling Product Regimen",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Curated salon formulations (Pre-stylers, clays, pomades, & finishing sprays).",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Category Filter Chips
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = categoryFilter == null,
                    onClick = { viewModel.setProductCategoryFilter(null) },
                    label = { Text("All Categories") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = GoldPrimary.copy(alpha = 0.2f),
                        selectedLabelColor = GoldPrimary
                    )
                )

                ProductCategory.entries.forEach { category ->
                    FilterChip(
                        selected = categoryFilter == category,
                        onClick = { viewModel.setProductCategoryFilter(category) },
                        label = { Text(category.displayName) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(category.badgeColorHex).copy(alpha = 0.2f),
                            selectedLabelColor = Color(category.badgeColorHex)
                        )
                    )
                }
            }
        }

        // Products List
        items(allProducts) { product ->
            val isInRoutine = savedProducts.any { it.productId == product.id && it.isInRoutine }
            ProductCard(
                product = product,
                isInRoutine = isInRoutine,
                onRoutineToggle = { viewModel.toggleProductInRoutine(product) },
                onClick = { viewModel.selectProductForDetails(product) }
            )
        }
    }

    // Modal Bottom Sheet for Product Detail
    selectedProductDetail?.let { product ->
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ModalBottomSheet(
            onDismissRequest = { viewModel.selectProductForDetails(null) },
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .testTag("product_detail_sheet"),
                contentPadding = PaddingValues(bottom = 36.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color(product.category.badgeColorHex).copy(alpha = 0.2f)
                            ) {
                                Text(
                                    text = product.category.displayName.uppercase(),
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(product.category.badgeColorHex),
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }

                            Text(
                                text = product.priceTier,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = GoldPrimary
                            )
                        }

                        Text(
                            text = product.name,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = product.brandType,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // Hold & Shine Indicator Box
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            HoldShineIndicator(holdLevel = product.holdLevel, shineLevel = product.shineLevel)
                        }
                    }
                }

                // How to Apply Section
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
                        ),
                        border = BorderStroke(1.dp, GoldPrimary.copy(alpha = 0.4f))
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "How to Apply for Maximum Results",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = product.howToApply,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                // Barber Pro Tip
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Lightbulb, contentDescription = null, tint = GoldPrimary, modifier = Modifier.size(20.dp))
                            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                Text(
                                    text = "Stylist Pro Tip",
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = product.tips,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                // Key Ingredients
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = "Key Ingredients",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            product.keyIngredients.forEach { ing ->
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                                ) {
                                    Text(
                                        text = "• $ing",
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                // Add to Routine Action
                item {
                    val isInRoutine = savedProducts.any { it.productId == product.id && it.isInRoutine }
                    Button(
                        onClick = { viewModel.toggleProductInRoutine(product) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = if (isInRoutine) {
                            ButtonDefaults.buttonColors(containerColor = EmeraldSuccess.copy(alpha = 0.2f), contentColor = EmeraldSuccess)
                        } else {
                            ButtonDefaults.buttonColors(containerColor = GoldPrimary, contentColor = Color(0xFF261A00))
                        }
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = if (isInRoutine) Icons.Default.Check else Icons.Default.Add,
                                contentDescription = null
                            )
                            Text(
                                text = if (isInRoutine) "In Your Daily Routine" else "Add to Daily Routine",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
