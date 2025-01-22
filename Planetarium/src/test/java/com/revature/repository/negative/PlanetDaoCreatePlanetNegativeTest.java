package com.revature.repository.negative;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.repository.parent.PlanetDaoTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;

import  static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class PlanetDaoCreatePlanetNegativeTest extends PlanetDaoTest {

    public Planet testPlanet;

    @Parameter
    public int planetId;

    @Parameter(1)
    public String planetName;

    @Parameter(2)
    public int ownerId;

    @Parameter(3)
    public String imagePath;

    @Parameter(4)
    public String exceptionMessage;

    @Parameters
    public static Collection<Object> inputs(){
        return Arrays.asList(new Object[][]{
                {0, "Venus -55_", 1, "src/test/resources/Celestial-Images/planet-gif.gif", "Invalid file type"},
                {0, "", 1, "src/test/resources/Celestial-Images/planet-1.jpg", "Invalid planet name"},
                {0, "Planet Name That-is 1  long_name ", 1, "src/test/resources/Celestial-Images/planet-1.jpg", "Invalid planet name"},
                {0, "Exciting!! planet", 1, "src/test/resources/Celestial-Images/planet-1.jpg", "Invalid planet name"},
                {0, "Earth", 1, "src/test/resources/Celestial-Images/planet-1.jpg", "Invalid planet name"},
                {0, "", 1, "", "Invalid planet name"},
        });
    }

    @Test
    public void daoCreatePlanetNegativeTest(){
        testPlanet = new Planet();
        testPlanet.setPlanetId(planetId);
        testPlanet.setPlanetName(planetName);
        testPlanet.setOwnerId(ownerId);
        if(!imagePath.isEmpty()){
            imageHelper(imagePath);
        }

        PlanetFail exception = Assert.assertThrows(PlanetFail.class, ()-> {planetDao.createPlanet(testPlanet);});
        Assert.assertEquals(exceptionMessage, exception.getMessage());
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

        testPlanet.setImageData(Base64.getEncoder().encodeToString(imageData));
    }

}
