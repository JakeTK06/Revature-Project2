package com.revature.repository.negative;

import com.revature.planetarium.entities.Moon;
import com.revature.repository.parent.MoonDaoTest;
import org.junit.*;

import java.util.List;

public class MoonDaoRetrievalNegativeTest extends MoonDaoTest {

    private int negativePlanetIdForRetrieval;

    @BeforeClass
    public static void startTestOutput(){
        System.out.println("Running MoonDaoRetrievalNegativeTest...");
    }

    @AfterClass
    public static void endTestOutput(){
        System.out.println("MoonDaoRetrievalNegativeTest Finished");
    }

    @Before
    public void negativeSetup(){
        negativePlanetIdForRetrieval = 7;
    }

    @Test
    public void daoReadMoonsByPlanetNegativeTest(){
        List<Moon> result = moonDao.readMoonsByPlanet(negativePlanetIdForRetrieval);
        Assert.assertTrue(result.isEmpty());
    }
}
