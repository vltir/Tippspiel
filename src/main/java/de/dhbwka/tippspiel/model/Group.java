package de.dhbwka.tippspiel.model;

public class Group {

    private String groupName;
    private int groupOrderID;
    private int groupID;

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupOrderID(Integer groupOrderID) {
        this.groupOrderID = groupOrderID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getGroupOrderID() {
        return groupOrderID;
    }

    public int getGroupID() {
        return groupID;
    }
}
