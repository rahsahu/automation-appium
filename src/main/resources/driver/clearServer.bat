



wmic Path win32_process Where "CommandLine Like '%[n]ode.*appium%'" Call Terminate
wmic Path win32_process Where "CommandLine Like '%[n]ode.*driver%'" Call Terminate
wmic Path win32_process Where "CommandLine Like '%[A]ndroid.*/adb%'" Call Terminate
wmic Path win32_process Where "CommandLine Like '%[s]elenium-server-standalone.*%'" Call Terminate