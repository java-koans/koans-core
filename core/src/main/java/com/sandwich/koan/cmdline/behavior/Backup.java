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

import java.io.File;
import java.io.IOException;

import com.sandwich.util.io.CopyFileOperation;

public class Backup extends KoanFileCopying{

	@Override
	protected void copy(String backupSrcDirectory, String appSrcDirectory)
			throws IOException {
		File backupDir = new File(backupSrcDirectory);
		if(!backupDir.exists()){
			backupDir.mkdirs();
		}
		File sourceDir = new File(appSrcDirectory);
		new CopyFileOperation(sourceDir, backupDir){
			public void onNew(File file) throws IOException {
				file.mkdirs();
			};
		}.operate();
	}
	
}
