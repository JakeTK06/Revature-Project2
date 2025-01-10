package com.revature.repository.negative;

import com.revature.planetarium.entities.Planet;
import com.revature.repository.parent.PlanetDaoTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PlanetDaoReadPlanetsByOwnerNegativeTest extends PlanetDaoTest {

    private int negativeOwnerId;

    @Before
    public void negativeSetup(){
        negativeOwnerId = 0;
    }

    @Test
    public void readPlanetByOwnerNegativeTest(){
        List<Planet> response = planetDao.readPlanetsByOwner(negativeOwnerId);
        Assert.assertTrue(response.isEmpty());
    }

}
