package santa.barbara.appdsm.citasHelper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import santa.barbara.appdsm.R

class AdaptadorCitas(private var Datos: List<tbCitas>) : RecyclerView.Adapter<ViewHolderCitas>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCitas {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_citas, parent, false)

        return ViewHolderCitas(vista)
    }

    override fun getItemCount() = Datos.size

    override fun onBindViewHolder(holder: ViewHolderCitas, position: Int) {
        val item = Datos[position]
        holder.lblCitaCard.text = item.motivo
    }
}