package ca.mcit.sagin

class Tjoin {

  def join(trip:List[Trip],route:List[Route]):List[InterTripRoute]
  = {val x: Map[Int,Route] = route.map(route=>route.route_id->route).toMap
                trip.filter(trip=> x.contains(trip.route_id)).map(trip => InterTripRoute(trip,x(trip.route_id)))

  }

  def enrichedJoin(tripRoute:List[InterTripRoute], calen: List[Calender]):List[FinalEnriched] = {
    val x: Map[String,Calender] = calen.map(calen => calen.service_id -> calen).toMap
    tripRoute.filter(tripRoute => x.contains(tripRoute.trip.service_id)).map(tripRoute => FinalEnriched(tripRoute,x(tripRoute.trip.service_id)
    ))
  }











}
