package org.xjd.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CommonUtils {

	
	public   static   String   inputStream2String(InputStream   is)   throws   IOException{ 
        ByteArrayOutputStream   baos   =   new   ByteArrayOutputStream(); 
        int   i=-1; 
        while((i=is.read())!=-1){ 
        baos.write(i); 
        } 
       return   baos.toString(); 
}


}
