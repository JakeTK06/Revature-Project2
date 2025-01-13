package com.revature.service.suite;

import com.revature.service.negative.MoonServiceCreateMoonNegativeTest;
import com.revature.service.negative.MoonServiceDeleteMoonNegativeTest;
import com.revature.service.negative.MoonServiceRetrievalNegativeTest;
import com.revature.service.positive.MoonServicePositiveTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MoonServicePositiveTest.class,
        MoonServiceCreateMoonNegativeTest.class,
        MoonServiceRetrievalNegativeTest.class,
        MoonServiceDeleteMoonNegativeTest.class
})

public class MoonServiceTestSuite {


}
