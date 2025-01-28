package com.revature.repository.positive;

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
import java.util.Optional;

import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class PlanetDaoUpdatePlanetPositiveTest extends PlanetDaoTest{
    private Planet testPlanet;
    private Planet newPlanet;
    private int validPlanetId;
    private String validPlanetName;

    @Parameter
    public int positivePlanetId;

    @Parameter(1)
    public String positivePlanetName;

    //@Parameter(2)
    //public String positiveImagePath;


    @Parameters
    public static Collection<Object> inputs() {
        //String jpegPath = "src/test/resources/Celestial-Images/moon-1.jpg";
        //String pngPath = "src/test/resources/Celestial-Images/planet-png.png";
        //String gifPath = "src/test/resources/Celestial-Images/planet-gif.gif";
        int validPlanetId = 1;
        String validPlanetName = "Earth";

        String newPlanetName1 = "Venus -55_";
        String newPlanetName2 = "";
        int newOwnerId = 2;

        return Arrays.asList(new Object[][]{
                {validPlanetId,newPlanetName1},
                {validPlanetId,newPlanetName2},
                {newOwnerId,newPlanetName1},
                {newOwnerId,newPlanetName2},
                {newOwnerId,validPlanetName}
        });

    }

    @Before
    public void positiveSetup() {
        validPlanetId = 1;
        validPlanetName = "Earth";
        testPlanet = new Planet(validPlanetId, validPlanetName);
    }

    @Test
    public void daoUpdatePlanetPositiveTest() {
        newPlanet = new Planet(positivePlanetId, positivePlanetName);
        Optional<Planet> updatedPlanet = planetDao.updatePlanet(testPlanet);
        Assert.assertTrue(updatedPlanet.isPresent());
        Assert.assertEquals(newPlanet, updatedPlanet.get());
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
