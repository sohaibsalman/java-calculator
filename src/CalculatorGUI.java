 
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sohaib
 */
public class CalculatorGUI
{
    //Frame 
    JFrame frame;
    
    //Panels
    JPanel buttonsPanel;
    JPanel textPanel;
    JPanel memoryButtonsPanel;
    
    //Button
    JButton btn;
    
    //Text Fields
    JTextField historyField;
    JTextField inputField;
    JTextField tempField;
    
    //Buttons Text
    private final String digits[] = { "7", "8", "9", "4", "5", "6", "1", "2", "3", "0"};
    private final String operators[] = { "%", "sqrt", "sq", "1/x", "/", "x", "-", "+", "+/-", ".", "="};
    private final String functions[] = { "C", "CE", "BACK" };
    private final String memoryFunctions[] = {"MC", "MR", "M+", "M-", "MS"};
        
    public CalculatorGUI()
    {
        initCaclulator();
    }

    private void initCaclulator()
    {
        //Initialize Frame
        frame = new JFrame("Calculator");
        
        
        //Set Layout of Frame
        frame.setLayout(null);
            
        
        //Initialize Components
        inputField = new JTextField(11);
        historyField = new JTextField(37);
        tempField = new JTextField();
                
        buttonsPanel = new JPanel();
        textPanel = new JPanel();
        memoryButtonsPanel = new JPanel();
        
        
        //Set Layout and Bounds of Components
        buttonsPanel.setLayout(new GridLayout(6, 4, 2, 2));
        buttonsPanel.setBounds(4, 200, 500, 280); 
        
        textPanel.setBounds(4, 50, 500, 100);
        
        
        memoryButtonsPanel.setLayout(new GridLayout(1, 5, 2, 2));
        memoryButtonsPanel.setBounds(4, 163, 500, 35);
        
        
        //Add Componets      
        addTextFields();
        addButtons();      
        addMemoryButtons();
        
        
        frame.add(memoryButtonsPanel);
        frame.add(buttonsPanel);
        frame.add(textPanel);
        
        //Set Frame Size and Position
        frame.setSize(514, 514);
        frame.setResizable(false);
        frame.setLocation(250, 120);
        frame.setVisible(true);
                
        //Set Default Close Action
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void addButtons()
    {
        int digitNumber, operatorNumber, functionNumber;
        digitNumber = operatorNumber = functionNumber = 0;
        
        int iterator = digits.length + operators.length + functions.length;
                
        for (int i = 0; i < iterator; i++)
        {
            //Check for Operators buttons
            if((i >= 0 && i <=3) || i == 7 || i == 11 || i ==15 || i==19  || i==20 || i==22 || i==23)
            {
                btn = new JButton(operators[operatorNumber++]);
                btn.setBackground(Color.darkGray.darker());
                btn.setFont(historyField.getFont().deriveFont(Font.BOLD, 16f));
            }
            //Check for function buttons
            else if(i >= 4 && i <=6)
            {
                btn = new JButton(functions[functionNumber++]);
                btn.setBackground(Color.darkGray.darker().darker().darker().darker());
                btn.setFont(historyField.getFont().deriveFont(Font.PLAIN, 14f));
            }
            //Digit Buttons
            else 
            {
                btn = new JButton(digits[digitNumber++]);
                btn.setBackground(Color.black);
                btn.setFont(historyField.getFont().deriveFont(Font.BOLD, 19f));
            }
            
            //Set Design of Button
            btn.setForeground(Color.white);
            // btn.setBorder(null);
            
            //Add Event Listener 
     
            btn.addActionListener(new BtnHnd(this));
            
            //Add Button to Panel
            buttonsPanel.add(btn);
        }
    }
    
    private void addMemoryButtons() 
    {
        for(int i = 0; i < memoryFunctions.length; i++)
        {
            btn = new JButton(memoryFunctions[i]);
            //Set Design of Button
            btn.setForeground(Color.white);
            btn.setBackground(Color.black);
            btn.setBorder(null);
            
            //Add Event Listener 
            btn.addActionListener(new BtnHnd(this));
            
            //Add Button to Panel
            memoryButtonsPanel.add(btn);
        }
    }
         
    private void addTextFields()
    {
        historyField.setFont(historyField.getFont().deriveFont(Font.BOLD, 15f));
        designTextFields(historyField);
        
        inputField.setFont(historyField.getFont().deriveFont(Font.BOLD, 50f));
        inputField.setText("0");
        designTextFields(inputField);
        
        textPanel.add(historyField);
        textPanel.add(inputField);
    }
    
    private void designTextFields(JTextField txt)
    {
        txt.setBorder(null);
        txt.setForeground(Color.black);
        txt.setHorizontalAlignment(JTextField.RIGHT);
        txt.setBackground(null);
        txt.setEditable(false);
    }
    
    public void setInputField(String str)
    {
        if(inputField.getText().length() < 11)
            inputField.setText(inputField.getText() + str);
    }
    
    public void setHistoryField(String str)
    {
        historyField.setText(historyField.getText() +  inputField.getText() + " " + str + " ");
    }
}