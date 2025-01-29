package com.revature.repository.negative;

import com.revature.repository.parent.PlanetDaoTest;
import org.junit.Assert;
import org.junit.Test;

public class PlanetDaoCheckUserExistsNegativeTest extends PlanetDaoTest {
    @Test
    public void daoCheckUserExistsNegativeTest(){
        boolean response = planetDao.checkOwnerExists(7);
        Assert.assertFalse(response);
    }
}
