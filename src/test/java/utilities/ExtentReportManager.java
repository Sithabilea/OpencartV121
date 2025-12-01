package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
//import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import org.apache.commons.mail.DefaultAuthenticator;
//import org.apache.commons.mail.ImageHtmlEmail;
//import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.sun.tools.javac.util.List;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext) {  //testContext captures info about the method
		
		/*//approach 1 - to add a date and time stamp to the report
		SimpleDateFormat df=new SimpleDateFormat ("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdatetimestamp=df.format(dt);
		*/
		
		
		//approach2 
		String timeStamp =new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());  //time stamp
		
		repName = "Test-Report"+timeStamp +".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
		
		sparkReporter.config().setDocumentTitle("opencart Automation Report");
		sparkReporter.config().setReportName("opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent= new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name")); //returns current user of the system
		extent.setSystemInfo("Environment", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");   //extracts the 'os' info that was entered on the xml file
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");  //extracts the browser info from the xml
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();  //extracts the included groups info from the xml
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());  //if the group info is present (ie group tags specified, then include them in the report
		}		
	}
	
	
	public void onTestSuccess(ITestResult result) {
		test= extent.createTest(result.getTestClass().getName());  //get class name ie. testcase name and create a new entry in the report
		test.assignCategory(result.getMethod().getGroups()); //to display groups in report
		test.log(Status.PASS, result.getName()+" successfully executed");
		
	}
	
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+" failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());  //calling captureScreen method, passes the name of test method that failed as the name of the screenshot with timestamp , and returns the screenshot path and stores it in a variable
			test.addScreenCaptureFromPath(imgPath);  //attaches screenshot to the report
		} catch (IOException e1) {  //incase screenshot is not available it might throw an exception
			e1.printStackTrace();
		}
	}
	
	
	
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	
	
	
	public void onFinish(ITestContext testContext) {
		
		extent.flush();  //consolidates all data from report and generate report
		
		String pathOfExtentReport= System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport =new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());  //opens the generated report on the browser automatically immediately after generation
		} catch(IOException e) {  //incase report is not available, handling the exception
			e.printStackTrace();
		}
		
		
		/*
		try {
		URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);  //converting report format to url format (its stored in the reports folder in html format)
		
		//create the email message
		ImageHtmlEmail email =new ImageHtmlEmail();
		email.setDataSourceResolver (new DataSourceUrlResolver(url));
		email.setHostName("stmp.googlemail.com");  //gmail
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("sender@email.com", "senderpassword"));
		email.setSSLOnConnect(true);
		email.setFrom("sender@email.com");  //sender
		email.setSubject("Test Results");
		email.setMsg("Please find attached report");
		email.addTo("receiver@email.com");  //receiver
		email.attach(url, "extent report", "please check report...");
		email.send();  //send the email
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		*/
		
		
	}
	
}
