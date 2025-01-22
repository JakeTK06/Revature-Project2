package com.revature.service.positive;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.entities.Planet;
import com.revature.service.parent.MoonServiceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class MoonServicePositiveTest extends MoonServiceTest {

    // inputs/outputs for CreateMoon

    // inputs/outputs for selectByPlanet
    private List<Moon> mockReturnedMoons;
    private Optional<Moon> mockOptionalNoImage;
    private Optional<Moon> mockOptionalWithImage;
    private int positivePlanetId;
    private Moon positiveMoonNoImage;
    private Moon positiveMoonWithImage;
    private Planet planet;
    byte[] imageDataJPG;
    byte[] imageDataPNG;


    // inputs/outputs for deleteMoon
    private Optional<Moon> positiveDeleteMoonOptional;
    private String positiveDeleteMoonName;
    private Moon mockReturnedMoon;

    @Before
    public void positiveSetup() {

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

        positiveMoonNoImage = new Moon(0, "Moon V2", 1);
        mockOptionalNoImage = Optional.of(positiveMoonNoImage);

        positiveMoonWithImage = new Moon(0, "Moon V3", 1);
        mockOptionalWithImage = Optional.of(positiveMoonWithImage);

        planet = new Planet();
        planet.setPlanetId(1);
        planet.setPlanetName("Earth");
        planet.setOwnerId(1);

        mockReturnedMoons = new ArrayList<Moon>();
        positivePlanetId = 1;
        mockReturnedMoons.add(new Moon(1, "Luna", 1));
        mockReturnedMoons.add(new Moon(2, "Titan", 2));

        mockReturnedMoon = new Moon(1, "Luna", 1);
        positiveDeleteMoonName = "Luna";
        positiveDeleteMoonOptional = Optional.of(mockReturnedMoon);
    }


    @Test
    public void serviceCreateMoonWithoutImagePositiveTest() {
        Mockito.when(moonDao.readPlanet(1)).thenReturn(Optional.of(planet));
        Mockito.when(moonDao.readMoon("Moon V2")).thenReturn(Optional.empty());
        Mockito.when(moonDao.createMoon(positiveMoonNoImage)).thenReturn(mockOptionalNoImage);
        boolean result = moonService.createMoon(positiveMoonNoImage);
        Assert.assertTrue(result);
    }

    @Test
    public void serviceCreateMoonImagePositiveTestPNG() {
        positiveMoonWithImage.setImageData(Base64.getEncoder().encodeToString(imageDataPNG));
        Mockito.when(moonDao.readPlanet(1)).thenReturn(Optional.of(planet));
        Mockito.when(moonDao.readMoon("Moon V3")).thenReturn(Optional.empty());
        Mockito.when(moonDao.createMoon(positiveMoonWithImage)).thenReturn(mockOptionalWithImage);
        boolean result = moonService.createMoon(positiveMoonWithImage);
        Assert.assertTrue(result);
    }


    @Test
    public void serviceCreateMoonImagePositiveTestJPG() {
        positiveMoonWithImage.setImageData(Base64.getEncoder().encodeToString(imageDataJPG));
        Mockito.when(moonDao.readPlanet(1)).thenReturn(Optional.of(planet));
        Mockito.when(moonDao.readMoon("Moon V3")).thenReturn(Optional.empty());
        Mockito.when(moonDao.createMoon(positiveMoonWithImage)).thenReturn(mockOptionalWithImage);
        boolean result = moonService.createMoon(positiveMoonWithImage);
        Assert.assertTrue(result);
    }

    @Test
    public void serviceSelectByPlanetPositiveTest() {
        Mockito.when(moonDao.readMoonsByPlanet(positivePlanetId)).thenReturn(mockReturnedMoons);
        List<Moon> result = moonService.selectByPlanet(positivePlanetId);
        Assert.assertEquals(result, mockReturnedMoons);
    }


    @Test
    public void serviceDeleteMoonWithoutImagePositiveTest() {
        Mockito.when(moonDao.readMoon("Luna")).thenReturn(positiveDeleteMoonOptional);
        Mockito.when(moonDao.deleteMoon(positiveDeleteMoonName)).thenReturn(true);
        boolean result = moonService.deleteMoon(positiveDeleteMoonName);
        Assert.assertTrue(result);
    }

}
