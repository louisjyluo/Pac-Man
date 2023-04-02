package model;

import java.util.Calendar;
import java.util.Date;

//Class that creates an Event, cited from AlarmSystem.
public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;

    // MODIFIES: this
    // EFFECTS: Creates an event with the given description and the current date/time stamp.
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    public Date getDate() {
        return dateLogged;
    }

    public String getDescription() {
        return description;
    }

    // MODIFIES: this
    // EFFECTS: Overrides the default equals method for classes.
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }


        if (other.getClass() != this.getClass()) {
            return false;
        }


        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged)
                && this.description.equals(otherEvent.description));
    }


    // MODIFIES: this
    // EFFECTS: Creates a hashcode for an event, overrides the default hashcode method
    @Override
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }


    // MODIFIES: this
    // EFFECTS: Turns the event into Strings, overrides the default toString method
    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}

