package santa.barbara.appdsm.citasHelper

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import santa.barbara.appdsm.R

class ViewHolderCitas(view: View) : RecyclerView.ViewHolder(view) {
    val lblCitaCard: TextView = view.findViewById(R.id.lblCitaCard)
}