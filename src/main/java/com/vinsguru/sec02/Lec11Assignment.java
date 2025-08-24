package com.vinsguru.sec02;

import com.vinsguru.sec02.assignment.FileServiceImpl;
import com.vinsguru.sec02.common.Util;

public class Lec11Assignment {

    public static void main(String args[]){

        var fileService = new FileServiceImpl();
        fileService.write("file.txt","this is a file")
                .subscribe(Util.subscriber());
        System.out.println("Hello");


        fileService.read("file.txt")
                .subscribe(Util.subscriber());

        fileService.delete("file.txt")
                .subscribe(Util.subscriber());
    }
}
