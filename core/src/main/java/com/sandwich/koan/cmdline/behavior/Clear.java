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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sandwich.koan.util.ApplicationUtils;
import com.sandwich.util.io.directories.DirectoryManager;

public class Clear extends AbstractArgumentBehavior {

	List<String> files = Arrays.asList(
			DirectoryManager.getDataFile(),
			DirectoryManager.getBinDir(),
			DirectoryManager.getDataDir());
	
	public void run(String... values) throws Exception {
		List<File> unableToDelete = new ArrayList<File>();
		for(String fileName : files){
			File file = new File(fileName);
			if(file.exists()){
				if(file.delete()){
					ApplicationUtils.getPresenter().displayMessage(file.getAbsolutePath() + " deleted successfully.");
				}else{
					unableToDelete.add(file);
					ApplicationUtils.getPresenter().displayError(file.getAbsolutePath() + " was NOT DELETED. Please delete manually.");
				}
			}else{
				ApplicationUtils.getPresenter().displayMessage(file.getAbsolutePath() + " does not exist. Skipping.");
			}
		}
		if(!unableToDelete.isEmpty()){
			throw new RuntimeException("Unable to delete: "+unableToDelete+" see output for details.");
		}
	}
	
}
