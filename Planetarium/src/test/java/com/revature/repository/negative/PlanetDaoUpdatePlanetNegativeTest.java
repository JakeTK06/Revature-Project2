package com.revature.repository.negative;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.repository.parent.PlanetDaoTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class PlanetDaoUpdatePlanetNegativeTest extends PlanetDaoTest {
    private Planet testPlanet;

    @Parameter
    public String negativePlanetName;

    @Parameter(1)
    public int negativePlanetId;

    @Parameter(2)
    public String exceptionMessage;

    @Parameters
    public static Collection<Object> inputs() {
        //String jpegPath = "src/test/resources/Celestial-Images/moon-1.jpg";
        //String pngPath = "src/test/resources/Celestial-Images/planet-png.png";
        //String gifPath = "src/test/resources/Celestial-Images/planet-gif.gif";
        //String validPlanetName = "Venus";
        int validPlanetId = 1;
        String validPlanetName = "Venus-55_";

        String InvalidPlanetName1 = "";
        String InvalidPlanetName2 = "Planet name that-is way_way2 long";
        String InvalidPlanetName3 = "Exciting!! planet";
        String InvalidPlanetName4 = "Earth";

        int invalidPlanetId = 7;

        String planetNameMessage = "Invalid planet name";
        String planetIdMessage = "Invalid planet id";
        //String fileTypeMessage = "Invalid file type";

        return Arrays.asList(new Object[][]{
                {InvalidPlanetName1,validPlanetId,planetNameMessage},
                {InvalidPlanetName2,validPlanetId,planetNameMessage},
                {InvalidPlanetName3,validPlanetId,planetNameMessage},
                {InvalidPlanetName4,validPlanetId,planetNameMessage},
                {validPlanetName,invalidPlanetId,planetIdMessage},
                {InvalidPlanetName1,invalidPlanetId,planetNameMessage},
                {InvalidPlanetName2,invalidPlanetId,planetNameMessage},
                {InvalidPlanetName3,invalidPlanetId,planetNameMessage},
                {InvalidPlanetName4,invalidPlanetId,planetNameMessage},
        });

    }

//    @Before
//    public void negativeSetup() {
//
//    }

    @Test
    public void daoUpdatePlanetNegativeTest() {
        testPlanet = new Planet(negativePlanetId, negativePlanetName);
        // Set image if there  is one
        /*if (negativeImagePath != null && !negativeImagePath.isEmpty()){
            imageHelper(negativeImagePath);
        }*/
        PlanetFail exception = Assert.assertThrows(PlanetFail.class, () -> {planetDao.updatePlanet(testPlanet);});
        Assert.assertEquals(exceptionMessage, exception.getMessage());
    }

    /*public void imageHelper(String path){
        byte[] imageData;
        {
            try {
                imageData = Files.readAllBytes(Path.of(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        testPlanet.setImageData(Base64.getEncoder().encodeToString(imageData));
    }*/

}
