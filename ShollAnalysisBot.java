import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class ShollAnalysisBot extends JFrame {
    // Integer variables to store the number of dendrite crossings for each concentric circle
    int circle1Counter;
    int circle2Counter;
    int circle3Counter;
    int circle4Counter;
    int circle5Counter;
    // Coordinates of the center of the circle
    int circleCenterXValue;
    int circleCenterYValue;
    // Array list that stores the names of the segments
    ArrayList <String> segmentNames = new ArrayList<String>();

    private JFrame InputWindow; // First input window to type in coordinates
    private JTextField segmentName; // Text field to write in the segment name/ID
    private JSpinner startCoordinateX; // Text field to write in the segment's starting point's X value
    private JSpinner startCoordinateY; // Text field to write in the segment's starting point's Y value
    private JSpinner endCoordinateX; // Text field to write in the segment's end point's X value
    private JSpinner endCoordinateY; // Text field to write in the segment's end point's X value
    private JLabel segmentNameInstructions; // Text to tell user to type in segment ID in below box
    private JLabel startCoordinateInstructions; // Text to tell user to type in starting coordinates below
    private JLabel endCoordinateInstructions; // Text to tell user to type in end coordinates below
    private JLabel circleCenterInstructions; // Text to tell user to type in circle center coordinates below
    private JSpinner circleCenterX; // Input for coordinates of circle's center X
    private JSpinner circleCenterY; // Input for coordinates of circle's center Y
    private JButton circleButtonEnter; // Button to enter the coordinates of the circle
    private JButton segmentButtonEnter; // Button to enter the segment data
    private JButton showProgress; // Button to show progress so far, including circle counts and segments recorded
    private JLayeredPane layeredPane; // Allows me to layer components
    
    int fontSize = 13; // Setting variable for font size
    Font defaultFont = new Font("Courier", Font.BOLD, fontSize); // Creating default font

    /**
     *  Input window where dendrite segment data will be entered
    */
    public ShollAnalysisBot () {
        // Setting up format of the main window
        int frameWidth = 1000;
        int frameHeight = 600;
        InputWindow = new JFrame();
        InputWindow.setTitle("Coordinate Input Window");
        InputWindow.setSize(frameWidth, frameHeight);
        InputWindow.setVisible(true);
        InputWindow.setLayout(null);
        InputWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InputWindow.setLocationRelativeTo(null);


        layeredPane = new JLayeredPane();

        int heightOfFirstRow = 25; // value of height of first row of objects
        int heightOfSecondRow = 150; // value of height of second row of objects

        // Formatting instructions for box to enter segment name, <html> is to wrap text
        segmentNameInstructions = new JLabel("<html>" + "Enter the segment name/ID here:"
            + "</html>");
        segmentNameInstructions.setBounds(50, heightOfFirstRow, 
            200, 100);
        segmentNameInstructions.setFont(defaultFont);

        // Inputting segment name/ID
        segmentName = new JTextField();
        segmentName.setBounds(50, heightOfSecondRow, 150, 50);
        segmentName.setFont(defaultFont);
        
        // Formatting instructions for box to enter start coordinates
        startCoordinateInstructions = new JLabel("<html>" + "Enter the coordinates of the first point of the dendrite here (X,Y)"
            + "</html>");
        startCoordinateInstructions.setBounds(250, heightOfFirstRow, 200, 100);
        startCoordinateInstructions.setFont(defaultFont);

        // Comma to place between X and Y boxes for easy visual
        JLabel commaXYStart = new JLabel (",");
        commaXYStart.setBounds(330, heightOfSecondRow, 100, 50);
        commaXYStart.setFont(defaultFont);

        // Inputting X start coordinate
        startCoordinateX = new JSpinner();
        startCoordinateX.setBounds(250, heightOfSecondRow, 75, 50);
        startCoordinateX.setFont(defaultFont);

        //Inputting Y start coordinate
        startCoordinateY = new JSpinner();
        startCoordinateY.setBounds(340, heightOfSecondRow, 75, 50);
        startCoordinateY.setFont(defaultFont);

        // Formatting instructions for box to enter end coordinates
        endCoordinateInstructions = new JLabel("<html>" + "Enter the coordinates of the final point of the dendrite here (X,Y)"
            + "</html>");
        endCoordinateInstructions.setBounds(500, heightOfFirstRow, 
            200, 100);
        endCoordinateInstructions.setFont(defaultFont);

        // Comma to place between X and Y boxes for easy visual
        JLabel commaXYEnd = new JLabel (",");
        commaXYEnd.setBounds(580, heightOfSecondRow, 100, 50);
        commaXYEnd.setFont(defaultFont);


        // Inputting X end coordinate
        endCoordinateX = new JSpinner();
        endCoordinateX.setBounds(500, heightOfSecondRow, 75, 50);
        endCoordinateX.setFont(defaultFont);

        // Inputting Y end coordinate
        endCoordinateY = new JSpinner();
        endCoordinateY.setBounds(590, heightOfSecondRow, 75, 50);
        endCoordinateY.setFont(defaultFont);

        //Formatting instructions for box to enter circle center coordinates
        circleCenterInstructions = new JLabel ("<html>" + "Enter the coordinates of the neuron's center here (X,Y)" +
            "</html>");
        circleCenterInstructions.setBounds(750, heightOfFirstRow, 200, 100);
        circleCenterInstructions.setFont(defaultFont);

        // Comma to place between X and Y boxes for easy visual
        JLabel commaXYCircle = new JLabel (",");
        commaXYCircle.setBounds(830, heightOfSecondRow, 100, 50);
        commaXYCircle.setFont(defaultFont);

        // Inputting circle center X coordinate
        circleCenterX = new JSpinner();
        circleCenterX.setBounds(750, heightOfSecondRow, 75, 50);
        circleCenterX.setFont(defaultFont);

        // Inputting circle center Y coordinate
        circleCenterY = new JSpinner();
        circleCenterY.setBounds(840, heightOfSecondRow, 75, 50);
        circleCenterY.setFont(defaultFont);

        
        // Formatting button to set the circle center coordinates
        circleButtonEnter = new JButton();
        circleButtonEnter.setBounds(750, 225, 75, 50);
        circleButtonEnter.setFont(defaultFont);
        circleButtonEnter.setText("Enter");
        // Making button store the center coordinate values
        circleButtonEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent a) {
                circleCenterXValue =(Integer) circleCenterX.getValue();
                circleCenterYValue =(Integer) circleCenterY.getValue();
            }
            }
        );

        // Formatting button to process entered dendrite data, main button
        segmentButtonEnter = new JButton ();
        segmentButtonEnter.setBounds(250, 225, 200, 50);
        segmentButtonEnter.setFont(defaultFont);
        segmentButtonEnter.setText("Enter segment");
        InputWindow.getRootPane().setDefaultButton(segmentButtonEnter);
        /* Making button process the segment data including the circles it passes and 
            its ID
         */
        segmentButtonEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent a) {
                int startCoordinateXPlaceholder = (Integer) startCoordinateX.getValue();
                int startCoordinateYPlaceholder = (Integer) startCoordinateY.getValue();
                int endCoordinateXPlaceholder = (Integer) endCoordinateX.getValue();
                int endCoordinateYPlaceholder = (Integer) endCoordinateY.getValue();
                // Radius start from 12.48 and increment upwards in 11.88
                // Adding segments to the circles they pass
                if ((Math.pow(startCoordinateXPlaceholder-circleCenterXValue, 2)+Math.pow(startCoordinateYPlaceholder-circleCenterYValue, 2))
                    <= Math.pow(12.48, 2) && 
                    (Math.pow(endCoordinateXPlaceholder-circleCenterXValue, 2) + Math.pow(endCoordinateYPlaceholder-circleCenterYValue, 2)) >= 
                    Math.pow(12.48, 2)) {
                    circle1Counter += 1;
                }
                if ((Math.pow(startCoordinateXPlaceholder-circleCenterXValue, 2)+Math.pow(startCoordinateYPlaceholder-circleCenterYValue, 2))
                    <= Math.pow(24.36, 2) && 
                    (Math.pow(endCoordinateXPlaceholder-circleCenterXValue, 2) + Math.pow(endCoordinateYPlaceholder-circleCenterYValue, 2)) 
                    >= Math.pow(24.36, 2)) {
                    circle2Counter += 1;
                }
                if ((Math.pow(startCoordinateXPlaceholder-circleCenterXValue, 2)+Math.pow(startCoordinateYPlaceholder-circleCenterYValue, 2))
                    <= Math.pow(36.24, 2) && 
                    (Math.pow(endCoordinateXPlaceholder-circleCenterXValue, 2) + Math.pow(endCoordinateYPlaceholder-circleCenterYValue, 2)) 
                    >= Math.pow(36.24, 2)) {
                    circle3Counter += 1;
                }
                if ((Math.pow(startCoordinateXPlaceholder-circleCenterXValue, 2)+Math.pow(startCoordinateYPlaceholder-circleCenterYValue, 2))
                    <= Math.pow(48.12, 2) && 
                    (Math.pow(endCoordinateXPlaceholder-circleCenterXValue, 2) + Math.pow(endCoordinateYPlaceholder-circleCenterYValue, 2)) 
                    >= Math.pow(48.12, 2)) {
                    circle4Counter += 1;
                }
                if ((Math.pow(startCoordinateXPlaceholder-circleCenterXValue, 2)+Math.pow(startCoordinateYPlaceholder-circleCenterYValue, 2))
                    <= Math.pow(60, 2) && 
                    (Math.pow(endCoordinateXPlaceholder-circleCenterXValue, 2) + Math.pow(endCoordinateYPlaceholder-circleCenterYValue, 2)) 
                    >= Math.pow(60, 2)) {
                    circle5Counter += 1;
                }
                // Adding inputted segment name to the array list
                String individualSegmentName = segmentName.getText();
                segmentNames.add(individualSegmentName);
                System.out.println(circle1Counter);
            }
            }
        );

        // Formatting show progress button
        showProgress = new JButton();
        showProgress.setBounds(50, 225, 150, 50);
        showProgress.setFont(defaultFont);
        showProgress.setText("Show Progress");

        // Making button create popup to show the progress of analysis
        showProgress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent a) {
                
            }
        }); 

        layeredPane.add(segmentNameInstructions);
        layeredPane.add(segmentName);
        layeredPane.add(startCoordinateInstructions);
        layeredPane.add(startCoordinateX);
        layeredPane.add(commaXYStart);
        layeredPane.add(startCoordinateY);
        layeredPane.add(endCoordinateInstructions);
        layeredPane.add(commaXYEnd);
        layeredPane.add(endCoordinateX);
        layeredPane.add(endCoordinateY);
        layeredPane.add(circleCenterInstructions);
        layeredPane.add(circleCenterX);
        layeredPane.add(commaXYCircle);
        layeredPane.add(circleCenterY);
        layeredPane.add(circleButtonEnter);
        layeredPane.add(segmentButtonEnter);
        layeredPane.add(showProgress);
        InputWindow.setContentPane(layeredPane);
    }

    public static void main (String[] args) {
        ShollAnalysisBot app = new ShollAnalysisBot();
        app.setVisible(true);
    }
}
