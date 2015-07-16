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

import com.sandwich.util.io.filecompiler.CompilationListener;
import com.sandwich.util.io.filecompiler.FileCompilerAction;

public class KoanSuiteCompilationListener implements CompilationListener {

	private boolean lastCompilationAttemptFailed = false;
	private String lastMessageShown = null;
	
	public void compilationFailed(File src, String[] command, int exitCode, String errorMessage, Throwable x) {
		if(lastMessageShown == null || !errorMessage.trim().equals(lastMessageShown.trim())){
			FileCompilerAction.LOGGING_HANDLER.compilationFailed(src, command, exitCode, errorMessage, x);
		}
		lastMessageShown = errorMessage;
		lastCompilationAttemptFailed = true;
	}
	
	public void compilationSucceeded(File src, String[] command, String stdIo, Throwable x) {
		lastMessageShown = null; // reset last failed compilation message
		lastCompilationAttemptFailed = false;
	}
	
	public boolean isLastCompilationAttemptFailure(){
		return lastCompilationAttemptFailed;
	}
}
