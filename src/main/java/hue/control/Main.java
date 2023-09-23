package hue.control;

import java.util.ArrayList;
import javax.swing.JPanel;

import hue.control.GUI.HueGUI;
import hue.control.GUIDevices.ColorLight;
import hue.control.GUIDevices.WhiteLight;

public class Main {
	public static void main(String[] args) throws NullPointerException {
		System.out.println("Starting program!");
		// Look for mac address of d8-32-14-5a-17-45

		// Creates individual lights
		WhiteLight light1 = new WhiteLight(1);
		WhiteLight light2 = new WhiteLight(2);
		WhiteLight light3 = new WhiteLight(3);
		ColorLight light5 = new ColorLight(5);

		// Puts all of the white light panels into one array
		ArrayList<JPanel> whiteLights = new ArrayList<JPanel>();
		whiteLights.add(light1.getPanel());
		whiteLights.add(light2.getPanel());
		whiteLights.add(light3.getPanel());

		// Puts all of the color light panels into another array
		ArrayList<JPanel> colorLights = new ArrayList<JPanel>();
		colorLights.add(light5.getPanel());
		
		// Starts the GUI by passing the light panel arrays to the HueGUI class
		new HueGUI(whiteLights, colorLights);

		/*
		 * When a single light gets changed, only it will change.
		 * 
		 * Commands:
		 * 		setState(boolean state) -- turns the light on or off
		 * 		setColor(Color color) -- changes the color of the light (N/A for white lights)
		 * 		setBrightness(int bri) -- changes the brightness of the light (range of 0 to 100)
		 */

		/* Creates a Command Center.  The place where all of the commands are stored.
		CommandCenter commandCenter = new CommandCenter(url);

		JSONObject systemConfig = commandCenter.getSystemConfig();
		JSONObject lights = (JSONObject) systemConfig.get("lights");
		ArrayList<JSONObject> allTheLights = commandCenter.getAllLights(systemConfig);

		System.out.println("Found " + allTheLights.size() + " lights.");

		for (JSONObject json : allTheLights) {
			System.out.println(json);
		}

		JSONObject light1s = (JSONObject) lights.get("1");
		JSONObject state = (JSONObject) light1s.get("state");
		boolean on = (boolean) state.get("on");

		System.out.println(on);

		/*
		// First we set up our lights factory.
		LightsFactory factory = new LightsFactory("10.180.51.60", "oSpVxc0qDnFRlPKDIkMsPLWAQdE9Y38oa91AOBCg");

		// Then we get a dump of the configuration.
		SystemConfiguration sc = factory.getSystemConfiguration();
		System.out.println(sc.getConfig().getName());

		// And now a map of the lights.
		Map<String, LightsEntry> lights = sc.getLights();

		// Loop through them to see some info.
		for (Map.Entry<String, LightsEntry> entry : lights.entrySet()) {
			String key = entry.getKey();
			LightsEntry value = entry.getValue();

			System.out.println("Light " + key + " - " + value.getName());
		}

		// Set a light
		State light1State = new State();
		light1State.setOn(true);
		// light1State.setHue(150); // ???
		// light1State.setSat(200); // 78.7%
		// light1State.setBri(124); // 48.8%

		// Note, the lights are 1-based, not 0.
		if (factory.setLightState(1, light1State) == false) {
			System.out.println("Hmm...didn't set the light");
		}

		// Scheduling demonstration
		//playWithSchedules(factory);

		System.out.println("Hello World!");
		*/
	}

	/*
	private static void playWithSchedules(LightsFactory factory) {
		{
			System.out.println("Get the current schedule...");
			
			// Get the current schedule
			Map<String, SchedulesEntry> schedules = factory.getSchedules();

			for (Map.Entry<String, SchedulesEntry> entry : schedules.entrySet()) {
				String key = entry.getKey();
				SchedulesEntry value = entry.getValue();

				System.out.println("Schedule " + key + " - \n\tName:" + value.getName() + "\n\tDescription:" + value.getDescription() + "\n\tDate and Time:" + value.getTime() + "\n\tCommand: " + value.getCommand());
			}
		}

		// Now let's schedule light #1 to turn on 1 minute from now

		// First let's turn off light #1
		State light1State = new State();
		light1State.setOn(false);

		if (factory.setLightState(1, light1State) == false) System.out.println("Hmm...didn't set the light for demonstrating scheduling");

		// And now we begin putting together the schedule object

		// First the easy stuff
		SchedulesEntry se = new SchedulesEntry();
		se.setName("Demo schedule whee");
		se.setDescription("Demonstrating a scheduled task");

		// And configure the time to go a minute from now...

		// Hue only works with times in UTC
		DateTime dt = new DateTime(DateTimeZone.UTC);
		// Now add a minute
		DateTime newDT = dt.plusMinutes(1);
		
		// And set the date and time in the format Hue wants
		se.setTime(newDT.toString("YYYY-MM-dd'T'hh:mm:ss"));

		/*
		 * And now set the command we're going to execute, which is going to be
		 * to turn light #1 on to a bright level of 124 and a transition time of
		 * 200
		 */
		/*
		Command command = new Command();
		command.setAddress("/api/newdeveloper/lights/1/state");
		command.setMethod("PUT");

		Body body = new Body();
		body.setOn(true);
		body.setBri(255);
		// According to the documentation, 10 = 1 second, so let's set it to go over a minute
		body.setTransitiontime(10 * 60 * 5);
		
		// And set the color in CIE
		List<Double> cieXY = new ArrayList<Double>();
		cieXY.add(.651);
		cieXY.add(.390);
		body.setXy(cieXY);
		
		
		command.setBody(body);
		se.setCommand(command);

		// And now actually set the schedule
		factory.setSchedule(se);

		// And for yucks, let's run through the schedule list again

		{
			System.out.println("Now getting the new schedule...");
			
			// Get the current schedule
			Map<String, SchedulesEntry> schedules = factory.getSchedules();

			for (Map.Entry<String, SchedulesEntry> entry : schedules.entrySet()) {
				String key = entry.getKey();
				SchedulesEntry value = entry.getValue();

				System.out.println("Schedule " + key + " - \n\tName:" + value.getName() + "\n\tDescription:" + value.getDescription() + "\n\tDate and Time:" + value.getTime() + "\n\tCommand: " + value.getCommand().getBody());
			}
		}	
	}
	*/
}