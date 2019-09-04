package com.automation.mobile.app;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.automation.mobile.AdbUtils;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public enum DriverType implements CapabilitiesSetup {

	ANDROID_WAP_CHROME {

		public DesiredCapabilities getDesiredCapabilities(String deviceId) {
			AdbUtils adbUtils = new AdbUtils(deviceId);

			try {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
						adbUtils.getProp("ro.build.version.release"));
				capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
				capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
				capabilities.setCapability(MobileCapabilityType.UDID, deviceId);
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, adbUtils.getProp("ro.product.model"));
				capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
				capabilities.setCapability(AndroidMobileCapabilityType.SUPPORTS_ALERTS, true);
//				capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
				capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 600);
//				capabilities.setCapability(AndroidMobileCapabilityType.APPIUM, appiumPort);
//				capabilities.setCapability("–-session-override",true);
				// App specific can perametrise via app package and app activity
				capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
						"com.sec.android.app.simsettingmgr");
				capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".NetworkManagement");
				return capabilities;
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}

	},

	ANDROID_CHROME {

		public DesiredCapabilities getDesiredCapabilities(String deviceId) {
			AdbUtils adbUtils = new AdbUtils(deviceId);

			try {
				DesiredCapabilities capabilities = new DesiredCapabilities();

				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
//				capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
						adbUtils.getProp("ro.build.version.release"));
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, adbUtils.getProp("ro.product.model"));
				capabilities.setCapability("udid", deviceId);
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--disable-notifications");
				chromeOptions.addArguments("--host-resolver-rules=MAP www.opticks.io 127.0.0.1");
				chromeOptions.addArguments("--host-resolver-rules=MAP www.track.greentropolo.com 127.0.0.1");
				capabilities.setCapability("chromeOptions", chromeOptions);
				return capabilities;
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}

	},;
}