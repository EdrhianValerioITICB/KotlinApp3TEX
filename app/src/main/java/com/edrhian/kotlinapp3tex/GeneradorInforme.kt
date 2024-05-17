package com.edrhian.kotlinapp3tex

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.text.TextPaint
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import com.echo.holographlibrary.Bar
import com.echo.holographlibrary.BarGraph
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Calendar


class GeneradorInforme : AppCompatActivity() {
    private lateinit var mp: MediaPlayer
    private lateinit var etMes: NumberPicker
    private lateinit var etAnyo: NumberPicker
    private lateinit var cbMediaActivos: CheckBox
    private lateinit var cbCantidadTipos: CheckBox
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.generador_informe)
        mp = MediaPlayer.create(this, R.raw.button_click)
        var btnGenerarPdf = findViewById<Button>(R.id.btn_generar_informe)

        cbMediaActivos = findViewById(R.id.cb_media_activos)
        cbCantidadTipos = findViewById(R.id.cb_cantidad_tipos)

        etMes = findViewById<NumberPicker>(R.id.et_mes)
        etAnyo = findViewById<NumberPicker>(R.id.et_anyo)

        //Limites de mes y fechas
        etMes.minValue = 1
        etMes.maxValue = 12
        etAnyo.minValue = 2021
        etAnyo.maxValue = 2024

        val databaseHelper = BaseDatos(this, "myDatabase", null, 1)
        database = databaseHelper.readableDatabase

        if (checkPermission()) {
            Toast.makeText(this, "Permiso Aceptado", Toast.LENGTH_LONG).show()
        } else {
            requestPermissions()
        }

        btnGenerarPdf.setOnClickListener(View.OnClickListener {
            generarInforme()
        })
    }

    @SuppressLint("Range")
    private fun generarInforme() {
        val margenIzq = 20f

        val nombre1 = "Mustapha Bouleili"
        val nombre2 = "Edrhian Valerio"
        val fechaEntera = Calendar.getInstance().time
        val formatoFecha = SimpleDateFormat("dd-MM-yyyy")
        val fechaActual = formatoFecha.format(fechaEntera)

        val mes = etMes.value
        val ano = etAnyo.value

        // CONDICIONES PARA CREAR EL INFORME
        if (!cbMediaActivos.isChecked && !cbCantidadTipos.isChecked) {
            Toast.makeText(this, "Marca por lo menos una casilla de los datos", Toast.LENGTH_LONG).show()
            return
        }

        var tituloText = "Informe 3TEX: $mes/$ano"
        var contenidosText = "Contenidos de este informe:\n"

        if (cbMediaActivos.isChecked) {
            contenidosText += "- Media de usuarios activos por dia\n"
        }
        if (cbCantidadTipos.isChecked) {
            contenidosText += "- Recuento de la cantidad y el tipo de partidas\n"
        }

        val pdfDocument = PdfDocument()

        val paint = Paint()
        val encabezado = TextPaint()
        encabezado.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD))
        encabezado.textSize = 12f

        val titulo = TextPaint()
        titulo.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD))
        titulo.textSize = 20f

        val cuerpo = TextPaint()
        cuerpo.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL))
        cuerpo.textSize = 14f

        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_logo)
        val bitmapEscala = Bitmap.createScaledBitmap(bitmap, 80, 80, false)

        canvas.drawText("$nombre1 y $nombre2", margenIzq, 50f, encabezado)
        canvas.drawText("Fecha de creación: $fechaActual", 380f, 50f, encabezado)
        canvas.drawBitmap(bitmapEscala, 450f, 80f, paint)
        canvas.drawText(tituloText, margenIzq, 150f, titulo)

        val arrContenidos = contenidosText.split("\n")

        var y = 200f
        for (item in arrContenidos) {
            canvas.drawText(item, margenIzq, y, cuerpo)
            y += 15
        }
        y = 600f
        // Consulta los datos de la base de datos y los agrega al PDF
        val cursor = obtenerDatosDesdeBaseDeDatos(mes, ano)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val puntos = ArrayList<Bar>()
                do {
                    val tipoPartida = cursor.getString(cursor.getColumnIndex("MODO_JUEGO"))
                    val cantidad = cursor.getInt(cursor.getColumnIndex("TOTAL_PARTIDAS"))
                    val barra = Bar()
                    barra.color = generarColorHexAleatorio().toColorInt()
                    barra.name = tipoPartida
                    barra.value = cantidad.toFloat()
                    puntos.add(barra)
                } while (cursor.moveToNext())

                // Llama a la función para dibujar la gráfica en el PDF
                dibujarGraficaPDF(canvas, puntos, margenIzq, y)
            }
            cursor.close()
        }

        pdfDocument.finishPage(page)

        val file = File(Environment.getExternalStorageDirectory(), "Archivo.pdf")
        try {
            pdfDocument.writeTo(FileOutputStream(file))
            Toast.makeText(this, "Se creó el PDF correctamente", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error al crear el PDF", Toast.LENGTH_LONG).show()
        }

        pdfDocument.close()
    }

    private fun dibujarGraficaPDF(canvas: Canvas, puntos: ArrayList<Bar>, margenIzq: Float, y: Float) {
        // Calcular el valor máximo entre todas las barras
        val valorMaximo = puntos.maxOf { it.value }
        val margenSuperior = 450f
        val margenInferior = 50f
        val alturaDisponible = 792f
        val graficaHeight = alturaDisponible - margenSuperior - margenInferior
        val barraWidth = 50f
        val espacioEntreBarras = 50f

        var x = margenIzq
        puntos.forEach { barra ->
            val barraHeight = (barra.value / valorMaximo) * graficaHeight
            val barraRect = RectF(x, y - barraHeight, x + barraWidth, y)
            canvas.drawRect(barraRect, Paint().apply { color = barra.color })

            // Dibuja la etiqueta (nombre) de la barra
            val etiquetaX = x + barraWidth / 2
            val etiquetaY = y + 20
            canvas.drawText(barra.name, etiquetaX, etiquetaY, Paint().apply { textSize = 12f; textAlign = Paint.Align.CENTER })

            // Dibuja el valor de la barra
            val valorX = x + barraWidth / 2
            val valorY = y - barraHeight - 10
            canvas.drawText(barra.value.toString(), valorX, valorY, Paint().apply { textSize = 12f; textAlign = Paint.Align.CENTER })

            x += barraWidth + espacioEntreBarras
        }
    }




    private fun graficarBarras(puntos: ArrayList<Bar>) {
        val grafica = findViewById<View>(R.id.graphBar) as BarGraph
        grafica.bars = puntos
    }
    private fun generarColorHexAleatorio(): String {
        val letras = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F")
        var color = "#"
        for (i in 0..5) {
            color += letras[(Math.random() * 15).toInt()]
        }
        return color
    }

    private fun obtenerDatosDesdeBaseDeDatos(month: Int, year: Int): Cursor? {
        val databaseHelper = BaseDatos(this, "myDatabase", null, 1)
        val database = databaseHelper.readableDatabase

        return database.rawQuery(
            "SELECT MODO_JUEGO, SUM(NUM_PARTIDAS) AS TOTAL_PARTIDAS FROM Modos WHERE strftime('%m', FECHA) = ? AND strftime('%Y', FECHA) = ? GROUP BY MODO_JUEGO",
            arrayOf(String.format("%02d", month), year.toString())
        )
    }


    private fun checkPermission(): Boolean {
        val permission1 = ContextCompat.checkSelfPermission(applicationContext, WRITE_EXTERNAL_STORAGE)
        val permission2 = ContextCompat.checkSelfPermission(applicationContext, READ_EXTERNAL_STORAGE)
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE),
            200
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 200) {
            if (grantResults.size > 0) {
                val writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED

                if (!(writeStorage && readStorage)) {
                    Toast.makeText(this, "Permisos rechazados", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
        }
    }
}
