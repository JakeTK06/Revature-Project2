package com.revature.service.negative;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.service.parent.PlanetServiceTest;
import org.junit.*;
import org.junit.runner.RunWith;
import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Optional;

@RunWith(Parameterized.class)
public class PlanetServiceCreateNegativeTest extends PlanetServiceTest {
    @Parameter
    public String planetName;

    @Parameter(1)
    public int ownerId;

    @Parameter(2)
    public String imagePath;

    @Parameter(3)
    public String expectedMessage;

    private Planet negativePlanet;

    private Planet positivePlanet;

    @Parameters
    public static Collection<Object> inputs() {
        String jpegPath = "src/test/resources/Celestial-Images/moon-1.jpg";
        String pngPath = "src/test/resources/Celestial-Images/planet-png.png";
        String gifPath = "src/test/resources/Celestial-Images/planet-gif.gif";
        return Arrays.asList(new Object[][] {
            {"",1,null, "Invalid planet name"},
            {"Planet Name That-is 1  long_name ",1, null, "Invalid planet name"},
            {"Exciting!! planet",1,null,"Invalid planet name"},
            {"Earth",1,null,"Invalid planet name"},
            {"Venus -55_", 1, gifPath, "Invalid file type"},
            {"Earth",1,gifPath,"Invalid planet name"},
            {"Earth",1,pngPath,"Invalid planet name"},
            {"Earth",1,jpegPath,"Invalid planet name"}
        });
    }

    public void imageHelper(String path) {
        byte[] imageData;
        try {
            imageData = Files.readAllBytes(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        negativePlanet.setImageData(Base64.getEncoder().encodeToString(imageData));
    }

    @BeforeClass
    public static void startTestOutput(){
        System.out.println("Running PlanetServiceCreateNegativeTest...");
    }

    @AfterClass
    public static void endTestOutput(){
        System.out.println("PlanetServiceCreateNegativeTest Finished");
    }

    @Before
    public void negativeSetup() {
        negativePlanet = new Planet();
        negativePlanet.setPlanetName(planetName);
        negativePlanet.setOwnerId(ownerId);
        if (imagePath != null && !imagePath.isEmpty()){imageHelper(imagePath);}

        positivePlanet = new Planet();
        positivePlanet.setPlanetName("Earth");
        positivePlanet.setOwnerId(1);
    }

    @Test
    public void serviceCreatePlanetNegativeTest() {
        Mockito.when(planetDao.readPlanet("Earth")).thenReturn(Optional.of(positivePlanet));
        Mockito.when(planetDao.createPlanet(negativePlanet)).thenThrow(new AssertionError("PlanetFail exception, but it was not thrown when it should have been."));
        PlanetFail planetFail = Assert.assertThrows(PlanetFail.class, () -> {planetService.createPlanet(negativePlanet);});
        Assert.assertEquals(expectedMessage, planetFail.getMessage());
    }
}
