/**
 *
 * This application reads, calculates and displays statistics of text typed by a user. The statistics displayed
 * will be amount of words, average word length, longest word, shortest word etc.
 *
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.*;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
import javax.swing.border.TitledBorder;


public class TextReaderPanel extends JPanel {

 private JLabel words, averageWordLength, longestWord, shortestWord, charAmount; //each label used for statistics
 private JTextArea textBox; //refrence to a textbox
 private JScrollPane scroll; //textbox to be place in scroll object
 private JButton stats; // button to calculate statistics
 private int wordsCount,longestCount,shortestCount, charCount;
 private double averageCount = 0;


 public TextReaderPanel()
 {

  //Box layout to display statistics
  JPanel statsPanel = new JPanel();
  statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));

  //each statistic instantiated with display text
  words = new JLabel("Amount of words: " + "__");
  averageWordLength = new JLabel("Average word length: " + "__" );
  longestWord = new JLabel("Longest word length: " + "__");
  shortestWord = new JLabel("Shortest word length: " + "__");
  charAmount = new JLabel("Amount of characters: " + "__");

  stats = new JButton("Calculate"); //instantiate button with text
  stats.addActionListener(new TextListener()); //add a listener to the button that activates an action when pressed

  TitledBorder tb = BorderFactory.createTitledBorder("Text Statistics"); //border around statistics with a title
  tb.setTitleJustification(TitledBorder.LEFT);
  tb.setTitleFont(new Font("Courier New", Font.BOLD, 15)); //font change for the border title

  //add each label to the Box Layout with space in between
  statsPanel.setBorder(tb);
  statsPanel.add(Box.createRigidArea(new Dimension(20,20))); //blank space between labels
  statsPanel.add(words);
  statsPanel.add(Box.createRigidArea(new Dimension(20,20)));
  statsPanel.add(averageWordLength);
  statsPanel.add(Box.createRigidArea(new Dimension(20,20)));
  statsPanel.add(longestWord);
  statsPanel.add(Box.createRigidArea(new Dimension(20,20)));
  statsPanel.add(shortestWord);
  statsPanel.add(Box.createRigidArea(new Dimension(20,20)));
  statsPanel.add(charAmount);
  statsPanel.add(Box.createRigidArea(new Dimension(20,20)));
  statsPanel.add(stats);

  textBox = new JTextArea(); //instantiates object to hold user text
  scroll = new JScrollPane(textBox); //add to Scroll object to allow for scrolling of text
  scroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
  scroll.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);

  setLayout(new BorderLayout()); //borderayout to display objects
  add(statsPanel, BorderLayout.WEST); //statistics to be displayed on the left side
  add(scroll); //textbox displayed to the rest of screen(center as default)
 }

private class TextListener implements ActionListener //private class to implement action when button is pressed
{

 public void actionPerformed(ActionEvent event)
 {

  wordsCount = 0;
  averageCount = 0;
  longestCount = 0;
  shortestCount = 0;
  charCount = 0;

  String textRead; // stores each word individually
  Scanner scan = new Scanner(textBox.getText()); // stores all text from textBox into a scanner object

  while (scan.hasNext()) //loop to read and store statistics of each String
  {
   textRead = scan.next(); //scan and store each string seperated by white space
   wordsCount++;
   averageCount += textRead.length(); //stores the length of each word
   charCount += textRead.length(); // stores length to calculate amount of characters in text

   if(wordsCount == 1) //at least one character typed for proper calculation of shortest/longest
   {
    longestCount = textRead.length();
    shortestCount = textRead.length();
   }
   if(textRead.length() > longestCount)
    longestCount = textRead.length();
   if(textRead.length() < shortestCount)
    shortestCount = textRead.length();
  }

   //formatting for calculating average
   DecimalFormat fmt = new DecimalFormat("0.###");

   if(wordsCount == 0)
   averageWordLength.setText("Average word length: " + 0 ); //would print out Nan without the if statement
   else
   averageWordLength.setText("Average word length: " + fmt.format(averageCount/wordsCount));

   //display results in statistics area of program
   words.setText("Amount of words: " + wordsCount);
   longestWord.setText("Longest word length: " + longestCount);
   shortestWord.setText("Shortest word length: " + shortestCount);
   charAmount.setText("Amount of characters: " + charCount);

    scan.close();
   }

  }

}



