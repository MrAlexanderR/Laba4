package com.company;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        Masks.kontur(new File(args[0]), new File(args[1]), args[2], args[3]);
    }
}
