package com.revature.service.negative;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.service.parent.MoonServiceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Optional;

import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MoonServiceCreateMoonNegativeTest extends MoonServiceTest {

    private Moon negativeMoon;
    private Moon positiveMoon;
    private Planet positivePlanet;
    private Optional<Planet> mockPositivePlanet;

    @Parameter
    public int moonId;

    @Parameter(1)
    public String moonName;

    @Parameter(2)
    public int ownerId;

    @Parameter(3)
    public String imagePath;

    @Parameter(4)
    public String exceptionMessage;

    @Parameters
    public static Collection<Object> inputs(){
        return Arrays.asList(new Object[][]{
                {0, "", 1, "src/test/resources/Celestial-Images/planet-1.jpg", "Invalid moon name"},
                {0, "Moon name that-is way_way2 long", 1, "src/test/resources/Celestial-Images/planet-1.jpg", "Invalid moon name"},
                {0, "Exciting!! Moon", 1, "src/test/resources/Celestial-Images/planet-1.jpg", "Invalid moon name"},
                {0, "Luna", 1, "src/test/resources/Celestial-Images/planet-1.jpg", "Invalid moon name"},
                {0, "quasi-moon 1 Zoozve_", 1, "src/test/resources/Celestial-Images/planet-gif.gif", "Invalid file type"},
                {0, "quasi-moon 1 Zoozve_", 3, "src/test/resources/Celestial-Images/planet-1.jpg", "Invalid planet ID"}
        });
    }

    @Before
    public void negativeSetup(){
        positivePlanet = new Planet();
        positivePlanet.setPlanetName("Earth");
        positivePlanet.setOwnerId(1);
        mockPositivePlanet = Optional.of(positivePlanet);
        positiveMoon = new Moon(0, "Moon2.0", 1);
        negativeMoon = new Moon(moonId, moonName, ownerId);
        if(!imagePath.isEmpty()){
            imageHelper(imagePath);
        }
    }

    @Test
    public void serviceCreateMoonNegativeTest(){
        Mockito.when(moonDao.readPlanet(1)).thenReturn(mockPositivePlanet);
        Mockito.when(moonDao.readPlanet(3)).thenReturn(Optional.empty());
        Mockito.when(moonDao.readMoon("Luna")).thenReturn(Optional.of(positiveMoon));
        Mockito.when(moonDao.createMoon(negativeMoon)).thenThrow(new AssertionError("MoonFail exception expected, but it was not thrown when it should have been"));
        MoonFail moonFail = Assert.assertThrows(MoonFail.class, ()-> {moonService.createMoon(negativeMoon);});
        Assert.assertEquals(exceptionMessage, moonFail.getMessage());

    }

    public void imageHelper(String path){
        byte[] imageData;
        {
            try {
                imageData = Files.readAllBytes(Path.of(imagePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        negativeMoon.setImageData(Base64.getEncoder().encodeToString(imageData));
    }
}
