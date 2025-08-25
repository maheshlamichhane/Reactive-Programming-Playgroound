package com.vinsguru.sec04.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class FIleReaderServiceImpl implements FileReaderService{

    private static final Logger logger = LoggerFactory.getLogger(FileReaderService.class);

    @Override
    public Flux<String> read(Path path) {
        return Flux.generate(
                () -> openFile(path),
                this::readFile,
                this::closedFile
        );
    }

    private BufferedReader openFile(Path path) throws IOException{
        logger.info("opening file");
        return Files.newBufferedReader(path);
    }

    private BufferedReader readFile(BufferedReader reader, SynchronousSink<String> sink){
        try {
            var line = reader.readLine();
            logger.info("reading line: {}",line);
            if(Objects.isNull(line)){
                sink.complete();
            }
            else{
                sink.next(line);
            }

        }
        catch (Exception e){
            sink.error(e);
        }
        return reader;
    }

    public void closedFile(BufferedReader reader){
        try {
            reader.close();
            logger.info("closed file");
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
