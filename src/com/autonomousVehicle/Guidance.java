package com.autonomousVehicle;

abstract class Compute  {
    abstract String newRotation(String target, String rotation);
    abstract int[] endPoint(int[] coordinate, String rotation);
}

public class Guidance extends Compute  {

    private String[] rotationLookup(String rotation){
        final String[] N = {"W", "E"};
        final String[] W = {"S", "N"};
        final String[] S = {"E", "W"};
        final String[] E = {"N", "S"};
        String[] newVal = new String[0];
        switch (rotation){
            case "N":
                newVal = N;
                break;
            case "W":
                newVal = W;
                break;
            case "S":
                newVal = S;
                break;
            case "E":
                newVal = E;
                break;
        }
        return newVal;
    }

    public String newRotation(String target, String rotation){
        return switch (target) {
            case "L" -> rotationLookup(rotation)[0];
            case "R" -> rotationLookup(rotation)[1];
            default -> rotation;
        };
    }

    public int[] endPoint(int[] coordinate, String rotation){
        int x = coordinate[0];
        int y = coordinate[1];
        switch (rotation) {
            case "W" -> x -= 1;
            case "S" -> y -= 1;
            case "E" -> x += 1;
            case "N" -> y += 1;
        }
        return new int[]{x, y};
    }
}
