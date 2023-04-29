package com.example.toby_spring.user.template;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;

public interface BufferedReaderCallBack {
    Integer doSomethingWithReader(BufferedReader br) throws IOException;
}
