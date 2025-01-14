package com.revature.repository.negative;

import com.revature.planetarium.entities.Moon;
import com.revature.repository.parent.MoonDaoTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MoonDaoRetrievalNegativeTest extends MoonDaoTest {

    private int negativePlanetIdForRetrieval;

    @Before
    public void negativeSetup(){
        negativePlanetIdForRetrieval = 7;
    }

    @Test
    public void daoReadMoonsByPlanetNegativeTest(){
        List<Moon> result = moonDao.readMoonsByPlanet(negativePlanetIdForRetrieval);
        Assert.assertTrue(result.isEmpty());
    }
}
