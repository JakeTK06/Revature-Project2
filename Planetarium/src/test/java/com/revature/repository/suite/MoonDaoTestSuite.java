package com.revature.repository.suite;

import com.revature.repository.negative.MoonDaoCreateMoonNegativeTest;
import com.revature.repository.negative.MoonDaoDeletionNegativeTest;
import com.revature.repository.negative.MoonDaoRetrievalNegativeTest;
import com.revature.repository.positive.MoonDaoPositiveTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MoonDaoPositiveTest.class,
        MoonDaoDeletionNegativeTest.class,
        MoonDaoRetrievalNegativeTest.class,
        MoonDaoCreateMoonNegativeTest.class
})
public class MoonDaoTestSuite {

}
