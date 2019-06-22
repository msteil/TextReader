/**
 * TextReader.java
 *
 * This application reads, calculates and displays statistics of text typed by a user. The statistics displayed
 * will be amount of words, average word length, longest word, shortest word etc.
 *
 */


import java.awt.Dimension;
import javax.swing.JFrame;

public class TextReader {

 public static void main(String[] args) {
   //instantiate a heavyweaight container with title
  JFrame frame = new JFrame("Text Reader");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close on command
  frame.setMinimumSize(new Dimension(800,600)); //sets a minimum size for the container

  TextReaderPanel textReader = new TextReaderPanel(); // instantiate a lightweight object
  frame.getContentPane().add(textReader); //adds the object to the frame
  frame.pack(); //sizes contents at or above their preferred size
  frame.setVisible(true);


    }
}
