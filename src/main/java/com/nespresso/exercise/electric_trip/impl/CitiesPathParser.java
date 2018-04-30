package com.nespresso.exercise.electric_trip.impl;

import java.util.Set;

interface CitiesPathParser
{
  Set<City> parseCitiesPath(final String path);
}
