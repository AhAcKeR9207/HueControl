# HueControl
An API for Philips Hue lights.
Please note that this API is not very beginner friendly!  It will walk you through some things, but it may be hard to understand everything.  If you ever get stuck, feel free to open an issue or email me.
You can also go to https://developers.meethue.com/develop/get-started-2/ for the official guide on how to send requests.

# Setting up
To set up the API, you will need to first go into "src/main/java/hue/control/Constants.java"
Then, you will edit the "ip" and "username" tags with their appropriate values.

To get the IP of your philips hue bridge, you can find it in the app, or ping your broadcast address and then run "arp -a | findstr '00:17:88'"
To get a username for your philips hue bridge, you need to go to your web browser and type in the IP address of the bridge.  Then add "/debug/clip.html" to the end of the url. (EX: 192.168.10.50/debug/clip.html)
Now, put "/api" into the URL box, "{'devicetype':'philipshueapi#myDevice turbo'}" into the Message Body box, and click the button that says "POST" on it while holding down the button on the physical bridge.
Once you do that, you should see a very long string of characters below.  Copy that (without the quotes), and replace "yourUsernameHere" with the thign you copied.
Great!  Now that you have done that, you are ready to add or remove lights.

# Adding a light
To add a light, you need to do a few things.  The first is add a new line around line 20 that you will use to create the light object.  If it is a white light, create a new WhiteLight object (as seen on line 18).
If it is a color light, create a new ColorLight object (as seen on line 19).  When doing this, you may notice the number inside of the parenthesis.  This is the ID of your light.  Right now, this needs to be explicitly defined, however this will be changed in a later build.
To get the id of the light, go back to that debug/clip.html website we were at earlier.  Then, in the url box at the top, type in /api/username, where you replace username with the username you got above.  Now, click the GET button and find the light you want to make.
Once you find the light, the topmost element in that block should be a number.  This number is the id of the light, which is what we will put inside of the parenthesis.  Finally, you will want to add the panel of the light to its respective ArrayList.
To do this, you will see if the light you have is a white light or a color light.  If the light is white, then you will write out something like what is on line 25.  If it is a color light, then look at line 29.

