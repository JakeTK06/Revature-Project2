package com.revature.repository.negative;

import com.revature.planetarium.exceptions.MoonFail;
import com.revature.repository.parent.MoonDaoTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoonDaoDeletionNegativeTest extends MoonDaoTest {

    private String negativeMoonToDelete;
    private String exceptionMessage;

    @Before
    public void negativeSetup(){
       negativeMoonToDelete = "Moon";
       exceptionMessage = "Invalid moon name";
    }

    @Test
    public void deleteMoonNegativeTest(){
        MoonFail exception = Assert.assertThrows(MoonFail.class, () -> {moonDao.deleteMoon(negativeMoonToDelete); });
        Assert.assertEquals(exceptionMessage, exception.getMessage());
    }
}
