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
3. Set the dimensions of your concentric circles that you will be analyzing 
across by clicking the "Set parameters" button at the bottom of the main input
window. Input the number of circles that you want to be analyzed across and the
inner radius of the concentric circles and the outer radius of the concentric 
circles.
4. Trace your segments using the ImageScience plugin by opening "NeuronJ" 
under the Plugins menu. You can move vertices if the tracings are not "snapping"
onto the dendrites correctly. When you are completed with your tracings, click
the "Measure Tracings" button. You only need to display vertex measurements. De-
select "Calibrate measurements" in the "Measure Tracings" window. This makes the
coordinates of the tracings in units of microns when the Sholl analysis is meant
to be done across concentric circles of radii in units of pixels. It is possible
to keep the coordinates in units of microns but you will have to change the dim-
ensions of the concentric circle radii that you put in to the "Setting parameters"
window to convert the radii in pixels to the same radii in microns. Copy and 
paste the vertex data into an empty spreadsheet.
5. On your spreadsheet, select the entire column that contains the segment names.
They should be a sequence of N's with numbers behind them. Each segment should
have multiple vertices. Copy the entire column. Paste the
data into the first text field on the program below the caption that says 
"Paste segment names here".
6. On your spreadsheet select the entire column that contains the X coordinate
vertex data. Copy and paste this data into the second text field on the program.
Do the same for the Y coordinate vertex data, but paste it into the third text
field on the program.
7. To determine the coordinates of the neuron's center to measure the dendrites
from, hover your mouse over the center of the neuron's nucleus. The X and Y 
coordinates will display in the NeuronJ window, type this coordinates into
the fourth and fifth text fields, respectively.
8. To show the results of the data, click the red "Analyze" button at the 
bottom of the program window (or simply click the "Enter" key when you are
in the window, "Analyze" is set as the default button). Then, click the blue 
"Show Analysis" button. A window will pop up that displays the amount of dendrite 
passings per concentric circle from the neuron's center. All the data in the 
text fields will automatically delete after the "Show Analysis" button is
clicked.
9. If you save the tracing data in the .ndf file format, you can load up the 
measurements for the image later if you need to re-analyze the data and set 
different parameters within the program. This allows the same Sholl analysis 
data to be done across many different parameters.