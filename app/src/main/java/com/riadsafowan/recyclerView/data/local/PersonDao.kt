package com.riadsafowan.recyclerView.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PersonDao {
    @Query("SELECT * FROM person")
     fun getAll(): List<Person>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertUsers(persons: Person)
}