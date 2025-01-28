package com.revature.repository.positive;

import com.revature.planetarium.entities.Moon;
import com.revature.repository.parent.MoonDaoTest;
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
import java.util.Optional;

import  static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class MoonDaoUpdatePositiveTest extends MoonDaoTest {

    private Moon testMoon;

    @Parameter
    public int positiveMoonId;

    @Parameter(1)
    public String positiveNewMoonName;

    @Parameter(2)
    public int positiveNewPlanetId;

    @Parameter(3)
    public String positiveImagePath;

    @Parameters
    public static Collection<Object> inputs(){
        int validMoonId = 1;
        String validNewMoonName = "quasi-moon 1 Zoozve_";
        int validNewPlanetId = 2;
        String jpegPath = "src/test/resources/Celestial-Images/moon-2.jpg";
        String pngPath = "src/test/resources/Celestial-Images/planet-png.png";

        // String existing moon data
        String existingMoonName = "Luna";
        int existingPlanetId = 1;
        String existingImagePath = "src/test/resources/Celestial-Images/moon-1.jpg";

        return Arrays.asList(new Object[][]{
                {validMoonId,existingMoonName,existingPlanetId,existingImagePath},
                {validMoonId,validNewMoonName,existingPlanetId,existingImagePath},
                {validMoonId,validNewMoonName,validNewPlanetId,existingImagePath},
                {validMoonId,validNewMoonName,validNewPlanetId,null},
                {validMoonId,validNewMoonName,validNewPlanetId,jpegPath},
                {validMoonId,validNewMoonName,validNewPlanetId,pngPath}
        });
    }

    @Test
    public void daoUpdateMoonPositiveTest(){

        testMoon = new Moon(positiveMoonId,positiveNewMoonName,positiveNewPlanetId);

        if (positiveImagePath != null){
            imageHelper(positiveImagePath);
          // TODO: Implement updating image data
        }

        Optional<Moon> updatedMoon = moonDao.updateMoon(testMoon);
        Assert.assertTrue(updatedMoon.isPresent());
        Assert.assertEquals(positiveNewMoonName, updatedMoon.get().getMoonName());
        Assert.assertEquals(positiveNewPlanetId, updatedMoon.get().getOwnerId());
        Assert.assertEquals(testMoon.getImageData(), updatedMoon.get().getImageData());


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

        testMoon.setImageData(Base64.getEncoder().encodeToString(imageData));
    }

}
