package com.riadsafowan.recyclerView.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.riadsafowan.recyclerView.data.local.Person
import com.riadsafowan.recyclerView.data.local.PersonDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository(private val personDao: PersonDao) {

    @WorkerThread
    suspend fun insertToCart( person : Person) = withContext(Dispatchers.IO) {
        personDao.insertUsers(person)
    }

    @WorkerThread
    suspend fun getAllCart(): List<Person> {
        var count : List<Person>
        withContext(Dispatchers.IO) {
            count = personDao.getAll()
        }
        return count
    }
}