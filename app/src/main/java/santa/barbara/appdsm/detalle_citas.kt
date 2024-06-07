package santa.barbara.appdsm

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
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
import com.google.firebase.database.FirebaseDatabase

class detalle_citas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_citas)
        window.statusBarColor = resources.getColor(R.color.statusBarColor, theme)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imgAtrasDetalleCitas = findViewById<ImageView>(R.id.imgAtrasDetalleCitas)
        val lblPacienteDetalleCita = findViewById<TextView>(R.id.lblPacienteDetalleCita)
        val lblFechaDetalleCita = findViewById<TextView>(R.id.lblFechaDetalleCita)
        val lblHoraDetalleCita = findViewById<TextView>(R.id.lblHoraDetalleCita)
        val lblDoctorDetalleCita = findViewById<TextView>(R.id.lblDoctorDetalleCita)
        val lblMotivoDetalleCita = findViewById<TextView>(R.id.lblMotivoDetalleCita)
        val btnEditarDetalleCita = findViewById<Button>(R.id.btnEditarDetalleCita)
        val btnBorrarDetalleCita = findViewById<Button>(R.id.btnBorrarDetalleCita)

        //Recibir todos los valores de la card seleccionada
        val idRecibido = intent.getStringExtra("id")!!
        val pacienteRecibido = intent.getStringExtra("paciente")
        val fechaRecibida = intent.getStringExtra("fecha")
        val horaRecibida = intent.getStringExtra("hora")
        val doctorRecibido = intent.getStringExtra("doctor")
        val motivoRecibido = intent.getStringExtra("motivo")

        lblPacienteDetalleCita.text = pacienteRecibido
        lblFechaDetalleCita.text = fechaRecibida
        lblHoraDetalleCita.text = horaRecibida
        lblDoctorDetalleCita.text = doctorRecibido
        lblMotivoDetalleCita.text = motivoRecibido

        //Boton borrar
        btnBorrarDetalleCita.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Confirmación")
            builder.setMessage("¿Quieres eliminar esta cita?")

            builder.setPositiveButton("Sí") { dialog, which ->
                Toast.makeText(this, "Cita eliminada", Toast.LENGTH_SHORT).show()
                val database = FirebaseDatabase.getInstance()
                val reference = database.getReference("citas").child(idRecibido)
                reference.removeValue()
                finish()
            }

            builder.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }

        //TODO: Boton editar
        btnEditarDetalleCita.setOnClickListener {
            val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            builder.setCustomTitle(TextView(this).apply {
                setText("Editar datos de la cita")
                setTextAppearance(context, R.style.AlertDialogTitle)
            })
            val pacienteDetalleActual = lblPacienteDetalleCita.text.toString()
            val fechaDetalleActual = lblFechaDetalleCita.text.toString()
            val HoraDetalleActual = lblHoraDetalleCita.text.toString()
            val doctorDetalleActual = lblDoctorDetalleCita.text.toString()
            val motivoDetalleActual = lblMotivoDetalleCita.text.toString()

            val txtNuevoPacienteDetalle = EditText(this).apply {
                setText(pacienteDetalleActual)
            }
            val txtNuevaFechaDetalle = EditText(this).apply {
                setText(fechaDetalleActual)
            }
            val txtNuevaHoraDetalle = EditText(this).apply {
                setText(HoraDetalleActual)
            }
            val txtNuevoDoctorDetalle = EditText(this).apply {
                setText(doctorDetalleActual)
            }
            val txtNuevoMotivoDetalle = EditText(this).apply {
                setText(motivoDetalleActual)
            }

            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                addView(txtNuevoPacienteDetalle)
                addView(txtNuevaFechaDetalle)
                addView(txtNuevaHoraDetalle)
                addView(txtNuevoDoctorDetalle)
                addView(txtNuevoMotivoDetalle)
            }
            builder.setView(layout)

            builder.setPositiveButton("Guardar") { dialog, which ->
                val nuevoPacienteDetalle = txtNuevoPacienteDetalle.text.toString()
                val nuevaFechaDetalle = txtNuevaFechaDetalle.text.toString()
                val nuevaHoraDetalle = txtNuevaHoraDetalle.text.toString()
                val nuevoDoctorDetalle = txtNuevoDoctorDetalle.text.toString()
                val nuevoMotivoDetalle = txtNuevoMotivoDetalle.text.toString()

                val database = FirebaseDatabase.getInstance()
                val reference = database.getReference("citas").child(idRecibido)

                // Actualizo los datos en Firebase
                reference.child("paciente").setValue(nuevoPacienteDetalle)
                reference.child("fecha").setValue(nuevaFechaDetalle)
                reference.child("hora").setValue(nuevaHoraDetalle)
                reference.child("doctor").setValue(nuevoDoctorDetalle)
                reference.child("motivo").setValue(nuevoMotivoDetalle)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Paciente actualizado", Toast.LENGTH_SHORT).show()
                        lblPacienteDetalleCita.text = nuevoPacienteDetalle
                        lblFechaDetalleCita.text = nuevaFechaDetalle
                        lblHoraDetalleCita.text = nuevaHoraDetalle
                        lblDoctorDetalleCita.text = nuevoDoctorDetalle
                        lblMotivoDetalleCita.text = nuevoMotivoDetalle
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error al actualizar la cita", Toast.LENGTH_SHORT).show()
                        println("Error al actualizar la cita: ${e.message}")
                    }
            }
            builder.setNegativeButton("Cancelar", null)

            val dialog = builder.create()
            dialog.show()
        }

        imgAtrasDetalleCitas.setOnClickListener {
            finish()
        }


    }
}