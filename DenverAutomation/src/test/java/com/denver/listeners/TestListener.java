package com.denver.listeners;

import com.denver.base.BaseClass;
import com.denver.utilities.ScreenshotUtil;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("PASS: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("FAIL: " + result.getName());

        Object instance = result.getInstance();

        if (instance instanceof BaseClass) {
            BaseClass test = (BaseClass) instance;
            ScreenshotUtil.capture(test.driver, result.getName());
        }

        Throwable error = result.getThrowable();

        if (error != null) {
            System.err.println(error.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("SKIPPED: " + result.getName());
    }
}
