/**
 * Copyright 2015 Java Koans
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.sandwich.koan;

import java.io.FileInputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import com.sandwich.util.io.directories.DirectoryManager;

public class ApplicationSettings {

	private static ResourceBundle CONFIG_BUNDLE;
	
	private static boolean debug;
	
	public static void setDebug(boolean debug2) {
		debug = debug2;
	}
	
	public static boolean isDebug(){
		return debug || isEqual(getConfigBundle().getString("debug"), true, true);
	}
	
	public static boolean isEncouragementEnabled(){
		return isEqual(getConfigBundle().getString("enable_encouragement"), true, true);
	}
	
	public static char getExitChar(){
		return getConfigBundle().getString("exit_character").charAt(0);
	}
	
	public static String getPathXmlFileName(){
		return getConfigBundle().getString("path_xml_filename");
	}
	
	public static long getFileCompilationTimeoutInMs(){
		return Long.valueOf(getConfigBundle().getString("compile_timeout_in_ms"));
	}
	
	private static boolean isEqual(String value, Object e2, boolean ignoreCase){
		if(value == e2){
			return true;
		}
		if(value != null){
			String stringValue = String.valueOf(e2);
			return ignoreCase ? value.equalsIgnoreCase(stringValue) : value.equals(stringValue);
		}
		return false;
	}
	
	private static ResourceBundle getConfigBundle(){
		if(CONFIG_BUNDLE == null){
			try {
				CONFIG_BUNDLE = new PropertyResourceBundle(new FileInputStream(
						DirectoryManager.injectFileSystemSeparators(
							DirectoryManager.getConfigDir(), "config.properties")));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return CONFIG_BUNDLE;	
	}
	
}
