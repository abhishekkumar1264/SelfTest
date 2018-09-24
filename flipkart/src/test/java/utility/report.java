package utility;

import org.testng.TestListenerAdapter;
import org.testng.ITestResult;

public class report extends TestListenerAdapter{

	public void onTestSuccess(ITestResult tr) {
		
		System.out.println(tr.getName() +"-->"+"Sucess");
		
	}
	
	public void onTestFailure(ITestResult tr) {
		
		System.out.println(tr.getName() +"-->"+"failure");
		
	}	
	
}
