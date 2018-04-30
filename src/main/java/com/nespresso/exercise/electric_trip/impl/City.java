package com.nespresso.exercise.electric_trip.impl;

class City
{
  private final String name;

  private City nextCity;
  private int distanceToNextCity;

  City(String name)
  {
    this.name = name;
  }

  final void setNextCity(City nextCity, int distanceToNextCity)
  {
    this.nextCity = nextCity;
    this.distanceToNextCity = distanceToNextCity;
  }

  final double costToNextCity(final int speed)
  {
    return Integer.valueOf(distanceToNextCity)
        .doubleValue() / speed;
  }

  final boolean hasNextCity()
  {
    return nextCity != null;
  }

  final boolean canGoToNextCity(final double batterySize, final int speed)
  {
    return hasNextCity() && batterySize >= costToNextCity(speed);
  }

  final City nextCity()
  {
    return nextCity;
  }

  final int distanceToNextCity()
  {
    return distanceToNextCity;
  }

  final String getName()
  {
    return name;
  }

  int chargePerHour()
  {
    return 0;
  }

  @Override
  public final int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public final boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    City other = (City) obj;
    if (name == null)
    {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }
}
