package com.childintherye.noteapplication.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.childintherye.noteapplication.sample.Notes
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface myNotesDao {
    @Query("SELECT * FROM Notes")
    fun getAll() : Flowable<List<Notes>>
    @Insert
    fun insert(notes : Notes) : Completable
    @Delete
    fun delete(notes: Notes) : Completable
}