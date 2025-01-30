package com.revature.repository.negative;

import com.revature.repository.parent.PlanetDaoTest;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlanetDaoCheckUserExistsNegativeTest extends PlanetDaoTest {

    @BeforeClass
    public static void startTestOutput(){
        System.out.println("Running PlanetDaoCheckUserExistsNegativeTest...");
    }

    @AfterClass
    public static void endTestOutput(){
        System.out.println("PlanetDaoCheckUserExistsNegativeTest Finished");
    }

    @Test
    public void daoCheckUserExistsNegativeTest(){
        boolean response = planetDao.checkOwnerExists(7);
        Assert.assertFalse(response);
    }
}
