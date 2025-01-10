package com.revature.repository.suite;

import com.revature.repository.negative.UserDaoCreateUserNegativeTest;
import com.revature.repository.negative.UserDaoFindUserByUsernameNegativeTest;
import com.revature.repository.positive.PlanetDaoPositiveTest;
import com.revature.repository.positive.UserDaoPositiveTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PlanetDaoPositiveTest.class
})
public class PlanetDaoTestSuite {

}