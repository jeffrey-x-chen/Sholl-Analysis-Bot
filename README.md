# Sholl-Analysis-Bot
 Automated sholl analysis program
The purpose of this program is to automatically perform Sholl Analysis on the 
coordinates of a traced neuron using the coordinate data of the start and end points 
of individual dendrite segments. The program receives an input of coordinate data 
from a manually traced neuron using the ImageScience plugin on ImageJ Fiji. When the 
user is finished, they can acess the number of dendrite intersections for each 
concentric circle.

The Beta version is an inefficient version that I created first. The .jar file that
will be used to run the program runs the Alpha version.

// INSTRUCTIONS ARE CURRENTLY BEING UPDATED. THE BELOW INSTRUCTIONS ARE NOT ACCURATE
TO THE FUNCTION OF THE PROGRAM. SORRY FOR THE DELAY.

Instructions on how to use this program:
1. To use the program as an app through the .jar file, you have to first download
a JDK (Java Development Kit). To do this, you can download VS code and follow all
prompts to download all necessary extensions and plugins, which should include the
proper JDK. After downloading everything, you can open the .jar file and use it.
2. The second thing you have to do to use the program is to download the ImageScience
plugin on ImageJ Fiji. This is the tracing program that you will use to get the 
segment coordinate data. To download ImageScience, navigate to the top bar of 
ImageJ and click "Help" -> "Update..." -> "Manage Update Sites". Scroll down and
check the box of ImageScience and click "Apply and Close". Then click "Apply 
Changes". ImageScience will download to the program, and the only thing you 
have to do is reset the application to be able to use the plugin.
3. Trace your segments using the ImageScience plugin by opening "NeuronJ" 
under the Plugins menu. You can move vertices if the tracings are not "snapping"
onto the dendrites correctly. When you are completed with your tracings, click
the "Measure Tracings" button. You only need to display vertex measurements.
Copy and paste the vertex data into an empty spreadsheet.
4. On your spreadsheet, select the entire column that contains the segment names.
They should be a sequence of N's with numbers behind them. Each segment should
have multiple vertices. Click "Ctrl + C" to copy the entire column. Paste the
data into the first text field on the program below the caption that says 
"Paste segment names here".
5. On your spreadsheet select the entire column that contains the X coordinate
vertex data. Copy and paste this data into the second text field on the program.
Do the same for the Y coordinate vertex data, but paste it into the third text
field on the program.
6. To determine the coordinates of the neuron's center to measure the dendrites
from, hover your mouse over the center of the neuron's nucleus. The X and Y 
coordinates will display in the NeuronJ window, type this coordinates into
the fourth and fifth text fields, respectively.
7. To show the results of the data, click the red "Analyze" button at the 
bottom of the program window. Then, click the blue "Show Analysis" button.
A window will pop up that displays the amount of dendrite passings per 
concentric circle from the neuron's center. There are 6 circles. The outer
radius of the largest circle is 60, while the inner radius of the smallest
circle is 12.48. Each circle radius increments higher by 11.88.