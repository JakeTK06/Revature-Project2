package com.revature.repository.negative;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.repository.parent.PlanetDaoTest;
import org.junit.*;

import java.util.List;

public class PlanetDaoDeletePlanetByNameNegativeTest extends PlanetDaoTest {

    private String negativePlanetName;
    private String exceptionMessage;

    @BeforeClass
    public static void startTestOutput(){
        System.out.println("Running PlanetDaoDeletePlanetByNameNegativeTest...");
    }

    @AfterClass
    public static void endTestOutput(){
        System.out.println("PlanetDaoDeletePlanetByNameNegativeTest Finished");
    }

    @Before
    public void negativeSetup(){
        negativePlanetName = "Earth 2";
        exceptionMessage = "Invalid planet name";
    }

    @Test
    public void daoReadPlanetByOwnerNegativeTest(){
//        Boolean response = planetDao.deletePlanet(negativePlanetName);
//        Assert.assertEquals(false, response);
        PlanetFail exception = Assert.assertThrows(PlanetFail.class, () -> {planetDao.deletePlanet(negativePlanetName); });
        Assert.assertEquals(exceptionMessage, exception.getMessage());
    }

}