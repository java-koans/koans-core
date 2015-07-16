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
package com.sandwich.koan.runner;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.sandwich.koan.ApplicationSettings;
import com.sandwich.koan.cmdline.CommandLineArgument;
import com.sandwich.koan.cmdline.CommandLineArgumentBuilder;
import com.sandwich.koan.cmdline.CommandLineArgumentRunner;
import com.sandwich.koan.constant.ArgumentType;
import com.sandwich.koan.util.ApplicationUtils;
import com.sandwich.util.io.FileMonitor;
import com.sandwich.util.io.FileMonitorFactory;
import com.sandwich.util.io.KoanFileCompileAndRunListener;
import com.sandwich.util.io.directories.DirectoryManager;

public class AppLauncher {

	public static void main(final String... args) throws Throwable {
		Map<ArgumentType, CommandLineArgument> argsMap = new CommandLineArgumentBuilder(args);
		if(argsMap.containsKey(ArgumentType.RUN_KOANS)){
			new Thread(new Runnable(){ 
				public void run() {
					do{
						try {
							char c = (char)System.in.read();
							if(Character.toUpperCase(c) == 
									Character.toUpperCase(ApplicationSettings.getExitChar())){
								FileMonitorFactory.closeAll();
								System.exit(0);
							}
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
					}while(true);
				}
			}).start();
			FileMonitor monitor = FileMonitorFactory.getInstance(
					new File(DirectoryManager.getProdMainDir()), 
					new File(DirectoryManager.getDataFile()));
			if(ApplicationUtils.isFirstTimeAppHasBeenRun()){
				monitor.writeChanges();
			}
			monitor.addFileSavedListener(new KoanFileCompileAndRunListener(argsMap));
		}
		new CommandLineArgumentRunner(argsMap).run();
		if(ApplicationSettings.isDebug()){
			StringBuilder argsBuilder = new StringBuilder();
			int argNumber = 0;
			for(String arg : args){
				argsBuilder.append("Argument number "+String.valueOf(++argNumber)+": '"+arg+"'");
			}
			ApplicationUtils.getPresenter().displayMessage(argsBuilder.toString());
		}
	}
}
