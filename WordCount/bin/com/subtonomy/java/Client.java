package com.subtonomy.java;

import java.io.IOException;

public class Client {
	
	public static void main(String[] args) throws IOException{
		
		if(args.length==0) {
			System.out.println("No file name provided. Exiting...");
			return;
		}
		
		String host = "127.0.0.1";
		int port = 4443;
		
		GetFileFromServer getFile = new GetFileFromServer(host, port);
		
		// call downloadFile method of getFile class
		getFile.downloadFile(args[0]);	
		
		// call the method for counting occurrence of words in file
		JavaWordCount wordCount = new JavaWordCount();
		wordCount.countWords(args[0]);	
	}

}
