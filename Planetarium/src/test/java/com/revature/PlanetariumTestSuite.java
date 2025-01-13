package com.revature;

import com.revature.repository.suite.MoonDaoTestSuite;
import com.revature.repository.suite.PlanetDaoTestSuite;
import com.revature.repository.suite.UserDaoTestSuite;
import com.revature.service.suite.MoonServiceTestSuite;
import com.revature.service.suite.PlanetServiceTestSuite;
import com.revature.service.suite.UserServiceTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserDaoTestSuite.class,
        UserServiceTestSuite.class,
        PlanetDaoTestSuite.class,
        PlanetServiceTestSuite.class,
        MoonDaoTestSuite.class,
        MoonServiceTestSuite.class
})
public class PlanetariumTestSuite {
}
