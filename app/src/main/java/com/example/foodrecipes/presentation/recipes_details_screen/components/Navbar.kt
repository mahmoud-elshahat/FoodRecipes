package com.example.foodrecipes.presentation.recipes_details_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.foodrecipes.presentation.theme.TextPrimary
import com.example.foodrecipes.R

@Composable
fun Navbar(navController: NavController) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painterResource(R.drawable.ic_back_arrow),
            contentDescription = "back_button",
            modifier = Modifier
                .size(24.dp)
                .clickable { navController.navigateUp() },
        )
        Text(
            text = stringResource(R.string.recipe_details_title),
            color = TextPrimary,
            fontSize = 18.sp,
        )
        Image(
            painterResource(R.drawable.ic_menu),
            contentDescription = "menu_button",
            modifier = Modifier.size(24.dp),
        )
    }
}