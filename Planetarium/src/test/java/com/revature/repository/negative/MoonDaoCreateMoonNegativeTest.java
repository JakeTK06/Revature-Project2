package com.revature.repository.negative;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.repository.parent.MoonDaoTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;

import  static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class MoonDaoCreateMoonNegativeTest extends MoonDaoTest {

    private Moon testMoon;

    @Parameter
    public String negativeMoonName;

    @Parameter(1)
    public int negativePlanetId;

    @Parameter(2)
    public String negativeImagePath;

    @Parameter(3)
    public String exceptionMessage;

    @Parameters
    public static Collection<Object> inputs() {
        String jpegPath = "src/test/resources/Celestial-Images/moon-1.jpg";
        String pngPath = "src/test/resources/Celestial-Images/planet-png.png";
        String gifPath = "src/test/resources/Celestial-Images/planet-gif.gif";

        String validMoonName = "Venus -55_";
        int validPlanetId = 1;

        String invalidMoonName1 = "";
        String invalidMoonName2 = "Moon name that-is way_way2 long";
        String invalidMoonName3 = "Exciting!! Moon";
        String invalidMoonName4 = "Luna";

        int invalidPlanetId = 7;

        String moonNameMessage = "Invalid moon name";
        String planetIdMessage = "Invalid planet ID";
        String fileTypeMessage = "Invalid file type";


        return Arrays.asList(new Object[][]{
                {invalidMoonName1,validPlanetId,null,moonNameMessage},
                {invalidMoonName2,validPlanetId,null,moonNameMessage},
                {invalidMoonName3,validPlanetId,null,moonNameMessage},
                {invalidMoonName4,validPlanetId,null,moonNameMessage},
                {validMoonName,invalidPlanetId,null,planetIdMessage},
                {invalidMoonName4,invalidPlanetId,null,moonNameMessage},
                {validMoonName,validPlanetId,gifPath,fileTypeMessage},
                {invalidMoonName4,validPlanetId,gifPath,moonNameMessage},
                {validMoonName,invalidPlanetId,gifPath,planetIdMessage},
                {invalidMoonName4,invalidPlanetId,gifPath,moonNameMessage},
                {invalidMoonName4,validPlanetId,jpegPath,moonNameMessage},
                {validMoonName,invalidPlanetId,jpegPath,planetIdMessage},
                {invalidMoonName4,invalidPlanetId,jpegPath,moonNameMessage},
                {invalidMoonName4,validPlanetId,pngPath,moonNameMessage},
                {validMoonName,invalidPlanetId,pngPath,planetIdMessage},
                {invalidMoonName4,invalidPlanetId,pngPath,moonNameMessage}
        });

    }

    @Test
    public void createMoonNegativeTest(){
        testMoon = new Moon(0,negativeMoonName,negativePlanetId);

        // Set image if there  is one
        if (negativeImagePath != null && !negativeImagePath.isEmpty()){
            imageHelper(negativeImagePath);
        }

        MoonFail exception = Assert.assertThrows(MoonFail.class, () -> {moonDao.createMoon(testMoon); });
        Assert.assertEquals(exceptionMessage,exception.getMessage());
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
