package com.automation.mobile.app;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class APPTest {

	@BeforeSuite
	public void foo() {

		try {
			ServerUtil serverUtil = new ServerUtil();
			serverUtil.startServer("4387", "5555", "ANDROID");
		} catch (InterruptedException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String seleniumPort() {
		return "5555";
	}

	public static String appiumPort() {
		return "4387";
	}

	@Test
	public void testApplicaiton() {
		System.setProperty("ANDROID_HOME", "/Users/rahulsahu/Library/Android/sdk");
		AndroidDriver<MobileElement> driver=null,driver2=null;
		try {
			 driver = new AndroidDriver<MobileElement>(
					new URL("http://localhost:" + appiumPort() + "/wd/hub"),
					DriverType.ANDROID_WAP_CHROME.getDesiredCapabilities(Configuration.deviceID));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			 driver2 = new AndroidDriver<MobileElement>(
					new URL("http://localhost:" + appiumPort() + "/wd/hub"),
					DriverType.ANDROID_CHROME.getDesiredCapabilities(Configuration.deviceID));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		

	}

}
