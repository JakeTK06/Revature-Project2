package com.revature.repository.parent;

import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import com.revature.planetarium.repository.user.UserDao;
import com.revature.planetarium.repository.user.UserDaoImp;
import com.revature.utility.Setup;
import org.junit.Before;

public class PlanetDaoTest {

    protected PlanetDao planetDao;

    @Before
    public void setup(){
        planetDao = new PlanetDaoImp();
        Setup.resetTestDatabase();
    }

}
