package com.tushar.todosample.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tushar.todosample.DB_COL_DUE_DATE
import com.tushar.todosample.DB_COL_TITLE
import com.tushar.todosample.DB_TODO_TABLE

@Entity(tableName = DB_TODO_TABLE)
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = DB_COL_TITLE) var taskTitle: String? = null,
    @ColumnInfo(name = DB_COL_DUE_DATE) var dueDate: String? = null
)