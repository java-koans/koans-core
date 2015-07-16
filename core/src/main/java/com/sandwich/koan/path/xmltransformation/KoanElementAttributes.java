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
package com.sandwich.koan.path.xmltransformation;


public class KoanElementAttributes{
	
	public String name, displayIncompleteKoanException, className;
	
	public KoanElementAttributes(String name, String displayIncompleteKoanException, String className){
		this.name = name;
		this.displayIncompleteKoanException = displayIncompleteKoanException;
		this.className = className;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((className == null) ? 0 : className.hashCode());
		result = prime
				* result
				+ ((displayIncompleteKoanException == null) ? 0
						: displayIncompleteKoanException.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KoanElementAttributes other = (KoanElementAttributes) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (displayIncompleteKoanException == null) {
			if (other.displayIncompleteKoanException != null)
				return false;
		} else if (!displayIncompleteKoanException
				.equals(other.displayIncompleteKoanException))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KoanElementAttributes [name=" + name
				+ ", displayIncompleteKoanException="
				+ displayIncompleteKoanException + ", className=" + className
				+ "]";
	}
	
}
