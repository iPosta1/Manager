package com.exadel.web;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class SessionCounterListener implements HttpSessionListener {

 private static int totalActiveSessions;

 public static int getTotalActiveSession(){
   return totalActiveSessions;
 }

 public void sessionCreated(HttpSessionEvent arg0) {
   totalActiveSessions++;
 }

 public void sessionDestroyed(HttpSessionEvent arg0) {
   totalActiveSessions--;
 } 
}