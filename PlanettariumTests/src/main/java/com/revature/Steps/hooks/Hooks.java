package com.revature.Steps.hooks;

import com.revature.utility.Setup;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void resetDataBase(){
        Setup.resetTestDatabase();
    }
}
