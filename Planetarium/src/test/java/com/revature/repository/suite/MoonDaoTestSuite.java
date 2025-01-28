package com.revature.repository.suite;

import com.revature.repository.negative.MoonDaoCreateMoonNegativeTest;
import com.revature.repository.negative.MoonDaoDeletionNegativeTest;
import com.revature.repository.negative.MoonDaoRetrievalNegativeTest;
import com.revature.repository.negative.MoonDaoUpdateNegativeTest;
import com.revature.repository.positive.MoonDaoPositiveTest;
import com.revature.repository.positive.MoonDaoUpdatePositiveTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MoonDaoPositiveTest.class,
        MoonDaoDeletionNegativeTest.class,
        MoonDaoRetrievalNegativeTest.class,
        MoonDaoCreateMoonNegativeTest.class,
        MoonDaoUpdatePositiveTest.class,
        MoonDaoUpdateNegativeTest.class
})
public class MoonDaoTestSuite {

}
