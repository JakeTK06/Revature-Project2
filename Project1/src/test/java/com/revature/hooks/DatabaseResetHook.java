package com.revature.hooks;

import com.revature.utility.Setup;
import io.cucumber.java.Before;

public class DatabaseResetHook {
    @Before
    public void resetDatabase(){
        Setup.resetTestDatabase();
    }
}
