#!/bin/bash

echo "Print running comands"
ps aux | grep "[n]ode.*appium" 
ps aux | grep "[n]ode.*driver" 
ps aux | grep "[A]ndroid.*/adb" 
ps aux | grep "[s]elenium-server-standalone.*" 

ps aux | grep "[n]ode.*appium" | awk '{print $2}' | xargs kill -9
ps aux | grep "[n]ode.*driver" | awk '{print $2}' | xargs kill -9
ps aux | grep "[A]ndroid.*/adb" | awk '{print $2}' | xargs kill -9
ps aux | grep "[s]elenium-server-standalone.*" | awk '{print $2}' | xargs kill -9
