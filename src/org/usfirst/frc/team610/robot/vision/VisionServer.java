package org.usfirst.frc.team610.robot.vision;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.spectrum3847.RIOdroid.RIOadb;

// Code taken from team 254 github

public class VisionServer implements Runnable {

	private static VisionServer instance = null;
	private ServerSocket serverSocket;
	double lastMessageReceivedTime = 0;
	private volatile boolean wantsAppRestart = false;
	private Socket p;
	private String rawInput;
	private boolean startInput = false;
	private ServerThread commThread;



	private static final int port = 3000;

	public static VisionServer getInstance() {
		if (instance == null) {
			instance = new VisionServer();
		}
		return instance;
	}

	private boolean connected = false;

	public boolean isConnected() {
		return connected;
	}

	// Thread to deal with sending heartbeat messages and receiving heartbeat
	// and vision messages
	protected class ServerThread implements Runnable {
		private Socket socket;

		public ServerThread(Socket socket) {
			this.socket = socket;
		}

		public boolean isAlive() {
			return socket != null && socket.isConnected() && !socket.isClosed();
		}

		@Override
		public void run() {
			// Once thread used, do not run entire loop
			if (socket == null) {
				return;
			}
			try {
				InputStream is = socket.getInputStream();
				byte[] buffer = new byte[2048];
				int read;
				// Continue while connected and have messages to read
				while (socket.isConnected() && (read = is.read(buffer)) != -1) {
					startInput = true;  
					String messageRaw = new String(buffer, 0, read);
					rawInput = messageRaw;
				}
				System.out.println("Socket disconnected");
				p = null;
				commThread = null;
				startInput = false;
				
			} catch (IOException e) {
				System.err.println("Could not talk to socket");
				socket = null;
				p = null;
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		public void close() {
			// TODO Auto-generated method stub
			
		}
	}

	private VisionServer() {
		System.out.println("VisionServer initializing");
		try {
			// Creating a socket and setting up connection over adb to start tcp
			serverSocket = new ServerSocket(port);
			try{
				RIOadb.init();
			} catch(IndexOutOfBoundsException e){
				System.out.println("Device Not Connected");
			}
			AdbUtils.adbReverseForward(port, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Thread(this).start();
		new Thread(new AppMaintainanceThread()).start();
	}

	public String getRawInput() {
		if(startInput){
			if(commThread == null){
				return "Socket Not Connected";
			}
			return rawInput;
		} else {
			return "lmao";
		}
	}

	public double getDouble() {
		String sub = getRawInput();
		String [] tokens = sub.split("/n");
		if((tokens[0].charAt(0) < 57 && tokens[0].charAt(0) > 48 )|| tokens[0].charAt(0) == 45){
			return Double.parseDouble(tokens[0]);
		} else {
			return 0;
		}
	}

	/**
	 * If a VisionUpdate object (i.e. a target) is not in the list, add it.
	 * 
	 * @see VisionUpdate
	 */



	

	@Override
	public void run() {
		System.out.println("VisionServer thread starting");

		p = null;
		while (true) {
			// If socket is disconnected, attempt to reconnect and start new
			// ServerThread
		
			try {
				if (p == null) {
					
					System.out.println("Attempting to accept socket");
					p = serverSocket.accept();
					System.out.println("Socket Accepted!");
					commThread = new ServerThread(p);
					new Thread(commThread).start();
					System.out.println("Connected");
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class AppMaintainanceThread implements Runnable {
		@Override
		public void run() {
			while (true) {
				if (getTimestamp() - lastMessageReceivedTime > .1) {
					// camera disconnected
					AdbUtils.adbReverseForward(port, port);
					connected = false;
				} else {
					connected = true;
				}
				if (wantsAppRestart) {
					AdbUtils.restartApp();
					wantsAppRestart = false;
				}
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private double getTimestamp() {
		return System.currentTimeMillis();
	}

}
