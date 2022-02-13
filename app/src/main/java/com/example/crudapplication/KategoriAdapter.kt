package com.example.crudapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crudapplication.model.DataItem
import com.squareup.picasso.Picasso

class KategoriAdapter(val dataKategori: List<DataItem?>?) :
    RecyclerView.Adapter<KategoriAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgView      = view.findViewById<ImageView>(R.id.item_image)
        val textKategori = view.findViewById<TextView>(R.id.item_tv_kategori)
        val textTanggal  = view.findViewById<TextView>(R.id.item_tv_author)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_kategori,parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textKategori.text = dataKategori?.get(position)?.namaKategori
        holder.textTanggal.text = dataKategori?.get(position)?.createdAt

        Picasso.get()
            .load(R.drawable.por1)
            .placeholder(R.drawable.ic_launcher_foreground) //sementara loading gamabr ini yang ditampilkan
            .error(R.drawable.ic_launcher_background)
            .into(holder.imgView)

    }

    override fun getItemCount(): Int {
        if (dataKategori != null){
            return dataKategori.size
        }
        return 0
    }
}