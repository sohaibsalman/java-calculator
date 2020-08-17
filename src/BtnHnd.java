
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sohaib
 */
public class BtnHnd implements ActionListener
{
    CalculatorGUI ref;

    public BtnHnd(CalculatorGUI ref)
    {
        this.ref = ref;
    }
    
    @Override
    public void actionPerformed(ActionEvent e )
    {
        if(ref.inputField.getText().equals("Infinity") || ref.inputField.getText().equals("NaN") || ref.inputField.getText().equals("Result is undefined"))
        {
            ref.inputField.setText("0");
        }
        String str = e.getActionCommand();
        
        //Check for operator buttons        
        if(str.equals("+") || str.equals("-") || str.equals("x") || str.equals("/") || str.equals("%"))
        {
            String temp = ref.historyField.getText();
            if(!temp.equals("") && ref.inputField.getText().equals(""))
            {
                if(Calculator.isOperator(temp, temp.length()-2))
                {
                    StringBuffer sb = new StringBuffer(ref.historyField.getText());
                    sb.delete(temp.length()-3, temp.length());
                    temp = sb.toString();
                    ref.historyField.setText(temp);
                }
            }
            ref.setHistoryField(str);
            ref.inputField.setText(null);
        }
        //Check for equal button
        else if(str.equals("="))
        {
            Calculator.evaluate();
            ref.historyField.setText(null);
        }
        //Check for C (Clear All) button
        else if(str.equals("C"))
        {
            Calculator.ClearAllunction();
        }
        //Check for C (Clear input entered) button
        else if(str.equals("CE"))
        {
            Calculator.ClearFunction();
        }
        //Check for BACK button
        else if(str.equals("BACK"))
        {
            Calculator.backFunction();
        }
        //Check for +/- (Negation) button
        else if(str.equals("+/-"))
        {
            Calculator.negationOfNumber();
        }
        //Check for sq (Square) button
        else if(str.equals("sq"))
        {
            Calculator.squareOfNumber();
            Calculator.flag = true;
        }
        //Check for 1/x (Inverse) button 
        else if(str.equals("1/x"))
        {
            Calculator.inverseOfNumber();
        }
        //Check for sqrt (Square root) button
        else if(str.equals("sqrt"))
        {
            Calculator.squareRootOfNumber();
        }
        else if(str.equals("."))
        {
            Calculator.isFraction = true;
            if(!ref.inputField.getText().contains("."))
            {
                 if(ref.inputField.getText().equals(""))
                    ref.setInputField("0.");
                 else if(ref.inputField.getText().equals("Infinity") || ref.inputField.getText().equals("NaN"))
                    ref.inputField.setText("0.");
                 else
                    ref.setInputField(str);
            }
        }
        else if(str.equals("MS"))
        {
            Calculator.memoryStore();
        }
        else if(str.equals("MR"))
        {
            Calculator.memoryRead();
        }
        else if(str.equals("M+"))
        {
            Calculator.memoryAdd();
        }
        else if(str.equals("M-"))
        {
            Calculator.memorySubtract();
        }
        else if(str.equals("MC"))
        {
            Calculator.memoryClear();
        }
        //Check for Digit Buttons
        else if(str.charAt(0) >= '0' && str.charAt(0) <= '9')
        {
            //If input field is zero, set it to null
            if("0".equals(ref.inputField.getText()) || ref.inputField.getText().equals("NaN"))
            {
                ref.inputField.setText(null);
            }
            if(!Calculator.flag)
            {
                ref.setInputField(str);
            }
            else
            {
                if(!ref.inputField.getText().equals("0."))
                {
                    ref.inputField.setText(null);
                }
                ref.setInputField(str);
                Calculator.flag = false;
            }
        }
    }  
}