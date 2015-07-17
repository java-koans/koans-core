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

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UTFDataFormatException;

/**
 * Handles persistence to/from a filesystem, and makes assumption instances of T
 * are mutated after retrieval. Their mutations are saved to file on app
 * shutdown automatically.
 * 
 * @param <T>
 */
public class DataFileHelper<T> {

	private File dataFile;
	private T lastRetrieval;
	private T defaultState;
	
	public DataFileHelper(File dataFile, T defaultState){
		this.dataFile = dataFile;
		if(!dataFile.exists()){
			dataFile.getParentFile().mkdirs();
			write(defaultState);
		}
		this.defaultState = defaultState;
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				if(lastRetrieval != null){
					write(lastRetrieval);
				}
	        }
	    }));
	}
	
	@SuppressWarnings("unchecked")
	public T read(){
		ObjectInputStream objectInputStream = null;
		try {
			File dataFile = getDataFile();
			if(!dataFile.exists()){
				return null;
			}
			objectInputStream = new ObjectInputStream(new FileInputStream(dataFile));
			if(dataFile.exists()){
				try	{
					return lastRetrieval = (T)objectInputStream.readObject();
				}catch(UTFDataFormatException x){
					createNewFile(dataFile);
					return defaultState;
				}catch(EOFException x){
					createNewFile(dataFile);
					return defaultState;
				}
			}else{
				return null;
			}
		} catch (Exception e) {
			try{
				if(objectInputStream != null){
					objectInputStream.close();
				}
			}catch(Exception e2){
				throw new RuntimeException(e2);
			}
			throw new RuntimeException(e);
		} finally {
			if(objectInputStream != null){
				try {
					objectInputStream.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	private void createNewFile(File dataFile) throws IOException {
		dataFile.delete();
		dataFile.createNewFile();
		write(defaultState);
	}

	public void write(T state){
		ObjectOutputStream objectOutputStream = null;
		try {
			objectOutputStream = new ObjectOutputStream(new FileOutputStream(getDataFile()));
			objectOutputStream.writeObject(state);
		} catch (Exception e) {
			try{
				if(objectOutputStream != null){
					objectOutputStream.close();
				}
			}catch(Exception e2){
				throw new RuntimeException(e2);
			}
			throw new RuntimeException(e);
		} finally {
			if(objectOutputStream != null){
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	private File getDataFile() {
		return dataFile;
	}
	
}
