package com.revature.service.positive;

import com.revature.planetarium.entities.Planet;
import com.revature.service.parent.PlanetServiceTest;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import java.util.Optional;

public class PlanetServicePositiveTest extends PlanetServiceTest{

    //code for creation
    byte[] imageDataJPG;
    byte[] imageDataPNG;

    // code for retrieval
    private Planet positivePlanet;
    private Optional<Planet> mockOptional;
    private Planet mockReturnedPlanet;
    private Planet mockReturnedPlanet1;
    private Planet mockReturnedPlanet2;
    private List<Planet> mockReturnedPlanetList = new ArrayList<>();

    // code for deletion
    private Optional<Planet> positiveDeletePlanetOptional;
    private String positiveDeletePlanetName;

    public void imageHelper(String path) {
        byte[] imageData;
        try {
            imageData = Files.readAllBytes(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        positivePlanet.setImageData(Base64.getEncoder().encodeToString(imageData));
    }
    @Before
    public void positiveSetup() {
        positivePlanet = new Planet();
        positivePlanet.setPlanetId(0);
        positivePlanet.setPlanetName("Venus-55_");
        positivePlanet.setOwnerId(1);


        //setup for planet creation test
        mockOptional = Optional.of(positivePlanet);

        //setup for planet retrieval test
        mockReturnedPlanet = new Planet();
        mockReturnedPlanet.setPlanetId(3);
        mockReturnedPlanet.setPlanetName("Venus-55_");
        mockReturnedPlanet.setOwnerId(1);

        mockReturnedPlanet1 = new Planet();
        mockReturnedPlanet1.setPlanetId(1);
        mockReturnedPlanet1.setPlanetName("Earth");
        mockReturnedPlanet1.setOwnerId(1);
        mockReturnedPlanet2 = new Planet();
        mockReturnedPlanet2.setPlanetId(2);
        mockReturnedPlanet2.setPlanetName("Mars");
        mockReturnedPlanet2.setOwnerId(1);

        mockReturnedPlanetList.add(mockReturnedPlanet1);
        mockReturnedPlanetList.add(mockReturnedPlanet2);

        // setup for planet delete test
        positiveDeletePlanetName = "Earth";
        positiveDeletePlanetOptional = Optional.of(mockReturnedPlanet1);

        //setup for images
        String jpegPath = "src/test/resources/Celestial-Images/moon-1.jpg";
        String pngPath = "src/test/resources/Celestial-Images/planet-png.png";

        {
            try {
                imageDataJPG = Files.readAllBytes(Path.of(jpegPath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        {
            try {
                imageDataPNG = Files.readAllBytes(Path.of(pngPath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Test
    public void serviceCreatePlanetPositiveTest() {
        Mockito.when(planetDao.createPlanet(positivePlanet)).thenReturn(mockOptional);
        Mockito.when(planetDao.readPlanet("Venus-55_")).thenReturn(Optional.empty());
        boolean result = planetService.createPlanet(positivePlanet);
        Assert.assertTrue(result);
    }

    @Test
    public void serviceCreatePlanetImagePositiveTestPNG() {
        positivePlanet.setImageData(Base64.getEncoder().encodeToString(imageDataPNG));
        Mockito.when(planetDao.readPlanet("Venus-55_")).thenReturn(Optional.empty());
        Mockito.when(planetDao.createPlanet(positivePlanet)).thenReturn(mockOptional);
        boolean result = planetService.createPlanet(positivePlanet);
        Assert.assertTrue(result);
    }

    @Test
    public void serviceCreatePlanetImagePositiveTestJPG() {
        positivePlanet.setImageData(Base64.getEncoder().encodeToString(imageDataJPG));
        Mockito.when(planetDao.readPlanet("Venus-55_")).thenReturn(Optional.empty());
        Mockito.when(planetDao.createPlanet(positivePlanet)).thenReturn(mockOptional);
        boolean result = planetService.createPlanet(positivePlanet);
        Assert.assertTrue(result);
    }

    @Test
    public void serviceRetrievalPlanetPositiveTest() {
        Mockito.when(planetDao.readPlanetsByOwner(positivePlanet.getOwnerId())).thenReturn(mockReturnedPlanetList);
        List<Planet> result = planetService.selectByOwner(1);
        Assert.assertEquals(mockReturnedPlanetList, result);
    }

    @Test
    public void serviceDeletePlanetPositiveTest() {
        Mockito.when(planetDao.readPlanet("Earth")).thenReturn(positiveDeletePlanetOptional);
        Mockito.when(planetDao.deletePlanet(positiveDeletePlanetName)).thenReturn(true);
        boolean result = planetService.deletePlanet(positiveDeletePlanetName);
        Assert.assertTrue(result);

    }
}
