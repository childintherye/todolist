package com.childintherye.noteapplication.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.childintherye.noteapplication.sample.Notes

@Database(entities = [Notes::class], version = 1)
abstract class myNotesDatabase : RoomDatabase() {
    abstract fun myNotesDao(): myNotesDao }