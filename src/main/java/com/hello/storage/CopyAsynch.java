package com.hello.storage;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;

@Component
public class CopyAsynch {

	
    @Async
    public Future<Boolean> saveACopyAsynch(MultipartFile file) throws InterruptedException, Exception {
    	//System.out.println("File Copy");
		String rootPath = System.getProperty("user.home");
		File dir1 = new File(rootPath + File.separator + "tmpcopyFilesUpload");
		Path rootLocation1 = Paths.get(dir1.getAbsolutePath());
		
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd_HHmmss");//dd/MM/yyyy
		Date now = new Date();
		String strDate = sdfDate.format(now);
		Files.copy(file.getInputStream(), rootLocation1.resolve(strDate+file.getOriginalFilename()));
		System.out.println("Backup File Copied");
		
        return new AsyncResult<Boolean>(true);
    }
}
