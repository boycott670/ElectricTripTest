package com.nespresso.exercise.electric_trip.impl;

final class Trip
{
  private City currentCity;

  private final int initialBatterySize;
  private double currentBatterySize;

  private final int lowSpeed;
  private final int highSpeed;

  Trip(City startCity, int batterySize, int lowSpeed, int highSpeed)
  {
    this.currentCity = startCity;

    this.initialBatterySize = batterySize;
    this.currentBatterySize = this.initialBatterySize;

    this.lowSpeed = lowSpeed;
    this.highSpeed = highSpeed;
  }

  private int distanceToNextCharingCity()
  {
    City currentCity = this.currentCity;
    int sumOfDistances = 0;

    while (currentCity.hasNextCity())
    {
      sumOfDistances += currentCity.distanceToNextCity();

      if (!(currentCity.nextCity() instanceof ChargingCity))
      {
        currentCity = currentCity.nextCity();
      } else
      {
        break;
      }
    }

    return sumOfDistances;
  }

  private boolean fullyCharged()
  {
    return currentBatterySize == initialBatterySize;
  }

  private double remainingDistanceInBattery(final int speed)
  {
    return currentBatterySize * speed;
  }

  private void genericGo(final int speed)
  {
    while (currentCity instanceof ChargingCity && !fullyCharged()
        ? remainingDistanceInBattery(speed) >= distanceToNextCharingCity()
        : currentCity.canGoToNextCity(currentBatterySize, speed))
    {
      currentBatterySize -= currentCity.costToNextCity(speed);

      currentCity = currentCity.nextCity();
    }
  }

  void go()
  {
    genericGo(lowSpeed);
  }

  void sprint()
  {
    genericGo(highSpeed);
  }

  String locationOf()
  {
    return currentCity.getName();
  }

  double chargeOf()
  {
    return currentBatterySize / initialBatterySize;
  }

  void charge(final int hoursOfCharge)
  {
    currentBatterySize += currentCity.chargePerHour() * hoursOfCharge;
    currentBatterySize = Math.min(initialBatterySize, currentBatterySize);
  }
}
