package santa.barbara.appdsm

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import santa.barbara.appdsm.pacientesHelper.tbPacientes
import java.io.ByteArrayOutputStream
import java.util.Calendar
import java.util.UUID


class agregar_pacientes : Fragment() {

    private lateinit var imgAgregarFotoPaciente: ImageView
    private lateinit var imgFotoPaciente: ImageView
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_CAMERA_PERMISSION = 100
    val referencia = FirebaseDatabase.getInstance().getReference("pacientes")
    val nuevoPacienteRef = referencia.push()
    val nuevoPacienteId = nuevoPacienteRef.key
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

        imgAgregarFotoPaciente = root.findViewById(R.id.imgAgregarFotoPaciente)
        imgFotoPaciente = root.findViewById(R.id.imgFotoPaciente)

        imgAgregarFotoPaciente.setOnClickListener {
            abrirCamara()
        }

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

    private fun abrirCamara() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
           println("Error al abrir la cámara: $e")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                abrirCamara()
            } else {
                Toast.makeText(requireContext(), "Permiso de la cámara denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            subirimagenFirebase(imageBitmap)
        }
    }

    private fun subirimagenFirebase(bitmap: Bitmap) {
        val storageRef = Firebase.storage.reference
        val imageRef = storageRef.child("images/${nuevoPacienteId}.jpg")
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        val uploadTask = imageRef.putBytes(data)
        uploadTask.addOnFailureListener {
            Toast.makeText(requireContext(), "Error al subir la imagen", Toast.LENGTH_SHORT).show()
        }.addOnSuccessListener { taskSnapshot ->
            imageRef.downloadUrl.addOnSuccessListener { uri ->
                val imageUrl = uri.toString()
                Glide.with(this).load(imageUrl).into(imgFotoPaciente)
            }
        }
    }



}