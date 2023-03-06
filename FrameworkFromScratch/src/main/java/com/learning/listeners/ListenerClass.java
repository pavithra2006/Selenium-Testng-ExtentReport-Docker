package com.learning.listeners;

import com.learning.reports.ExtentLogger;
import com.learning.reports.ExtentManager;
import com.learning.reports.ExtentSpark;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class ListenerClass implements ITestListener, ISuiteListener {
    public void onStart(ISuite suite) {  // ISuiteListener
        ExtentSpark.initReports();
    }

    public void onFinish(ISuite suite) {
        try {
            ExtentSpark.flushReports();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onTestStart(ITestResult result) {
        ExtentSpark.createTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(result.getMethod().getMethodName() + " Testcase passed");
    }

    public void onTestFailure(ITestResult result) {
        ExtentLogger.fail(result.getMethod().getMethodName() + " Testcase failed");
        //add screenshot
    }

    public void onTestSkipped(ITestResult result) {
        ExtentLogger.skip(result.getMethod().getMethodName() + " Testcase skipped");
    }

}
