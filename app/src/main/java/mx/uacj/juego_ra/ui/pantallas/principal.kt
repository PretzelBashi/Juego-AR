package mx.uacj.juego_ra.ui.pantallas

import android.location.Location
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.uacj.juego_ra.R
import mx.uacj.juego_ra.modelos.Informacion
import mx.uacj.juego_ra.modelos.InformacionInteractiva
import mx.uacj.juego_ra.modelos.Pista
import mx.uacj.juego_ra.modelos.TiposDePistas
import mx.uacj.juego_ra.repositorios.estaticos.RepositorioPruebas
import mx.uacj.juego_ra.ui.organismos.InformacionInteractivaVista
import mx.uacj.juego_ra.ui.organismos.InformacionVista
import mx.uacj.juego_ra.ui.organismos.pista_mostar

@Composable
fun Principal(ubicacion: Location?, modificador: Modifier = Modifier){

    var mostrar_pantalla_generica by remember { mutableStateOf(true) }
    var mostrar_pista_cercana by remember { mutableStateOf(false) }
    var cerca_de_pista by remember { mutableStateOf(false) }
    var pista_a_mostar by remember { mutableStateOf<Pista>(RepositorioPruebas.pistas[0]) }


    Column(modificador) {
        var distancia_mas_cerca = 150f;
        
        for(pista in RepositorioPruebas.pistas) {
            if (ubicacion == null) {
                break
            }

            var distancia_a_la_pista = ubicacion.distanceTo(pista.ubicacion)

            if(distancia_a_la_pista < pista.distancia_maxima){
                mostrar_pantalla_generica = true;
            }

            var nivel_de_distancia: Float =
                (distancia_a_la_pista * 100) / (pista.distancia_maxima - pista.distancia_minima)

            if (distancia_mas_cerca > nivel_de_distancia) {
                distancia_mas_cerca = nivel_de_distancia
                pista_a_mostar = pista
            }
        }

        if(distancia_mas_cerca > 75 && mostrar_pantalla_generica && distancia_mas_cerca < 149){
            pista_mostar(0, distancia_mas_cerca)
        }

        else if (distancia_mas_cerca > 30 && mostrar_pantalla_generica && distancia_mas_cerca < 149){
            pista_mostar(1, distancia_mas_cerca )
        }

        else if(distancia_mas_cerca < 30 && mostrar_pantalla_generica && distancia_mas_cerca < 149){
            pista_mostar(2, distancia_mas_cerca)
        } else {
            pista_mostar(3, 0f)
        }

        if(mostrar_pista_cercana) {
            when (pista_a_mostar.cuerpo.tipo) {
                TiposDePistas.texto -> {
                    InformacionVista(pista_a_mostar.cuerpo as Informacion)
                }

                TiposDePistas.interactiva -> {
                    InformacionInteractivaVista(pista_a_mostar.cuerpo as InformacionInteractiva)
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

    if(mostrar_pantalla_generica){
        Column(modificador) {
            Text("NO te encuentras cercas de alguna pista por el momento ")
            Text("Por favor sigue explorando  ")
        }

    }

}
