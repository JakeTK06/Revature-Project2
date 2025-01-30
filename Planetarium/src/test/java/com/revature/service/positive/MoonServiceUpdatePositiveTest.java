package com.revature.service.positive;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.entities.Planet;
import com.revature.service.parent.MoonServiceTest;
import org.junit.*;
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

import  static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class MoonServiceUpdatePositiveTest extends MoonServiceTest {
    private Moon testMoon;
    private Moon lunaMoon;
    private Planet earthPlanet;
    private Planet marsPlanet;

    @Parameter
    public int positiveMoonId;

    @Parameter(1)
    public String positiveNewMoonName;

    @Parameter(2)
    public int positiveNewPlanetId;

    @Parameter(3)
    public String positiveImagePath;

    @Parameters
    public static Collection<Object> inputs(){
        int validMoonId = 1;
        String validNewMoonName = "quasi-moon 1 Zoozve_";
        int validNewPlanetId = 2;
        String jpegPath = "src/test/resources/Celestial-Images/moon-2.jpg";
        String pngPath = "src/test/resources/Celestial-Images/planet-png.png";

        // String existing moon data
        String existingMoonName = "Luna";
        int existingPlanetId = 1;
        String existingImagePath = "src/test/resources/Celestial-Images/moon-1.jpg";

        return Arrays.asList(new Object[][]{
                {validMoonId,existingMoonName,existingPlanetId,existingImagePath},
                {validMoonId,validNewMoonName,existingPlanetId,existingImagePath},
                {validMoonId,validNewMoonName,validNewPlanetId,existingImagePath},
                {validMoonId,validNewMoonName,validNewPlanetId,null},
                {validMoonId,validNewMoonName,validNewPlanetId,jpegPath},
                {validMoonId,validNewMoonName,validNewPlanetId,pngPath}
        });
    }

    @BeforeClass
    public static void startTestOutput(){
        System.out.println("Running MoonServiceUpdatePositiveTest...");
    }

    @AfterClass
    public static void endTestOutput(){
        System.out.println("MoonServiceUpdatePositiveTest Finished");
    }

    @Before
    public void positiveSetup(){
        lunaMoon = new Moon(1, "Luna", 1);
        lunaMoon.setImageData(Base64.getEncoder().encodeToString(imageHelper("src/test/resources/Celestial-Images/moon-1.jpg")));

        earthPlanet = new Planet();
        earthPlanet.setPlanetId(1);
        earthPlanet.setPlanetName("Earth");
        earthPlanet.setOwnerId(1);
        earthPlanet.setImageData(Base64.getEncoder().encodeToString(imageHelper("src/test/resources/Celestial-Images/planet-1.jpg")));

        marsPlanet = new Planet();
        marsPlanet.setPlanetId(2);
        marsPlanet.setPlanetName("Mars");
        marsPlanet.setOwnerId(1);
        marsPlanet.setImageData(Base64.getEncoder().encodeToString(imageHelper("src/test/resources/Celestial-Images/planet-2.jpg")));
    }

    @Test
    public void serviceUpdateMoonPositiveTest(){
        /*
            Methods to mock:
            moonDao.readMoon(moon_id)
            moonDao.readMoon(moon_name)
            moonDao.readPlanet(planet_id)
            moonDao.updateMoon(moon)
         */
        Mockito.when(moonDao.readMoon(1)).thenReturn(Optional.of(lunaMoon));
        Mockito.when(moonDao.readMoon("Luna")).thenReturn((Optional.of(lunaMoon))); // existing moon name
        Mockito.when(moonDao.readMoon("quasi-moon 1 Zoozve_")).thenReturn(Optional.empty()); // valid new moon name
        Mockito.when(moonDao.readPlanet(1)).thenReturn(Optional.of(earthPlanet)); // current planet (Earth)
        Mockito.when(moonDao.readPlanet(2)).thenReturn(Optional.of(marsPlanet)); // new planet (Mars)
        testMoon = new Moon(positiveMoonId, positiveNewMoonName, positiveNewPlanetId);
        if (positiveImagePath != null){
            testMoon.setImageData(Base64.getEncoder().encodeToString(imageHelper(positiveImagePath)));
        }
        Mockito.when(moonDao.updateMoon(testMoon)).thenReturn(Optional.of(testMoon));
        Moon updatedMoon = moonService.updateMoon(testMoon);
        Assert.assertEquals(testMoon, updatedMoon);
    }

    public byte[] imageHelper(String path){
        byte[] imageData;
        {
            try {
                imageData = Files.readAllBytes(Path.of(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

//        testMoon.setImageData(Base64.getEncoder().encodeToString(imageData));
        return imageData;
    }
}
