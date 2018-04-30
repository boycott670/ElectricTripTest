package com.nespresso.exercise.electric_trip.impl;

final class ChargingCity extends City
{
  private final int chargePerHour;

  ChargingCity(String name, int chargePerHour)
  {
    super(name);
    this.chargePerHour = chargePerHour;
  }

  @Override
  int chargePerHour()
  {
    return chargePerHour;
  }

}
