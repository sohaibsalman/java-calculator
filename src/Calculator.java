
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sohaib
 */
public class Calculator
{
    private static CalculatorGUI gui;
    
    static boolean isFraction;
    static boolean flag;

    public Calculator()
    {
        gui = new CalculatorGUI();
        isFraction = false;
        flag = false;
    }
    
    public static void evaluate()
    {
        String str = gui.historyField.getText() + gui.inputField.getText() + " ";
        
        if(gui.historyField.getText().equals(""))
            return;
        
        char expression[] =  str.toCharArray();
        
        String operand1, operand2, operator;
        operand1 = operand2 = operator = "";
        
        int index = 0;
        
        int length = 0;
        int operands = 0;
        
        while(index < str.length())
        {
            if(isOperator(str, index))
            {
                if(str.charAt(++index) == ' ')
                    length++;
            }
            else if(str.charAt(index) != ' ' && str.charAt(index+1) == ' ')
                operands++;
            index++;
        }
                
//        if(operands-1!=length)
//        {
//            gui.inputField.setText("0");
//            return;
//        }
        
        index = 0;
            
        isFraction = gui.historyField.getText().contains(".");
        
        for(int i = 0; i < length; i++)
        {
            if(operand1 != null)
            {
                 //Get First Operand
                while(expression[index] != ' ')
                    operand1 += expression[index++];
            }
           
            //Get the Operator
            operator += expression[++index];
            index +=2;
            //Get Second Operator
            while(expression[index] != ' ')
                operand2 += expression[index++];
            
            if(operand2.equals(""))
            {
                operand2 = operand1;
            }

            operand1 = calculate(operand1, operand2, operator);
            operand2 = operator = "";
        }
        if(operand1.length() > 11)
        {
            operand1 = breakString(operand1);
        }
        gui.inputField.setText(operand1);
        flag = true;
    }
    
    private static String calculate(String operand1, String operand2, String operator)
    {
        String result ="";
        
        if(!gui.inputField.getText().contains(".") && !gui.historyField.getText().contains("."))
        {
            if(operator.equals("+"))
                result = Long.toString(Long.parseLong(operand1) + Long.parseLong(operand2));
        
            else if(operator.equals("-"))
                result = (Long.toString(Long.parseLong(operand1) - Long.parseLong(operand2)));

            else if(operator.equals("x"))
                result = (Long.toString(Long.parseLong(operand1) * Long.parseLong(operand2)));

            else if(operator.equals("/"))
            {
                result = (Double.toString(Double.parseDouble(operand1) / Double.parseDouble(operand2)));
                isFraction = true;
            }
            else if(operator.equals("%"))
            {
                if(operand2.equals("0"))
                    result = "Result is undefined";
                else
                    result = (Integer.toString(Integer.parseInt(operand1) % Integer.parseInt(operand2)));
            }
        }
        else 
        {
            if(operator.equals("+"))
            result = (Double.toString(Double.parseDouble(operand1) + Double.parseDouble(operand2)));
        
            else if(operator.equals("-"))
                result = (Double.toString(Double.parseDouble(operand1) - Double.parseDouble(operand2)));

            else if(operator.equals("x"))
                result = (Double.toString(Double.parseDouble(operand1) * Double.parseDouble(operand2)));

            else if(operator.equals("/"))
                result = (Double.toString(Double.parseDouble(operand1) / Double.parseDouble(operand2)));

            else if(operator.equals("%"))
                result = (Double.toString(Double.parseDouble(operand1) % Double.parseDouble(operand2)));
        }
        
        return result;
    }
    
    public static void backFunction()
    {
        String temp = gui.inputField.getText();
        if(temp.length()==2 && temp.charAt(0) == '-')
        {
            gui.inputField.setText("0");
            return;
        }
        if(temp.equals("NaN") || temp.equals("Infinity") || temp.equals("Result is undefined") || temp.equals("") || temp.equals("-NaN") || temp.equals("-Infinity"))
        {
            gui.inputField.setText("0");
            return;
        }
        int i = temp.length();
        char arr[] = temp.toCharArray();
        
        arr[i - 1] = '\0';
        temp = "";
        i = 0;
        while(arr[i] != '\0')
            temp +=arr[i++];
        if(temp.equals(""))
            temp = "0";
        gui.inputField.setText(temp);
    }
    
    public static void ClearAllunction()
    {
        gui.inputField.setText("0");
        gui.historyField.setText(null);
        isFraction = false;
    }
    
    public static void ClearFunction()
    {
        gui.inputField.setText("0");
    }
    
    public static void negationOfNumber()
    {
        if(gui.inputField.getText().equals(""))
            return;
        if(isFraction)
            gui.inputField.setText(Double.toString(Double.parseDouble(gui.inputField.getText()) * -1));
        else
            gui.inputField.setText(Long.toString(Long.parseLong(gui.inputField.getText()) * -1));
    }
    
    public static void squareOfNumber()
    {
        String str;
        if(!isFraction)
            str = (Long.toString(Long.parseLong(gui.inputField.getText()) * Long.parseLong(gui.inputField.getText())));
        else
           str = (Double.toString(Double.parseDouble(gui.inputField.getText()) * Double.parseDouble(gui.inputField.getText())));
        if(str.length() > 11)
            str = breakString(str);
        gui.inputField.setText(str);
    }
    
    public static void inverseOfNumber()
    {
        String str;
        str = (Double.toString(1 / Double.parseDouble(gui.inputField.getText())));
        if(str.length() > 11)
            str = breakString(str);
        gui.inputField.setText(str);
        isFraction = true;
    }
    
    public static void squareRootOfNumber() 
    {
        String str;
        str = (Double.toString(Math.sqrt(Double.parseDouble(gui.inputField.getText()))));
        if(str.length() > 11)
            str = breakString(str);
        gui.inputField.setText(str);
        isFraction = true;
    }
    
    private static String readFromFile()
    {
       try
        {
            
            BufferedReader br = new BufferedReader(new FileReader(new File("mem.txt")));
            
            String str = br.readLine();
            
            if(str.equals(""))
                return "0";
            br.close();
            return str;
            
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
        } 
        return null;
    }
    
    private static void writeToFile(String str)
    {
        try
        {            
            PrintWriter pw = new PrintWriter(new FileWriter(new File("mem.txt")));
            pw.println(str);
            pw.close();
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public static void memoryStore()
    {
        String str = gui.inputField.getText();
        writeToFile(str);
    }
    
    public static void memoryRead()
    {
        String str = readFromFile();
        gui.inputField.setText(str);
    }
    
    public static void memoryAdd()
    {
        String str = readFromFile();
        if(gui.inputField.getText().equals(""))
           return;
        if(isFraction)
            str = Double.toString(Double.parseDouble(gui.inputField.getText()) + Double.parseDouble(str));
        else
            str = Integer.toString(Integer.parseInt(gui.inputField.getText()) + Integer.parseInt(str));
        writeToFile(str);
    }
    public static void memorySubtract()
    {
        String str = readFromFile();
        if(gui.inputField.getText().equals(""))
           return;
        if(isFraction)
            str = Double.toString(Double.parseDouble(str) - Double.parseDouble(gui.inputField.getText()));
        else
            str = Integer.toString(Integer.parseInt(str) - Integer.parseInt(gui.inputField.getText()));
        writeToFile(str);
    }
    public static void memoryClear()
    {
        writeToFile("");
    }
    
    public static boolean isOperator(String str, int i)
    {
        if(str.charAt(i) == '+')
            return true;
        else if(str.charAt(i) =='-')
            return true;
        else if(str.charAt(i) =='x')
            return true;
        else if(str.charAt(i) =='/')
            return true;
        else if(str.charAt(i) =='%')
            return true;
        return false;
    }
    
    private static String breakString(String str)
    {
        if(str.equals("Result is undefined"))
            return str;
        StringBuffer sb = new StringBuffer(str);
        sb.delete(12, str.length());
        str = sb.toString();
        return str;
    }
    
}