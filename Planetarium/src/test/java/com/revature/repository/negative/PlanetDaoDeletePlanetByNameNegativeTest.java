package com.revature.repository.negative;

import com.revature.planetarium.entities.Planet;
import com.revature.repository.parent.PlanetDaoTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PlanetDaoDeletePlanetByNameNegativeTest extends PlanetDaoTest {

    private String negativePlanetName;

    @Before
    public void negativeSetup(){
        negativePlanetName = "Earth 2";
    }

    @Test
    public void readPlanetByOwnerNegativeTest(){
        Boolean response = planetDao.deletePlanet(negativePlanetName);
        Assert.assertEquals(false, response);
    }

}