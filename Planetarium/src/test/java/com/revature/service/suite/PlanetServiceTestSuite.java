package com.revature.service.suite;
import com.revature.planetarium.service.planet.PlanetService;
import com.revature.service.negative.PlanetServiceCreateNegativeTest;
import com.revature.service.negative.PlanetServiceDeletionNegativeTest;
import com.revature.service.negative.PlanetServiceRetrievalNegativeTest;
import com.revature.service.positive.PlanetServicePositiveTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PlanetServicePositiveTest.class,
        PlanetServiceCreateNegativeTest.class,
        PlanetServiceRetrievalNegativeTest.class,
        PlanetServiceDeletionNegativeTest.class
})

public class PlanetServiceTestSuite {

}
