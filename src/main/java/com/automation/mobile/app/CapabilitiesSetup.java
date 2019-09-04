package com.automation.mobile.app;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * 
 * @author rahulsahu
 *
 */
public interface CapabilitiesSetup {
	DesiredCapabilities getDesiredCapabilities(String deviceId);
}
