package com.example.week2_0706012110051

import Adapter.ListHewanRVAdapter
import Database.GlobalVar
import Interface.CardListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        notification()

    }

    override fun onResume() {
        super.onResume()
    }

    private fun notification(){
        if(GlobalVar.listDataHewan.isNotEmpty()){
            viewBind.middleTextView.setText("")
        }
        else if(GlobalVar.listDataHewan.isEmpty())
        {
            viewBind.middleTextView.setText("Empty")
        }
    }

    private fun listener(){
        viewBind.tambahHewanFAB.setOnClickListener {
            val myIntent = Intent(this, TambahHewanActivity::class.java)
            startActivity(myIntent)
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