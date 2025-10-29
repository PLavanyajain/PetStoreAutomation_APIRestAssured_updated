package api.Utilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // -------------------------------
    // 1️⃣ Create and return singleton ExtentReports instance
    // -------------------------------
    private static synchronized ExtentReports getExtent() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setReportName("Petstore API Automation Report");
            reporter.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Lavanya");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }

    // -------------------------------
    // 2️⃣ Safely get test instance
    // -------------------------------
    public static ExtentTest getTest() {
        ExtentTest extentTest = test.get();
        if (extentTest == null) {
            // Optional: fallback for unexpected null cases
            extentTest = getExtent().createTest("Unnamed Test");
            test.set(extentTest);
        }
        return extentTest;
    }

    // -------------------------------
    // 3️⃣ Listener methods
    // -------------------------------
    @Override
    public void onTestStart(ITestResult result) {
        // Create new test node for each test
        ExtentTest extentTest = getExtent().createTest(
            result.getMethod().getMethodName(),
            result.getMethod().getDescription()
        );
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        getTest().log(Status.PASS, "✅ Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        getTest().log(Status.FAIL, "❌ Test Failed: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        getTest().log(Status.SKIP, "⚠️ Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }
}
