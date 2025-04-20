package TestUtilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportClass {
	
	public static ExtentReports extentReport()
	{
		String pathString = System.getProperty("user.dir")+"/reports/index.html";
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(pathString);
		extentSparkReporter.config().setDocumentTitle("Amazon Validation");
		extentSparkReporter.config().setReportName("AddProduct");
		
		ExtentReports extentReport = new ExtentReports();
		extentReport.attachReporter(extentSparkReporter);
		extentReport.setSystemInfo("Chrome", "SmokeTest");
		return extentReport;
		
	}

}
