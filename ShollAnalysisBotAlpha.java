import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;

public class ShollAnalysisBotAlpha extends JFrame{
    // Array List to store variable circle radius values
    ArrayList <Double> circleRadii = new ArrayList <Double>();

    // Array to store the number of dendrite crossings for each radius
    int [] circleCounter;

    // Coordinates of the center of the circle
    double circleCenterXValue;
    double circleCenterYValue;
    // Concentric circle parameters
    double circleQuantityValue;
    double innerRadiusValue;
    double outerRadiusValue;

    int textFieldWidth = 1; // Setting width of text fields

    private JFrame inputWindow; // Creating main window
    private JTextField segmentNames; // Creating text field to paste in the segment names
    private JTextField xCoordinates; // Creating text field to paste in the X coordinates for the vertices
    private JTextField yCoordinates; // Creating text field to paste in the Y coordinates for the vertices
    private JTextField xCircle; // Creating text field to paste in the X coordinates of the circle center
    private JTextField yCircle; // Creating text field to paste in the Y coordinates of the circle center
    private JButton runAnalysis; // Creating button to start the analysis
    private JButton showAnalysis; // Creating button to show the analysis
    private JButton setParameters; // Creating a button to set the circle properties of Analysis
    private JPanel mainPanel; // Main panel that components will be displayed on

    private JTextField circleQuantity; // Text field to enter amount of concentric circles in
    private JTextField innerRadius; // Text field to enter the inner radius in
    private JTextField outerRadius; // Text field to enter the outer radius in

    int fontSize = 13; // Setting font size
    Font defaultFont = new Font("Courier", Font.BOLD, fontSize); // Creating default font

    public ShollAnalysisBotAlpha () {
        inputWindow = new JFrame();
        int inputWindowWidth = 700;
        int inputWindowHeight = 500;
        inputWindow.setSize(inputWindowWidth, inputWindowHeight);
        inputWindow.setTitle("Main Input Window");
        inputWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputWindow.setLocationRelativeTo(null);

        // Instructions for segment input
        JLabel segmentInstructions = new JLabel();
        segmentInstructions.setText("<html>" + "Paste segment names here: " + "</html>");
        segmentInstructions.setFont(defaultFont);

        // Text field to paste segment names
        segmentNames = new JTextField(textFieldWidth);
        segmentNames.setFont(defaultFont);

        // Instructions for X, Y coordinate input
        JLabel coordinateInstructions = new JLabel();
        coordinateInstructions.setText("<html>" + "Paste the X coordinates in the top box, and the Y coordinates in the bottom"
            + "</html>");
        coordinateInstructions.setFont(defaultFont);

        // Text field to paste X coordinates
        xCoordinates = new JTextField(textFieldWidth);
        xCoordinates.setFont(defaultFont);

        // Text field to paste Y coordinates
        yCoordinates = new JTextField(textFieldWidth);
        yCoordinates.setFont(defaultFont);

        // Instructions for X, Y coordinates of circle
        JLabel circleInstructions = new JLabel();
        circleInstructions.setText("<html>" + "Type the X coordinates of the circle's center in the top box and the Y coordinates in the bottom"
            + "</html>");
        circleInstructions.setFont(defaultFont);

        // Text field to paste X coordinates of circle center
        xCircle = new JTextField(textFieldWidth);
        xCircle.setFont(defaultFont);


        // Text field to paste Y coordinates of circle center
        yCircle = new JTextField(textFieldWidth);
        yCircle.setFont(defaultFont);
        
        // Formatting button to start analysis
        runAnalysis = new JButton();
        runAnalysis.setBounds(0, 400, 500, 50);
        runAnalysis.setFont(defaultFont);
        runAnalysis.setForeground(Color.RED);
        runAnalysis.setText("<html>" + "Analyze" + "</html>");
        inputWindow.getRootPane().setDefaultButton(runAnalysis);

        // Coding analysis/ function of button
        runAnalysis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent a) {
                storeCircleCenter();
                analysisMethod();
            }
            }
        );
        
        showAnalysis = new JButton();
        showAnalysis.setBounds(500, 400, 500, 50);
        showAnalysis.setForeground(Color.BLUE);
        showAnalysis.setText("<html>" + "Show analysis" + "</html>");
        showAnalysis.setFont(defaultFont);

        showAnalysis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent a) {
                // Formatting popup window that will show analysis
                JFrame popupAnalysis = new JFrame();
                int popupAnalysisWidth = 400;
                int popupAnalysisHeight = 300;
                popupAnalysis.setSize(popupAnalysisWidth, popupAnalysisHeight);
                popupAnalysis.setTitle("Analysis Results");
                
                // Formatting panel
                JPanel analysisPanel = new JPanel();
                analysisPanel.setLayout(new GridLayout(1, 1));
                analysisPanel.setPreferredSize(new Dimension (300, 200));

                // Text to display results in popup window
                JLabel circleCountResults = new JLabel();
                circleCountResults.setFont(defaultFont);
                String countResultsString = "Circle 1: " + circleCounter[0];
                for (int i = 1; i < circleQuantityValue; i++) {
                    String newString = " Circle " + (i+1) + ": "+ circleCounter[i];
                    countResultsString += newString;
                }
                String finalCountResultsString = "<html>" + countResultsString + "</html>";
                circleCountResults.setText(finalCountResultsString);

                analysisPanel.add(circleCountResults);
                JScrollPane scrollAnalysis = new JScrollPane(analysisPanel);
                scrollAnalysis.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrollAnalysis.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                popupAnalysis.getContentPane().add(scrollAnalysis);
                popupAnalysis.setVisible(true);
                scrollAnalysis.setVisible(true);

                
                // Resets the values of text fields after completing analysis
                segmentNames.setText("");
                xCoordinates.setText("");
                yCoordinates.setText("");
                xCircle.setText("");
                yCircle.setText("");
                

                circleCenterXValue = 0;
                circleCenterYValue = 0;
            }
        }
        );

        setParameters = new JButton();
        setParameters.setForeground(Color.GREEN);
        setParameters.setText("<html>" + "Set Parameters" + "</html>");
        setParameters.setFont(defaultFont);
        setParameters.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                // Formatting popup window that values will be entered into
                JFrame setParametersFrame = new JFrame("Setting Parameters");
                int setParametersFrameWidth = 500;
                int setParametersFrameHeight = 300;
                setParametersFrame.setSize(setParametersFrameWidth, setParametersFrameHeight);

                // Instruction text to enter the number of circles
                JLabel circleQuantityInstructions = new JLabel();
                circleQuantityInstructions.setText("<html>" + "Enter the amount of circles you want the neuron to be analyzed across below"
                 + "</html>");
                circleQuantityInstructions.setFont(defaultFont);

                // Entering the number of circles
                circleQuantity = new JTextField(textFieldWidth);
                circleQuantity.setFont(defaultFont);

                // Instruction text to enter the inner radius
                JLabel innerRadiusInstructions = new JLabel();
                innerRadiusInstructions.setText("<html>" + "Enter the inner radius of concentric circles below" + "</html>");
                innerRadiusInstructions.setFont(defaultFont);

                // Entering the inner circle radius
                innerRadius = new JTextField(textFieldWidth);
                innerRadius.setFont(defaultFont);

                // Instruction text to enter the outer radius
                JLabel outerRadiusInstructions = new JLabel();
                outerRadiusInstructions.setText("<html>" + "Enter the outer radius of concentric circles below" + "</html>");
                outerRadiusInstructions.setFont(defaultFont);

                // Entering the inner circle radius
                outerRadius = new JTextField(textFieldWidth);
                outerRadius.setFont(defaultFont);

                // Button to store the values
                JButton storeCircleValueButton = new JButton();
                storeCircleValueButton.setText("<html>" + "Store Values" + "</html>");
                storeCircleValueButton.setFont(defaultFont);
                setParametersFrame.getRootPane().setDefaultButton(storeCircleValueButton);

                storeCircleValueButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed (ActionEvent a) {
                        storeConcentricValues();
                        setCircleRadii();
                        setParametersFrame.dispose();
                    }
                });

                JPanel parameterPanel = new JPanel();
                parameterPanel.setLayout(new GridLayout(7, 0));
                parameterPanel.setPreferredSize(new Dimension(setParametersFrameWidth-50, 
                    setParametersFrameHeight-50));
                //KEEP THIS ORDER THE SAME, OR ELSE COMPONENTS WILL BE FLIPPED ON PANEL
                parameterPanel.add(circleQuantityInstructions); // 1
                parameterPanel.add(circleQuantity); // 2
                parameterPanel.add(innerRadiusInstructions); // 3
                parameterPanel.add(innerRadius); // 4
                parameterPanel.add(outerRadiusInstructions); // 5
                parameterPanel.add(outerRadius); // 6
                parameterPanel.add(storeCircleValueButton); // 7

                JScrollPane scrollParameter = new JScrollPane(parameterPanel);
                scrollParameter.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrollParameter.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                
                setParametersFrame.getContentPane().add(scrollParameter);
                scrollParameter.setVisible(true);
                setParametersFrame.setVisible(true);
            }
        }
        );

        
        mainPanel = new JPanel();
        // Setting layout of the panel, row number needs to equal component number to make the window properly formatted
        mainPanel.setLayout(new GridLayout(11, 0));
        mainPanel.setPreferredSize(new Dimension (inputWindowWidth - 50, inputWindowHeight-60));

        //KEEP THIS ORDER THE SAME, OR ELSE COMPONENTS WILL BE FLIPPED ON PANEL
        mainPanel.add(segmentInstructions); // 1
        mainPanel.add(segmentNames); // 2
        mainPanel.add(coordinateInstructions); // 3
        mainPanel.add(xCoordinates); // 4
        mainPanel.add(yCoordinates); // 5
        mainPanel.add(circleInstructions); // 6
        mainPanel.add(xCircle); // 7
        mainPanel.add(yCircle); // 8
        mainPanel.add(runAnalysis); // 9
        mainPanel.add(showAnalysis); // 10
        mainPanel.add(setParameters); // 11

        JScrollPane scroll = new JScrollPane(mainPanel);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        inputWindow.getContentPane().add(scroll);
        scroll.setVisible(true);
        inputWindow.setVisible(true);
    }

    /**
     * This method stores the concentric circle values (number of circles, inner radius, outer radius) inputted into the 
     * window's text fields.
     */
    public void storeConcentricValues () {
        circleQuantityValue = Integer.parseInt(circleQuantity.getText());
        innerRadiusValue = Double.parseDouble(innerRadius.getText());
        outerRadiusValue = Double.parseDouble(outerRadius.getText());
    }

    /**
     * This method stores the inputted values of the circle's center into the variables of circleCenterXValue and circleCenterYValue
     */
    public void storeCircleCenter () {
        circleCenterXValue = Double.parseDouble(xCircle.getText());
        circleCenterYValue = Double.parseDouble(yCircle.getText());
    }

    /**
     * This method adds the radii of each concentric circle to the Array List of radii (circleRadii) by calculating them from the outer 
     * radius, inner radius, and amount of circles.
     */
    public void setCircleRadii () {
        circleRadii.clear();
        double radiusIncrement = (outerRadiusValue - innerRadiusValue)/(circleQuantityValue-1);
        double initialRadius = innerRadiusValue;
        circleRadii.add(initialRadius);
        for (int i = 1; i < circleQuantityValue-1; i++) {
            double currentRadius = initialRadius + radiusIncrement;
            currentRadius = Math.floor(currentRadius * 100) / 100;
            circleRadii.add(currentRadius);
            initialRadius = currentRadius;
        }
        circleRadii.add(outerRadiusValue);
    }

    /**
     * This is the main method to analyze the data by calculating the radius of individual segments
     * and seeing which circles they cross
     */
    public void analysisMethod () {
        System.out.println(circleCenterXValue + "\n" + circleCenterYValue);
        System.out.println(circleQuantityValue);
        
        for (int i = 0; i < circleRadii.size(); i++) {
            System.out.println(circleRadii.get(i));
        }
        

        // Creating String array of segment names
        String segmentNamesTempOrigin = (String) segmentNames.getText();
        String [] segmentNamesTemp = (segmentNamesTempOrigin).split(" ");

        // Creating integer array of X coordinates from inputted String type
        String [] xCoordinatesTempOrigin = (xCoordinates.getText()).split(" ");
        double [] xCoordinatesTemp = new double[xCoordinatesTempOrigin.length];
        for (int i = 0; i < xCoordinatesTempOrigin.length; i++) {
            xCoordinatesTemp[i] = Double.parseDouble(xCoordinatesTempOrigin[i]);
        }

        // Creating integer array of Y coordinates from inputted String type
        String[] yCoordinatesTempOrigin = (yCoordinates.getText()).split(" ");
        double [] yCoordinatesTemp = new double[yCoordinatesTempOrigin.length];
        for (int i = 0; i < yCoordinatesTempOrigin.length; i++) {
            yCoordinatesTemp[i] = Double.parseDouble(yCoordinatesTempOrigin[i]);
        }

        // Creating circle counter array with the amount of inputted circles
        circleCounter = new int [(int) circleQuantityValue];

        // Scanning for first and last vertex in a segment
        int segmentBeginningIndex = 0;
        int segmentEndingIndex = 0;
        double segmentStartingX = 0;
        double segmentStartingY = 0;
        double segmentEndingX = 0;
        double segmentEndingY = 0;
        for (int i = 0; i < segmentNamesTemp.length-1; i++) {
            System.out.println ("Counting at " + i);            
            segmentStartingX = xCoordinatesTemp[segmentBeginningIndex]; // Matching the coordinates from the indexes
            segmentStartingY = yCoordinatesTemp[segmentBeginningIndex];
            if (!segmentNamesTemp[i].equalsIgnoreCase(segmentNamesTemp[i+1])) {
                System.out.println("Ran");
                segmentEndingIndex = i;
                segmentEndingX = xCoordinatesTemp[segmentEndingIndex];
                segmentEndingY = yCoordinatesTemp[segmentEndingIndex];
                // Finding the radial distance of the start point
                double startRadius = Math.pow(Math.pow(segmentStartingX-circleCenterXValue, 2) +
                Math.pow(segmentStartingY - circleCenterYValue, 2), 0.5);
                // Finding the radial distance of the end point
                double endRadius = Math.pow(Math.pow(segmentEndingX-circleCenterXValue, 2) +
                Math.pow(segmentEndingY - circleCenterYValue, 2), 0.5);

                // Adding dendrite intersections to their respective circles
                for (int z = 0; z < circleQuantityValue; z++) {
                    System.out.println("Ran 2 " + z + "\n" + circleRadii.get(z) + "\n" + startRadius + "\n" + endRadius);
                    if (startRadius <= circleRadii.get(z) && endRadius >= circleRadii.get(z)) {
                        circleCounter[z]++;
                        System.out.println(segmentEndingX + "a" + z + "a" + circleCounter[z]);
                    }
                }
                segmentBeginningIndex = i+1;
            } else if ((i+1) == segmentNamesTemp.length-1) { // To analyze the last segment
                // System.out.println("Ran");
                segmentEndingIndex = i+1;
                segmentEndingX = xCoordinatesTemp[segmentEndingIndex];
                segmentEndingY = yCoordinatesTemp[segmentEndingIndex];
                // Finding the radial distance of the start point
                double startRadius = Math.pow(Math.pow(segmentStartingX-circleCenterXValue, 2) +
                Math.pow(segmentStartingY - circleCenterYValue, 2), 0.5);
                // Finding the radial distance of the end point
                double endRadius = Math.pow(Math.pow(segmentEndingX-circleCenterXValue, 2) +
                Math.pow(segmentEndingY - circleCenterYValue, 2), 0.5);
                for (int x = 0; x < circleQuantityValue; x++) {
                    System.out.println("Ran 2" + x + "\n" + circleRadii.get(x) + "\n" + startRadius + "\n" + endRadius);
                    if (startRadius <= circleRadii.get(x) && endRadius >= circleRadii.get(x)) {
                        circleCounter[x]++;
                        System.out.println(segmentEndingX + "a" + x + "a" + circleCounter[x]);                    
                    }
                }
                segmentBeginningIndex = i+1;
            }
        }
    } 

    public static void main (String[] args) {
        ShollAnalysisBotAlpha app = new ShollAnalysisBotAlpha();
        app.setVisible(true);
    }
}