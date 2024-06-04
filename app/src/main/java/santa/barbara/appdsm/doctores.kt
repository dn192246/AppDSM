package santa.barbara.appdsm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import santa.barbara.appdsm.doctoresHelper.AdaptadorDoctores
import santa.barbara.appdsm.doctoresHelper.tbDoctores


class doctores : Fragment() {
    val datos = mutableListOf<tbDoctores>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_doctores, container, false)

        val btnAgregarDoctor = root.findViewById<FloatingActionButton>(R.id.btnAgregarDoctor)
        val rcvDoctores = root.findViewById<RecyclerView>(R.id.rcvDoctores)
        rcvDoctores.layoutManager = LinearLayoutManager(requireContext())

        val database = FirebaseDatabase.getInstance()
        val referencia = database.getReference("doctores")

        /////Mostrar datos de Doctores/////
        fun obtenerDatos(): List<tbDoctores> {
            referencia.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    datos.clear()
                    for (dataSnapshot in snapshot.children) {
                        val id = snapshot.key
                        val doctor = snapshot.getValue(tbDoctores::class.java)
                        val myid = dataSnapshot.child("id").value
                        val nombre = dataSnapshot.child("nombre").value
                        val especialidad = dataSnapshot.child("especialidad").value
                        val telefono = dataSnapshot.child("telefono").value
                        if (doctor != null && id != null) {
                            val doctorNuevo = tbDoctores(
                                myid.toString(),
                                nombre.toString(),
                                especialidad.toString(),
                                telefono.toString()
                            )
                            datos.add(doctorNuevo)
                        }
                    }
                    val adapter = AdaptadorDoctores(datos)
                    rcvDoctores.adapter = adapter
                }
                override fun onCancelled(error: DatabaseError) {
                    println("Error al mostrar doctores: $error")
                }
            })
            return datos
        }
        obtenerDatos()
        btnAgregarDoctor.setOnClickListener {
            findNavController().navigate(R.id.action_doctores_to_add_doctor)
        }
        return root
    }
}