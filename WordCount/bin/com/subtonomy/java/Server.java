package com.subtonomy.java;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	
	public static void fileTransfer(String fileName, Socket socket) throws IOException {
		
		InputStream fileInputStream = null;
		OutputStream outputStream = null;
		PrintWriter writer = null;
		
//		String path = ".\\";
		try {
			File file = new File(fileName);
			outputStream = socket.getOutputStream();
			writer = new PrintWriter(outputStream, true);
			
			// if file does not exist, send message to client that no such file found
			if(!file.exists()) {
				writer.println("filenotfound");
				throw new FileNotFoundException();
			}
			
			// if file found, let the client know that file name is valid
	        writer.println("filefound");
			fileInputStream = new FileInputStream(file);
	        byte[] bytes = new byte[16 * 1024];
	        
	        int count;
	        
	        // send the content of file byte by byte
	        while ((count = fileInputStream.read(bytes)) > 0) {
	            outputStream.write(bytes, 0, count);
	        }
		}catch(FileNotFoundException e) {
			System.out.println("File not found. Try again!");
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			fileInputStream.close();
			outputStream.close();
		}
	}

	public static void main(String[] args) throws IOException{
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(4443);
		} catch(IOException e) {
			System.out.println("Can't setup server on this port number. ");
		}
	
		Socket socket = null;
		
		try {
			socket = serverSocket.accept();
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String clientMessage = reader.readLine();
			System.out.println("Client requested for file: "+ clientMessage);
			
			// call the fileTransfer method
			fileTransfer(clientMessage, socket);
			System.out.println("File successfully trasfered");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
	        socket.close();
	        serverSocket.close();
		}
	}

}
