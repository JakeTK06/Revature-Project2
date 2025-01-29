package com.revature.service.positive;

import com.revature.planetarium.entities.Planet;
import com.revature.service.parent.PlanetServiceTest;
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

import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class PlanetServiceUpdatePositiveTest extends PlanetServiceTest {
    private Planet testPlanet;
    private Planet earthPlanet;

    @Parameter
    public int positivePlanetId;

    @Parameter(1)
    public String positivePlanetName;

    @Parameter(2)
    public int positiveNewOwnerId;

    @Parameter(3)
    public String positiveImagePath;


    @Parameters
    public static Collection<Object> inputs() {
        String jpegPath = "src/test/resources/Celestial-Images/moon-1.jpg";
        String pngPath = "src/test/resources/Celestial-Images/planet-png.png";
        String existingImagePath = "src/test/resources/Celestial-Images/planet-1.jpg";
        int validPlanetId = 1;
        String validNewPlanetName = "Venus -55_";
        String existingPlanetName = "Earth";
        int newOwnerId = 2;
        int existingOwnerId = 1;

        return Arrays.asList(new Object[][]{
                {validPlanetId,existingPlanetName,existingOwnerId,existingImagePath},
                {validPlanetId,validNewPlanetName,existingOwnerId,existingImagePath},
                {validPlanetId,validNewPlanetName,newOwnerId,existingImagePath},
                {validPlanetId,validNewPlanetName,newOwnerId,null},
                {validPlanetId,validNewPlanetName,newOwnerId,jpegPath},
                {validPlanetId,validNewPlanetName,newOwnerId,pngPath}
        });

    }

    @Before
    public void positiveSetup(){
        earthPlanet = new Planet(1,"Earth",1);
    }

    @Test
    public void serviceUpdatePlanetPositiveTest() {
        /*
            Need to mock:
            planetDao.readPlanet(planet_id);
            planetDao.readPlanet(planet_name);
            planetDao.checkOwnerExists(owner_id);
            planetDao.updatePlanet(planet);
         */
        Mockito.when(planetDao.readPlanet(1)).thenReturn(Optional.of(earthPlanet));
        Mockito.when(planetDao.readPlanet("Earth")).thenReturn(Optional.of(earthPlanet));
        Mockito.when(planetDao.readPlanet("Venus -55_")).thenReturn(Optional.empty());
        Mockito.when(planetDao.checkOwnerExists(1)).thenReturn(true);
        Mockito.when(planetDao.checkOwnerExists(2)).thenReturn(true);
        testPlanet = new Planet(positivePlanetId, positivePlanetName, positiveNewOwnerId);
        if(positiveImagePath != null){
            testPlanet.setImageData(Base64.getEncoder().encodeToString(imageHelper(positiveImagePath)));;
        }

        Mockito.when(planetDao.updatePlanet(testPlanet)).thenReturn(Optional.of(testPlanet));
        Planet updatedPlanet = planetService.updatePlanet(testPlanet);
        Assert.assertEquals(testPlanet, updatedPlanet);

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
