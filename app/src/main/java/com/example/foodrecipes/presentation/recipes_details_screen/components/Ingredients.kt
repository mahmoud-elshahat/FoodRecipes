package com.example.foodrecipes.presentation.recipes_details_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.foodrecipes.data.remote.Constants
import com.example.foodrecipes.domain.model.recipe_details.Ingredient
import com.example.foodrecipes.presentation.theme.TextPrimary
import com.example.foodrecipes.presentation.theme.TextSecondary
import com.example.foodrecipes.R

@Composable
fun Ingredients(ingredients: List<Ingredient>) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.recipe_details_ingredients_title),
                overflow = TextOverflow.Ellipsis,
                color = TextPrimary,
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
            )
            Text(
                text = "${ingredients.size} " + stringResource(R.string.recipe_details_ingredient_items),
                overflow = TextOverflow.Ellipsis,
                color = TextSecondary,
                fontSize = 15.sp,
                textAlign = TextAlign.Start,
            )
        }
        LazyRow {
            items(ingredients.size) { index ->
                IngredientItem(ingredients[index])
            }
        }
    }
}

@Composable
fun IngredientItem(ingredient: Ingredient) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 4.dp),
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFf8f8f8))
                .padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(ingredient.image
                    .let {
                        (Constants.IMAGE_BASE_URL + ingredient.image)
                    }
                ),
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.FillBounds,
            )
        }
        Text(
            modifier = Modifier
                .width(110.dp)
                .padding(top = 6.dp, bottom = 4.dp),
            text = ingredient.name,
            overflow = TextOverflow.Ellipsis,
            color = TextPrimary,
            maxLines = 1,
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
        )
        Text(
            text = "${ingredient.amount} ${ingredient.unit}",
            overflow = TextOverflow.Ellipsis,
            color = TextSecondary,
            fontSize = 12.sp,
            textAlign = TextAlign.Start,
        )
    }
}