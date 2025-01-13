package com.revature.service.negative;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.service.planet.PlanetService;
import com.revature.service.parent.PlanetServiceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class PlanetServiceRetrievalNegativeTest extends PlanetServiceTest {

    private int negativeUserId;
    private List<Planet> mockReturnedPlanets;
    @Before
    public void negativeSetup() {
        negativeUserId = 7;
        mockReturnedPlanets = new ArrayList<>();
    }

    @Test
    public void retrievePlanetNegativeTest() {
        Mockito.when(planetDao.readPlanetsByOwner(negativeUserId)).thenReturn(mockReturnedPlanets);
        List<Planet> actualPlanets = planetService.selectByOwner(negativeUserId);
        Assert.assertEquals(actualPlanets, mockReturnedPlanets);
    }
}


