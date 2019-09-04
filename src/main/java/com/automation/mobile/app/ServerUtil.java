package com.automation.mobile.app;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.automation.mobile.AdbUtils;
import com.automation.os.OS;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

/**
 * @author rahulsahu
 */
public class ServerUtil {

	CommandLineUtils clu = new CommandLineUtils();

	public void startServer(String appiumPort, String seleniumPort, String driverType)
			throws InterruptedException, IOException {
		System.out.println("Driver_Type: " + driverType);
		checkAndKillServers();
		if (driverType.startsWith("DESKTOP")) {
			startSeleniumServer(seleniumPort);
		} else {
			startSeleniumServer(seleniumPort);
			startAppiumServer(appiumPort);
		}
	}

	public void startAppiumServer(String appiumPort) {
		System.setProperty("ANDROID_HOME", "/Users/rahulsahu/Library/Android/sdk");
		AppiumDriverLocalService service = null;
		DesiredCapabilities cap;
		cap = new DesiredCapabilities();
//		cap.setCapability("--chromedriver-port", "62366");
		cap.setCapability("noReset", "false");
		HashMap<String, String> osPaths = getOSSpecificPaths(System.getProperty("os.name").toLowerCase());
		AdbUtils adbUtils=new AdbUtils(Configuration.deviceID);
		adbUtils.killadbServer();
//		clu.runShellCommand("adb kill-server", "Killing adb server");
		clu.runShellCommand("rm -rf /tmp/5037", "Killing adb server");
		System.out.println("Starting Appium Server on port - " + appiumPort + " from - " + osPaths.toString());
		if (osPaths.get("appiumPath") != null) {
			service = AppiumDriverLocalService
					.buildService(new AppiumServiceBuilder()
//							.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
							.usingDriverExecutable(new File(osPaths.get("nodePath"))).withIPAddress("127.0.0.1")
							.usingPort(Integer.parseInt(appiumPort.trim()))
							.withArgument(GeneralServerFlag.LOG_LEVEL, "error:debug")
							.withAppiumJS(new File(osPaths.get("appiumPath")))
							.withLogFile(new File("target/appiumLogs.txt")).withStartUpTimeOut(1, TimeUnit.MINUTES));
			service.start();
			System.out.println(" Appium Server started on port - " + appiumPort);
		} else {
			throw new RuntimeException("ERROR - Appium path not provided");
		}
	}

	public void startSeleniumServer(String seleniumPort) throws InterruptedException, IOException {
		System.out.println("user.dir: " + System.getProperty("user.dir"));
		String osName = System.getProperty("os.name");
		String cmd[] = null;
		if (osName.toLowerCase().contains("mac")) {
			cmd = new String[] { System.getProperty("user.dir")+"/src/main/resources/driver/startSeleniumServer.sh", System.getProperty("user.dir")
					+ "/src/main/resources/driver/selenium-server-standalone-3.141.59.jar ", seleniumPort };
		} else if (osName.toLowerCase().contains("windows")) {
			cmd = new String[] { "/src/main/resources/driver/startSeleniumServer.bat", System.getProperty("user.dir")
					+ "/src/main/resources/driver/selenium-server-standalone-3.141.59.jar ", seleniumPort };
		}

		clu.runShellCommandAndWaitTillExecute(cmd, "Running Selenium server");
		System.out.println(" Selenium Server started on port - " + seleniumPort);
		Thread.sleep(7000);
//		clu.runShellCommandAndWaitTillExecute("ps", "Running Selenium server");
	}

	public HashMap<String, String> getOSSpecificPaths(String osName) {
		HashMap<String, String> osPaths = new HashMap<String, String>();
		if (osName.contains("mac")) {
			osPaths.put("nodePath", "/usr/local/bin/node");
			osPaths.put("appiumPath", "/usr/local/lib/node_modules/appium/build/lib/main.js");
		} else if (osName.contains("linux")) {
			osPaths.put("nodePath", "/opt/node/bin/node");
			osPaths.put("appiumPath", "/opt/node/lib/node_modules/appium/build/lib/main.js");
		} else {
			throw new RuntimeException("not able to set node and appium path");
		}
		System.out.println("osPaths - " + osPaths);
		return osPaths;
	}

	public void checkAndKillServers() throws InterruptedException {
		String osName = System.getProperty("os.name");
		System.out.println("The osname printing is" + osName);
		String killCommand = "";
		if (OS.isMac()) {
			killCommand = System.getProperty("user.dir") + "/src/main/resources/driver/clearServer.sh";
		} else if (osName.toLowerCase().contains("windows")) {
			killCommand = System.getProperty("user.dir") + "/src/main/resources/driver/clearServer.bat";
		}

		String killMessage = "kill existing Selenium Server using command - " + killCommand;
		try {
			clu.runShellCommandAndWaitTillExecute(killCommand, killMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(100);
	}

}
