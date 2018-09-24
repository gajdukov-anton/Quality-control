package com.company;

public class Main {

    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                UrlParser url = new UrlParser(args[0]);
                url.writeResultToFile("link.txt", "brokenLink.txt");
                //url.showResult();
            } catch (IllegalArgumentException e) {
                System.out.print("Illegal argument");
            }
        } else {
            System.out.println("Please enter one website");
        }
    }
}

