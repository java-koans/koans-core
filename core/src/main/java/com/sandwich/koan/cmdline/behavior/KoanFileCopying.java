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
package com.sandwich.koan.cmdline.behavior;

import java.io.IOException;

import com.sandwich.koan.ui.SuitePresenter;
import com.sandwich.koan.util.ApplicationUtils;
import com.sandwich.util.io.directories.DirectoryManager;

public abstract class KoanFileCopying extends AbstractArgumentBehavior {

	public void run(String... values) {
		SuitePresenter presenter = ApplicationUtils.getPresenter();
		try {
			copy(DirectoryManager.getProjectDataSourceDir(), DirectoryManager.getSourceDir());
		} catch (IOException e) {
			e.printStackTrace();
			presenter.displayError(getErrorMessage());
			System.exit(-1);
		}
		presenter.displayMessage(getSuccessMessage());
	}

	protected abstract void copy(String backupSrcDirectory, String appSrcDirectory) throws IOException;  
	
}
