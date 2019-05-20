package com.alkfejl.mindenkepp.laugh.web.service;

import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Date;

@Service
public class LoggerService {
    private PrintStream stream;
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String RED = "\033[0;31m";     // RED
    public static final String RESET = "\033[0m";  // Text Reset

    public LoggerService(){
        stream = System.out;
    }

    public void d(Object sender, String msg) {
        stream.println(sender.getClass() + "(" +new Date().toString() + "): " + msg);
    }

    public void w(Object sender, String msg) {
        stream.println(YELLOW + sender.getClass() + "(" +new Date().toString() + "): " + msg + RESET);
    }

    public void e(Object sender, String msg) {
        stream.println(RED + sender.getClass() + "(" +new Date().toString() + "): " + msg + RESET);
    }

    public PrintStream getStream() {
        return stream;
    }

    public void setStream(PrintStream stream) {
        this.stream = stream;
    }
}
