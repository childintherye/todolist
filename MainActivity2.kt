package com.childintherye.noteapplication.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.room.Room
import com.childintherye.noteapplication.databinding.ActivityMain2Binding
import com.childintherye.noteapplication.roomdb.myNotesDao
import com.childintherye.noteapplication.roomdb.myNotesDatabase
import com.childintherye.noteapplication.sample.Notes
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Package as Package

private lateinit var binding: ActivityMain2Binding
private lateinit var database: myNotesDatabase
private lateinit var notesDao: myNotesDao
val compositeDisposable = CompositeDisposable()
var notesMain: Notes? = null
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        database = Room.databaseBuilder(applicationContext, myNotesDatabase::class.java, "Places").build()
        notesDao = database.myNotesDao()
        val intent = intent
        val info = intent.getStringExtra("info")
        if (info == "new"){
            binding.saveButton.visibility = View.VISIBLE
            binding.button2.visibility = View.GONE
        } else {
            notesMain.let { val intent = intent
                val info = intent.getStringExtra("info")
                binding.headText.setText(it!!.head)
                binding.textNotes.setText(it.note)
                binding.saveButton.visibility = View.GONE
                binding.button2.visibility = View.VISIBLE }


        }

    }
    fun saveButton(view : View){
        val note = Notes(binding.headText.text.toString(), binding.textNotes.text.toString())
        compositeDisposable.add(notesDao.insert(note).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()).subscribe(this::goBack))


    }
    private fun goBack(){
        val intent = Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
    fun deleteButton(view : View){
        notesMain?.let{
            compositeDisposable.add(
                myNotesDao.delete(it).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::goBack))
        }



    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}