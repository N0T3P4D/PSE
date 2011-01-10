package org.ojim.server;

//import com.sun.java_cup.internal.runtime.Scanner;
import java.util.Scanner;

public class ServerStart {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Starting Server");
		OjimServer server = new OjimServer("testServer");
		System.out.println("Server Stoped");
	}

}
