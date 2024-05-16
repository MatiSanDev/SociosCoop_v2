package com.example.socios.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.LocalContentAlpha
import com.example.socios.Views.Resources.MyMessage


@Composable
fun TitleView(name: String) {
    Text(text = name, fontSize = 40.sp, fontWeight = FontWeight.Bold)
}
@Composable
fun CustomTextBox(
    text: String,
    fontSize: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    fontFamily: FontFamily = FontFamily.Default,
    textAlign: TextAlign = TextAlign.Center,
    modifier: Modifier = Modifier
) {
    val textColor = LocalContentColor.current

    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        color = textColor,
        textAlign = textAlign,
        modifier = modifier
    )
}

@Composable
fun CustomIcon(
    icon: ImageVector,
    contentDescription: String?,
    tint: Color? = Color.Unspecified,
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = icon,
        contentDescription = contentDescription,
        tint = tint ?: LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
        modifier = modifier
    )
}
@Composable
fun CustomImage(
    painter: Painter,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Fit,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    )
}


@Composable
fun Space() {
    Spacer(modifier = Modifier.height(10.dp))
}


@Composable
fun MainButton(name: String, backColor: Color, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = color,
            containerColor = backColor
        )
    ) {
        Text(text = name)
    }
}



//Notificaciones aleatorias
fun generateDummyMessages(): List<MyMessage> {
    return listOf(
        MyMessage(
            "Haz que tu dinero trabaje para ti",
            "Aprovecha nuestras opciones de depósitos a plazo con distintos plazos y tasas de interés. Optimiza tus ingresos de manera segura y confiable en Socio Ahorro Seguro."
        ),
        MyMessage(
            "Planifica tu futuro con confianza",
            "Prepárate para imprevistos y proyectos futuros con una cuenta de ahorro diseñada para tus necesidades. En Socio Finanzas, tu seguridad financiera es nuestra prioridad."
        ),
        MyMessage(
            "¡Obtén el crédito que necesitas!",
            "En Socio Capital te ofrecemos préstamos flexibles con tasas competitivas. ¡Haz realidad tus proyectos hoy mismo!"
        ),
        MyMessage(
            "Empieza a ahorrar con nosotros",
            "Abre tu cuenta de ahorro con Socio Capital y comienza a construir tu futuro financiero. Con nuestros servicios, alcanzar tus metas es más fácil."
        ),
        MyMessage(
            "Incrementa tus ahorros con nuestros plazos fijos",
            "Obtén rendimientos atractivos al invertir tus ahorros en nuestros depósitos a plazo en SocioInversiones. Mayor seguridad y ganancias aseguradas."
        ),
        MyMessage(
            "Financia tus sueños con nosotros",
            "Con nuestros créditos de consumo, podrás adquirir ese automóvil, realizar mejoras en tu hogar o planificar unas vacaciones inolvidables. ¡Descubre nuestras opciones en Socio Finanzas!"
        ),
        MyMessage(
            "Invierte con tranquilidad y rentabilidad",
            "Con nuestros plazos fijos, podrás planificar tus ganancias con certeza. Descubre cómo maximizar tus recursos financieros de manera inteligente en SocioInversiones."
        ),
        MyMessage(
            "¡Comienza a alcanzar tus metas financieras!",
            "Con SocioInversiones, accede a herramientas financieras inteligentes para multiplicar tus ahorros y asegurar tu futuro."
        ),
        MyMessage(
            "Encuentra la cuenta de ahorro perfecta para ti",
            "En Socio Finanzas, te ofrecemos una variedad de opciones para que elijas la cuenta de ahorro que se ajuste a tus necesidades y objetivos financieros."
        ),
        MyMessage(
            "Descubre nuestras opciones de crédito flexibles",
            "En Socio Capital, nos adaptamos a tus necesidades financieras. Con nuestros créditos, podrás realizar tus proyectos de forma cómoda y accesible."
        ),
        MyMessage(
            "Inversiones inteligentes, resultados confiables",
            "Con SocioInversiones, obtén asesoría experta y accede a productos financieros diseñados para optimizar tus inversiones y asegurar tus ganancias."
        ),
        MyMessage(
            "Gestiona tu dinero de forma eficiente",
            "En Socio Finanzas, te ofrecemos herramientas y servicios para administrar tu dinero con confianza y seguridad. ¡Descubre la libertad financiera!"
        ),
        MyMessage(
            "Tu seguridad financiera, nuestra prioridad",
            "En Socio Ahorro Seguro, protegemos tus fondos mientras maximizamos tus ganancias. ¡Confía en nosotros para asegurar tu bienestar financiero!"
        ),
    )
}

fun validarCorreoElectronico(correo: String): Boolean {
    val partesCorreo = correo.split("@")
    if (partesCorreo.size != 2 || partesCorreo[0].isEmpty() || partesCorreo[1].isEmpty()) {
        println("Correo electrónico inválido: debe contener un solo símbolo '@'")
        return false
    }
    val dominio = partesCorreo[1].split(".")
    if (dominio.size < 2) {
        println("Correo electrónico inválido: dominio incompleto")
        return false
    }
    if (correo.length !in 5..50) {
        println("Correo electrónico inválido: longitud fuera de rango")
        return false
    }
    val caracteresPermitidos = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    if (!caracteresPermitidos.matches(correo)) {
        println("Correo electrónico inválido: contiene caracteres no permitidos")
        return false
    }

    return true
}
fun Modifier.maxWidthIn(maxWidth: Dp): Modifier = composed {
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints.copy(maxWidth = maxWidth.roundToPx()))
        layout(placeable.width.coerceAtMost(maxWidth.roundToPx()), placeable.height) {
            placeable.placeRelative(0, 0)
        }
    }
}
