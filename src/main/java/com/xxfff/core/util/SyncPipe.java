package com.xxfff.core.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class SyncPipe implements Runnable {
	public SyncPipe(InputStream istrm, OutputStream ostrm) {
		istrm_ = istrm;
		ostrm_ = ostrm;
	}
	
	public void run() {
		try {
			final byte[] buffer = new byte[1024];
			for (int length = 0; (length = istrm_.read(buffer)) != -1;) {
				ostrm_.write(buffer, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final OutputStream ostrm_;
	private final InputStream istrm_;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		String[] command = { "cmd", };
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Process p = Runtime.getRuntime().exec(command);
		new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
		Thread t = new Thread(new SyncPipe(p.getInputStream(), baos));
		t.start();
		PrintWriter stdin = new PrintWriter(p.getOutputStream());
		stdin.println("dir c:\\ /A /Q");
		// write any other commands you want here
		
		stdin.close();
		
		int returnCode = p.waitFor();
		System.out.println("Return code = " + returnCode);
		t.join();
		System.out.println(baos.toString());
	}
}