package com.revature.repository.negative;

import com.revature.planetarium.entities.Planet;
import com.revature.repository.parent.PlanetDaoTest;
import org.junit.*;

import java.util.List;

public class PlanetDaoReadPlanetsByOwnerNegativeTest extends PlanetDaoTest {

    private int negativeOwnerId;

    @BeforeClass
    public static void startTestOutput(){
        System.out.println("Running PlanetDaoReadPlanetsByOwnerNegativeTest...");
    }

    @AfterClass
    public static void endTestOutput(){
        System.out.println("PlanetDaoReadPlanetsByOwnerNegativeTest Finished");
    }

    @Before
    public void negativeSetup(){
        negativeOwnerId = 0;
    }

    @Test
    public void daoReadPlanetByOwnerNegativeTest(){
        List<Planet> response = planetDao.readPlanetsByOwner(negativeOwnerId);
        Assert.assertTrue(response.isEmpty());
    }

}
