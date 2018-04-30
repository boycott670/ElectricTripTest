package com.nespresso.exercise.electric_trip.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class ElectricTrip
{
  private final CitiesPathParser citiesPathParser = new DefaultCitiesPathParser();

  private final Map<String, City> cities;
  private final Map<Integer, Trip> trips = new HashMap<>();

  private int nextTripId = 1;

  public ElectricTrip(final String path)
  {
    cities = citiesPathParser.parseCitiesPath(path)
        .stream()
        .collect(Collectors.toMap(City::getName, Function.identity()));
  }

  public int startTripIn(final String startCityName, final int batterySize, final int lowSpeedPerformance,
      final int highSpeedPerformance)
  {
    trips.put(nextTripId, new Trip(cities.get(startCityName), batterySize, lowSpeedPerformance, highSpeedPerformance));
    return nextTripId++;
  }

  public void go(final int tripId)
  {
    trips.get(tripId)
        .go();
  }

  public void sprint(final int tripId)
  {
    trips.get(tripId)
        .sprint();
  }

  public String locationOf(final int tripId)
  {
    return trips.get(tripId)
        .locationOf();
  }

  public String chargeOf(final int tripId)
  {
    return String.format("%.0f%%", trips.get(tripId)
        .chargeOf() * 100);
  }

  public void charge(final int tripId, final int hoursOfCharge)
  {
    trips.get(tripId)
        .charge(hoursOfCharge);
  }
}
