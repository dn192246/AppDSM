package santa.barbara.appdsm.pacientesHelper

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import santa.barbara.appdsm.R
import santa.barbara.appdsm.detalle_paciente

class AdaptadorPacientes(private var Datos: MutableList<tbPacientes>) :
    RecyclerView.Adapter<ViewHolderPacientes>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPacientes {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_card_pacientes, parent, false)
        return ViewHolderPacientes(vista)
    }

    override fun getItemCount() = Datos.size

    override fun onBindViewHolder(holder: ViewHolderPacientes, position: Int) {
        //Asigno los valores a los textView de la card
        val item = Datos[position]
        holder.lblPacienteNombre.text = item.nombreMascota
        holder.lblPacienteEspecie.text = item.especie
        holder.lblRazaPaciente.text = item.raza
        holder.itemView.tag = item.id

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, detalle_paciente::class.java)
            intent.putExtra(
                "id",
                item.id
            )
            intent.putExtra(
                "nombreMascota",
                item.nombreMascota
            )
            intent.putExtra(
                "nombreDuenio",
                item.nombreDuenio
            )
            intent.putExtra(
                "especie",
                item.especie
            )
            intent.putExtra(
                "raza",
                item.raza
            )
            intent.putExtra(
                "peso",
                item.peso
            )
            intent.putExtra(
                "tamanio",
                item.tamanio
            )
            intent.putExtra(
                "sexo",
                item.sexo
            )
            intent.putExtra(
                "fechaNacimiento",
                item.fechaNacimiento
            )
            intent.putExtra(
                "historialMedico",
                item.historialMedico)

            context.startActivity(intent)
        }
    }

}