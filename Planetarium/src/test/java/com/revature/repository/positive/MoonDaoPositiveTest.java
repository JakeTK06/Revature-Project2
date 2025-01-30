package com.revature.repository.positive;

import com.revature.planetarium.entities.Moon;
import com.revature.repository.parent.MoonDaoTest;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class MoonDaoPositiveTest extends MoonDaoTest {

    private Moon positiveMoon;
    private byte[] imageData;
    private int positivePlanetIdForRetrieval;
    private String positiveMoonToDelete;

    private final File CelestialPNG = new File("src/test/resources/Celestial-Images/planet-png.png");

    @BeforeClass
    public static void startTestOutput(){
        System.out.println("Running MoonDaoPositiveTest...");
    }

    @AfterClass
    public static void endTestOutput(){
        System.out.println("MoonDaoPositiveTest Finished");
    }

//    positivePlanet.setImageData(Base64.getEncoder().encodeToString(imageData));
    @Before
    public void positiveSetup(){
        positiveMoon = new Moon(0, "quasi-moon 1 Zoozve_", 1);
        positivePlanetIdForRetrieval = 1;
        positiveMoonToDelete = "Luna";
    }

    @Test
    public void daoCreateMoonPositiveTestNoImage(){
//        positiveMoon.setImageData(Base64.getEncoder().encodeToString());
        Optional<Moon> result = moonDao.createMoon(positiveMoon);
        Assert.assertTrue(result.isPresent());
        Assert.assertNotEquals(0, result.get().getMoonId());
    }

    @Test
    public void daoCreateMoonPositiveTestJPG(){
        try{
            imageData = Files.readAllBytes(Path.of("src/test/resources/Celestial-Images/moon-1.jpg"));
        } catch(IOException e){
            throw new RuntimeException(e);
        }

        positiveMoon.setImageData(Base64.getEncoder().encodeToString(imageData));
        Optional<Moon> result = moonDao.createMoon(positiveMoon);
        Assert.assertTrue(result.isPresent());
        Assert.assertNotEquals(0, result.get().getMoonId());
    }

    @Test
    public void daoCreateMoonPositiveTestPNG(){
        try{
            imageData = Files.readAllBytes(Path.of("src/test/resources/Celestial-Images/planet-png.png"));
        } catch(IOException e){
            throw new RuntimeException(e);
        }
        Optional<Moon> result = moonDao.createMoon(positiveMoon);
        Assert.assertTrue(result.isPresent());
        Assert.assertNotEquals(0, result.get().getMoonId());
    }

    @Test
    public void daoReadMoonsByPlanetPositiveTest(){
        List<Moon> result = moonDao.readMoonsByPlanet(positivePlanetIdForRetrieval);
        Assert.assertFalse(result.isEmpty());
    }


    @Test
    public void daoDeleteMoonPositiveTest(){
        Assert.assertTrue(moonDao.deleteMoon(positiveMoonToDelete));
    }

}
