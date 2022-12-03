package com.example.foodrecipes.presentation.recipes_details_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodrecipes.domain.model.recipe_details.RecipeDetails
import com.example.foodrecipes.presentation.theme.LightBorder
import com.example.foodrecipes.presentation.theme.Primary
import com.example.foodrecipes.presentation.theme.TextSecondary
import kotlin.math.roundToInt
import com.example.foodrecipes.R


@Composable
fun DetailsItems(recipeDetails: RecipeDetails) {
    Box(
        Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(1.dp, LightBorder), RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Item(
                stringResource(id = R.string.recipe_details_likes_title),
                recipeDetails.aggregateLikes
            )
            Item(
                stringResource(id = R.string.recipe_details_minutes_title),
                recipeDetails.readyInMinutes
            )
            Item(
                stringResource(id = R.string.recipe_details_serving_title),
                recipeDetails.servings
            )
            Item(
                stringResource(id = R.string.recipe_details_price_title),
                recipeDetails.pricePerServing.roundToInt(),
                contentAddOn = "$"
            )
        }
    }
}

@Composable
fun Item(title: String, content: Int, contentAddOn: String = "") {
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$content$contentAddOn",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Primary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = TextSecondary,
            fontSize = 14.sp,
        )
    }
}