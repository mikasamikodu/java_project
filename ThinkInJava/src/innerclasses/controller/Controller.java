package innerclasses.controller;

import java.util.*;

public class Controller {

	private List<Event> eventList = new ArrayList<Event>();
	
	public void addEvent(Event event) {
		eventList.add(event);
	}
	
	public void run() {
		while(eventList.size() > 0) {
			for(Event event : new ArrayList<Event>(eventList)) {
				if(event.ready()) {
					System.out.println(event);
					event.start();
					eventList.remove(event);
				}
			}
		}
	}
}
