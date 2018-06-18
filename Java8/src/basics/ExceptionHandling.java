package basics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class ExceptionHandling {
	
	/*
	 * Resources opened by using the try-with-resources statement are always closed.
	 */
	@Test
	public void closableResources(){
		
		try(InputStream in = new FileInputStream("./src/basics/SubClass.java")){
			System.out.println("File is open");
			in.read();
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void multiCatch(){
		
		try(InputStream in = new FileInputStream("./src/basics/SubClass.java")){
			System.out.println("File is open");
			in.read();
		} catch (IOException | NumberFormatException e) { 
			e.printStackTrace();
		} 
	}
}
