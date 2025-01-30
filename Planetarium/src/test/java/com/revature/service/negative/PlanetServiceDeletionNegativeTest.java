package com.revature.service.negative;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.service.parent.PlanetServiceTest;
import org.junit.*;
import org.mockito.Mockito;

import java.util.Optional;

public class PlanetServiceDeletionNegativeTest extends PlanetServiceTest {
    private String negativePlanetName;
    private String expectedMessage;

    @BeforeClass
    public static void startTestOutput(){
        System.out.println("Running PlanetServiceDeletionNegativeTest...");
    }

    @AfterClass
    public static void endTestOutput(){
        System.out.println("PlanetServiceDeletionNegativeTest Finished");
    }

    @Before
    public void negativeSetup() {
        negativePlanetName = "Planet Name That-is 1  long_name ";
        expectedMessage = "Invalid planet name";
    }

    @Test
    public void serviceDeletePlanetNegativeTest() {
        Mockito.when(planetDao.readPlanet(negativePlanetName)).thenReturn(Optional.empty());
        Mockito.when(planetDao.deletePlanet(negativePlanetName)).thenThrow(new AssertionError("PlanetFail exception, but it was not thrown when it should have been."));
        PlanetFail planetFail = Assert.assertThrows(PlanetFail.class, () -> {planetService.deletePlanet(negativePlanetName);});
        Assert.assertEquals(expectedMessage, planetFail.getMessage());
    }
}
