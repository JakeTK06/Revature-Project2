package com.revature.repository.parent;

import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import com.revature.utility.Setup;
import org.junit.Before;

public class MoonDaoTest {

    protected MoonDao moonDao;

    @Before
    public void setup(){
        moonDao = new MoonDaoImp();
        Setup.resetTestDatabase();
    }

}
