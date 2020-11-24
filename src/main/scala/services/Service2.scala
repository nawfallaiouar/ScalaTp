package services

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, hash}
import reader.SparkReaderWriter

object Service2 {
  def AnonymisationDonnées(df : DataFrame, OutputPath: String, OutputFormat: String)= {

    System.out.println("Anonymisation")

    var DataHash = df.filter(col("ClientID") === 292494523)


    var df1 = DataHash.withColumn("Region", hash(DataHash.columns.map(col):_*))
    var df2 = df1.withColumn("Country", hash(df1.columns.map(col):_*))
    var df3 = df2.withColumn("Item Type", hash(df2.columns.map(col):_*))
    var df4 = df3.withColumn("Sales Channel", hash(df3.columns.map(col):_*))
    var df5 = df4.withColumn("Order Priority", hash(df4.columns.map(col):_*))
    var df6 = df5.withColumn("Order Date", hash(df5.columns.map(col):_*))
    var df7 = df6.withColumn("ClientID", hash(df6.columns.map(col):_*))
    var df8 = df7.withColumn("Ship Date", hash(df7.columns.map(col):_*))
    var df9 = df8.withColumn("Units Sold", hash(df8.columns.map(col):_*))
    var df10 = df9.withColumn("Unit Price", hash(df9.columns.map(col):_*))
    var df11 = df10.withColumn("Unit Cost", hash(df10.columns.map(col):_*))
    var df12 = df11.withColumn("Total Revenue", hash(df11.columns.map(col):_*))
    var df13 = df12.withColumn("Total Cost", hash(df12.columns.map(col):_*))
    var df14 = df13.withColumn("Total Profit", hash(df13.columns.map(col):_*))
    println("On commence à hash les colonnes la c'est un exemple des 3 premières, on fait pareil pour toutes les autres")
    df3.show()
    var dfOtherFilter = df.filter(col("ClientID") =!= 292494523)
    var resultDF = dfOtherFilter.union(df14)

    //Pour écrire le résultat dans le fichier excel en sortie
    SparkReaderWriter.writeData(resultDF, OutputPath, OutputFormat)
    System.out.println("OK")





  }

}
