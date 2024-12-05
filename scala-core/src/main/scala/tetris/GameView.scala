import android.content.Context
import android.graphics.{Canvas, Paint}
import android.view.{MotionEvent, SurfaceHolder,SurfaceView}
import android.os.Handler

class GameView (context: Context) extends SurfaceView(context) with SurfaceHolder.Callback {
  private val board = new Board(10, 20)
  private val paint = new Paint()

  private val cellsize = 40

  @volatile private var isRunning = true
  @volatile private var isPaused = false

  private val handler = new Handler()

  def pauseGame(): Unit = {
    isPaused = true
  }

  def resumeGame(): Unit = {
    isPaused = false
    handler.post(gameRunnable)
  }

  getHolder.addCallback(this)


  val gameRunnable: Runnable = new Runnable {
    override def run(): Unit = {
      if (isRunning && !isPaused && !board.checkGameOver()){
        moveTetrominoDown()
        handler.postDelayed(this, 500)
      }
    }
  }

  override def onDraw(canvas: Canvas): Unit = {
    super.onDraw(canvas)
    canvas.drawColor(0xFF000000)// черный

    for(y <- 0 until board.totalHeight){
      for(x <- 0 until board.width){
        if(board.grid(y)(x) !=0){
          paint.setColor(board.colors(y)(x)) // зеленый
          canvas.drawRect(x * cellsize, y * cellsize, (x + 1) * cellsize, (y + 1) * cellsize, paint)
        }
      }
    }

    board.currentTetromino.blocks.foreach{
      case (x, y) =>
        if (x >= 0 && x < board.width && y >= 0 && y < board.totalHeight) {
          paint.setColor(board.currentTetromino.color) // цвет падающей фигуры
          canvas.drawRect(x * cellsize, y * cellsize, (x + 1) * cellsize, (y + 1) * cellsize, paint)
        }
    }

    paint.setColor(0xFFFFFFFF) //белый
    paint.setTextSize(50)
    canvas.drawText(s"Score: ${board.score}", 20, 50, paint)

  }

  override def onTouchEvent(event: MotionEvent): Boolean = {
    event.getAction match {
      case MotionEvent.ACTION_DOWN =>
        val x = event.getX.toInt / cellsize
        val y = event.getY.toInt / cellsize
        println(s"Tapped on block: ($x, $y)")
        true
      case _ => false
    }
  }

//  def updateGame(): Unit = {
//    if (!isPaused && isRunning) {
//      board.moveTetrominoDown()
//      postInvalidate()
//    }
//  }

  override def surfaceCreated(holder: SurfaceHolder): Unit = {
    isRunning = true
    handler.post(gameRunnable)

  }

  def moveTetrominoLeft():Unit = {
    if(!isPaused && isRunning) {
      board.moveTetrominoLeft()
      postInvalidate()
    }
  }

  def moveTetrominoRight(): Unit = {
    if (!isPaused && isRunning) {
      board.moveTetrominoRight()
      postInvalidate()
    }
  }

  def moveTetrominoDown():Unit = {
    if (!isPaused && isRunning) {
      board.moveTetrominoDown()
      postInvalidate()
    }
  }

  def rotateTetromino(): Unit = {
    if (!isPaused && isRunning) {
      board.rotateTetromino()
      postInvalidate()
    }
  }



  override def surfaceChanged (holder: SurfaceHolder, format: Int, width: Int, height: Int): Unit = { }

  override def surfaceDestroyed(holder: SurfaceHolder): Unit = {
    isRunning = false
    handler.removeCallbacks(gameRunnable)
  }
}


