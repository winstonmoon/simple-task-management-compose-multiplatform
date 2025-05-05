package com.winstonmoon.simpletaskmanagement.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.winstonmoon.simpletaskmanagement.database.model.TaskEntity
import com.winstonmoon.simpletaskmanagement.model.Status
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskEntity: TaskEntity)

    @Query(
        value = """
            DELETE FROM task_entity
            WHERE id in (:id)
        """,
    )
    suspend fun deleteTask(id: Long)
    
    @Query(
        value = """
            SELECT * FROM task_entity
            WHERE status in (:status)
        """
    )
    fun getTasksByStatus(status: Status): Flow<List<TaskEntity>>
}