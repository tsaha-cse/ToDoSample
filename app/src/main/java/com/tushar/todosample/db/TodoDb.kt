package com.tushar.todosample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import be.maximedupierreux.todoapplication.android.dao.TodoDao
import com.tushar.todosample.DB_TODO_TABLE
import com.tushar.todosample.entity.Todo

@Database(entities = [Todo::class], version = 1)
abstract class TodoDb : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        private var INSTANCE: TodoDb? = null

        fun getDatabase(context: Context): TodoDb {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDb::class.java,
                    DB_TODO_TABLE
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}