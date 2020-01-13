package models;

import java.util.ArrayList;

public class Squad {
    private String squadName;
    private String cause;
    private int maxSize;
    private boolean availability;
    private static ArrayList<Squad>allSquads=new ArrayList<>();
    public Squad(String squadName, String cause, int maxSize, boolean availability){
        this.squadName = squadName;
        this.cause=cause;
        this.maxSize=maxSize;
        this.availability=availability;
        allSquads.add(this);

    }
    public String getSquadName(){return squadName;}
    public String getCause(){return cause;}

    public int getMaxSize() {
        return maxSize;
    }

    public boolean isAvailability() {
        return availability;
    }
    public static ArrayList<Squad>getAllSquads(){return allSquads;}
}
