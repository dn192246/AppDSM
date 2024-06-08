package santa.barbara.appdsm

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import santa.barbara.appdsm.citasHelper.tbCitas
import santa.barbara.appdsm.doctoresHelper.tbDoctores
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class agregar_citas : Fragment() {

    private lateinit var txtDoctorCita: Spinner
    private lateinit var databaseDoctores: DatabaseReference

    private lateinit var txtPacienteCita: Spinner
    private lateinit var databasePacientes: DatabaseReference

    fun showTimePickerDialog(textView: EditText) {
        val cal = Calendar.getInstance()
        val hour = cal.get(Calendar.HOUR_OF_DAY)
        val minute = cal.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _: TimePicker, hourOfDay: Int, minute: Int ->
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                cal.set(Calendar.MINUTE, minute)
                val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
                val formattedTime = format.format(cal.time)
                textView.setText(formattedTime)
            },
            hour,
            minute,
            false
        )

        timePickerDialog.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_agregar_citas, container, false)

        val imgAtras = root.findViewById<ImageView>(R.id.imgAtrasCitas)
        val txtfechaCita = root.findViewById<EditText>(R.id.txtFechaCita)
        val txtHoraCita = root.findViewById<EditText>(R.id.txtHoraCita)
        txtPacienteCita = root.findViewById<Spinner>(R.id.txtPacienteCita)
        txtDoctorCita = root.findViewById<Spinner>(R.id.txtDoctorCita)
        val txtMotivoCita = root.findViewById<EditText>(R.id.txtMotivoCita)
        val btnCrearCita = root.findViewById<Button>(R.id.btnCrearCita)

        databaseDoctores = FirebaseDatabase.getInstance().reference.child("doctores")
        databasePacientes = FirebaseDatabase.getInstance().reference.child("pacientes")

        //Mostrar el calendario al hacer click en el EditText txtFechaNacimientoPaciente
        txtfechaCita.setOnClickListener {
            val calendario = Calendar.getInstance()
            val anio = calendario.get(Calendar.YEAR)
            val mes = calendario.get(Calendar.MONTH)
            val dia = calendario.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { view, anioSeleccionado, mesSeleccionado, diaSeleccionado ->
                    val fechaSeleccionada =
                        "$diaSeleccionado/${mesSeleccionado + 1}/$anioSeleccionado"
                    txtfechaCita.setText(fechaSeleccionada)
                },
                anio, mes, dia
            )
            datePickerDialog.show()
        }

            txtHoraCita.setOnClickListener {
                showTimePickerDialog(txtHoraCita)
            }

        btnCrearCita.setOnClickListener {

            if (txtfechaCita.text.toString().isEmpty() || txtHoraCita.text.toString()
                    .isEmpty() || txtMotivoCita.text.toString().isEmpty()
            ) {
                Toast.makeText(
                    requireContext(),
                    "Por favor, complete todos los campos.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val referencia = FirebaseDatabase.getInstance().getReference("citas")
                val nuevaCitaRef = referencia.push()
                val nuevaCitaId = nuevaCitaRef.key

                if (nuevaCitaId != null) {
                    val nuevaCita = tbCitas(
                        nuevaCitaId,
                        txtfechaCita.text.toString(),
                        txtHoraCita.text.toString(),
                        txtPacienteCita.selectedItem.toString(),
                        txtDoctorCita.selectedItem.toString(),
                        txtMotivoCita.text.toString()
                    )
                    nuevaCitaRef.setValue(nuevaCita).addOnSuccessListener {
                            txtfechaCita.text.clear()
                            txtHoraCita.text.clear()
                            txtPacienteCita.setSelection(0)
                            txtDoctorCita.setSelection(0)
                            txtMotivoCita.text.clear()
                            Toast.makeText(
                                requireContext(),
                                "Cita agendada exitosamente",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .addOnFailureListener { e ->
                            println("Error al agregar citas: $e")
                        }
                }
            }
        }
            imgAtras.setOnClickListener {
                findNavController().navigateUp()
            }
        loadDoctorNames()
        loadPacienteNames()

            return root
        }

    private fun loadDoctorNames() {
        val doctorNames = mutableListOf<String>()

        databaseDoctores.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                doctorNames.clear()
                for (doctorSnapshot in snapshot.children) {
                    val doctorName = doctorSnapshot.child("nombre").getValue(String::class.java)
                    doctorName?.let { doctorNames.add(it) }
                }
                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, doctorNames)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                txtDoctorCita.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Error loading doctors", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun loadPacienteNames() {
        val doctorNames = mutableListOf<String>()

        databasePacientes.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                doctorNames.clear()
                for (doctorSnapshot in snapshot.children) {
                    val doctorName = doctorSnapshot.child("nombreMascota").getValue(String::class.java)
                    doctorName?.let { doctorNames.add(it) }
                }
                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, doctorNames)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                txtPacienteCita.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Error loading doctors", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
