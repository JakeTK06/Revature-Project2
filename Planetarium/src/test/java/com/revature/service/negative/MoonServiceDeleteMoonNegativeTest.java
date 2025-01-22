package com.revature.service.negative;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.service.parent.MoonServiceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

public class MoonServiceDeleteMoonNegativeTest extends MoonServiceTest {

    private String negativeMoonName;
    private String expectedExceptionMessage;


    @Before
    public void negativeSetup(){
       negativeMoonName = "Moon";
       expectedExceptionMessage = "Invalid moon name";
    }

    @Test
    public void serviceDeleteMoonNegativeTest(){
        Mockito.when(moonDao.readMoon(negativeMoonName)).thenReturn(Optional.empty());
        Mockito.when(moonDao.deleteMoon(negativeMoonName)).thenThrow(new AssertionError("MoonFail exception expected, but it was not thrown when it should have been"));
        MoonFail moonFail = Assert.assertThrows(MoonFail.class, ()->{moonService.deleteMoon(negativeMoonName);});
        Assert.assertEquals(expectedExceptionMessage, moonFail.getMessage());
    }

}