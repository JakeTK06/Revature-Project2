package com.revature.service.positive;

import com.revature.planetarium.entities.Moon;
import com.revature.service.parent.MoonServiceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MoonServicePositiveTest extends MoonServiceTest {

    // inputs/outputs for CreateMoon

    // inputs/outputs for selectByPlanet
    private List<Moon> mockReturnedMoons;

    // inputs/outputs for deleteMoon


    @Before
    public void positiveSetup() {

        mockReturnedMoons = new ArrayList<Moon>();
        mockReturnedMoons.add(new Moon(1, "Luna", 1));
        mockReturnedMoons.add(new Moon(2, "Titan", 2));
    }


    @Test
    @Ignore("cannot compare Moon with boolean")
    // @TODO: Refactor code
    public void createMoonWithoutImagePositiveTest() {

    }

    @Test
    @Ignore("cannot compare Moon with boolean")
    // @TODO: Refactor code
    public void createMoonImagePositiveTest() {
    }


    @Test
    public void selectByPlanetPositiveTest() {
        Mockito.when(moonDao.readMoonsByPlanet(1)).thenReturn(mockReturnedMoons);
        List<Moon> result = moonService.selectByPlanet(1);
        Assert.assertEquals(result, mockReturnedMoons);
    }





    @Test
    @Ignore("cannot compare String with boolean")
    // @TODO: Refactor code
    public void deleteMoonWithoutImagePositiveTest() {

    }

    @Test
    @Ignore("cannot compare String with boolean")
    // @TODO: Refactor code
    public void deleteMoonWithImagePositiveTest() {

    }
}
