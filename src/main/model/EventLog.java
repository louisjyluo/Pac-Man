package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

//class that logs input events, cited from AlarmSystem.
public class EventLog  implements Iterable<Event> {
    private static EventLog theLog;
    private Collection<Event> events;

    // MODIFIES: this
    // EFFECTS: Prevent external construction. (Singleton Design Pattern).
    private EventLog() {
        events = new ArrayList<Event>();
    }

    // MODIFIES: this
    // EFFECTS: Gets instance of EventLog, creates it if it doesn't already exist.
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }

        return theLog;
    }

    // MODIFIES: this
    // EFFECTS: adds an event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }


    // MODIFIES: this
    // EFFECTS: clears the entire eventLog.
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }


    // MODIFIES: this
    // EFFECTS: overrides the default iterator class
    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
