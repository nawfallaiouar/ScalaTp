package services


import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.col
import reader.SparkReaderWriter

object Service1 {

  def suppressionDonnées(df : DataFrame, OutputPath: String, OutputFormat: String) = {

    System.out.println("methode suppressionData")
    var dataResult =  df.filter(col("ClientID") =!= 292494523)
    println(dataResult.count());

    //écrire le résultat dans le fichier excel en sortie
    SparkReaderWriter.writeData(dataResult, OutputPath, OutputFormat)

    System.out.println("OK")


  }

}
