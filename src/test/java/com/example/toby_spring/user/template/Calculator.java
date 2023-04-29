package com.example.toby_spring.user.template;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    public int calcSum(String path) throws IOException {
        BufferedReaderCallBack sumCallback = br -> {
            Integer sum = 0;
            String line = null;

            while ((line = br.readLine()) != null) {
                sum += Integer.valueOf(line);
            }

            return sum;
        };

        return fileReadTemplate(path, sumCallback);
    }

    public int calcMultiply(String path) throws IOException {
        BufferedReaderCallBack multiplyCallback = br -> {
            Integer multiply = 1;
            String line = null;

            while ((line = br.readLine()) != null) {
                multiply *= Integer.valueOf(line);
            }

            return multiply;
        };

        return fileReadTemplate(path, multiplyCallback);
    }

    public Integer fileReadTemplate(String filepath, BufferedReaderCallBack callBack) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filepath));
            int ret = callBack.doSomethingWithReader(br);
            return ret;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    throw e;
                }
            }
        }
    }
}
