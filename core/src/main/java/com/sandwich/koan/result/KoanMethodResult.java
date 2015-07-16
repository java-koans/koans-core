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
package com.sandwich.koan.result;

import com.sandwich.koan.KoanMethod;

public class KoanMethodResult {

	public final static KoanMethodResult PASSED = new KoanMethodResult(null,"PASSED","PASSED");
	
	private final String message;
	private final String lineNumber;
	private final KoanMethod failingMethod;	
	
	public KoanMethodResult(KoanMethod failingMethod, String message, String lineNumber){
		this.message = message;
		this.failingMethod = failingMethod;
		this.lineNumber = lineNumber;
	}

	public static KoanMethodResult getPassing() {
		return PASSED;
	}

	public String getMessage() {
		return message;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public KoanMethod getFailingMethod() {
		return failingMethod;
	}

	public boolean isPassed(){
		return this == PASSED;
	}
	
	@Override
	public String toString() {
		return "KoanMethodResult [message=" + message + ", lineNumber="
				+ lineNumber + ", failingMethod=" + failingMethod + "]";
	}
	
}
