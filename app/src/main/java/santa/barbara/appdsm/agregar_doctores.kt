package santa.barbara.appdsm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase
import santa.barbara.appdsm.doctoresHelper.tbDoctores

class agregar_doctores : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_agregar_doctores, container, false)


        val imgAtras = root.findViewById<ImageView>(R.id.imgAtras)
        val txtNombre = root.findViewById<EditText>(R.id.txtNombreDoctor)
        val txtEspecialidad = root.findViewById<EditText>(R.id.txtEspecialidadDoctor)
        val txtTelefono = root.findViewById<EditText>(R.id.txtTelefonoDoctor)
        val btnGuardar = root.findViewById<Button>(R.id.btnCrearCita)

        imgAtras.setOnClickListener {
            findNavController().navigateUp()
        }

        btnGuardar.setOnClickListener {

            if (txtNombre.text.toString().isEmpty() || txtEspecialidad.text.toString()
                    .isEmpty() || txtTelefono.text.toString().isEmpty()
            ) {
                Toast.makeText(
                    requireContext(),
                    "Por favor, complete todos los campos.",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val referencia = FirebaseDatabase.getInstance().getReference("doctores")
                val nuevoDoctorRef = referencia.push()
                val nuevoDoctorId = nuevoDoctorRef.key

                if (nuevoDoctorId != null) {
                    val nuevoDoctor = tbDoctores(
                        nuevoDoctorId,
                        txtNombre.text.toString(),
                        txtEspecialidad.text.toString(),
                        txtTelefono.text.toString()
                    )

                    nuevoDoctorRef.setValue(nuevoDoctor)
                        .addOnSuccessListener {
                            txtNombre.text.clear()
                            txtEspecialidad.text.clear()
                            txtTelefono.text.clear()
                            println("Doctor agregado exitosamente")
                            Toast.makeText(
                                requireContext(),
                                "Doctor agregado exitosamente",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .addOnFailureListener { e ->
                            println("Error al agregar doctores: $e")
                        }
                }
            }
        }

        return root
    }


}