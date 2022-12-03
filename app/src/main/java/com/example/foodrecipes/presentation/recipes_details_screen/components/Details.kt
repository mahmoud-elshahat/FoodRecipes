package com.example.foodrecipes.presentation.recipes_details_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.foodrecipes.R
import com.example.foodrecipes.domain.model.recipe_details.RecipeDetails
import com.example.foodrecipes.presentation.theme.TextPrimary
import com.example.foodrecipes.presentation.theme.TextSecondary


@Composable
fun DetailsHeader(recipeDetails: RecipeDetails) {
    Image(
        painter = rememberAsyncImagePainter(recipeDetails.image),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .clip(RoundedCornerShape(20.dp)),
        contentScale = ContentScale.FillBounds,
    )
    Text(
        text = recipeDetails.title,
        modifier = Modifier.padding(top = 16.dp, bottom = 6.dp),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = TextPrimary,
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
    )
    val openDialog = remember { mutableStateOf(false) }
    if (openDialog.value) {
        AlertDialog(onDismissRequest = {
            openDialog.value = false
        }, title = {
            Text(
                text = stringResource(id = R.string.instructions),
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }, text = {
            Text(
                recipeDetails.instructions ?: "",
                color = TextPrimary,
                fontSize = 15.sp,
                modifier = Modifier
            )
        }, buttons = {})
    }
    Box(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .clickable {
                openDialog.value = true
            },
    ) {
        var hasVisualOverflow by remember { mutableStateOf(false) }
        Text(
            text = recipeDetails.instructions ?: "",
            maxLines = 3,
            onTextLayout = { hasVisualOverflow = it.hasVisualOverflow },
            color = TextSecondary,
            fontSize = 15.sp,
        )

        if (hasVisualOverflow) {
            Row(
                modifier = Modifier.align(Alignment.BottomEnd), verticalAlignment = Alignment.Bottom
            ) {
                Spacer(modifier = Modifier)
                Text(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(start = 8.dp),
                    text = stringResource(R.string.recipe_details_instructions_show_more),
                    color = MaterialTheme.colors.primary,
                    fontSize = 15.sp,
                )
            }
        }
    }
}