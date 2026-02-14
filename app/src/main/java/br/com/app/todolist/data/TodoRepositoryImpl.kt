package br.com.app.todolist.data

import br.com.app.todolist.domain.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl(
    private val dao: TodoDao
) : TodoRepository {
    override suspend fun insert(title: String, description: String?) {
        val entity = TodoEntity(
            title = title,
            description = description,
            isCompleted = false
        )

        dao.insert(entity)
    }

    override suspend fun updateCompleted(id: Long, isCompleted: Boolean) {
        val existsEntity = dao.getById(id) ?: return
        val uptadeEntity = existsEntity.copy(isCompleted = isCompleted)
        dao.insert(uptadeEntity)
    }

    override suspend fun delete(id: Long) {
        val existsEntity = dao.getById(id) ?: return
        dao.delete(existsEntity)
    }

    override fun getAll(): Flow<List<Todo>> {
        return dao.getAll().map { entities ->
            entities.map { entity ->
                Todo(
                    id = entity.id,
                    title = entity.title,
                    description = entity.description,
                    isCompleted = entity.isCompleted
                )
            }
        }
    }

    override suspend fun getById(id: Long): Todo? {
        return dao.getById(id)?.let {
            Todo(
                id = it.id,
                title = it.title,
                description = it.description,
                isCompleted = it.isCompleted
            )
        }
    }
}