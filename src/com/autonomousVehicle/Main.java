package com.autonomousVehicle;

public class Main {
    public static void main(String[] args) {
	    String[] inputData = {"5 5", "1 2 N","LMLMLMLMM","3 3 E","MMRMMRMRRM"};

        ControlCenter cc = new ControlCenter(inputData);
        cc.start();
        cc.output();
    }
}


