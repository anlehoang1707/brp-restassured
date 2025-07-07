package listeners;

import utils.LogUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class LogListener implements ITestListener {
    @Override
    public void onStart(ITestContext result) {
        LogUtils.info("\n===============================================================\n==================== " + result.getSuite().getName().toUpperCase() + " STARTING ===================\n===============================================================");

    }

    @Override
    public void onFinish(ITestContext result) {
        LogUtils.info("\n################################# FINISH ################################");
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("\n*****************************************************************");
        LogUtils.info("Running Test Case : " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("Test Case " + result.getName() + " is passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("Test Case " + result.getName() + " is failed.");
        LogUtils.error(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn("Test Case " + result.getName() + " is skipped.");
    }
}