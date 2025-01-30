package com.revature.service.negative;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.service.parent.PlanetServiceTest;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Optional;

import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class PlanetServiceUpdateNegativeTest extends PlanetServiceTest {
    private Planet testPlanet;
    private Planet earthPlanet;
    private Planet marsPlanet;

    @Parameter
    public int planetId;

    @Parameter(1)
    public String newPlanetName;

    @Parameter(2)
    public int newOwnerId;

    @Parameter(3)
    public String imagePath;

    @Parameter(4)
    public String exceptionMessage;

    @Parameters
    public static Collection<Object> inputs() {
        //Valid inputs
        String jpegPath = "src/test/resources/Celestial-Images/moon-1.jpg";
        String pngPath = "src/test/resources/Celestial-Images/planet-png.png";
        int validPlanetId = 1;
        int validNewOwnerId = 2;
        String validPlanetName = "Venus-55_";

        //Existing inputs
        String existingPlanetName = "Earth";
        String existingImagePath = "src/test/resources/Celestial-Images/planet-1.jpg";
        int existingOwnerId = 1;

        //Invalid inputs
        String invalidPlanetName1 = "";
        String invalidPlanetName2 = "Planet name that-is way_way2 long";
        String invalidPlanetName3 = "Exciting!! planet";
        String invalidPlanetName4 = "Mars";
        int invalidPlanetId = 7;
        int invalidNewOwnerId = 10;
        String gifPath = "src/test/resources/Celestial-Images/planet-gif.gif";
        String planetNameMessage = "Invalid planet name";
        String ownerIdMessage = "Invalid owner id";
        String fileTypeMessage = "Invalid file type";
        String planetDoesNotExistMessage = "Planet not in database";

        return Arrays.asList(new Object[][]{
                {validPlanetId,validPlanetName,validNewOwnerId,gifPath,fileTypeMessage},
                {validPlanetId,validPlanetName,invalidNewOwnerId,existingImagePath,ownerIdMessage},
                {validPlanetId,validPlanetName,invalidNewOwnerId,null,ownerIdMessage},
                {validPlanetId,validPlanetName,invalidNewOwnerId,jpegPath,ownerIdMessage},
                {validPlanetId,validPlanetName,invalidNewOwnerId,pngPath,ownerIdMessage},
                {validPlanetId,validPlanetName,invalidNewOwnerId,gifPath,ownerIdMessage},
                {validPlanetId,invalidPlanetName1,existingOwnerId,existingImagePath,planetNameMessage},
                {validPlanetId,invalidPlanetName1,validNewOwnerId,existingImagePath,planetNameMessage},
                {validPlanetId,invalidPlanetName1,validNewOwnerId,null,planetNameMessage},
                {validPlanetId,invalidPlanetName1,validNewOwnerId,jpegPath,planetNameMessage},
                {validPlanetId,invalidPlanetName1,validNewOwnerId,pngPath,planetNameMessage},
                {validPlanetId,invalidPlanetName1,validNewOwnerId,gifPath,planetNameMessage},
                {validPlanetId,invalidPlanetName2,existingOwnerId,existingImagePath,planetNameMessage},
                {validPlanetId,invalidPlanetName2,validNewOwnerId,existingImagePath,planetNameMessage},
                {validPlanetId,invalidPlanetName2,validNewOwnerId,null,planetNameMessage},
                {validPlanetId,invalidPlanetName2,validNewOwnerId,jpegPath,planetNameMessage},
                {validPlanetId,invalidPlanetName2,validNewOwnerId,pngPath,planetNameMessage},
                {validPlanetId,invalidPlanetName2,validNewOwnerId,gifPath,planetNameMessage},
                {validPlanetId,invalidPlanetName3,existingOwnerId,existingImagePath,planetNameMessage},
                {validPlanetId,invalidPlanetName3,validNewOwnerId,existingImagePath,planetNameMessage},
                {validPlanetId,invalidPlanetName3,validNewOwnerId,null,planetNameMessage},
                {validPlanetId,invalidPlanetName3,validNewOwnerId,jpegPath,planetNameMessage},
                {validPlanetId,invalidPlanetName3,validNewOwnerId,pngPath,planetNameMessage},
                {validPlanetId,invalidPlanetName3,validNewOwnerId,gifPath,planetNameMessage},
                {validPlanetId,invalidPlanetName4,existingOwnerId,existingImagePath,planetNameMessage},
                {validPlanetId,invalidPlanetName4,validNewOwnerId,existingImagePath,planetNameMessage},
                {validPlanetId,invalidPlanetName4,validNewOwnerId,null,planetNameMessage},
                {validPlanetId,invalidPlanetName4,validNewOwnerId,jpegPath,planetNameMessage},
                {validPlanetId,invalidPlanetName4,validNewOwnerId,pngPath,planetNameMessage},
                {validPlanetId,invalidPlanetName4,validNewOwnerId,gifPath,planetNameMessage},
                {invalidPlanetId,existingPlanetName,existingOwnerId,existingImagePath,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,existingOwnerId,existingImagePath,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,validNewOwnerId,existingImagePath,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,validNewOwnerId,null,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,validNewOwnerId,jpegPath,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,validNewOwnerId,pngPath,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,validNewOwnerId,gifPath,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,invalidNewOwnerId,existingImagePath,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,invalidNewOwnerId,null,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,invalidNewOwnerId,jpegPath,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,invalidNewOwnerId,pngPath,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,invalidNewOwnerId,gifPath,planetDoesNotExistMessage},
        });

    }

    @BeforeClass
    public static void startTestOutput(){
        System.out.println("Running PlanetServiceUpdateNegativeTest...");
    }

    @AfterClass
    public static void endTestOutput(){
        System.out.println("PlanetServiceUpdateNegativeTest Finished");
    }

    @Before
    public void positiveSetup(){
        earthPlanet = new Planet(1,"Earth",1);
        earthPlanet.setImageData(Base64.getEncoder().encodeToString(imageHelper("src/test/resources/Celestial-Images/planet-1.jpg")));

        marsPlanet = new Planet();
        marsPlanet.setPlanetId(2);
        marsPlanet.setPlanetName("Mars");
        marsPlanet.setOwnerId(1);
        marsPlanet.setImageData(Base64.getEncoder().encodeToString(imageHelper("src/test/resources/Celestial-Images/planet-2.jpg")));
    }

    @Test
    public void serviceUpdatePlanetNegativeTest() {
        /*
            Need to mock:
            planetDao.readPlanet(planet_id);
            planetDao.readPlanet(planet_name);
            planetDao.checkOwnerExists(owner_id);
            planetDao.updatePlanet(planet);
         */
        Mockito.when(planetDao.readPlanet(1)).thenReturn(Optional.of(earthPlanet));
        Mockito.when(planetDao.readPlanet(7)).thenReturn(Optional.empty());
        Mockito.when(planetDao.readPlanet("Earth")).thenReturn(Optional.of(earthPlanet));
        Mockito.when(planetDao.readPlanet("Venus -55_")).thenReturn(Optional.empty());
        Mockito.when(planetDao.readPlanet("")).thenReturn(Optional.empty());
        Mockito.when(planetDao.readPlanet("Planet name that-is way_way2 long")).thenReturn(Optional.empty());
        Mockito.when(planetDao.readPlanet("Exciting!! planet")).thenReturn(Optional.empty());
        Mockito.when(planetDao.readPlanet("Mars")).thenReturn(Optional.of(marsPlanet));
        Mockito.when(planetDao.checkOwnerExists(1)).thenReturn(true);
        Mockito.when(planetDao.checkOwnerExists(2)).thenReturn(true);
        Mockito.when(planetDao.checkOwnerExists(10)).thenReturn(false);

        testPlanet = new Planet(planetId, newPlanetName, newOwnerId);
        if (imagePath != null){
            testPlanet.setImageData(Base64.getEncoder().encodeToString(imageHelper(imagePath)));
        }
        Mockito.when(planetDao.updatePlanet(testPlanet)).thenThrow(new AssertionError("PlanetFail exception expected but was never thrown"));
        PlanetFail planetFail = Assert.assertThrows(PlanetFail.class, ()-> {planetService.updatePlanet(testPlanet);});
        Assert.assertEquals(exceptionMessage, planetFail.getMessage());
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
