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

    @Test
    public void daoUpdatePlanetPositiveTest() {
        testPlanet = new Planet(positivePlanetId,positivePlanetName,positiveNewOwnerId);
        if (positiveImagePath != null){
            imageHelper(positiveImagePath);
        }
        Optional<Planet> updatedPlanet = planetDao.updatePlanet(testPlanet);
        Assert.assertTrue(updatedPlanet.isPresent());
        Assert.assertEquals(positivePlanetName, updatedPlanet.get().getPlanetName());
        Assert.assertEquals(positiveNewOwnerId, updatedPlanet.get().getOwnerId());
        Assert.assertEquals(testPlanet.getImageData(), updatedPlanet.get().getImageData());
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
