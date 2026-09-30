package com.laura.horoscopo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val horooscopeList: List<Horoscope> = listOf(
        Horoscope(
            id = "sagittarius",
            name = R.string.horoscope_name_aries,
            dates = R.string.horoscope_date_aries,
            sign = R.drawable.sagittarius_icon
        )
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

//        val primerSigno = horooscopeList.firstOrNull()
//        if (primerSigno != null) {
//            // getString() convierte el R.string.horoscope_name_aries en el texto "Aries"
//            val nombreLegible = getString(primerSigno.name)
//            println("PruebaHoroscopo: ¡El primer signo cargado es $nombreLegible con ID ${primerSigno.id}!")
//        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}