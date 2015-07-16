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
package com.sandwich.koan.constant;

import com.sandwich.util.Strings;

public abstract class KoanConstants {

	private KoanConstants(){}
	
	public static final String  __					= Strings.getMessage("__");
	
	public static final String  EOL					= System.getProperty("line.separator");
	public static final String  EOLS				= "[\n\r"+EOL+"]";
	
	public static final String  PERIOD 				= ".";
	public static final String  EXPECTATION_LEFT_ARG= "has expectation as wrong argument!";
	public static final String  EXPECTED_LEFT 		=  ":<";
	public static final String  EXPECTED_RIGHT 		= ">";
	public static final String  LINE_NO_START		= ".java:";
	public static final String  LINE_NO_END 		= ")";
	
	public static final String 	COMPLETE_CHAR 		= "X";
	public static final String 	INCOMPLETE_CHAR		= "-";
	
	public static final int		PROGRESS_BAR_WIDTH	= 50;
	public static final String 	PROGRESS_BAR_START	= "[";
	public static final String 	PROGRESS_BAR_END	= "]";
	
	public static final String  XML_PARAMETER_START = "${";
	public static final String  XML_PARAMETER_END 	= "}";
	
}
