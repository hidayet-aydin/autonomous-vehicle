package com.autonomousVehicle;

abstract class FieldObject {
    public int id;
    protected int[] coordinate; // X, Y
    protected String rotation;

    FieldObject(int id, String coordinate){
        // this.coordinate = Arrays.copyOfRange(raw, 0, 2);
        this.id = id;
        String[] raw = coordinate.split(" ");
        this.coordinate = new int[]{Integer.parseInt(raw[0]), Integer.parseInt(raw[1])};
        this.rotation = raw[2];
    }
}

abstract class VehicleMonitor extends FieldObject {
    protected boolean isDone = false;
    protected int actionStep = 0;
    protected String[] orientation;

    VehicleMonitor(int id, String coordinate, String orientation) {
        super(id, coordinate);
        this.orientation = orientation.split("");
    }

    public int[] coordinate(){ return coordinate; }
    public String rotation(){ return rotation; }
    public boolean state(){ return isDone; }
    public String target(){ return orientation[actionStep]; }
    public String print(){
        String x = String.valueOf(coordinate[0]);
        String y = String.valueOf(coordinate[1]);
        String r = String.valueOf(rotation);
        return x + " " + y + " " + r;
    }
}

abstract class VehicleControl extends VehicleMonitor {
    VehicleControl(int id, String coordinate, String orientation) {
        super(id, coordinate, orientation);
    }

    public void editCoordinate(int[] coordinate){ this.coordinate = coordinate; }
    public void editRotation(String rotation){ this.rotation = rotation; }
    public void step(){
        this.actionStep++;
        this.isDone = this.actionStep + 1 > this.orientation.length;
//        System.out.println(String.valueOf(id) +": "+ String.valueOf(actionStep) +", "+ String.valueOf(isDone));
    }
}

public class VehicleProcessor extends VehicleControl {
    private final Guidance guidance = new Guidance();

    VehicleProcessor(int id, String coordinate, String orientation) {
        super(id, coordinate, orientation);
    }

    public void run(){
        if (!isDone){
            String target = target();

            if (target.equals("M")){
                int[] endPoint = guidance.endPoint(coordinate(), rotation());
                this.editCoordinate(endPoint);
            } else {
                String newRotation = guidance.newRotation(target, rotation());
                editRotation(newRotation);
            }
        }
        step();
    }
}