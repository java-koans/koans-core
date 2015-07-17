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
package com.sandwich.util.io;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class FileMonitorFactory {

	private static Map<String, FileMonitor> monitors = new LinkedHashMap<String, FileMonitor>();
	public static final int SLEEP_TIME_IN_MS = 500;

	static {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
			public void run() {
				for(FileMonitor monitor : monitors.values()){
					monitor.close();
				}
				monitors.clear();
			}
		}));
		Thread pollingThread = new Thread(new Runnable(){
			public void run() {
				do{
					try {
						Thread.sleep(SLEEP_TIME_IN_MS);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					for(Entry<String, FileMonitor> filePathAndMonitor : monitors.entrySet()){
						FileMonitor monitor = filePathAndMonitor.getValue();
						if(monitor != null){
							monitor.notifyListeners();
						}
					}
				}while(true);
			}
		});
		pollingThread.setName("FileMonitorPolling");
		pollingThread.start();
	}
	
	public static FileMonitor getInstance(File monitoredFile, File dataFile) {
		String key = monitoredFile.getAbsolutePath() + dataFile.getAbsolutePath();
		FileMonitor monitor = monitors.get(key);
		if(monitor == null){
			monitor = new FileMonitor(monitoredFile, dataFile);
			monitors.put(key, monitor);
		}
		return monitor;
	}

	public static void removeInstance(FileMonitor monitor){
		monitor.close();
		monitors.remove(monitor.getFilesystemPath());
	}
	
	public static void removeInstance(String absolutePath) {
		monitors.remove(absolutePath);
	}

	public static void closeAll() {
		for(FileMonitor monitor : monitors.values()){
			monitor.close();
		}
		monitors.clear();
	}

}
