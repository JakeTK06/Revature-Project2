package com.revature.service.parent;

import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import com.revature.planetarium.service.planet.PlanetService;
import com.revature.planetarium.service.planet.PlanetServiceImp;
import com.revature.utility.Setup;
import org.junit.Before;
import org.mockito.Mockito;

public class PlanetServiceTest {
    protected PlanetDao planetDao;
    protected PlanetService planetService;

    @Before
    public void setup() {
        planetDao = Mockito.mock(PlanetDaoImp.class);
        planetService = new PlanetServiceImp(planetDao);
        Setup.resetTestDatabase();
    }
}
