package hello;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class BatchRun {
	public static void main(String[] args) throws Exception {
//		String path = "C:\\Users\\999951.TCSGEGDC\\Documents\\TCS Internal\\Software\\glassfish4\\glassfish4\\bin\\asadmin.bat";
//		Runtime rn = Runtime.getRuntime();
//		Process pr = rn.exec(path);
//		Process p =  Runtime.getRuntime().exec("cmd /c asadmin.bat", null, new File("C:\\Users\\999951.TCSGEGDC\\Documents\\TCS Internal\\Software\\glassfish4\\glassfish4\\bin"));

		Runtime.getRuntime().exec("cmd /c start C:\\Users\\999951.TCSGEGDC\\Documents\\glassfish4\\bin\\asadmin.bat");
//		Runtime runtime = Runtime.getRuntime();
//		try {
//		    Process p1 = runtime.exec("C:\\Users\\999951.TCSGEGDC\\Documents\\TCS Internal\\Software\\glassfish4\\glassfish4\\bin\\asadmin.bat");
//		    InputStream is = p1.getInputStream();
//		    int i = 0;
//		    while( (i = is.read() ) != -1) {
//		        System.out.print((char)i);
//		    }
//		} catch(IOException ioException) {
//		    System.out.println(ioException.getMessage() );
//		}
	}
}
