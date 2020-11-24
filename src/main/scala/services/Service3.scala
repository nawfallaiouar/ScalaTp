package services

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.col

object Service3 {

  def RecuperationDonneesClient(df : DataFrame, OutputPath: String, OutputFormat: String) = {
    System.out.println("methode Récupération des données d'un client")
    var dataResult =  df.filter(col("ClientID") === 292494523)
    println(dataResult.count());
    dataResult.show()

    //TODO : envoie de mail

    System.out.println("OK")

  }

}
