package com.example.tetrismobile.tetris

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import com.example.tetrismobile.R



class GameActivity extends Activity {
  var gameView: GameView = _

  private var currentScore = 0
  private var currentTetromino: Tetromino = Tetromino.randomTetromino()
  private var currentRow = 0
  private var currentCol = 0

  private def restoreState(savedInstanceState: Bundle): Unit = {
    currentScore = savedInstanceState.getInt("score")
    currentTetromino = savedInstanceState.getSerializable("current_tetromino").asInstanceOf[Tetromino]
    currentRow = savedInstanceState.getInt("currentRow")
    currentCol = savedInstanceState.getInt("currentCol")
  }

  override def onSaveInstanceState(outState: Bundle): Unit = {
    super.onSaveInstanceState(outState)
    outState.putInt("score", currentScore)
    outState.putSerializable("current_tetromino", currentTetromino)
    outState.putInt("currentRow", currentRow)
    outState.putInt("currentCol", currentCol)
  }

  override def onRestoreInstanceState(savedInstanceState: Bundle): Unit = {
    super.onRestoreInstanceState(savedInstanceState)

    restoreState(savedInstanceState)
  }


  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    gameView = new GameView(this)
    setContentView(gameView)

    if (savedInstanceState !=null ){
      restoreState(savedInstanceState)
    }


    val leftButton = findViewById(R.id.left_button).asInstanceOf[Button]
    val rightButton = findViewById(R.id.right_button).asInstanceOf[Button]
    val downButton = findViewById(R.id.down_button).asInstanceOf[Button]
    val rotateButton = findViewById(R.id.rotate_button).asInstanceOf[Button]

    leftButton.setOnClickListener(_ => gameView.moveTetrominoLeft())
    rightButton.setOnClickListener(_ => gameView.moveTetrominoRight())
    downButton.setOnClickListener(_ => gameView.moveTetrominoDown())
    rotateButton.setOnClickListener(_ => gameView.rotateTetromino())
  }

  override def onPause(): Unit  = {
    super.onPause()
    gameView.pauseGame()
  }

  override def onResume(): Unit = {
    super.onResume()
    gameView.resumeGame()
  }



}
