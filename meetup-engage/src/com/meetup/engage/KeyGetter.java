package com.meetup.engage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class KeyGetter {
	private String key = "";

	public KeyGetter(String aFile) throws Exception {
		try {
			byte[] keyArr = Files.readAllBytes(Paths.get(aFile));
			
			if (keyArr.length == 0) {
				System.err.println("The key file '" + aFile + "' is empty.");
			} else {
				this.key = new String(keyArr);
			}
		} catch (IOException e) {
			System.err.println("The key file '" + aFile + "' does not exist or cannot be accessed.");
		}
	}
	
	public String readKey() throws Exception {
		if (key.isEmpty()) {
			System.err.println("A key has not been loaded.");
			return null;
		} else {
			System.out.println("The key has been retrived.");
			return key;
		}
	}
}
 