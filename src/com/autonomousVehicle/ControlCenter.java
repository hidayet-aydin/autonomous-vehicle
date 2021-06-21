package com.autonomousVehicle;

import java.util.ArrayList;

public class ControlCenter{
    public ArrayList<VehicleProcessor> vehicles = new ArrayList<>();
    public int[] field; // X, Y
    public boolean isSimulationDone = true;

    ControlCenter(String[] rawData){
        String[] strField = rawData[0].split(" ");
        field = new int[]{ Integer.parseInt(strField[0]), Integer.parseInt(strField[0])};
        for(int i = 1; i < rawData.length; i += 2) {
            vehicles.add( new VehicleProcessor(
                            (i+1)/2,
                            rawData[i],
                            rawData[i+1],
                            field)
            );
        }
    }

    private void stateVehicles(){
        isSimulationDone = false;
        vehicles.forEach(vehicle -> isSimulationDone = (isSimulationDone || !vehicle.state()));
    }

    public void start() {
        while (isSimulationDone) {
            vehicles.forEach(vehicle -> vehicle.run());
            stateVehicles();
        }
    }

    public void output(){
        System.out.print("Results:\n{\"");
        vehicles.forEach(vehicle -> System.out.print(vehicle.print()+"\",\""));
        System.out.print("\b\b}\n");
    }

}
