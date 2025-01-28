package com.revature.repository.negative;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
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

import  static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class MoonDaoUpdateNegativeTest extends MoonDaoTest {

    private Moon testMoon;

    @Parameter
    public int moonId;

    @Parameter(1)
    public String newMoonName;

    @Parameter(2)
    public int newPlanetId;

    @Parameter(3)
    public String imagePath;

    @Parameter(4)
    public String expectedExceptionMessage;

    @Parameters
    public static Collection<Object> inputs(){
        // Valid inputs
        int validMoonId = 1;
        String validNewMoonName = "quasi-moon 1 Zoozve_";
        int validNewPlanetId = 2;
        String jpegPath = "src/test/resources/Celestial-Images/moon-2.jpg";
        String pngPath = "src/test/resources/Celestial-Images/planet-png.png";

        // existing inputs
        String existingMoonName = "Luna";
        int existingPlanetId = 1;
        String existingImagePath = "src/test/resources/Celestial-Images/moon-1.jpg";

        // Invalid inputs
        int invalidMoonId = 7;
        String inmn1 = "";
        String inmn2 = "Moon name that-is way_way2 long";
        String inmn3 = "Exciting!! Moon";
        String inmn4 = "Titan";
        int invalidNewPlanetId = 10;
        String gifPath = "src/test/resources/Celestial-Images/planet-gif.gif";
        String fileTypeExceptionMessage = "Invalid file type";
        String planetIdExceptionMessage = "Invalid planet ID";
        String moonNameExceptionMessage = "Invalid moon name";
        String moonIdExceptionMessage = "Invalid moon ID";

        return Arrays.asList(new Object[][]{
                {validMoonId,validNewMoonName,validNewPlanetId,gifPath,fileTypeExceptionMessage},
                {validMoonId,existingMoonName,invalidNewPlanetId,existingImagePath,planetIdExceptionMessage},
                {validMoonId,existingMoonName,invalidNewPlanetId,null,planetIdExceptionMessage},
                {validMoonId,existingMoonName,invalidNewPlanetId,jpegPath,planetIdExceptionMessage},
                {validMoonId,existingMoonName,invalidNewPlanetId,pngPath,planetIdExceptionMessage},
                {validMoonId,existingMoonName,invalidNewPlanetId,gifPath,planetIdExceptionMessage},
                {validMoonId,inmn1,existingPlanetId,existingImagePath,moonNameExceptionMessage},
                {validMoonId,inmn1,invalidNewPlanetId,existingImagePath,moonNameExceptionMessage},
                {validMoonId,inmn1,invalidNewPlanetId,null,moonNameExceptionMessage},
                {validMoonId,inmn1,invalidNewPlanetId,jpegPath,moonNameExceptionMessage},
                {validMoonId,inmn1,invalidNewPlanetId,pngPath,moonNameExceptionMessage},
                {validMoonId,inmn1,invalidNewPlanetId,gifPath,moonNameExceptionMessage},
                {validMoonId,inmn2,existingPlanetId,existingImagePath,moonNameExceptionMessage},
                {validMoonId,inmn2,invalidNewPlanetId,existingImagePath,moonNameExceptionMessage},
                {validMoonId,inmn2,invalidNewPlanetId,null,moonNameExceptionMessage},
                {validMoonId,inmn2,invalidNewPlanetId,jpegPath,moonNameExceptionMessage},
                {validMoonId,inmn2,invalidNewPlanetId,pngPath,moonNameExceptionMessage},
                {validMoonId,inmn2,invalidNewPlanetId,gifPath,moonNameExceptionMessage},
                {validMoonId,inmn3,existingPlanetId,existingImagePath,moonNameExceptionMessage},
                {validMoonId,inmn3,invalidNewPlanetId,existingImagePath,moonNameExceptionMessage},
                {validMoonId,inmn3,invalidNewPlanetId,null,moonNameExceptionMessage},
                {validMoonId,inmn3,invalidNewPlanetId,jpegPath,moonNameExceptionMessage},
                {validMoonId,inmn3,invalidNewPlanetId,pngPath,moonNameExceptionMessage},
                {validMoonId,inmn3,invalidNewPlanetId,gifPath,moonNameExceptionMessage},
                {validMoonId,inmn4,existingPlanetId,existingImagePath,moonNameExceptionMessage},
                {validMoonId,inmn4,invalidNewPlanetId,existingImagePath,moonNameExceptionMessage},
                {validMoonId,inmn4,invalidNewPlanetId,null,moonNameExceptionMessage},
                {validMoonId,inmn4,invalidNewPlanetId,jpegPath,moonNameExceptionMessage},
                {validMoonId,inmn4,invalidNewPlanetId,pngPath,moonNameExceptionMessage},
                {validMoonId,inmn4,invalidNewPlanetId,gifPath,moonNameExceptionMessage},
                {invalidMoonId,existingMoonName,existingPlanetId,existingImagePath,moonIdExceptionMessage},
                {invalidMoonId,validNewMoonName,existingPlanetId,existingImagePath,moonIdExceptionMessage},
                {invalidMoonId,validNewMoonName,invalidNewPlanetId,existingImagePath,moonIdExceptionMessage},
                {invalidMoonId,validNewMoonName,invalidNewPlanetId,null,moonIdExceptionMessage},
                {invalidMoonId,validNewMoonName,invalidNewPlanetId,jpegPath,moonIdExceptionMessage},
                {invalidMoonId,validNewMoonName,invalidNewPlanetId,pngPath,moonIdExceptionMessage},
                {invalidMoonId,validNewMoonName,invalidNewPlanetId,gifPath,moonIdExceptionMessage},
                {invalidMoonId,existingMoonName,invalidNewPlanetId,existingImagePath,moonIdExceptionMessage},
                {invalidMoonId,existingMoonName,invalidNewPlanetId,null,moonIdExceptionMessage},
                {invalidMoonId,existingMoonName,invalidNewPlanetId,jpegPath,moonIdExceptionMessage},
                {invalidMoonId,existingMoonName,invalidNewPlanetId,pngPath,moonIdExceptionMessage},
                {invalidMoonId,existingMoonName,invalidNewPlanetId,gifPath,moonIdExceptionMessage}
        });
    }

    @Test
    public void daoUpdateMoonNegativeTest(){
        testMoon = new Moon(moonId,newMoonName,newPlanetId);

        // TODO: implement setting image here
        if(imagePath != null){
            imageHelper(imagePath);
        }


        MoonFail exception = Assert.assertThrows(MoonFail.class, () -> moonDao.updateMoon(testMoon));
        Assert.assertEquals(expectedExceptionMessage,exception.getMessage());
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
