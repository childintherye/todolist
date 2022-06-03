package com.childintherye.noteapplication.sample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Notes (
    @ColumnInfo(name = "name")
    var head : String,
    @ColumnInfo(name = "note")
    var note : String, ): Serializable{
    @PrimaryKey(autoGenerate = true)
    var id = 0


}