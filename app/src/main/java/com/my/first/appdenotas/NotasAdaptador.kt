package com.my.first.appdenotas

import android.content.Context
import android.content.Intent
import android.renderscript.Type
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NotasAdaptador (
    private var notas : List<Nota>,
            context: Context) : RecyclerView.Adapter<NotasAdaptador.NotaViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nota, parent, false)
        return NotaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notas.size
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = notas[position]
        holder.itemTitulo.text = nota.titulo
        holder.itemDescription.text = nota.descripcion

        holder.ivActualizar.setOnClickListener { val intent = Intent(holder.itemView.context,
            ActualizarNotaActivity::class.java).apply { putExtra("id_nota", nota.id) }

            holder.itemView.context.startActivity(intent)
            Toast.makeText(
                holder.itemView.context,
                "EL id de la nota seleccionada es ${nota.id}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTitulo: TextView = itemView.findViewById(R.id.item_titulo)
        val itemDescription: TextView = itemView.findViewById(R.id.item_descripcion)
        val ivActualizar : ImageView = itemView.findViewById(R.id.ivActualizar)
    }
    fun refrescarLista (nuevaNota : List<Nota>){
        notas = nuevaNota
        notifyDataSetChanged()
    }
}

