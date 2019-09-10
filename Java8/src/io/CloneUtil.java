package io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CloneUtil {
	
    public static Object deepCopy(Object original) {
    	
        Object obj = null;
        try {
            // Write the object out to a byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(original);
            out.flush();
            out.close();

            // Make an input stream from the byte array and read a copy of the object back in.
            ObjectInputStream in = new ObjectInputStream(
                    new ByteArrayInputStream(bos.toByteArray()));
            obj = in.readObject();
        }
        catch (Exception e) {
            throw new RuntimeException("deepy copy of " + original.getClass().getName() + " failed --> (" + e.getMessage() + ")");
        }
        return obj;
    }
}
