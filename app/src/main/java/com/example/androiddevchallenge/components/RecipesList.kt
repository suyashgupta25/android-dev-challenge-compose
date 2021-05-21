package com.example.androiddevchallenge.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.Recipe
import com.example.androiddevchallenge.model.RecipesData
import com.example.androiddevchallenge.ui.theme.DarkGray
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun RecipesListScreen() {
    Column {
        val emptyView = true
        if (emptyView) {
            EmptyView(Modifier.weight(1f))
        } else {
            RecipeListView(Modifier.weight(1f))
        }

        BottomView()
    }
}

@Composable
fun RecipeListView(modifier: Modifier) {
    LazyColumn(
        modifier = modifier.background(DarkGray)
    ) {
        items(0) {
            RecipeCard()
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
        }
    }
}

@Composable
private fun BottomView() {
    val itemsSelected by remember { mutableStateOf(true) }
    if (itemsSelected) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Total price",
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colors.onSurface
            )
            Text(text = "$ X", color = MaterialTheme.colors.onSurface)
        }
        AddButton()
    } else {
        AddButton()
    }
}

@Composable
fun EmptyView(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(DarkGray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No recipes added",
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.h5
        )
    }
}


@Composable
fun AddButton() {
    var text by remember { mutableStateOf("Add recipe") }
    Button(
        onClick = { text = "Clicked!" },
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
    ) {
        Text(text = text)
    }
}

@Composable
fun RecipeCard(onClick: () -> Unit = {}) {
    Card(
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        val recipe = Recipe(
            name = RecipesData.names.random(),
            price = RecipesData.randomPrice,
            color = RecipesData.randomColor
        )
        Row(
            Modifier
                .padding(16.dp)
                .animateContentSize()
        ) {
            val centerVerticalAlignment = Modifier.align(Alignment.CenterVertically)
            ColorView(color = recipe.color, centerVerticalAlignment)
            RecipeName(
                recipe,
                centerVerticalAlignment
                    .weight(1f)
                    .padding(start = 8.dp)
            )
            VerticalDivider(centerVerticalAlignment)
            RecipePrice(recipe, centerVerticalAlignment)
        }
    }
}

@Composable
fun ExpandedCard(onClick: () -> Unit = {}) {
    Card(
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Column(
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .animateContentSize()
                .clickable { onClick() }
        ) {
            val recipe = Recipe(
                name = RecipesData.names.random(),
                price = RecipesData.randomPrice,
                color = RecipesData.randomColor
            )
            ColorView2(color = recipe.color)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            Row {
                val centerVerticalAlignment = Modifier.align(Alignment.CenterVertically)
                RecipeName(recipe, centerVerticalAlignment.weight(1f))
                VerticalDivider(centerVerticalAlignment)
                RecipePrice(recipe, centerVerticalAlignment)
            }
        }
    }
}

@Composable
fun RecipePrice(
    recipe: Recipe,
    modifier: Modifier = Modifier
) {
    Text(
        text = String.format("$ %.2f", recipe.price / 100),
        modifier = modifier
            .padding(start = 8.dp)
            .width(52.dp),
        style = MaterialTheme.typography.subtitle2.copy(fontStyle = FontStyle.Italic)
    )
}

@Composable
fun RecipeName(
    recipe: Recipe,
    modifier: Modifier = Modifier
) {
    Text(
        text = recipe.name,
        modifier.padding(start = 8.dp, end = 8.dp)
    )
}

@Composable
fun VerticalDivider(modifier: Modifier) {
    Spacer(
        modifier = modifier
            .width(1.dp)
            .height(42.dp)
            .background(Color.DarkGray)
    )
}

@Composable
fun ColorView(color: Color, modifier: Modifier) {
    Spacer(
        modifier = modifier
            .width(8.dp)
            .height(52.dp)
            .background(color, shape = RoundedCornerShape(6.dp))
    )
}

@Composable
fun ColorView2(color: Color, modifier: Modifier = Modifier) {
    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(86.dp)
            .background(color, shape = RoundedCornerShape(6.dp))
    )
}

@Composable
fun ColorFilter() {
    Row(
        Modifier
            .background(DarkGray)
            .padding(vertical = 8.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        RecipesData.colors.forEach { color ->
            ColorView3(color = color)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun ColorView3(color: Color, modifier: Modifier = Modifier) {
    Spacer(
        modifier = modifier
            .width(64.dp)
            .height(24.dp)
            .background(color, shape = RoundedCornerShape(12.dp))
//            .border(2.dp, Color.White, RoundedCornerShape(12.dp))
    )
}

@Preview
@Composable
fun ColorViewPreview() {
    MyTheme {
        RecipesListScreen()
    }
}