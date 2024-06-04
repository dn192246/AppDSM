package santa.barbara.appdsm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import santa.barbara.appdsm.pacientesHelper.AdaptadorPacientes
import santa.barbara.appdsm.pacientesHelper.tbPacientes

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [pacientes.newInstance] factory method to
 * create an instance of this fragment.
 */
class pacientes : Fragment() {

    val datos = mutableListOf<tbPacientes>()
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_pacientes, container, false)

        val btnAgregarPaciente = root.findViewById<FloatingActionButton>(R.id.btnAgregarPaciente)


        btnAgregarPaciente.setOnClickListener {
            findNavController().navigate(R.id.action_pacientes_to_add_pacientes)
        }

        val rcvPacientes = root.findViewById<RecyclerView>(R.id.rcvPacientes)
        rcvPacientes.layoutManager = LinearLayoutManager(requireContext())

        val database = FirebaseDatabase.getInstance()
        val referencia = database.getReference("pacientes")

        /////Mostrar datos de Doctores/////
        fun obtenerDatos(): List<tbPacientes> {
            referencia.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    datos.clear()
                    for (dataSnapshot in snapshot.children) {
                        val id = snapshot.key
                        val paciente = snapshot.getValue(tbPacientes::class.java)
                        val myid = dataSnapshot.child("id").value
                        val nombre = dataSnapshot.child("nombreMascota").value
                        val nombreDuenio = dataSnapshot.child("nombreDuenio").value
                        val especie = dataSnapshot.child("especie").value
                        val raza = dataSnapshot.child("raza").value
                        val peso = dataSnapshot.child("peso").value
                        val tamanio = dataSnapshot.child("tamanio").value
                        val sexo = dataSnapshot.child("sexo").value
                        val fechaNacimiento = dataSnapshot.child("fechaNacimiento").value
                        val historialMedico = dataSnapshot.child("historialMedico").value
                        if (paciente != null && id != null) {
                            val pacienteNuevo = tbPacientes(
                                myid.toString(),
                                nombre.toString(),
                                nombreDuenio.toString(),
                                especie.toString(),
                                raza.toString(),
                                peso.toString(),
                                tamanio.toString(),
                                sexo.toString(),
                                fechaNacimiento.toString(),
                                historialMedico.toString()
                            )
                            datos.add(pacienteNuevo)
                        }
                    }
                    val adapter = AdaptadorPacientes(datos)
                    rcvPacientes.adapter = adapter
                }
                override fun onCancelled(error: DatabaseError) {
                    println("Error al mostrar doctores: $error")
                }
            })
            return datos
        }
        obtenerDatos()

        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment pacientes.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            pacientes().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}