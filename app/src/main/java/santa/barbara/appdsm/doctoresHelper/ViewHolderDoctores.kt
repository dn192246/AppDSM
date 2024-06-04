package santa.barbara.appdsm.doctoresHelper

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import santa.barbara.appdsm.R

class ViewHolderDoctores(view: View) : RecyclerView.ViewHolder(view) {
    val lblDoctorCard: TextView = view.findViewById(R.id.lblDoctorNombre)
    val lblDoctorEspecialidad: TextView = view.findViewById(R.id.lblDoctorEspecialidad)
    val lblDoctorTelefono: TextView = view.findViewById(R.id.lblDoctorTelefono)
    val imgEditarDoctor: ImageView = view.findViewById(R.id.imgEditarDoctor)
    val imgBorrarDoctor: ImageView = view.findViewById(R.id.imgBorrarDoctor)
}