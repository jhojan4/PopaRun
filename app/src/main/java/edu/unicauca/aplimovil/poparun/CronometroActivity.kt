package edu.unicauca.aplimovil.poparun


import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class CronometroActivity : AppCompatActivity() {

    private lateinit var cronometro: Chronometer
    private lateinit var btnIniciar: Button
    private lateinit var btnDetener: Button
    private var running = false
    private var pauseOffset: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Layout principal
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setBackgroundColor(Color.WHITE)
            setPadding(32, 32, 32, 32)
        }

        // Cronómetro
        cronometro = Chronometer(this).apply {
            textSize = 48f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
        }
        layout.addView(cronometro)

        // Contenedor para los botones
        val buttonLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            setPadding(0, 50, 0, 0)
        }

        // Botón Iniciar
        btnIniciar = Button(this).apply {
            text = "Iniciar"
        }
        buttonLayout.addView(btnIniciar)

        // Espaciado entre botones
        val espacio = ViewGroup.MarginLayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply { setMargins(32, 0, 0, 0) }

        // Botón Detener
        btnDetener = Button(this).apply {
            text = "Detener"
            layoutParams = espacio
        }
        buttonLayout.addView(btnDetener)

        // Agregar botones al layout principal
        layout.addView(buttonLayout)

        // Establecer el layout como contenido de la actividad
        setContentView(layout)

        // Listeners de los botones
        btnIniciar.setOnClickListener {
            if (!running) {
                cronometro.base = SystemClock.elapsedRealtime() - pauseOffset
                cronometro.start()
                running = true
            }
        }

        btnDetener.setOnClickListener {
            if (running) {
                cronometro.stop()
                pauseOffset = SystemClock.elapsedRealtime() - cronometro.base
                running = false
            }
        }
    }
}

