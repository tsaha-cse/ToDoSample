package be.maximedupierreux.todoapplication.android.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tushar.todosample.DB_TODO_TABLE
import com.tushar.todosample.entity.Todo

@Dao
interface TodoDao {

    @Query("SELECT * from $DB_TODO_TABLE")
    suspend fun getAll(): List<Todo>

    @Insert
    suspend fun insert(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Query("DELETE FROM $DB_TODO_TABLE where id = :todoId")
    suspend fun delete(todoId: Int)
}