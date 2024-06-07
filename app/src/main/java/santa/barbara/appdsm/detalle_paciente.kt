package santa.barbara.appdsm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class detalle_paciente : AppCompatActivity() {
    private lateinit var storageReference: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_paciente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imgAtrasDetallePaciente = findViewById<ImageView>(R.id.imgAtrasDetallePaciente)
        val lblNombreMascota = findViewById<TextView>(R.id.lblNombreMascota)
        val lblEspecie = findViewById<TextView>(R.id.lblEspecie)
        val lblRaza = findViewById<TextView>(R.id.lblRaza)
        val lblPeso = findViewById<TextView>(R.id.lblPeso)
        val lblTamanio = findViewById<TextView>(R.id.lblAltura)
        val lblEdad = findViewById<TextView>(R.id.lblEdad)
        val lblSexo = findViewById<TextView>(R.id.lblGenero)
        val lblHistorialMedico = findViewById<TextView>(R.id.lblHistorialMedico)
        val btnBorrarPaciente = findViewById<Button>(R.id.btnBorrarPaciente)
        val btnEditarPaciente = findViewById<Button>(R.id.btnEditarPaciente)
        val btnAgendarProcimaCita = findViewById<Button>(R.id.btnAgendarProcimaCita)
        val imgFotoMascota = findViewById<ImageView>(R.id.imgFotoMascota)



        val idRecibido = intent.getStringExtra("id")!!
        val nombreMascotaRecibido = intent.getStringExtra("nombreMascota")
        val nombreDuenioRecibido = intent.getStringExtra("nombreDuenio")
        val especieRecibida = intent.getStringExtra("especie")
        val razaRecibida = intent.getStringExtra("raza")
        val pesoRecibido = intent.getStringExtra("peso")
        val tamanioRecibido = intent.getStringExtra("tamanio")
        val sexoRecibido = intent.getStringExtra("sexo")
        val fechaNacimientoRecibido = intent.getStringExtra("fechaNacimiento")
        val historialMedicoRecibido = intent.getStringExtra("historialMedico")


        lblNombreMascota.text = nombreMascotaRecibido
        lblEspecie.text = especieRecibida
        lblRaza.text = razaRecibida
        lblPeso.text = pesoRecibido
        lblTamanio.text = tamanioRecibido
        lblSexo.text = sexoRecibido
        lblEdad.text = fechaNacimientoRecibido
        lblHistorialMedico.text = historialMedicoRecibido

        btnBorrarPaciente.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Confirmación")
            builder.setMessage("¿Quieres eliminar este paciente?")

            builder.setPositiveButton("Sí") { dialog, which ->
            Toast.makeText(this, "Paciente eliminado", Toast.LENGTH_SHORT).show()
                val database = FirebaseDatabase.getInstance()
                val reference = database.getReference("pacientes").child(idRecibido)
                reference.removeValue()
                finish()
            }

            builder.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }

        btnEditarPaciente.setOnClickListener {
            val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            builder.setCustomTitle(TextView(this).apply {
                setText("Editar datos del paciente")
                setTextAppearance(context, R.style.AlertDialogTitle)
            })

            val nombrePacienteActual = lblNombreMascota.text.toString()
            val especieActual = lblEspecie.text.toString()
            val razaActual = lblRaza.text.toString()
            val pesoActual = lblPeso.text.toString()
            val tamanioActual = lblTamanio.text.toString()
            val sexoActual = lblSexo.text.toString()
            val fechaNacimientoActual = lblEdad.text.toString()
            val historialMedicoActual = lblHistorialMedico.text.toString()

            val txtNuevoNombrePaciente = EditText(this).apply {
                setText(nombrePacienteActual)
            }
            val txtNuevaEspecie = EditText(this).apply {
                setText(especieActual)
            }
            val txtNuevaRaza = EditText(this).apply {
                setText(razaActual)
            }
            val txtNuevoPeso = EditText(this).apply {
                setText(pesoActual)
            }
            val txtNuevoTamanio = EditText(this).apply {
                setText(tamanioActual)
            }
            val txtNuevoSexo = EditText(this).apply {
                setText(sexoActual)
                }
            val txtNuevaFechaNacimiento = EditText(this).apply {
                setText(fechaNacimientoActual)
            }
            val txtNuevoHistorialMedico = EditText(this).apply {
                setText(historialMedicoActual)
            }

            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                addView(txtNuevoNombrePaciente)
                addView(txtNuevaEspecie)
                addView(txtNuevaRaza)
                addView(txtNuevoPeso)
                addView(txtNuevoTamanio)
                addView(txtNuevoSexo)
                addView(txtNuevaFechaNacimiento)
                addView(txtNuevoHistorialMedico)
            }
            builder.setView(layout)

            builder.setPositiveButton("Guardar") { dialog, which ->
                val nuevoNombrePaciente = txtNuevoNombrePaciente.text.toString()
                val nuevaEspecie = txtNuevaEspecie.text.toString()
                val nuevaRaza = txtNuevaRaza.text.toString()
                val nuevoPeso = txtNuevoPeso.text.toString()
                val nuevoTamanio = txtNuevoTamanio.text.toString()
                val nuevoSexo = txtNuevoSexo.text.toString()
                val nuevaFechaNacimiento = txtNuevaFechaNacimiento.text.toString()
                val nuevoHistorialMedico = txtNuevoHistorialMedico.text.toString()


                val database = FirebaseDatabase.getInstance()
                val reference = database.getReference("pacientes").child(idRecibido)

                // Actualizo los datos en Firebase
                reference.child("nombreMascota").setValue(nuevoNombrePaciente)
                reference.child("especie").setValue(nuevaEspecie)
                reference.child("raza").setValue(nuevaRaza)
                reference.child("peso").setValue(nuevoPeso)
                reference.child("tamanio").setValue(nuevoTamanio)
                reference.child("sexo").setValue(nuevoSexo)
                reference.child("fechaNacimiento").setValue(nuevaFechaNacimiento)
                reference.child("historialMedico").setValue(nuevoHistorialMedico)

                    .addOnSuccessListener {
                    Toast.makeText(this, "Paciente actualizado", Toast.LENGTH_SHORT).show()
                        lblNombreMascota.text = nuevoNombrePaciente
                        lblEspecie.text = nuevaEspecie
                        lblRaza.text = nuevaRaza
                        lblPeso.text = nuevoPeso
                        lblTamanio.text = nuevoTamanio
                        lblSexo.text = nuevoSexo
                        lblEdad.text = nuevaFechaNacimiento
                        lblHistorialMedico.text = nuevoHistorialMedico
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error al actualizar el paciente: ${e.message}", Toast.LENGTH_SHORT).show()
                        println("Error al actualizar el paciente: ${e.message}")
                    }
            }
            builder.setNegativeButton("Cancelar", null)

            val dialog = builder.create()
            dialog.show()
        }

        btnAgendarProcimaCita.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("ir_a_agregar_citas", true)
            startActivity(intent)
        }


        imgAtrasDetallePaciente.setOnClickListener {
            finish()
        }

        storageReference = FirebaseStorage.getInstance().reference.child("images/$idRecibido.jpg")
        loadPatientImage(imgFotoMascota)
    }
    private fun loadPatientImage(imageView: ImageView) {
        storageReference.downloadUrl.addOnSuccessListener { uri ->
            // Usa Glide para cargar la imagen en el ImageView
            Glide.with(this@detalle_paciente)
                .load(uri)
                .into(imageView)
        }.addOnFailureListener {
            // Manejo de errores
        }
    }
}