package com.revature.repository.negative;

import com.revature.planetarium.exceptions.MoonFail;
import com.revature.repository.parent.MoonDaoTest;
import org.junit.*;

public class MoonDaoDeletionNegativeTest extends MoonDaoTest {

    private String negativeMoonToDelete;
    private String exceptionMessage;

    @BeforeClass
    public static void startTestOutput(){
        System.out.println("Running MoonDaoDeletionNegativeTest...");
    }

    @AfterClass
    public static void endTestOutput(){
        System.out.println("MoonDaoDeletionNegativeTest Finished");
    }

    @Before
    public void negativeSetup(){
       negativeMoonToDelete = "Moon";
       exceptionMessage = "Invalid moon name";
    }

    @Test
    public void daoDeleteMoonNegativeTest(){
        MoonFail exception = Assert.assertThrows(MoonFail.class, () -> {moonDao.deleteMoon(negativeMoonToDelete); });
        Assert.assertEquals(exceptionMessage, exception.getMessage());
    }
}
