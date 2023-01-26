package com.example.rmfxaplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GRecycleView : AppCompatActivity() {
    var totalLikes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycle_view)

        // Definir lista
        val listaEntrenador = arrayListOf<BEntrenador>()
        listaEntrenador
            .add(BEntrenador(1, "Francisco", "Xavier"))
        listaEntrenador
            .add(BEntrenador(2,"Rodríguez", "Machado"))
        // Inicializar Recycler View
        val recyclerView = findViewById<RecyclerView>(R.id.rv_entrenadores)
        inicializarRecyclerView(listaEntrenador,recyclerView)
    }
    fun inicializarRecyclerView(
        lista: ArrayList<BEntrenador>,
        recyclerView: RecyclerView
    ){
        val adaptador = FRecyclerViewAdaptadorNombreCedula(
            this, // Contexto
            lista, // Arreglo datos
            recyclerView // Reclycler view
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    fun aumentarTotalLikes(){
        totalLikes = totalLikes + 1
        val totalLikesTextView = findViewById<TextView>(R.id.tv_total_likes)
        totalLikesTextView.text = totalLikes.toString()
    }
}