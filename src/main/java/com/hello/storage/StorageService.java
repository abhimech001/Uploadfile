package com.hello.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(MultipartFile file) throws InterruptedException, Exception;

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

	void deleteAFile(String file) throws Exception;

}
