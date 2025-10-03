package com.example.recyclerviewpractice

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewpractice.Models.Person
import com.example.recyclerviewpractice.R
import com.google.android.material.card.MaterialCardView

class PersonAdapter(
    private val personList: ArrayList<Person>,
    private val context: Context
) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item_layout, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val personModel = personList[position]
        holder.nameTv.text = personModel.name
        holder.fnameTv.text = personModel.fname
        holder.provinceTv.text = personModel.province
        holder.favrtTv.text = personModel.favourite.toString()
        if (personModel.imageurl != null) {
            holder.profileImg.setImageURI(personModel.imageurl)
        } else {
            holder.profileImg.setImageResource(R.drawable.ic_launcher_background)
        }
        holder.personCard.setOnClickListener {
            val nextActivity = Intent(context, ShowPerson::class.java)
            nextActivity.putExtra("Index",position)
            nextActivity.putExtra("Name",personModel.name)
            nextActivity.putExtra("FName",personModel.fname)
            nextActivity.putExtra("Province",personModel.province)
            nextActivity.putExtra("Favourite",personModel.favourite.toString())
            nextActivity.putExtra("Image",personModel.imageurl?.toString())

            context.startActivity(nextActivity)
        }

    }

    override fun getItemCount(): Int {
        return personList.size
    }

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTv: TextView = itemView.findViewById(R.id.nametv)
        val fnameTv: TextView = itemView.findViewById(R.id.fnametv)
        val provinceTv: TextView = itemView.findViewById(R.id.provincetv)
        val favrtTv: TextView = itemView.findViewById(R.id.fvrttv)
        val profileImg: ImageView = itemView.findViewById(R.id.profileimg)
        val personCard: MaterialCardView = itemView.findViewById(R.id.personCard)

    }
}
