package com.revature.service.negative;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.MoonFail;
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
public class MoonServiceUpdateNegativeTest extends MoonServiceTest {
    private Moon testMoon;
    private Moon lunaMoon;
    private Planet earthPlanet;
    private Planet marsPlanet;
    private Moon titanMoon;

    @Parameterized.Parameter
    public int moonId;

    @Parameterized.Parameter(1)
    public String newMoonName;

    @Parameterized.Parameter(2)
    public int newPlanetId;

    @Parameterized.Parameter(3)
    public String imagePath;

    @Parameterized.Parameter(4)
    public String expectedExceptionMessage;

    @Parameterized.Parameters
    public static Collection<Object> inputs(){
        // Valid inputs
        int validMoonId = 1;
        String validNewMoonName = "quasi-moon 1 Zoozve_";
        int validNewPlanetId = 2;
        String jpegPath = "src/test/resources/Celestial-Images/moon-2.jpg";
        String pngPath = "src/test/resources/Celestial-Images/planet-png.png";

        // existing inputs
        String existingMoonName = "Luna";
        int existingPlanetId = 1;
        String existingImagePath = "src/test/resources/Celestial-Images/moon-1.jpg";

        // Invalid inputs
        int invalidMoonId = 7;
        String inmn1 = "";
        String inmn2 = "Moon name that-is way_way2 long";
        String inmn3 = "Exciting!! Moon";
        String inmn4 = "Titan";
        int invalidNewPlanetId = 10;
        String gifPath = "src/test/resources/Celestial-Images/planet-gif.gif";
        String fileTypeExceptionMessage = "Invalid file type";
        String planetIdExceptionMessage = "Invalid planet id";
        String moonNameExceptionMessage = "Invalid moon name";
        String moonIdExceptionMessage = "Invalid moon id";

        return Arrays.asList(new Object[][]{
                {validMoonId,validNewMoonName,validNewPlanetId,gifPath,fileTypeExceptionMessage},
                {validMoonId,existingMoonName,invalidNewPlanetId,existingImagePath,planetIdExceptionMessage},
                {validMoonId,existingMoonName,invalidNewPlanetId,null,planetIdExceptionMessage},
                {validMoonId,existingMoonName,invalidNewPlanetId,jpegPath,planetIdExceptionMessage},
                {validMoonId,existingMoonName,invalidNewPlanetId,pngPath,planetIdExceptionMessage},
                {validMoonId,existingMoonName,invalidNewPlanetId,gifPath,planetIdExceptionMessage},
                {validMoonId,inmn1,existingPlanetId,existingImagePath,moonNameExceptionMessage},
                {validMoonId,inmn1,invalidNewPlanetId,existingImagePath,moonNameExceptionMessage},
                {validMoonId,inmn1,invalidNewPlanetId,null,moonNameExceptionMessage},
                {validMoonId,inmn1,invalidNewPlanetId,jpegPath,moonNameExceptionMessage},
                {validMoonId,inmn1,invalidNewPlanetId,pngPath,moonNameExceptionMessage},
                {validMoonId,inmn1,invalidNewPlanetId,gifPath,moonNameExceptionMessage},
                {validMoonId,inmn2,existingPlanetId,existingImagePath,moonNameExceptionMessage},
                {validMoonId,inmn2,invalidNewPlanetId,existingImagePath,moonNameExceptionMessage},
                {validMoonId,inmn2,invalidNewPlanetId,null,moonNameExceptionMessage},
                {validMoonId,inmn2,invalidNewPlanetId,jpegPath,moonNameExceptionMessage},
                {validMoonId,inmn2,invalidNewPlanetId,pngPath,moonNameExceptionMessage},
                {validMoonId,inmn2,invalidNewPlanetId,gifPath,moonNameExceptionMessage},
                {validMoonId,inmn3,existingPlanetId,existingImagePath,moonNameExceptionMessage}, // 18 - Assertion error
                {validMoonId,inmn3,invalidNewPlanetId,existingImagePath,moonNameExceptionMessage},
                {validMoonId,inmn3,invalidNewPlanetId,null,moonNameExceptionMessage},
                {validMoonId,inmn3,invalidNewPlanetId,jpegPath,moonNameExceptionMessage},
                {validMoonId,inmn3,invalidNewPlanetId,pngPath,moonNameExceptionMessage},
                {validMoonId,inmn3,invalidNewPlanetId,gifPath,moonNameExceptionMessage},
                {validMoonId,inmn4,existingPlanetId,existingImagePath,moonNameExceptionMessage},
                {validMoonId,inmn4,invalidNewPlanetId,existingImagePath,moonNameExceptionMessage},
                {validMoonId,inmn4,invalidNewPlanetId,null,moonNameExceptionMessage},
                {validMoonId,inmn4,invalidNewPlanetId,jpegPath,moonNameExceptionMessage},
                {validMoonId,inmn4,invalidNewPlanetId,pngPath,moonNameExceptionMessage},
                {validMoonId,inmn4,invalidNewPlanetId,gifPath,moonNameExceptionMessage},
                {invalidMoonId,existingMoonName,existingPlanetId,existingImagePath,moonIdExceptionMessage},
                {invalidMoonId,validNewMoonName,existingPlanetId,existingImagePath,moonIdExceptionMessage},
                {invalidMoonId,validNewMoonName,invalidNewPlanetId,existingImagePath,moonIdExceptionMessage},
                {invalidMoonId,validNewMoonName,invalidNewPlanetId,null,moonIdExceptionMessage},
                {invalidMoonId,validNewMoonName,invalidNewPlanetId,jpegPath,moonIdExceptionMessage},
                {invalidMoonId,validNewMoonName,invalidNewPlanetId,pngPath,moonIdExceptionMessage},
                {invalidMoonId,validNewMoonName,invalidNewPlanetId,gifPath,moonIdExceptionMessage},
                {invalidMoonId,existingMoonName,invalidNewPlanetId,existingImagePath,moonIdExceptionMessage},
                {invalidMoonId,existingMoonName,invalidNewPlanetId,null,moonIdExceptionMessage},
                {invalidMoonId,existingMoonName,invalidNewPlanetId,jpegPath,moonIdExceptionMessage},
                {invalidMoonId,existingMoonName,invalidNewPlanetId,pngPath,moonIdExceptionMessage},
                {invalidMoonId,existingMoonName,invalidNewPlanetId,gifPath,moonIdExceptionMessage}
        });
    }

    @BeforeClass
    public static void startTestOutput(){
        System.out.println("Running MoonServiceUpdateNegativeTest...");
    }

    @AfterClass
    public static void endTestOutput(){
        System.out.println("MoonServiceUpdateNegativeTest Finished");
    }

    @Before
    public void negativeSetup(){
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

        titanMoon = new Moon(2, "Titan", 2);
        titanMoon.setImageData(Base64.getEncoder().encodeToString(imageHelper("src/test/resources/Celestial-Images/moon-2.jpg")));

    }

    @Test
    public void serviceUpdateMoonNegativeTest(){
        /*
            Methods to mock:
            moonDao.readMoon(moon_id)
            moonDao.readMoon(moon_name)
            moonDao.readPlanet(planet_id)
            moonDao.updateMoon(moon)
         */
        Mockito.when(moonDao.readMoon(1)).thenReturn(Optional.of(lunaMoon));
        Mockito.when(moonDao.readMoon(7)).thenReturn(Optional.empty());
        Mockito.when(moonDao.readMoon("Luna")).thenReturn(Optional.of(lunaMoon));
        Mockito.when(moonDao.readMoon("quasi-moon 1 Zoozve_")).thenReturn(Optional.empty());
        Mockito.when(moonDao.readMoon("")).thenReturn(Optional.empty());
        Mockito.when(moonDao.readMoon("Moon name that-is way_way2 long")).thenReturn(Optional.empty());
        Mockito.when(moonDao.readMoon("Exciting!! Moon")).thenReturn(Optional.empty());
        Mockito.when(moonDao.readMoon("Titan")).thenReturn(Optional.of(titanMoon));
        Mockito.when(moonDao.readPlanet(1)).thenReturn(Optional.of(earthPlanet)); // current planet (Earth)
        Mockito.when(moonDao.readPlanet(2)).thenReturn(Optional.of(marsPlanet)); // new planet (Mars)
        Mockito.when(moonDao.readPlanet(10)).thenReturn(Optional.empty());

        testMoon = new Moon(moonId, newMoonName, newPlanetId);
        if (imagePath != null){
            testMoon.setImageData(Base64.getEncoder().encodeToString(imageHelper(imagePath)));
        }
        Mockito.when(moonDao.updateMoon(testMoon)).thenThrow(new AssertionError("MoonFail exception expected, but it was not thrown when is should have been"));
        MoonFail moonFail = Assert.assertThrows(MoonFail.class, ()-> {moonService.updateMoon(testMoon);});
        Assert.assertEquals(expectedExceptionMessage, moonFail.getMessage());
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
