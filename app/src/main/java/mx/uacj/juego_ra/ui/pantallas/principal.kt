package mx.uacj.juego_ra.ui.pantallas

import android.location.Location
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import mx.uacj.juego_ra.modelos.Informacion
import mx.uacj.juego_ra.modelos.InformacionInteractiva
import mx.uacj.juego_ra.modelos.TiposDePistas
import mx.uacj.juego_ra.repositorios.estaticos.RepositorioPruebas
import mx.uacj.juego_ra.ui.organismos.InformacionInteractivaVista
import mx.uacj.juego_ra.ui.organismos.InformacionVista

@Composable
fun Principal(ubicacion: Location?, modificador: Modifier = Modifier){

    var mostrar_pantalla_generica by remember { mutableStateOf(true) }
    var mostrar_pista_cercana by remember { mutableStateOf(false) }

    Column(modificador) {
        for(pista in RepositorioPruebas.pistas){
            if(ubicacion == null){
                break
            }

            var distancia_a_la_pista = ubicacion.distanceTo(pista.ubicacion)

            if(distancia_a_la_pista < pista.distancia_maxima){
                mostrar_pantalla_generica = false
                var nivel_de_distancia = (distancia_a_la_pista * 100) / (pista.distancia_maxima - pista.distancia_minima)

                Text("La pista es: ${pista.nombre}")
                Text("el nivel de la distancia a la pista es ${nivel_de_distancia}")

                if(nivel_de_distancia > 75){
                    Text("Estas frio todavia")
                }

                else if (nivel_de_distancia > 50){
                    Text("Te estas acercando")
                }

                else if(nivel_de_distancia > 25){
                    Text("Muy cercas, sigue asi")
                }

                else if(nivel_de_distancia < 20 && !mostrar_pista_cercana){
                    Row(modifier = Modifier.fillMaxWidth().clickable {
                        mostrar_pista_cercana = true
                    }){
                        Text("Capturar pista cercana")
                    }
                }

                if(mostrar_pista_cercana) {
                    when (pista.cuerpo.tipo) {
                        TiposDePistas.texto -> {
                            InformacionVista(pista.cuerpo as Informacion)
                        }

                        TiposDePistas.interactiva -> {
                            InformacionInteractivaVista(pista.cuerpo as InformacionInteractiva)
                        }

                        TiposDePistas.camara -> {
                            TODO()
                        }

                        TiposDePistas.agitar_telefono -> {
                            TODO()
                        }
                    }
                }

            }
        }
    }

    if(mostrar_pantalla_generica){
        Column(modificador) {
            Text("NO te encuentras cercas de alguna pista por el momento ")
            Text("Por favor sigue explorando  ")
        }

    }

}
