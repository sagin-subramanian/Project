package ca.mcit.sagin

import java.io.{BufferedWriter, FileWriter}

import scala.io.Source

object EnrichmentOfData extends  App {
  val OutputFile = new BufferedWriter(new FileWriter("C:\\Users\\LENOVO\\Documents\\gtfs\\result.csv"))

  var calenSource = Source.fromFile("C:\\Users\\LENOVO\\IdeaProjects\\project-demo\\src\\main\\scala\\ca\\mcit\\sagin\\calender.txt")
  var calenList: List[Calender] = calenSource.getLines().toList.tail.map(_.split(",", -1)).map(data => Calender(data(0), data(1).toInt, data(2).toInt
    , data(3).toInt, data(4).toInt, data(5).toInt, data(6).toInt, data(7).toInt, data(8), data(9)))
  calenSource.close()


  var rouSource = Source.fromFile("C:\\Users\\LENOVO\\IdeaProjects\\project-demo\\src\\main\\scala\\ca\\mcit\\sagin\\routes.txt")
  var rouList: List[Route] = rouSource.getLines().toList.tail.map(_.split(",", -1)).map(data => Route(data(0).toInt, data(1), data(2), data(3), data(4),
    data(5), data(6), data(7)))
  rouSource.close()

  var triSource = Source.fromFile("C:\\Users\\LENOVO\\IdeaProjects\\project-demo\\src\\main\\scala\\ca\\mcit\\sagin\\trip.txt")
  var tripsList: List[Trip] = triSource.getLines().toList.tail.map(_.split(",", -1)).map(data => Trip(data(0).toInt, data(1), data(2), data(3), data(4).toInt,
    data(5).toInt, data(6).toInt))
  triSource.close()

  val tripNroute: List[InterTripRoute] = new Tjoin().join(tripsList, rouList)
  var enriched: List[FinalEnriched] = new Tjoin().enrichedJoin(tripNroute, calenList)

  val result = enriched.map(data => {
   val t = data.tripRoute.trip
    val r = data.tripRoute.route
    val c =data.calender
    t+","+r+","+c
  })
  for (i<-result){
    OutputFile.newLine()
    OutputFile.write(i)
  }
  OutputFile.close()
}






















