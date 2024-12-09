package com.example.uf_1_act_17_tresenratlla_guillemdehon

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Variables del juego
    private var currentPlayer = "X" // Comienza con "X"
    private var board = arrayOf(
        arrayOf("", "", ""),
        arrayOf("", "", ""),
        arrayOf("", "", "")
    )
    private lateinit var buttons: Array<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Asociar botones del tablero
        buttons = arrayOf(
            findViewById(R.id.btn1), findViewById(R.id.btn2), findViewById(R.id.btn3),
            findViewById(R.id.btn4), findViewById(R.id.btn5), findViewById(R.id.btn6),
            findViewById(R.id.btn7), findViewById(R.id.btn8), findViewById(R.id.btn9)
        )

        // Configurar eventos de los botones
        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                onButtonClick(index, button)
            }
        }

        // Botón de reinicio
        findViewById<Button>(R.id.btnReset).setOnClickListener {
            resetGame()
        }
    }

    private fun onButtonClick(index: Int, button: Button) {
        val row = index / 3
        val col = index % 3

        if (board[row][col].isNotEmpty()) {
            Toast.makeText(this, "Casilla ocupada", Toast.LENGTH_SHORT).show()
            return
        }

        // Actualizar tablero y botón
        board[row][col] = currentPlayer
        button.text = currentPlayer

        // Verificar si alguien ganó
        if (checkWin()) {
            Toast.makeText(this, "¡Jugador $currentPlayer ganó!", Toast.LENGTH_LONG).show()
            disableBoard()
            return
        }

        // Cambiar turno
        currentPlayer = if (currentPlayer == "X") "O" else "X"
    }

    private fun checkWin(): Boolean {
        // Verificar filas, columnas y diagonales
        for (i in 0..2) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) return true
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) return true
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) return true
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) return true
        return false
    }

    private fun disableBoard() {
        buttons.forEach { it.isEnabled = false }
    }

    private fun resetGame() {
        // Limpiar tablero
        board = arrayOf(
            arrayOf("", "", ""),
            arrayOf("", "", ""),
            arrayOf("", "", "")
        )
        // Reiniciar botones
        buttons.forEach {
            it.text = ""
            it.isEnabled = true
        }
        // Reiniciar jugador
        currentPlayer = "X"
    }
}
