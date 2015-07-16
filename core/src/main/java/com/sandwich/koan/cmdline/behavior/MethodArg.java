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

import com.sandwich.koan.path.PathToEnlightenment;

public class MethodArg extends AbstractArgumentBehavior {

	public void run(String...koanName) {
		if(koanName != null && 
				koanName.length > 0 &&
				koanName[0] != null && 
				koanName[0].trim().length() != 0){
			PathToEnlightenment.filterByKoan(koanName[0]);
		}
	}
	
}
