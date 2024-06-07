package santa.barbara.appdsm.citasHelper

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import santa.barbara.appdsm.R
import santa.barbara.appdsm.detalle_citas

class AdaptadorCitas(private var Datos: List<tbCitas>) : RecyclerView.Adapter<ViewHolderCitas>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCitas {
        val vista =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_card_citas, parent, false)

        return ViewHolderCitas(vista)
    }

    override fun getItemCount() = Datos.size

    override fun onBindViewHolder(holder: ViewHolderCitas, position: Int) {
        val item = Datos[position]
        holder.lblPacienteCita.text = item.paciente
        holder.lblFechaCita.text = item.fecha
        holder.lblHoraCita.text = item.hora

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val pantallaDetalleCitas = Intent(context, detalle_citas::class.java)
            pantallaDetalleCitas.putExtra("id", item.id)
            pantallaDetalleCitas.putExtra("paciente", item.paciente)
            pantallaDetalleCitas.putExtra("fecha", item.fecha)
            pantallaDetalleCitas.putExtra("hora", item.hora)
            pantallaDetalleCitas.putExtra("motivo", item.motivo)
            pantallaDetalleCitas.putExtra("doctor", item.doctor)
            context.startActivity(pantallaDetalleCitas)
        }
    }
}