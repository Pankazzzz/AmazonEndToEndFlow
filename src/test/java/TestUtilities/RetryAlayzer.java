package TestUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import AbstractTestClasses.BaseTest;

public class RetryAlayzer extends BaseTest implements IRetryAnalyzer {

	int  count=1;
	int maxTry=3;
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		while(count<maxTry)
		{
			count++;
			return true;
		}
		return false;
	}

}
