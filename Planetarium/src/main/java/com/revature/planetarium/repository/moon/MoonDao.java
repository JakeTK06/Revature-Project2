package com.revature.planetarium.repository.moon;

import java.util.List;
import java.util.Optional;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.entities.Planet;

public interface MoonDao {

    Optional<Moon> createMoon(Moon moon);
    Optional<Moon> readMoon(int id);
    Optional<Moon> readMoon(String name);
    List<Moon> readAllMoons();
    List<Moon> readMoonsByPlanet(int planetId);
    Optional<Moon> updateMoon(Moon moon);
    Optional<Planet> readPlanet(int id);
    boolean deleteMoon(int id);
    boolean deleteMoon(String name);

}
