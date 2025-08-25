package com.vinsguru.sec04;

import com.vinsguru.sec02.common.Util;
import com.vinsguru.sec04.assignment.FIleReaderServiceImpl;

import java.nio.file.Path;

public class Lec09Assignment {
    public static void main(String[] args) {

        var path = Path.of("src/main/resources/sec04/file.txt");
        var fileReaderService = new FIleReaderServiceImpl();
        fileReaderService.read(path)
                .subscribe(Util.subscriber());
    }
}
