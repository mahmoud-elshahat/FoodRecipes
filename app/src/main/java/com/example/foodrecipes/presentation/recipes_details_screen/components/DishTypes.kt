package com.example.foodrecipes.presentation.recipes_details_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.foodrecipes.presentation.theme.Primary
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DishTypes(dishTypes: List<String>) {
    LazyRow(modifier = Modifier.padding(top = 10.dp)) {
        items(dishTypes.size) { index ->
            Chip(
                modifier = Modifier.padding(end = 6.dp),
                onClick = { },
                border = BorderStroke(
                    ChipDefaults.OutlinedBorderSize, Primary
                ),
                colors = ChipDefaults.chipColors(
                    backgroundColor = Color.White, contentColor = Primary
                ),
            ) {
                Text(
                    dishTypes[index].replaceFirstChar { firstChar -> firstChar.uppercase() }
                )
            }

        }
    }
}