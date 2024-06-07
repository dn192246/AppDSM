package santa.barbara.appdsm.citasHelper

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import santa.barbara.appdsm.R

class ViewHolderCitas(view: View) : RecyclerView.ViewHolder(view) {
    val lblPacienteCita: TextView = view.findViewById(R.id.lblPacienteCita)
    val lblFechaCita: TextView = view.findViewById(R.id.lblFechaCita)
    val lblHoraCita: TextView = view.findViewById(R.id.lblHoraCita)
}