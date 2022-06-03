package com.childintherye.noteapplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.childintherye.noteapplication.databinding.RecyclerRowBinding
import com.childintherye.noteapplication.sample.Notes
import com.childintherye.noteapplication.view.MainActivity2

class myNotesAdapter(val myNotesList : List<Notes>) : RecyclerView.Adapter<myNotesAdapter.myNotesHolder>() {
    class myNotesHolder( val recyclerRowBinding : RecyclerRowBinding) : RecyclerView.ViewHolder(recyclerRowBinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myNotesHolder {
        val recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return myNotesHolder(recyclerRowBinding)
    }

    override fun onBindViewHolder(holder: myNotesHolder, position: Int) {
        holder.recyclerRowBinding.recyclerViewText.text = myNotesList.get(position).head
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context,MainActivity2::class.java)
            intent.putExtra("theNote",myNotesList.get(position))
            intent.putExtra("info", "old")
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return myNotesList.size
    }
}