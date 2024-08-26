import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ShollAnalysisBot extends JFrame {
    // Integer variables to store the number of dendrite crossings for each concentric circle
    int circle1Counter;
    int circle2Counter;
    int circle3Counter;
    int circle4Counter;
    int circle5Counter;
    int circle6Counter;
    private JFrame InputWindow; // First input window to type in coordinates
    private JTextField segmentName; // Text field to write in the segment name/ID
    private JTextField startCoordinateX; // Text field to write in the segment's starting point's X value
    private JTextField startCoordinateY; // Text field to write in the segment's starting point's Y value
    private JTextField endCoordinateX; // Text field to write in the segment's end point's X value
    private JTextField endCoordinateY; // Text field to write in the segment's end point's X value
    private JLabel segmentNameInstructions; // Text to tell user to type in segment ID in below box
    private JLabel startCoordinateInstructions; // Text to tell user to type in starting coordinates below
    private JLabel endCoordinateInstructinos; // Text to tell user to type in end coordinates below
    private JTextField circleCenterX; // Input for coordinates of circle's center X
    private JTextField circleCenterY; // Input for coordinates of circle's center Y
    private JLayeredPane layeredPane; // Allows me to layer components

    int fontSize = 13; // Setting variable for font size

    // Input window where dendrite segment data will be entered
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

        // Formatting instructions for box to enter segment name
        segmentNameInstructions = new JLabel("<html>" + "Enter the segment name/ID here:"
            + "</html>");
        segmentNameInstructions.setBounds(frameWidth/16, frameHeight/32, 
            frameWidth/8, frameHeight/8);
        segmentNameInstructions.setFont(new Font("Courier", Font.BOLD, fontSize));


        layeredPane.add(segmentNameInstructions);
        InputWindow.setContentPane(layeredPane);
    }

    public static void main (String[] args) {
        ShollAnalysisBot app = new ShollAnalysisBot();
        app.setVisible(true);
    }
}
