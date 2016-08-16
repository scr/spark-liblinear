package tw.edu.ntu.csie.liblinear

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by scr on 8/15/16.
  */
class DataPointTest extends FlatSpec with Matchers {
  "DataPoint.maxIndex" should "report the last index" in {
    val dataPoint = DataPoint(Array(Feature(0, 1d), Feature(1, 2d)), 5d)
    dataPoint.maxIndex shouldBe 1
  }

  "DataPoint.genTrainingPoint" should "return same x when b is < 0" in {
    val dataPoint = DataPoint(Array(Feature(0, 1d), Feature(1, 2d)), 5d)
    val trainingPoint = dataPoint.genTrainingPoint(3, -1d, 5d)
    trainingPoint.maxIndex shouldBe 1
    trainingPoint.x should be theSameInstanceAs dataPoint.x
  }
  it should "return same new x when b is > 0" in {
    val dataPoint = DataPoint(Array(Feature(0, 1d), Feature(1, 2d)), 5d)
    val trainingPoint = dataPoint.genTrainingPoint(4, 1d, 5d)
    trainingPoint.maxIndex shouldBe 3
    trainingPoint.x shouldNot be theSameInstanceAs dataPoint.x
    trainingPoint.x should contain allElementsOf dataPoint.x
    trainingPoint.x.length shouldBe dataPoint.x.length + 1
  }
  it should "return positive y when posLabel matches" in {
    val dataPoint = DataPoint(Array(Feature(0, 1d), Feature(1, 2d)), 5d)
    val trainingPoint = dataPoint.genTrainingPoint(3, -1d, 5d)
    trainingPoint.y shouldBe > (0d)
  }
  it should "return negative y when posLabel doesn't match" in {
    val dataPoint = DataPoint(Array(Feature(0, 1d), Feature(1, 2d)), 5d)
    val trainingPoint = dataPoint.genTrainingPoint(3, -1d, 222d)
    trainingPoint.y shouldBe < (0d)
  }

}
