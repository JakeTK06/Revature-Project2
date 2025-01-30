package com.revature.repository.negative;

import com.revature.planetarium.entities.User;
import com.revature.repository.parent.UserDaoTest;
import org.junit.*;

import java.util.Optional;

public class UserDaoFindUserByUsernameNegativeTest extends UserDaoTest {

    private String negativeUsername;

    @BeforeClass
    public static void startTestOutput(){
        System.out.println("Running UserDaoFindUserByUsernameNegativeTest...");
    }

    @AfterClass
    public static void endTestOutput(){
        System.out.println("UserDaoFindUserByUsernameNegativeTest Finished");
    }

    @Before
    public void negativeSetup(){
        negativeUsername = "Joker";
    }

    @Test
    public void daoFindUserByUsernameNegativeTest(){
        Optional<User> result = userDao.findUserByUsername(negativeUsername);
        Assert.assertFalse(result.isPresent());
    }

}
