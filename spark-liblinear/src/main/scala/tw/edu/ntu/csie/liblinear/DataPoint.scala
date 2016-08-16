package tw.edu.ntu.csie.liblinear

case class Feature(index: Int, value: Double) {}

/**
  * DataPoint represents a sparse data point with label.
  *
  * @param x features represented in an Array of Feature
  * @param y label
  */
case class DataPoint(x: Array[Feature], y: Double) {

  def maxIndex: Int = this.x.last.index

  def genTrainingPoint(n: Int, b: Double, posLabel: Double): DataPoint = {
    val x: Array[Feature] = if (b < 0) this.x else this.x :+ Feature(n - 1, b)
    val y = if (this.y == posLabel) 1.0 else -1.0
    DataPoint(x, y)
  }
}
