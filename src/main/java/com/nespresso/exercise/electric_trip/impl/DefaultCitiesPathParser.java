package com.nespresso.exercise.electric_trip.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

final class DefaultCitiesPathParser implements CitiesPathParser
{

  @Override
  public Set<City> parseCitiesPath(String path)
  {
    final String[] tokens = path.split("-");

    final Map<String, City> cities = new HashMap<>();

    for (int tokenIndex = 0; tokenIndex < tokens.length; tokenIndex += 2)
    {
      if (tokens[tokenIndex].indexOf(':') != -1)
      {
        final String[] subTokens = tokens[tokenIndex].split(":");

        cities.put(subTokens[0], new ChargingCity(subTokens[0], Integer.valueOf(subTokens[1])));
      } else
      {
        cities.put(tokens[tokenIndex], new City(tokens[tokenIndex]));
      }
    }

    final Function<? super String, ? extends String> cityNameFetcher = token -> token.indexOf(':') != -1
        ? token.split(":")[0]
        : token;

    for (int tokenIndex = 0; tokenIndex < tokens.length - 1; tokenIndex += 2)
    {
      cities.get(cityNameFetcher.apply(tokens[tokenIndex]))
          .setNextCity(cities.get(cityNameFetcher.apply(tokens[tokenIndex + 2])),
              Integer.valueOf(tokens[tokenIndex + 1]));
    }

    return new HashSet<>(cities.values());
  }

}
