package com.revature.service.positive;

import com.revature.planetarium.entities.Planet;
import com.revature.service.parent.PlanetServiceTest;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

public class PlanetServicePositiveTest extends PlanetServiceTest{
    private Planet positivePlanet;
    private Optional<Planet> mockOptional;
    private Planet mockReturnedPlanet;
    private Planet mockReturnedPlanet1;
    private Planet mockReturnedPlanet2;
    private List<Planet> mockReturnedPlanetList = new ArrayList<>();
    private boolean planetReturned;

    private Planet positivePlanetRetrieval;
    private Optional<Planet> planetRetrievalOptional;

    private Planet positiveDeletePlanet;
    private Optional<Planet> planetDeleteOptional;

    @Before
    public void positiveSetup() {
        positivePlanet = new Planet();
        positivePlanet.setPlanetId(0);
        positivePlanet.setPlanetName("Venus-55_");
        positivePlanet.setOwnerId(1);

        mockReturnedPlanet = new Planet();
        mockReturnedPlanet.setPlanetId(3);
        mockReturnedPlanet.setPlanetName("Venus-55_");
        mockReturnedPlanet.setOwnerId(1);

        mockReturnedPlanet1 = new Planet();
        mockReturnedPlanet1.setPlanetId(1);
        mockReturnedPlanet1.setPlanetName("Earth");
        mockReturnedPlanet1.setOwnerId(1);
        mockReturnedPlanet2 = new Planet();
        mockReturnedPlanet2.setPlanetId(2);
        mockReturnedPlanet2.setPlanetName("Mars");
        mockReturnedPlanet2.setOwnerId(1);

        mockReturnedPlanetList.add(mockReturnedPlanet1);
        mockReturnedPlanetList.add(mockReturnedPlanet2);
    }

    //TODO: Refactor code
    @Test @Ignore("Cannot compare String with boolean")
    public void createPlanetPositiveTest() {
    /*  Mockito.when(planetDao.createPlanet(positivePlanet)).thenReturn(mockReturnedPlanet);
        //returns planet or exception, not boolean. Needs fixing.
        boolean result = planetService.createPlanet(positivePlanet);
        Assert.assertEquals(planetReturned, result);
     */
    }

    @Test
    public void retrievalPlanetPositiveTest() {
        Mockito.when(planetDao.readPlanetsByOwner(positivePlanet.getOwnerId())).thenReturn(mockReturnedPlanetList);
        List<Planet> result = planetService.selectByOwner(1);
        Assert.assertEquals(mockReturnedPlanetList, result);
    }

    //TODO:Refactor code
    @Test @Ignore("cannot compare String and boolean")
    public void deletePlanetPositiveTest() {
    }
}
