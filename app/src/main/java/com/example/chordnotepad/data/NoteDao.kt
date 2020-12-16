package com.example.chordnotepad.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note : Note)

    @Query ("SELECT * FROM note_table ORDER BY id ASC")
    fun readAllData() : LiveData<List<Note>>

    @Delete
    suspend fun deleteNote(note : Note)
//
//    @Query("SELECT * FROM Note")
//    fun getAllNotes() : Single<List<Note>>
//
//    @Query ("SELECT * FROM Note WHERE title IN (:title)")
//    fun getNoteByTitle(title : String) : Single<Note>
}