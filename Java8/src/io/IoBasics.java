package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class IoBasics {

	@Test
	public void readAndWriteWithByteStreams(){
		
		byte[] bytes = new byte[256];
		try(InputStream in = new FileInputStream("./src/basics/SubClass.java");
			OutputStream os = new FileOutputStream("./src/io/SubClass.txt")){
			
			while(in.read(bytes) != -1){
				os.write(bytes);
			}
			
			System.out.println("done.");
			
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void readAndWriteWithCharacterStreams() {
		
		char[] chars = new char[256];
		try(FileReader reader = new FileReader("./src/basics/SubClass.java");
			FileWriter writer = new FileWriter("./src/io/SubClassFromWriter.txt")){
						
			while(reader.read(chars) != -1){
				writer.write(chars);
			}
			
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void chainedStreams(){
		
		//The size of the buffer can be set, but the default value is generally sufficient.
		try(BufferedReader bufReader = new BufferedReader(new FileReader("./src/basics/SubClass.java"));
			BufferedWriter bufWriter = new BufferedWriter(new FileWriter("./src/io/SubClassFromWriter.txt"));){
			
			String line = null;
			while((line = bufReader.readLine()) != null){
				bufWriter.write(line);
				bufWriter.newLine();
			}
			
		} catch(IOException e){
			e.printStackTrace();
			
		}
	}
	
	@Test
	public void channelIo(){
		
		/*Introduced in JDK 1.4, a channel reads bytes and characters in
		blocks, rather than one byte or character at a time. */
		try(FileChannel fcIn = new FileInputStream("./src/basics/SubClass.java").getChannel();
			FileChannel fcOut = new FileOutputStream("./src/io/SubClassFromWriter.txt").getChannel()) {
			
			/*
			 * Create a buffer sized the same as the file size, and then read and write the file in a single operation.
			 */
			ByteBuffer buff = ByteBuffer.allocate((int) fcIn.size());
			fcIn.read(buff);
			buff.position(0);
			fcOut.write(buff);
		} catch (IOException f) {
			f.printStackTrace();
		}
		
	}
}
