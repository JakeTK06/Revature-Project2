package com.revature.repository.suite;

import com.revature.repository.negative.*;
import com.revature.repository.positive.PlanetDaoPositiveTest;
import com.revature.repository.positive.UserDaoPositiveTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PlanetDaoPositiveTest.class,
        PlanetDaoCreatePlanetNegativeTest.class,
        PlanetDaoDeletePlanetByNameNegativeTest.class,
        PlanetDaoReadPlanetsByOwnerNegativeTest.class
})
public class PlanetDaoTestSuite {

}