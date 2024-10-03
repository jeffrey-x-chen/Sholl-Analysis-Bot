import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;

public class ShollAnalysisBotAlpha extends JFrame{
    // Integer variables to store the number of dendrite crossings for each concentric circle
    int circle1Counter;
    int circle2Counter;
    int circle3Counter;
    int circle4Counter;
    int circle5Counter;
    // Coordinates of the center of the circle
    int circleCenterXValue;
    int circleCenterYValue;
    // Concentric circle parameters
    int circleQuantityValue;
    int innerRadiusValue;
    int outerRadiusValue;

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
                // Setting the coordinates of the circle center
                int circleCenterXValue = Integer.parseInt(xCircle.getText());
                int circleCenterYValue = Integer.parseInt(yCircle.getText());

                // Creating String array of segment names
                String segmentNamesTempOrigin = (String) segmentNames.getText();
                String [] segmentNamesTemp = (segmentNamesTempOrigin).split(" ");
                
                // Creating integer array of X coordinates from inputted String type
                String [] xCoordinatesTempOrigin = (xCoordinates.getText()).split(" ");
                int [] xCoordinatesTemp = new int[xCoordinatesTempOrigin.length];
                for (int i = 0; i < xCoordinatesTempOrigin.length; i++) {
                    xCoordinatesTemp[i] = Integer.parseInt(xCoordinatesTempOrigin[i]);
                }

                // Creating integer array of Y coordinates from inputted String type
                String[] yCoordinatesTempOrigin = (yCoordinates.getText()).split(" ");
                int [] yCoordinatesTemp = new int[yCoordinatesTempOrigin.length];
                for (int i = 0; i < yCoordinatesTempOrigin.length; i++) {
                    yCoordinatesTemp[i] = Integer.parseInt(yCoordinatesTempOrigin[i]);
                }

                // Scanning for first and last vertex in a segment
                int segmentBeginningIndex = 0;
                int segmentEndingIndex = 0;
                int segmentStartingX = 0;
                int segmentStartingY = 0;
                int segmentEndingX = 0;
                int segmentEndingY = 0;
                for (int i = 0; i < segmentNamesTemp.length-1; i++) {
                    segmentStartingX = xCoordinatesTemp[segmentBeginningIndex]; // Matching the coordinates from the indexes
                    segmentStartingY = yCoordinatesTemp[segmentBeginningIndex];
                    if (!segmentNamesTemp[i].equalsIgnoreCase(segmentNamesTemp[i+1])) {
                        segmentEndingIndex = i;
                        segmentEndingX = xCoordinatesTemp[segmentEndingIndex];
                        segmentEndingY = yCoordinatesTemp[segmentEndingIndex];
                        // Finding the radial distance of the start point
                        double startRadius = Math.pow(Math.pow(segmentStartingX-circleCenterXValue, 2) +
                                            Math.pow(segmentStartingY - circleCenterYValue, 2), 0.5);
                        // Finding the radial distance of the end point
                        double endRadius = Math.pow(Math.pow(segmentEndingX-circleCenterXValue, 2) +
                                            Math.pow(segmentEndingY - circleCenterYValue, 2), 0.5);
                        if (startRadius <= 12.48 && endRadius > 12.48) {
                            circle1Counter ++;
                        } 
                        if (startRadius <= 24.36 && endRadius > 24.36) {
                            circle2Counter ++;
                        } 
                        if (startRadius <= 36.24 && endRadius > 36.24) {
                            circle3Counter ++;
                        }                         
                        if (startRadius <= 48.12 && endRadius > 48.12) {
                            circle4Counter ++;
                        }                        
                        if (startRadius <= 60 && endRadius > 60) {
                            circle5Counter ++;
                        } 
                        segmentBeginningIndex = i+1;
                    } else if ((i+1) == segmentNamesTemp.length-1) { // To analyze the last segment
                        segmentEndingIndex = i+1;
                        segmentEndingX = xCoordinatesTemp[segmentEndingIndex];
                        segmentEndingY = yCoordinatesTemp[segmentEndingIndex];
                        // Finding the radial distance of the start point
                        double startRadius = Math.pow(Math.pow(segmentStartingX-circleCenterXValue, 2) +
                                            Math.pow(segmentStartingY - circleCenterYValue, 2), 0.5);
                        // Finding the radial distance of the end point
                        double endRadius = Math.pow(Math.pow(segmentEndingX-circleCenterXValue, 2) +
                                            Math.pow(segmentEndingY - circleCenterYValue, 2), 0.5);
                        if (startRadius <= 12.48 && endRadius > 12.48) {
                            circle1Counter ++;
                        } 
                        if (startRadius <= 24.36 && endRadius > 24.36) {
                            circle2Counter ++;
                        } 
                        if (startRadius <= 36.24 && endRadius > 36.24) {
                            circle3Counter ++;
                        }                         
                        if (startRadius <= 48.12 && endRadius > 48.12) {
                            circle4Counter ++;
                        }                        
                        if (startRadius <= 60 && endRadius > 60) {
                            circle5Counter ++;
                        } 
                        segmentBeginningIndex = i+1;
                    }
                                                     
                }
                System.out.println(circle1Counter);      
                System.out.println(circle5Counter);
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
                circleCountResults.setText("<html>" + "Circle 1: " + circle1Counter + "\n" +
                    "Circle 2: " + circle2Counter + "\n" +
                    "Circle 3: " + circle3Counter + "\n" +
                    "Circle 4: " + circle4Counter + "\n" +
                    "Circle 5: " + circle5Counter + "\n" );

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

                circle1Counter = 0;
                circle2Counter = 0;
                circle3Counter = 0;
                circle4Counter = 0;
                circle5Counter = 0;
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
                int setParametersFrameHeight = 500;
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
        mainPanel.setPreferredSize(new Dimension (inputWindowWidth - 50, inputWindowHeight-50));

        //KEEP THIS ORDER THE SAME, OR ELSE COMPONENTS WILL BE FLIPPED ON PANEL
        mainPanel.add(segmentInstructions); // 1
        mainPanel.add(segmentNames); // 2
        mainPanel.add(coordinateInstructions); // 3
        mainPanel.add(xCoordinates); // 4
        mainPanel.add(yCoordinates); // 5
        mainPanel.add(runAnalysis); // 6
        mainPanel.add(circleInstructions); // 7
        mainPanel.add(xCircle); // 8
        mainPanel.add(yCircle); // 9
        mainPanel.add(showAnalysis); // 10
        mainPanel.add(setParameters); // 11

        JScrollPane scroll = new JScrollPane(mainPanel);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        inputWindow.getContentPane().add(scroll);
        scroll.setVisible(true);
        inputWindow.setVisible(true);
    }

    public void storeConcentricValues () {
        circleQuantityValue = Integer.parseInt(circleQuantity.getText());
        innerRadiusValue = Integer.parseInt(innerRadius.getText());
        outerRadiusValue = Integer.parseInt(outerRadius.getText());
    }

    public static void main (String[] args) {
        ShollAnalysisBotAlpha app = new ShollAnalysisBotAlpha();
        app.setVisible(true);
    }
}