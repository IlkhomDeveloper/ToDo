package com.example.alarmclockdemo.data.room.daos

import androidx.room.*

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data:T) : Long

    @Delete
    fun delete(data: T) : Int

    @Update
    fun update(data: T) : Int
}