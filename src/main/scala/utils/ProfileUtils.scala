package utils

object ProfileUtils {

  /*
    use to easily time / profile functions statements

    import ProfileUtils._
    val result = timeit2 { 1 + 2 }
    */
  def timeit[R](block: => R): R = {
    val t1 = System.nanoTime()
    val result = block    // call-by-name
    val t2 = System.nanoTime()
    println("### timeit() : Elapsed time:  %,d  ms".format( (t2 - t1) /1000000 ) )
    result
  }


  /*
    use to easily time / profile functions statements
    returns a tuple (result , elapsed_time_in_ms)

    import ProfileUtils._
    val (result, elapsed) = timeit2 { 1 + 2 }

    var x = timeit2 { some_function }
    // result in  x._1,   elapsed time : x._2

    */
    def timeit2 [R](block: => R): (R, Long) = {
      val t1 = System.nanoTime()
    val result = block    // call-by-name
    val t2 = System.nanoTime()
    //println("### timeit() : Elapsed time:  %,d  ms".format( (t2 - t1) /1000000 ) )
    (result,  (t2-t1)/1000000)
  }
}
