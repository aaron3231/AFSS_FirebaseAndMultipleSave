package event;

import database.Event_data;
import timer.Timerr;

public abstract class Event {
	public static Event_data event = new Event_data();
	public static Timerr timer;
	public static void setTime(Timerr timer)
	{
		Event.timer=timer;
	}
}
