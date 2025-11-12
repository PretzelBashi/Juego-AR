package mx.uacj.juego_ra.ui.organismos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.uacj.juego_ra.R

@Composable
fun pista_mostar(cercania: Int, distancia: Float) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (cercania) {
            0 -> {
                Image(
                    painter = painterResource(R.drawable.frio),
                    contentDescription = "Frio",
                    modifier = Modifier.size(200.dp)
                        .padding(bottom = 20.dp)
                )
                Text("La piedra es fria al tacto...",
                    color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center )
                Text("Te encuentras a ${distancia}m de la pista",
                    color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center )

            }

            1 -> {
                Image(
                    painter = painterResource(R.drawable.tibio),
                    contentDescription = "Tibio",
                    modifier = Modifier.size(200.dp)
                        .padding(bottom = 20.dp)
                )
                Text("Sientes un ligero calor viniendo de la piedra...)",
                    color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center )
                Text("Te encuentras a ${distancia}m de la pista",
                    color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center )
            }

            2 -> {
                Image(
                    painter = painterResource(R.drawable.caliente),
                    contentDescription = "Caliente",
                    modifier = Modifier.size(200.dp)
                        .padding(bottom = 20.dp)
                )
                Text("La piedra emana un brillo y calor muy particular!",
                    color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center )
                Text("Te encuentras a ${distancia}m de la pista",
                    color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center )
            }
            3 -> {
                Image(
                    painter = painterResource(R.drawable.frio),
                    contentDescription = "Frio",
                    modifier = Modifier.size(200.dp)
                        .padding(bottom = 20.dp)
                )
                Text("La piedra parece estar inactiva...",
                    color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center )
            }
        }
    }
}