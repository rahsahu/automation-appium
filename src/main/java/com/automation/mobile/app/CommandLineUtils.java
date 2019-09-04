package com.automation.mobile.app;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import com.automation.streams.StreamOPS;
import com.google.common.io.CharStreams;

public class CommandLineUtils {

	public Process runShellCommand(String cmd, String message, int sleepFor) {
		String[] command = { "/bin/bash", "-c", cmd };
		Process p = null;
		LogUtils.info("COMMAND: " + cmd);
		try {
			LogUtils.info(message + " - '" + Arrays.toString(command) + "'");
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
		} catch (Exception e) {
			throw new RuntimeException("Error running Shell command");
		}
		if (p.exitValue() != 0) {
			try {
				LogUtils.info(CharStreams.toString(new InputStreamReader(p.getErrorStream())));
			} catch (IOException e) {
				e.printStackTrace();
			}
			throw new RuntimeException("Shell command exit value is not 0");
		}
		try {
			LogUtils.info("COMMAND_OUTPUT: " + CharStreams.toString(new InputStreamReader(p.getErrorStream())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}

	public Process runShellCommand(String cmd, String message) {
		return runShellCommand(cmd, message, 1);
	}

	public Process runShellCommandAndWaitTillExecute(String command, String message) {
		return runShellCommandAndWaitTillExecute(new String[] { command }, message);
	}

	public Process runShellCommandAndWaitTillExecute(String[] command, String message) {
		Process pr = null;
		System.out.println(" Message:" + message + " - Command: " + command.toString() + "");
		Runtime run = Runtime.getRuntime();
		try {
			pr = run.exec((command));
			pr.waitFor();
			Thread.sleep(200);
			LogUtils.info("EXIT_VALUE: " + pr.exitValue());
			LogUtils.info("OUTPUT: " + pr.exitValue());

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to execute sh command");
		}
		LogUtils.info("EXIT_VALUE: " + pr.exitValue());
		LogUtils.info("INPUTSTREAM: " + StreamOPS.InputStreamToList(pr.getInputStream()));
		LogUtils.info("ERROR: " + StreamOPS.InputStreamToList(pr.getErrorStream()));
		LogUtils.info("OUTPUT: " + StreamOPS.outPutStreamToString(pr.getOutputStream()));
		return pr;

	}

//	public static void main(String are[]) {
////        System.setProperty("ANDROID_HOME", System.getenv("ANDROID_HOME"));
////		AdbUtils.showConnectedDevices();
//		CommandLineUtils clu = new CommandLineUtils();
////		String s = System.getenv("ANDROID_HOME");
//		Process p;
////				clu.runShellCommand(
////				"/Users/rahulsahu/Library/Android/sdk/platform-tools/adb devices -l | grep 'device usb' | awk '{print $2}'",
////				"MEssage");
//		p = clu.runShellCommand("ps -ef | grsep 'java' | awk '{print $2 $1}'", "MEssage");
//		try {
//			System.out.println("EXIT_VALUE: " + p.exitValue());
//			System.out.println("INPUT_STREAM: " + CharStreams.toString(new InputStreamReader(p.getInputStream())));
//			System.out.println("OUTPIT_STREAM: " + CharStreams.toString(new InputStreamReader(p.getErrorStream())));
////			System.out.println(CharStreams.toString(new OutputStreamWriter(p.getOutputStream()).toString()));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	 public static void main (String[] args) {
//	        Map<String, String> env = System.getenv();
//	        for (String envName : env.keySet()) {
//	            System.out.format("%s=%s%n",
//	                              envName,
//	                              env.get(envName));
//	        }
//	    }
}
