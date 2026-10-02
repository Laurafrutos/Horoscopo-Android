package com.laura.horoscopo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HoroscopeAdapter
    (val items: List<Horoscope>,
     val onitemclick:(position : Int) -> Unit) :
    RecyclerView.Adapter<HoroscopeViewHolder>() {



    //cual es la vista de cada elemento
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
        return HoroscopeViewHolder(view)

    }

    //cuales son los datos del elemento que esta en tal posiciom
    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int
    ) {
        val horoscope = items[position]
        holder.render(horoscope)
        holder.itemView.setOnClickListener {
            this.onitemclick(position)


        }

    }
//cuantas elementos tiene que mostrar
    override fun getItemCount(): Int {
       return items.size
    }
}


class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val signImageView: ImageView = view.findViewById(R.id.signimageview)
    val nameTextView: TextView = view.findViewById(R.id.nametextview)
    val dateTextView: TextView = view.findViewById(R.id.datetextview)


    fun render(horoscope: Horoscope){
        nameTextView.setText(horoscope.name)
        dateTextView.setText(horoscope.dates)
        signImageView.setImageResource(horoscope.sign)
    }
}


