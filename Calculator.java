import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.SpringLayout;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * This Calculator class represents the JFrame on containing the 
 * Roth IRA calculator. 
 * @author Your Friendly CS Professors
 *
 */
public class Calculator extends JFrame{
	
	/**
	 * Instance variables for State of Calculator
	 * Don't change the variable names they are used by test
	 * case
	 */
	protected int monthlySavings;
	protected int numYears;
	protected int currentAge;
	protected double rateOfReturn ;
	protected double investmentValue;
	protected int futureAge; 

	/**
	 * This represents the elements that are displayed on screen. 
	 * Don't change the name of these variables they ar used by the
	 * test cases. 
	 */
	protected JLabel ageLabel;
	protected JSpinner ageSpinner;
	protected JLabel monthSaveLabel;
	protected JSlider monthlySaveSlider;
	protected JLabel monthlySaveDisplayAmount;
	protected JLabel numYearsDisplay;
	protected JLabel futureAgeLabel; 
	protected JLabel investmentDisplayValue;
	protected JLabel rateOfReturnDisplay;
	protected JLabel numberOfYearsLabel;
	protected JSlider numYearsSlider;
	
	public String getFutureAgeDisplayText() {
		
		return "Future Age: " + futureAge;
	}
	public double calculateInvestmentValue() {
		investmentValue = monthlySavings*(Math.pow(1+rateOfReturn/12,12*numYears)-1)/(rateOfReturn/12)*(1+rateOfReturn/12);
		return investmentValue;
	}
	
	public String getInvestmentValueDisplayText() {
		return "Investment: $" +Integer.toString((int)Math.round(calculateInvestmentValue()));
	}

	/*
	 * Getters and setters
	 */
	public int getMonthlySavings() {
		return monthlySavings;
	}
	
	public void setMonthlySavings(int savings) {
		monthlySavings = savings;
		monthlySaveDisplayAmount.setText("$"+monthlySavings);
	}
	
	public int getNumYears() {
		return numYears;
	}


	public void setNumYears(int numYears) {
		this.numYears = numYears;
		numYearsDisplay.setText(numYears+" years");

	}


	public int getCurrentAge() {
		return currentAge;
	}


	public void setCurrentAge(int currentAge) {
		this.currentAge = currentAge;
	}


	public double getRateOfReturn() {
		return rateOfReturn;
	}


	public void setRateOfReturn(double rateOfReturn) {
		this.rateOfReturn = rateOfReturn;
	}


	public double getInvestmentValue() {
		return investmentValue;
	}


	public void setInvestmentValue(double investmentValue) {
		this.investmentValue = investmentValue;
		investmentDisplayValue.setText("Investment: $" + Math.round(investmentValue));
	}


	public int getFutureAge() {
		return futureAge;
	}


	public void setFutureAge() {
		futureAge = numYears+currentAge;
		futureAgeLabel.setText(getFutureAgeDisplayText());
	}


	/**
	 * Inner classes that implement the handler form listeners
	 *  These classes implement the change listeners. 
	 */
	protected class NumYearsListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
          	JSlider source = (JSlider)e.getSource();
          	int value = source.getValue();
          	setNumYears(value);
          	          	
			setFutureAge();
			investmentDisplayValue.setText("Investment: $" + Integer.toString((int)Math.round(calculateInvestmentValue()))); 
		  }	
    }
	
	/**(
	 *
	 *	Listener for age change events. 
	 */
	
	 protected class AgeListener implements ChangeListener{
		 public void stateChanged(ChangeEvent e) {
			 JSpinner source = (JSpinner)e.getSource();
			 int val =(int) source.getValue();
			 setCurrentAge(val);
			 setFutureAge();
			 investmentDisplayValue.setText("Investment: $" + Integer.toString((int)Math.round(calculateInvestmentValue())));
			 
		 }
	 }
	
	
	/**
	 * 
	 * Listener for month change events. 
	 *
	 */
	protected class MonthSavingListener implements ChangeListener{

		  public void stateChanged(ChangeEvent e) {
          	JSlider source = (JSlider)e.getSource();
          	int value = source.getValue();
          	setMonthlySavings(value);
          	monthlySaveDisplayAmount.setText("$"+monthlySavings);
          	investmentDisplayValue.setText("Investment: $" + Integer.toString((int)Math.round(calculateInvestmentValue())));
		  }	
	}
 
 /**
	 * Class represents the frame. That we will create
	 * @param monthlySavings
	 * @param numYears
	 * @param currentAge
	 * @param rateOfReturn
	 */
	public Calculator(int monthlySavings, int numYears, int currentAge, double rateOfReturn) {
		super();
		/*
		 * Set up the default values
		 */
		this.monthlySavings = monthlySavings;
		this.numYears = numYears;
		this.currentAge = currentAge;
		this.rateOfReturn = rateOfReturn;
		/*
		 * Setup close window behavior 
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 363);
		getContentPane().setLayout(null);
         
		 
        /*------------------------------------------------------------
         * Logo Section Value Section.
         * Doesn't show up in the designer.
         *  You will need to run the program to see it.
      	 * ------------------------------------------------------------
      	 */
        ImageIcon iconLogo = new ImageIcon("Images/logo-uvacs.png");
        JLabel logoCS = new JLabel();
        logoCS.setIcon(iconLogo);
        logoCS.setBounds(350, 28, 165, 63);
        getContentPane().add(logoCS);
      
		/*------------------------------------------------------------
		 * monthly contribution section 
		 * ------------------------------------------------------------
		 */
        //Month Saving Title Label
        monthSaveLabel = new JLabel("Monthly Savings");
        monthSaveLabel.setBounds(31, 131, 118, 26);
        getContentPane().add(monthSaveLabel);
        // Month Saving Display Label
        monthlySaveDisplayAmount = new JLabel("$"+getMonthlySavings());
        monthlySaveDisplayAmount.setBounds(474, 137, 46, 14);
        getContentPane().add(monthlySaveDisplayAmount);
        
        //Slide for selecting Month Savings 
        monthlySaveSlider = new JSlider();
        //Sets the maximum value of the slide
        monthlySaveSlider.setMaximum(500);
        //Sets the sliders default value 
        monthlySaveSlider.setValue(getMonthlySavings());
        //Set the location (x,y, .., ..) and the 
        // width and height (..., ..., width, height)
        monthlySaveSlider.setBounds(169, 122, 266, 45);
        //Show the ruler style lines
        monthlySaveSlider.setPaintTicks(true);
        //Spacing between major Ticks
        monthlySaveSlider.setMajorTickSpacing(150);
        //Spacing between minor Ticks
        monthlySaveSlider.setMinorTickSpacing(10);
        //Show labels on Ticks 
        monthlySaveSlider.setPaintLabels(true);
        //Add Change listener. 
        monthlySaveSlider.addChangeListener(new MonthSavingListener());
        getContentPane().add(monthlySaveSlider);
        
        numberOfYearsLabel = new JLabel("Number of Years");
        numberOfYearsLabel.setBounds(31, 210, 118, 26);
        getContentPane().add(numberOfYearsLabel);
        // Month Saving Display Label
        numYearsDisplay = new JLabel("70 years");
        numYearsDisplay.setBounds(474, 216, 55, 14);
        getContentPane().add(numYearsDisplay);
        
        //Slide for selecting number of years 
        numYearsSlider = new JSlider();
        //Sets the maximum value of the slide
        numYearsSlider.setMaximum(90);
        //Sets the sliders default value 
        numYearsSlider.setValue(getMonthlySavings());
        //Set the location (x,y, .., ..) and the 
        // width and height (..., ..., width, height)
        numYearsSlider.setBounds(169, 198, 266, 45);
        //Show the ruler style lines
        numYearsSlider.setPaintTicks(true);
        //Spacing between major Ticks
        numYearsSlider.setMajorTickSpacing(15);
        //Spacing between minor Ticks
        numYearsSlider.setMinorTickSpacing(5);
        //Show labels on Ticks 
        numYearsSlider.setPaintLabels(true);
        //Add Change listener. 
        numYearsSlider.addChangeListener(new NumYearsListener());
        getContentPane().add(numYearsSlider);

        
        //Add future age label
        futureAgeLabel = new JLabel(getFutureAgeDisplayText());
        futureAgeLabel.setBounds(31, 273, 118, 26);
        getContentPane().add(futureAgeLabel);
        
        //Add age JSpinner
        ageSpinner = new JSpinner();
        ageSpinner.setValue(currentAge);
        ageSpinner.setBounds(173, 55, 60, 34);
        ageSpinner.addChangeListener(new AgeListener());
        getContentPane().add(ageSpinner);
        
        //Add rate of return label
        rateOfReturnDisplay = new JLabel("Rate: %"+ (int)Math.round(rateOfReturn*100));
        rateOfReturnDisplay.setBounds(235,273, 60, 26);
        getContentPane().add(rateOfReturnDisplay);
        
        //Add Age label
        ageLabel = new JLabel("Age");
        ageLabel.setBounds(54,58, 34, 26);
        getContentPane().add(ageLabel);
        
        //Add investment label
        investmentDisplayValue = new JLabel("Investment: $" + ((Integer)(int)calculateInvestmentValue()).toString());
        investmentDisplayValue.setBounds(408, 269, 143, 34);
        getContentPane().add(investmentDisplayValue);
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator frame = new Calculator(77, 70, 18, 0.07);
					frame.setVisible(true);
										
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
