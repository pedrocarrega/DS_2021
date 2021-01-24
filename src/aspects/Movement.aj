package aspects;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet.EventReceiver;

import core.Client;
import events.MovementEvent;
import i18n.I18N;
import i18n.Messages;

public aspect Movement {
	
	private static LocalTime Client.movePatternStartTime;
	private static LocalTime Client.movePatternEndTime;
	private static String Client.movePatternLocation;
	
	
	after(): execution(* core.Client.inicializeMenu()){
		core.Client.menu.put(I18N.getString(Messages.MOVEMENT_OPTION), (i) -> createPattern());
	}
	
	private String createPattern() {
		Scanner sc = new Scanner(System.in);
		System.out.println(I18N.getString(Messages.START_TIME));
		String startTime = sc.nextLine();
		System.out.println(I18N.getString(Messages.END_TIME));
		String endTime = sc.nextLine();
		
		try {
			Client.movePatternStartTime = LocalTime.parse(startTime);
		Client.movePatternEndTime = LocalTime.parse(endTime);
		} catch(DateTimeParseException e) {
			sc.close();
			return I18N.getString(Messages.MOVEMENT_PATTERN_FAILED) + "\n";
		}

		System.out.println(I18N.getString(Messages.LOCATION) + "\n");
		Client.movePatternLocation = sc.nextLine();
		
		sc.close();
		
		return I18N.getString(Messages.MOVEMENT_PATTERN_CREATED) + "\n";
	}
	
	after(List<Class<? extends Event>> events): initialization(java.util.ArrayList+.new(..)) && args(events){
		events.add(MovementEvent.class);
	}
	
	after(EventReceiver event) : call(* com.bezirk.middleware.messages.EventSet.setEventReceiver(EventReceiver)) && target(event){
		if (event instanceof MovementEvent) {
			MovementEvent temp = (MovementEvent) event;
			if(temp.getTime().isBefore(Client.movePatternStartTime) && temp.getTime().isAfter(Client.movePatternEndTime)) {
				System.out.println(I18N.getString(Messages.MOVEMENT_ALERT) + temp.toString());
			}
		}
	}
}