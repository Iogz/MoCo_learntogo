package com.example.moco_learntogo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moco_learntogo.data.todo.room.Todo
import com.example.moco_learntogo.ui.theme.Beige1
import com.example.moco_learntogo.ui.theme.Beige3


@Composable
fun DetailScreen(
    selectedId: Long,
    onNavigate: () -> Unit,
) {
    val viewModel = viewModel(
        DetailViewModel::class.java,
        factory = DetailViewModelFactory(selectedId)
    )
    val state by viewModel.state.collectAsState()
    DetailScreenComponent(todoText = state.todo,
        onTodoTextChange = { viewModel.onTextChange(it) },
        timeText = state.time,
        onTimeTextChange = { viewModel.onTimeChange(it) },
        onNavigate = { onNavigate() },
        onSaveTodo = { viewModel.insert(it) },
        selectedId = state.selectId)
}

@Composable
fun DetailScreenComponent(
    todoText: String,
    onTodoTextChange: (String) -> Unit,
    timeText: String,
    onTimeTextChange: (String) -> Unit,
    onNavigate: () -> Unit,
    onSaveTodo: (Todo) -> Unit,
    selectedId: Long,
) {

    val isTodoEdit = selectedId == -1L
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Beige1)
    ) {
        Column(modifier = Modifier
            .padding(start = 10.dp, top = 10.dp, bottom = 10.dp, end = 10.dp),
        ) {
            Spacer(modifier = Modifier.size(10.dp))
            GetLogo()
            Spacer(modifier = Modifier.size(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp)),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = todoText,
                    onValueChange = { onTodoTextChange(it) },
                    label = { Text(text = "Todo-Name") }
                )
                Spacer(modifier = Modifier.size(16.dp))
                OutlinedTextField(
                    value = timeText,
                    onValueChange = { onTimeTextChange(it) },
                    label = { Text(text = "Todo-Time") }
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(
                    modifier = Modifier
                        .height(50.dp)
                        .width(100.dp),
                    onClick = {
                        val todo = if (isTodoEdit) Todo(todoText, timeText)
                        else Todo(todoText, timeText, id = selectedId)
                        onSaveTodo(todo)
                        onNavigate()
                    } ,
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Beige3)
                )
                {
                    val text = if (isTodoEdit) "Save" else "Update todo"
                    Text(
                        text = text,
                        fontSize = 18.sp,
                    )
                }
            }
        }
    }

}
