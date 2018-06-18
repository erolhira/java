package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ObjectSerialization {

	private List<String> list = Arrays.asList("Erol Hira", "Hilal Hira", "Zeynep Hira");
	
	@Test
	public void testObjectSerialization(){
		
		System.out.println("before  serialization: " + String.valueOf(list));
		serialize();
		Object deserializedList = deserialize();
		System.out.println("after deserialization: " + String.valueOf(deserializedList));
	}
	
	public void serialize(){
		
		try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("./src/io/serialized_object"));){
			os.writeObject(list);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public Object deserialize(){
		
		Object deserializedObject = null;
		try(ObjectInputStream is = new ObjectInputStream(new FileInputStream("./src/io/serialized_object"));){
			deserializedObject = is.readObject();
		} catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return deserializedObject;
	}
}
