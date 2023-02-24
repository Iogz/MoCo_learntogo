package com.example.moco_learntogo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.moco_learntogo.data.todo.room.Todo
import com.example.moco_learntogo.ui.theme.Beige2


@Composable
fun TodoItem(
    todo: Todo,
    onChecked: (Boolean) -> Unit,
    onDelete: (Todo) -> Unit,
    onNavigation: (Todo) -> Unit,
) {

    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(start = 10.dp, top = 10.dp, bottom = 10.dp, end = 10.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Column {
            Card {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                        .size(80.dp)
                        .background(Beige2)
                        .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                ) {

                    Checkbox(
                        checked = todo.isComplete,
                        onCheckedChange = { onChecked(it) }
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 10.dp))
                    {
                        Text(text = todo.todo)
                        Spacer(modifier = Modifier.size(2.dp))
                        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium)
                        {
                            Text(text = todo.time)
                        }
                    }

                    Spacer(modifier = Modifier.size(16.dp))

                    IconButton(onClick = { onDelete(todo) }
                    ) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                    }

                }
            }
        }
    }
}

