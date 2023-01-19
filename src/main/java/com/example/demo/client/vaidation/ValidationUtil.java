package com.example.demo.client.vaidation;

import org.springframework.util.StringUtils;

public class ValidationUtil {
    public static boolean validateIsPDF(String fileName) {
        if(StringUtils.endsWithIgnoreCase(fileName,"pdf")) {
            System.out.println("File name"+fileName);
            return true;
        }else
            return false;

    }
}
