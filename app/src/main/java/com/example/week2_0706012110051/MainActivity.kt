package com.example.week2_0706012110051

import Adapter.ListHewanRVAdapter
import Database.GlobalVar
import Interface.CardListener
import Model.Ayam
import Model.Kambing
import Model.Sapi
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week2_0706012110051.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CardListener {

    private lateinit var viewBind: ActivityMainBinding
    private val adapter=ListHewanRVAdapter(GlobalVar.listDataHewan, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        supportActionBar?.hide()
        setupRecyclerView()
        listener()

    }

    private fun listener(){
        viewBind.tambahHewanFAB.setOnClickListener {
            val myIntent = Intent(this, TambahHewanActivity::class.java)
            startActivity(myIntent)
        }
    }

    private fun notification(){
        if(GlobalVar.listDataHewan.isNotEmpty()){
            viewBind.middleTextView.visibility = View.GONE
        }
        else if(GlobalVar.listDataHewan.isEmpty())
        {
            viewBind.middleTextView.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        GlobalVar.filterListDataHewan.clear()
        viewBind.ayamButton.setOnClickListener {
            GlobalVar.filterListDataHewan.clear()
            for (item in GlobalVar.listDataHewan){
                if (item is Ayam){
                    GlobalVar.filterListDataHewan.add(item)
                }
            }
            viewBind.listDataHewanRecyclerView.adapter = ListHewanRVAdapter(GlobalVar.filterListDataHewan,this)
        }
        viewBind.sapiButton.setOnClickListener {
            GlobalVar.filterListDataHewan.clear()
            for (item in GlobalVar.listDataHewan){
                if (item is Sapi){
                    GlobalVar.filterListDataHewan.add(item)
                }
            }
            viewBind.listDataHewanRecyclerView.adapter = ListHewanRVAdapter(GlobalVar.filterListDataHewan,this)
        }
        viewBind.kambingButton.setOnClickListener {
            GlobalVar.filterListDataHewan.clear()
            for (item in GlobalVar.listDataHewan){
                if (item is Kambing){
                    GlobalVar.filterListDataHewan.add(item)
                }
            }
            viewBind.listDataHewanRecyclerView.adapter = ListHewanRVAdapter(GlobalVar.filterListDataHewan,this)
        }
        viewBind.resetButton.setOnClickListener{
            GlobalVar.filterListDataHewan.clear()
            viewBind.listDataHewanRecyclerView.adapter = ListHewanRVAdapter(GlobalVar.listDataHewan, this)
        }
        adapter.notifyDataSetChanged()
        if(GlobalVar.listDataHewan.isNotEmpty()){
            viewBind.middleTextView.visibility = View.GONE
        }
        else
        {
            viewBind.middleTextView.visibility = View.VISIBLE

        }
    }



    //untuk mengatur tampilan di Activity Recycler View. search google??
    private fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(baseContext) // lurus ke bawah
        viewBind.listDataHewanRecyclerView.layoutManager = layoutManager // set layout
        viewBind.listDataHewanRecyclerView.adapter = adapter // set adapter
    }

    override fun onCardClick(position: Int) {

    }
}