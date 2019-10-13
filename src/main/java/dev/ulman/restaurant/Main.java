package dev.ulman.restaurant;

import dev.ulman.restaurant.api.CLParser;

public class Main {
    public static void main(String[] args) {

        CLParser parser = new CLParser();
        parser.argsParser(args);
    }
}
