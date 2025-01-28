package com.revature.repository.positive;

import com.revature.planetarium.entities.Planet;
import com.revature.repository.parent.PlanetDaoTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class PlanetDaoPositiveTest extends PlanetDaoTest {

    private Planet positivePlanet;
    private int positivePlanetId;
    private String positivePlanetName;
    private String positivePlanetNameDeletion;
    private int positiveOwnerId;
    private byte[] imageData;

    @Before
    public void positiveSetup(){
        positivePlanet = new Planet();
        positivePlanetId = 0;
        positivePlanetName = "Venus -55_";
        positivePlanetNameDeletion = "Earth";
        positiveOwnerId = 1;
        positivePlanet.setPlanetName(positivePlanetName);
        positivePlanet.setOwnerId(positiveOwnerId);
        positivePlanet.setPlanetId(positivePlanetId);
    }

    @Test
    public void daoCreatePlanetPositiveTestJPG(){
        {
            try {
                imageData = Files.readAllBytes(Path.of("src/test/resources/Celestial-Images/planet-1.jpg"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        positivePlanet.setImageData(Base64.getEncoder().encodeToString(imageData));
        Optional<Planet> response = planetDao.createPlanet(positivePlanet);
        Assert.assertTrue(response.isPresent());
        Assert.assertNotEquals(0, response.get().getPlanetId());
    }

    @Test
    public void daoCreatePlanetPositiveTestPNG(){
        {
            try {
                imageData = Files.readAllBytes(Path.of("src/test/resources/Celestial-Images/planet-png.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        positivePlanet.setImageData(Base64.getEncoder().encodeToString(imageData));
        Optional<Planet> response = planetDao.createPlanet(positivePlanet);
        Assert.assertTrue(response.isPresent());
        Assert.assertNotEquals(0, response.get().getPlanetId());
    }

    @Test
    public void daoCreatePlanetPositiveTestNoImage(){
        Optional<Planet> response = planetDao.createPlanet(positivePlanet);
        Assert.assertTrue(response.isPresent());
        Assert.assertNotEquals(0, response.get().getPlanetId());
    }

    @Test
    public void daoReadPlanetByOwnerPositiveTest(){
        List<Planet> response = planetDao.readPlanetsByOwner(positiveOwnerId);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void daoDeletePlanetByNamePositiveTest(){
        Boolean response = planetDao.deletePlanet(positivePlanetNameDeletion);
        Assert.assertEquals(true, response);
    }

}
