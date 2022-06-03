package com.childintherye.noteapplication.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.childintherye.noteapplication.R
import com.childintherye.noteapplication.adapter.myNotesAdapter
import com.childintherye.noteapplication.databinding.ActivityMainBinding
import com.childintherye.noteapplication.roomdb.myNotesDatabase
import com.childintherye.noteapplication.sample.Notes
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val database = Room.databaseBuilder(applicationContext,myNotesDatabase::class.java,"Notes").build()
        val myNotesDao = database.myNotesDao()
        compositeDisposable.add(
            myNotesDao.getAll().subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe(this::goBack))





    }
    private fun goBack(listOfNotes : List<Notes>){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = myNotesAdapter(listOfNotes)
        binding.recyclerView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.notes_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_note) {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("info", "new")
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
