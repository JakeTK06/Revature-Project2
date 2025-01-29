package com.revature.repository.negative;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.repository.parent.PlanetDaoTest;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.runners.Parameterized.*;
import java.util.Base64;

@RunWith(Parameterized.class)
public class PlanetDaoUpdatePlanetNegativeTest extends PlanetDaoTest {
    private Planet testPlanet;

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
                {validPlanetId,validPlanetName,invalidPlanetId,existingImagePath,ownerIdMessage},
                {validPlanetId,validPlanetName,invalidPlanetId,null,ownerIdMessage},
                {validPlanetId,validPlanetName,invalidPlanetId,jpegPath,ownerIdMessage},
                {validPlanetId,validPlanetName,invalidPlanetId,pngPath,ownerIdMessage},
                {validPlanetId,validPlanetName,invalidPlanetId,gifPath,ownerIdMessage},
                {validPlanetId,invalidPlanetName1,existingOwnerId,existingImagePath,planetNameMessage},
                {validPlanetId,invalidPlanetName1,validPlanetId,existingImagePath,planetNameMessage},
                {validPlanetId,invalidPlanetName1,validPlanetId,null,planetNameMessage},
                {validPlanetId,invalidPlanetName1,validPlanetId,jpegPath,planetNameMessage},
                {validPlanetId,invalidPlanetName1,validPlanetId,pngPath,planetNameMessage},
                {validPlanetId,invalidPlanetName1,validPlanetId,gifPath,planetNameMessage},
                {validPlanetId,invalidPlanetName2,existingOwnerId,existingImagePath,planetNameMessage},
                {validPlanetId,invalidPlanetName2,validPlanetId,existingImagePath,planetNameMessage},
                {validPlanetId,invalidPlanetName2,validPlanetId,null,planetNameMessage},
                {validPlanetId,invalidPlanetName2,validPlanetId,jpegPath,planetNameMessage},
                {validPlanetId,invalidPlanetName2,validPlanetId,pngPath,planetNameMessage},
                {validPlanetId,invalidPlanetName2,validPlanetId,gifPath,planetNameMessage},
                {validPlanetId,invalidPlanetName3,existingOwnerId,existingImagePath,planetNameMessage},
                {validPlanetId,invalidPlanetName3,validPlanetId,existingImagePath,planetNameMessage},
                {validPlanetId,invalidPlanetName3,validPlanetId,null,planetNameMessage},
                {validPlanetId,invalidPlanetName3,validPlanetId,jpegPath,planetNameMessage},
                {validPlanetId,invalidPlanetName3,validPlanetId,pngPath,planetNameMessage},
                {validPlanetId,invalidPlanetName3,validPlanetId,gifPath,planetNameMessage},
                {validPlanetId,invalidPlanetName4,existingOwnerId,existingImagePath,planetNameMessage},
                {validPlanetId,invalidPlanetName4,validPlanetId,existingImagePath,planetNameMessage},
                {validPlanetId,invalidPlanetName4,validPlanetId,null,planetNameMessage},
                {validPlanetId,invalidPlanetName4,validPlanetId,jpegPath,planetNameMessage},
                {validPlanetId,invalidPlanetName4,validPlanetId,pngPath,planetNameMessage},
                {validPlanetId,invalidPlanetName4,validPlanetId,gifPath,planetNameMessage},
                {invalidPlanetId,existingPlanetName,existingOwnerId,existingImagePath,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,existingOwnerId,existingImagePath,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,validPlanetId,existingImagePath,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,validPlanetId,null,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,validPlanetId,jpegPath,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,validPlanetId,pngPath,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,validPlanetId,gifPath,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,invalidPlanetId,existingImagePath,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,invalidPlanetId,null,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,invalidPlanetId,jpegPath,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,invalidPlanetId,pngPath,planetDoesNotExistMessage},
                {invalidPlanetId,validPlanetName,invalidPlanetId,gifPath,planetDoesNotExistMessage},
        });

    }

    @Test
    public void daoUpdatePlanetNegativeTest() {
        testPlanet = new Planet(planetId, newPlanetName, newOwnerId);
        // Set image if there  is one
        if (imagePath != null){
            imageHelper(imagePath);
        }
        PlanetFail exception = Assert.assertThrows(PlanetFail.class, () -> {planetDao.updatePlanet(testPlanet);});
        Assert.assertEquals(exceptionMessage, exception.getMessage());
    }

    public void imageHelper(String path){
        byte[] imageData;
        {
            try {
                imageData = Files.readAllBytes(Path.of(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        testPlanet.setImageData(Base64.getEncoder().encodeToString(imageData));
    }

}
