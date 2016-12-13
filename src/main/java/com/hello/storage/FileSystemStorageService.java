package com.hello.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hello.AppConfig;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;

	@Autowired
	public CopyAsynch copy;
	
	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		System.out.println(properties.getLocation());
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public void store(MultipartFile file) throws InterruptedException, Exception {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
			}
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
			
			System.out.println("File Stored");

			//Future<Boolean> future = saveACopyAsynch(file);
//			ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);		
//			CopyAsynch copy = context.getBean(CopyAsynch.class);
//	        System.out.println("about to run");
	        Future<Boolean> future = copy.saveACopyAsynch(file);	        
			
//			System.out.println("return after a store before copy");

		} catch (IOException e) {
			throw new StorageException("Failed to store file " + file.getOriginalFilename() +" Note : Please Upload files with unique filename or Delete existing file.", e);
		}
	}

//	@Async
//	private Future<Boolean> saveACopyAsynch(MultipartFile file) throws IOException {
//	
//
//		return new AsyncResult<Boolean>(true);
//	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(path -> this.rootLocation.relativize(path));
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
//	public Resource loadAsResource(String filename) {
//		try {
//			Path file = load(filename);
//			System.out.println("file " + file);
//			System.out.println("load " + file.toUri());
//			Resource resource = new UrlResource(
//					"file:///C:/Users/999951.TCSGEGDC/Downloads/gs-uploading-files-master/gs-uploading-files-master/complete/upload-dir/AWO_SR.ppt");
//			if (resource.exists() || resource.isReadable()) {
//				return resource;
//			} else {
//				throw new StorageFileNotFoundException("Could not read file: " + filename);
//
//			}
//		} catch (MalformedURLException e) {
//			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
//		}
//	}

	public Resource loadAsResource(String filename) {
        try {
            Path file = this.load(filename);
            UrlResource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            throw new StorageFileNotFoundException("Could not read file: " + filename);
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, (Throwable)e);
        }
    }
	
	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

	@Override
	public void deleteAFile(String file) throws Exception {
		// TODO Auto-generated method stub

		// Files.copy(file.getInputStream(),
		// this.rootLocation.resolve(file.getOriginalFilename()));
		// String dir =file;
		// System.out.println("dir :"+dir);
		//// "C:/Users/999951.TCSGEGDC/Downloads/gs-uploading-files-master/gs-uploading-files-master/complete/upload-dir/"+
		// Path path = Paths.get(dir);
		// Files.delete(path);
		String rootPath = System.getProperty("user.home");
		File dir1 = new File(rootPath + File.separator + "tmpFilesUpload");
		String dir = "C:/Users/999951.TCSGEGDC/Downloads/gs-uploading-files-master/gs-uploading-files-master/complete/upload-dir/"
				+ file;
		Path path = Paths.get(dir1.getAbsolutePath()+ File.separator + file);
		Files.delete(path);
	}
}
