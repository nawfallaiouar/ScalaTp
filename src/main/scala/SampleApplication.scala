import java.util.logging.{Level, Logger}

import config.ConfigParser
import org.apache.spark.sql.SparkSession
import reader.SparkReaderWriter
import services.{Service1, Service2, Service3}

object SampleApplication {

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.OFF)
    val configCli: ConfigParser = ConfigParser.getConfigArgs(args)

    implicit val spark: SparkSession = SparkSession.builder().master("local").getOrCreate()


    val df = SparkReaderWriter.readData(configCli.inputPath, configCli.inputFormat)
    println(df.count());



    //Appel service 1

    Service1.suppressionDonnées(df, configCli.outputFormat,configCli.outputFormat)

    //Appel service 2

    Service2.AnonymisationDonnées(df, configCli.outputPath, configCli.outputFormat)

    //Appel service 3

    Service3.RecuperationDonneesClient(df, configCli.outputPath, configCli.outputFormat)

    /*configCli.service match {
      case " service1" => Service1.suppressionDonnées(df, configCli.clientId, configCli.outputPath, configCli.outputFormat)
      case " service2" => Service2.AnonymisationDonnées(df, configCli.clientId, configCli.outputPath, configCli.outputFormat)
      case " service3" => Service3.implemService3()
      case _ => {
        println("Service non reconnu")
        sys.exit(1)
      }
    }*/
  }
}