package santa.barbara.appdsm

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.FirebaseDatabase
import santa.barbara.appdsm.pacientesHelper.tbPacientes
import java.util.Calendar


class agregar_pacientes : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_agregar_pacientes, container, false)

        val txtNombrePaciente = root.findViewById<EditText>(R.id.txtNombreMascota)
        val txtNombreDuenio = root.findViewById<EditText>(R.id.txtNombreDuenio)
        val txtEspeciePaciente = root.findViewById<EditText>(R.id.txtEspeciePaciente)
        val txtRazaPaciente = root.findViewById<EditText>(R.id.txtRazaPaciente)
        val txtPeso = root.findViewById<EditText>(R.id.txtPeso)
        val txtTamanio = root.findViewById<EditText>(R.id.txtTamanio)
        val spinnerSexo = root.findViewById<Spinner>(R.id.spinnerSexo)
        val txtFechaNacimientoPaciente =
            root.findViewById<EditText>(R.id.txtFechaNacimientoPaciente)
        val txtHistorialMedicoPaciente =
            root.findViewById<EditText>(R.id.txtHistorialMedicoPaciente)

        val imgAtrasPacientes = root.findViewById<ImageView>(R.id.imgAtrasPacientes)
        val btnCrearPaciente = root.findViewById<Button>(R.id.btnCrearPaciente)

        //Mostrar el calendario al hacer click en el EditText txtFechaNacimientoPaciente
        txtFechaNacimientoPaciente.setOnClickListener {
            val calendario = Calendar.getInstance()
            val anio = calendario.get(Calendar.YEAR)
            val mes = calendario.get(Calendar.MONTH)
            val dia = calendario.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { view, anioSeleccionado, mesSeleccionado, diaSeleccionado ->
                    val fechaSeleccionada =
                        "$diaSeleccionado/${mesSeleccionado + 1}/$anioSeleccionado"
                    txtFechaNacimientoPaciente.setText(fechaSeleccionada)
                },
                anio, mes, dia
            )
            datePickerDialog.show()
        }

        btnCrearPaciente.setOnClickListener {
            if (txtNombrePaciente.text.toString().isEmpty() ||
                txtNombreDuenio.text.toString().isEmpty() ||
                txtEspeciePaciente.text.toString().isEmpty() ||
                txtRazaPaciente.text.toString().isEmpty() ||
                txtPeso.text.toString().isEmpty() ||
                txtTamanio.text.toString().isEmpty() ||
                spinnerSexo.selectedItemPosition == 0 ||
                txtFechaNacimientoPaciente.text.toString().isEmpty() ||
                txtHistorialMedicoPaciente.text.toString().isEmpty()
            ) {
                Toast.makeText(
                    requireContext(),
                    "Por favor, complete todos los campos.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val referencia = FirebaseDatabase.getInstance().getReference("pacientes")
                val nuevoPacienteRef = referencia.push()
                val nuevoPacienteId = nuevoPacienteRef.key

                if (nuevoPacienteId != null) {
                    val nuevoPaciente = tbPacientes(
                        nuevoPacienteId,
                        txtNombrePaciente.text.toString(),
                        txtNombreDuenio.text.toString(),
                        txtEspeciePaciente.text.toString(),
                        txtRazaPaciente.text.toString(),
                        txtPeso.text.toString(),
                        txtTamanio.text.toString(),
                        spinnerSexo.selectedItem.toString(),
                        txtFechaNacimientoPaciente.text.toString(),
                        txtHistorialMedicoPaciente.text.toString()
                    )
                    println("Nuevo paciente: $nuevoPaciente")
                    nuevoPacienteRef.setValue(nuevoPaciente)
                        .addOnSuccessListener {
                            txtNombrePaciente.text.clear()
                            txtNombreDuenio.text.clear()
                            txtEspeciePaciente.text.clear()
                            txtRazaPaciente.text.clear()
                            txtPeso.text.clear()
                            txtTamanio.text.clear()
                            spinnerSexo.setSelection(0)
                            txtFechaNacimientoPaciente.text.clear()
                            txtHistorialMedicoPaciente.text.clear()
                            Toast.makeText(
                                requireContext(),
                                "Paciente agregado exitosamente",
                                Toast.LENGTH_SHORT
                            ).show()
                            println("Nuevo paciente: $nuevoPaciente")
                        }
                        .addOnFailureListener { e ->
                            println("Error al agregar pacientes: $e")
                            println("Nuevo paciente: $nuevoPaciente")
                        }
                }
            }
        }

        imgAtrasPacientes.setOnClickListener {
            findNavController().navigateUp()
        }

        return root
    }
}