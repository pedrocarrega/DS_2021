package examples;

import static i18n.Messages.DEVICE_RUNNING;
import static i18n.Messages.MEDICINE;
import static i18n.Messages.DANGER;

import java.util.Timer;
import java.util.TimerTask;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;

import i18n.I18N;

public class AlertZirk {
	
	private Bezirk bezirk;

    public AlertZirk() {
        BezirkMiddleware.initialize();
        bezirk = BezirkMiddleware.registerZirk("Alert Zirk");
        System.err.println("Got Bezirk instance");
    }
    
    public void sendAlertUpdate() {
    	//produces some  values since this is a mock
        final AlertEvent alertEvent = new AlertEvent();
        alertEvent.setAlert(I18N.getString(DANGER));
        bezirk.sendEvent(alertEvent);
        System.err.println("Published alert event: " + alertEvent.toString());
    }
    
    public void sendPeriodiclyAlertUpdate(){
        new Timer().scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                AlertEvent alertEvent =  new AlertEvent(); 
                alertEvent.setAlert(I18N.getString(MEDICINE));
                bezirk.sendEvent(alertEvent);
            }
        }, 2000, 2000);
    }
   
	public static void main(String args[]){
        AlertZirk alertZirk = new AlertZirk();
        System.err.println("This product has an Alert");
        
        System.err.println(I18N.getString(DEVICE_RUNNING, "Alert"));
        alertZirk.sendAlertUpdate();
        alertZirk.sendPeriodiclyAlertUpdate();
    }
}
