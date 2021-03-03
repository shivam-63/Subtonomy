package com.subtonomy.java;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class GetFileFromServer {
	
	Socket socket = null;
	InputStream inputStream = null;
	OutputStream fileOutputStream = null;
	DataOutputStream dataOutputStream =null;
	
	public GetFileFromServer(String host, int port) throws UnknownHostException, IOException {
		socket = new Socket(host, port);
	}
	

	
	// this method requests file from the server and then saves it to the local directory
	public void downloadFile(String fileName) throws IOException{
		
		OutputStream output = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);
        
        // send name of file that is requested to the server
        writer.println(fileName);
        
        try {
        	inputStream = socket.getInputStream();
		} catch(IOException e) {
			System.out.println("Can't get socket input stream. ");
		}
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String serverMessage = reader.readLine();
			if(serverMessage.equals("filenotfound")) {
				System.out.println("File not found on server");
				return;
			}
			fileOutputStream = new FileOutputStream(fileName);
	
			byte[] bytes = new byte[16*1024];
			
			int count;
			
			// save file to local disk
	        while ((count = inputStream.read(bytes)) > 0) {
	        	fileOutputStream.write(bytes, 0, count);
	        }
	        System.out.println("File successfully received");
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            fileOutputStream.close();
            inputStream.close();
            socket.close();
        }

	}

}
