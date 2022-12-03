package com.example.foodrecipes.presentation.recipes_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodrecipes.R
import com.example.foodrecipes.presentation.theme.TextPrimary
import com.example.foodrecipes.presentation.theme.TextSecondary

@Composable
fun Header() {
    Column(
        modifier = Modifier.padding
            (bottom = 20.dp, top = 20.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = stringResource(R.string.recipes_welcome_message),
                    color = TextSecondary,
                    fontSize = 16.sp,
                )
                Text(
                    text = stringResource(R.string.recipes_user_name),
                    color = TextPrimary,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 6.dp),
                )
            }
            Image(
                painterResource(R.drawable.profile_image),
                contentDescription = "profile_image",
                modifier = Modifier
                    .size(54.dp)
                    .clip(RoundedCornerShape(80.dp)),
                contentScale = ContentScale.Crop
            )
        }
        var text by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .clip(RoundedCornerShape(10.dp)),
            placeholder = { Text(text = "Search by recipes", color = TextSecondary) },
            maxLines = 1,
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    tint = TextSecondary,
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = TextPrimary,
                backgroundColor = Color(0xFFF8F8F8),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }

}