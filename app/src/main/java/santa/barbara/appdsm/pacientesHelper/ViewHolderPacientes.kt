package santa.barbara.appdsm.pacientesHelper

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import santa.barbara.appdsm.R

class ViewHolderPacientes(view: View) : RecyclerView.ViewHolder(view) {
    val lblPacienteNombre: TextView = view.findViewById(R.id.lblPacienteNombre)
    val lblPacienteEspecie: TextView = view.findViewById(R.id.lblPacienteEspecie)
    val lblRazaPaciente: TextView = view.findViewById(R.id.lblRazaPaciente)
}