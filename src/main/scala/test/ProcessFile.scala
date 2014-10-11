package test

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import utils.ProfileUtils._

/*
Usage:
spark-submit --class 'test.ProcessFile' --master spark://localhost:7077  target/scala-2.10/opr-spark_2.10-1.0.jar    <file to process>

    file to process can be :  /etc/hosts   or   scripts/1M.data  ....

e.g:
- with 4G executor memory and turning off verbose logging
    spark-submit --class test.ProcessFile  --master spark://localhost:7077 --executor-memory 4g   --driver-class-path logging/  target/scala-2.10/opr-spark_2.10-1.0.jar   s3n://opr-spark/1M.data

*/
object ProcessFile {
  def main(args: Array[String]) {
    if (args.length != 1) {
        println ("need file to load")
        System.exit(1)
    }
    val file = args(0)

    val conf = new SparkConf().setAppName("Process File")
    val sc = new SparkContext(conf)

    // amazon security keys must be set to access data from S3
    // option 1) set as environment properties
    //  export AWS_ACCESS_KEY_ID="xxx"
    //  export AWS_SECRET_ACCESS_KEY="yyyy"
    //
    // option 2) set in the code as follows
    // sc.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", "xxx")
    // sc.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", "yyy")


    val f = sc.textFile(file)

    var t1 = 0L
    var t2 = 0L
    var count = 0L

    for (i <- 1 to 5) {
        val result = timeit2 { f.count() }
        println ("### %s : count (%,d) took %,d ms".format(file, result._1, result._2 ))
    }


    // cache
    val result  = timeit2 { f.cache()  }
    println ("### %s : --- time to cache : %,d ms".format( file,  result._2))

    // count again
    for (i <- 1 to 5) {
        val result = timeit2 { f.count() }
        println ("### %s : count (%,d) took %,d ms".format(file, result._1,  result._2))
    }

  }
}
