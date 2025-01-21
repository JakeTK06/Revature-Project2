package com.revature.service.negative;

import com.revature.planetarium.entities.Moon;
import com.revature.service.parent.MoonServiceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

public class MoonServiceRetrievalNegativeTest extends MoonServiceTest {

    private int negativePlanetId;
    private List<Moon> mockReturnedMoons;

    @Before
    public void negativeSetup(){
        negativePlanetId = 7;
        mockReturnedMoons = new ArrayList<>();
    }

    @Test
    public void serviceSelectByPlanetNegativeTest() {
        Mockito.when(moonDao.readMoonsByPlanet(negativePlanetId)).thenReturn(mockReturnedMoons);
        System.out.println(mockReturnedMoons);
        List<Moon> result = moonService.selectByPlanet(negativePlanetId);
        Assert.assertEquals(result, mockReturnedMoons);
    }

}
