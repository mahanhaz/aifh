/*
 * Artificial Intelligence for Humans
 * Volume 1: Fundamental Algorithms
 * Scala Version
 * http://www.aifh.org
 * http://www.jeffheaton.com
 *
 * Code repository:
 * https://github.com/jeffheaton/aifh

 * Copyright 2013 by Jeff Heaton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * For more information on Heaton Research copyrights, licenses
 * and trademarks visit:
 * http://www.heatonresearch.com/copyright
 */
package com.heatonresearch.aifh.examples.learning

import com.heatonresearch.aifh.general.data.BasicData
import com.heatonresearch.aifh.learning.RBFNetwork
import com.heatonresearch.aifh.learning.TrainGreedyRandom
import com.heatonresearch.aifh.learning.score.ScoreRegressionData

/**
 * Learn the XOR function with a RBF Network trained by Greedy Random.
 */
object LearnXOR extends App {

  /**
   * The input necessary for XOR.
   */
  val XOR_INPUT = Vector(Vector(0.0, 0.0), Vector(1.0, 0.0), Vector(0.0, 1.0), Vector(1.0, 1.0))
  /**
   * The ideal data necessary for XOR.
   */
  val XOR_IDEAL = Vector(Vector(0.0), Vector(1.0), Vector(1.0), Vector(0.0))

  (new LearnXOR).process()
}

class LearnXOR extends SimpleLearn {
  import LearnXOR._
  /**
   * Perform the example.
   */
  def process() {
    val trainingData = BasicData.convertArrays(XOR_INPUT, XOR_IDEAL)
    val network = new RBFNetwork(2, 5, 1)

    val score = new ScoreRegressionData(trainingData)
    val train = new TrainGreedyRandom(true, network, score)
    performIterations(train, 1000000, 0.01, shouldMinimize = true)
    SimpleLearn.query(network, trainingData)
  }
}
