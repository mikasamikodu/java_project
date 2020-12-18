package innerclasses.controller;
@SuppressWarnings("unused")
public class GreenHouseControls extends Controller {

	private boolean light = false;
	public class LightOn extends Event {
		
		public LightOn(long delayTime) {super(delayTime);}
		
		public void action() {
			//将硬件控制相关代码放这里，手动打开灯
			light = true;
		}
		
		public String toString() {
			return "Light is on";
		}
	}
	public class LightOff extends Event {
		
		public LightOff(long delayTime) {super(delayTime);}
		
		public void action() {
			//将硬件控制相关代码放这里，手动关闭灯
			light = false;
		}
		
		public String toString() {
			return "Light is off";
		}
	} 
	
	private boolean water = false;
	public class WaterOn extends Event {
		
		public WaterOn(long delayTime) {super(delayTime);}
		
		public void action() {
			water = true;
		}
		
		public String toString() {
			return "GreenHouse water is on";
		}
	}
	public class WaterOff extends Event {
		
		public WaterOff(long delayTime) {super(delayTime);}
		
		public void action() {
			water = false;
		}
		
		public String toString() {
			return "GreenHouse water is off";
		}
	}
	
	private String thermostat = "Day";
	public class ThermostatNight extends Event {
		
		public ThermostatNight(long delayTime) {super(delayTime);}
		
		public void action() {
			thermostat = "Night";
		}
		
		public String toString() {
			return "Thermostat on night setting";
		}
	}
	public class ThermostatDay extends Event {
		
		public ThermostatDay(long delayTime) {super(delayTime);}
		
		public void action() {
			thermostat = "Day";
		}
		
		public String toString() {
			return "Thermostat on day setting";
		}
	}
	
	public class Bell extends Event {
		
		public Bell(long delayTime) {super(delayTime);}
		
		public void action() {
			addEvent(new Bell(delayTime));
		}
		
		public String toString() {
			return "Bing!";
		}
	}
	public class Restart extends Event {
		
		private Event[] eventList;
		public Restart(long delayTime, Event[] eventList) {
			super(delayTime);
			this.eventList = eventList;
			for(Event event : eventList) {
				addEvent(event);
			}
		}
		
		public void action() {
			for(Event event : eventList) {
				event.start(); //重新运行每个事件
				addEvent(event);
			}
			start();//重新运行这个事件
			addEvent(this);
		}
		
		public String toString() {
			return "Restarting system";
		}
	}
	
	public static class Terminate extends Event {
		
		public Terminate(long delayTime) {super(delayTime);}
		
		public void action() {
			System.exit(0);
		}
		
		public String toString() {
			return "Terminating";
		}
	}
}
