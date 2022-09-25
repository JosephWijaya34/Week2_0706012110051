package Adapter

import Database.GlobalVar
import Interface.CardListener
import Model.*
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.week2_0706012110051.R
import com.example.week2_0706012110051.TambahHewanActivity
import com.example.week2_0706012110051.databinding.HewancardBinding

class ListHewanRVAdapter(val listHewan: ArrayList<Hewan>, val CardListener: CardListener) :
    RecyclerView.Adapter<ListHewanRVAdapter.viewHolder>() {
    class viewHolder(itemView: View, val cardListener1: CardListener) :
        RecyclerView.ViewHolder(itemView) {
        val viewBind=HewancardBinding.bind(itemView)

        fun setData(data: Hewan) {
            viewBind.namaTextView.text=data.nama
            viewBind.jenisTextView.text=data.jenis
            viewBind.usiaTextView.text=data.usia

            if (!data.imageUri!!.isEmpty()) {
                viewBind.fotoCard.setImageURI(Uri.parse(data.imageUri))
            }
            itemView.setOnClickListener {
                cardListener1.onCardClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val view=layoutInflater.inflate(R.layout.hewancard, parent, false)
        return viewHolder(view, CardListener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(listHewan[position])
        holder.viewBind.deleteButton.setOnClickListener {
            GlobalVar.listDataHewan.removeAt(position)
            Toast.makeText(it.context, "Delete Success", Toast.LENGTH_SHORT).show()
        }
        holder.viewBind.editButton.setOnClickListener {
            val myIntent=Intent(it.context, TambahHewanActivity::class.java).putExtra(
                "position", position
            )
            it.context.startActivity(myIntent)
        }

        holder.viewBind.playSoundButton.setOnClickListener {
            if(GlobalVar.filterListDataHewan.isEmpty()){
                    Toast.makeText(it.context, GlobalVar.listDataHewan.get(position).speak(), Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(it.context, GlobalVar.filterListDataHewan.get(position).speak(), Toast.LENGTH_SHORT).show()
            }
        }
        holder.viewBind.foodButton.setOnClickListener {
            if(GlobalVar.filterListDataHewan.isEmpty()){
                    if(GlobalVar.listDataHewan.get(position).jenis == "AYAM"){
                        Toast.makeText(it.context, GlobalVar.listDataHewan.get(position).eat(biji = Biji()), Toast.LENGTH_SHORT).show()
                    } else{
                        Toast.makeText(it.context, GlobalVar.listDataHewan.get(position).eat(rumput = Rumput()), Toast.LENGTH_SHORT).show()
                    }
            }else{
                    if(GlobalVar.filterListDataHewan.get(position).jenis == "AYAM"){
                        Toast.makeText(it.context, GlobalVar.listDataHewan.get(position).eat(biji = Biji()), Toast.LENGTH_SHORT).show()
                    } else{
                        Toast.makeText(it.context, GlobalVar.listDataHewan.get(position).eat(rumput = Rumput()), Toast.LENGTH_SHORT).show()
                    }
            }
            }
        }

    override fun getItemCount(): Int {
        return listHewan.size
    }
}

