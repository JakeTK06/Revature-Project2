package com.revature.repository.positive;

import com.revature.planetarium.entities.User;
import com.revature.repository.parent.UserDaoTest;
import org.junit.*;

import java.util.Optional;

public class UserDaoPositiveTest extends UserDaoTest {

    private User positiveUser;
    private String positiveUsername;

    @BeforeClass
    public static void startTestOutput(){
        System.out.println("Running UserDaoPositiveTest...");
    }

    @AfterClass
    public static void endTestOutput(){
        System.out.println("UserDaoPositiveTest Finished");
    }

    @Before
    public void positiveSetup(){
        positiveUser = new User(0, "Super_man-2001", "Krypton-was_2000");
        positiveUsername = "Batman";
    }

    @Test
    public void daoCreateUserPositiveTest(){
        Optional<User> response = userDao.createUser(positiveUser);
        Assert.assertTrue(response.isPresent());
        Assert.assertNotEquals(0, response.get().getId());
    }

    @Test
    public void daoFindUserByUsernamePositiveTest(){
        Optional<User> result = userDao.findUserByUsername(positiveUsername);
        Assert.assertTrue(result.isPresent());
    }

}
