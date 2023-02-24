package com.example.moco_learntogo

import android.content.Context
import com.example.moco_learntogo.data.todo.TodoDataSource

object Graph {
    lateinit var database: TodoDatabase
        private set
    val todoRepo by lazy {
        TodoDataSource(database.todoDao())
    }

    fun provide(context: Context) {
        database = TodoDatabase.getDatabase(context)
    }
}