package com.example.moco_learntogo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.moco_learntogo.data.todo.room.Todo
import com.example.moco_learntogo.ui.theme.Beige1
import com.example.moco_learntogo.ui.theme.Beige3
import java.text.DateFormat
import java.util.*

@Composable
fun HomeScreen(onNavigate: (Todo?) -> Unit) {

    val navController = rememberNavController()

    val viewModel = viewModel(HomeViewModel::class.java)
    val state by viewModel.state.collectAsState()

    Scaffold(floatingActionButton = {
        FloatingActionButton(
            backgroundColor = Beige3,
            onClick = { onNavigate(null) }
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    },

        ) {

        Column (modifier = Modifier
            .fillMaxSize()
            .background(Beige1)
            .padding(start = 10.dp, top = 10.dp, bottom = 10.dp, end = 10.dp)
        ) {
            GetLogo()
            Spacer(modifier = Modifier.size(30.dp))
            GetDateAndTime()
            Spacer(modifier = Modifier.size(30.dp))
            LazyColumn(modifier = Modifier
                .fillMaxSize()
            ) {
                items(state.todoList) { todo ->
                    TodoItem(
                        todo = todo,
                        onChecked = { viewModel.updateTodo(it, todo.id) },
                        onDelete = { viewModel.delete(it) },
                        onNavigation = { onNavigate(it) }
                    )
                }
            }

        }
    }
}

@Composable
fun GetDateAndTime () {
    val calendar = Calendar.getInstance().time
    val dateFormat = DateFormat.getDateInstance(DateFormat.FULL).format(calendar)
    val timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar)

    Column {
        Text(
            text = "$dateFormat",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp)
        Text(
            text = "$timeFormat",
            fontSize = 20.sp)
    }
}

@Composable
fun GetLogo () {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .size(250.dp),
        painter = painterResource(id = R.drawable.beige_logo3),
        contentDescription = "logo")
}