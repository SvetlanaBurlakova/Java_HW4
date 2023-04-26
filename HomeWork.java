import java.util.*;
//import java.io.FileWriter;
import java.io.IOException;
import java.text.*;
import java.util.logging.*;

public class HomeWork {
    //public 
    public static void main(String[] args) throws ParseException, IOException{
        // 1. ����� ��� LinkedList � ����������� ����������. ���������� �����, ������� ������ ������������ ������.
        List<Integer> list = new LinkedList<Integer>();
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            list.add(rand.nextInt(15));
        }
        System.out.println("�������������� ������:");
        System.out.println(list.toString());
        System.out.println("������������ ������");
        ReversePrint(list);
        System.out.println(list.toString());
        /* 2. ���������� ������� � ������� LinkedList �� ���������� ��������:
        � enqueue() � �������� ������� � ����� �������,
        � dequeue() � ���������� ������ ������� �� ������� � ������� ���,
        � first() � ���������� ������ ������� �� �������, �� ������. */
        MyQueue(list);
        // 3. � ����������� �������� ����������� �������� ��������� ��������.
        Calculator();
        
    }

    public static List<Integer> ReversePrint(List<Integer> list) {
        Collections.reverse(list);
        return list;
    }

    public static void MyQueue(List<Integer> list) {
        enqueue(list); // �������� ������� � ����� �������,
        System.out.println(list.toString());

        int num = dequeue(list); //���������� ������ ������� �� ������� � ������� ���,
        System.out.printf("%d - ������ ������� �������, ������ �� ������", num);
        System.out.println();
        System.out.println(list.toString());

        int num2 = first(list); //���������� ������ ������� �� �������, �� ������.
        System.out.printf("%d - ������ ������� �������, �� ������ �� ������", num2);
        System.out.println();
        System.out.println(list.toString());
    }
    public static List<Integer> enqueue(List<Integer> list) {
        Scanner iScanner = new Scanner(System.in);
        System.out.println("������� �����,������� ���������� ��������� � ����� ������: ");
        int number = iScanner.nextInt();
        list.add(number);
        return list;
    }
    public static Integer dequeue(List<Integer> list) {
        int number = list.get(0);
        list.remove(0);
        return number;
    }
    public static Integer first(List<Integer> list) {
        int number = list.get(0);
        return number;
    }
    public static void Calculator() {
    try {
        Logger logger = Logger.getLogger(HomeWork.class.getName());
        FileHandler fh = new FileHandler("calculator.log"); 
        logger.addHandler(fh);
        SimpleFormatter sFormat = new SimpleFormatter();
        fh.setFormatter(sFormat);
        logger.setUseParentHandlers(false);
        logger.log(Level.INFO, "Logging start");
        Stack<Double> stack = new Stack<Double>();
        
        Scanner iScanner = new Scanner(System.in);
        String isCont = "yes";
        Double number1 = 0.0;
        Double number2 = 0.0;
        String operation = "";
        while (!isCont.equals("no")) {
            if (stack.isEmpty()){
                System.out.print("������� 1-�� �����: ");
                String str1 = iScanner.nextLine();
                number1 = Double.parseDouble(str1);
                System.out.print("������� 2-�� �����: ");
                String str2 = iScanner.nextLine();
                number2 = Double.parseDouble(str2);
                System.out.print("������� �������� (+, -, *, /): ");
                operation = iScanner.nextLine(); 
            }
            else{  
                if (stack.size() == 1) {
                    System.out.print("������� �������� (+, -, *, /): ");
                    operation = iScanner.nextLine(); 
                }
                else {
                    System.out.print("������� �������� (+, -, *, /): ");
                    System.out.println("��� ������ ���������� ��������, ������� return: ");   
                    operation = iScanner.nextLine(); 
                } 
                if (!operation.equals("return"))  {    
                    System.out.print("������� �����: ");
                    String str2 = iScanner.nextLine();
                    number2 = Double.parseDouble(str2);
                }
            }
            if (operation.equals("+")) {
                stack.push(number1+number2);
                String expr = String.format("%f + %f = %f", number1,number2,number1+number2);
                System.out.print(expr);  
                logger.log(Level.INFO, expr);
            }
            else if (operation.equals("-")) {
                stack.push(number1-number2);
                String expr = String.format("%f - %f = %f", number1,number2,number1-number2);
                System.out.print(expr); 
                logger.log(Level.INFO, expr);
            }
            else if (operation.equals("*")) {
                stack.push(number1*number2);
                String expr = String.format("%f * %f = %f", number1,number2,number1*number2);
                System.out.print(expr);  
                logger.log(Level.INFO, expr);
            }
            else if (operation.equals("/")){
                stack.push(number1/number2);
                String expr = String.format("%f / %f = %f", number1,number2,number1/number2);
                System.out.print(expr);  
                logger.log(Level.INFO, expr);
            }
            else if (operation.equals("return")){
                double num = stack.pop();
                number1 = stack.peek();
                String expr = String.format("%f - ��������� ��������� ��������", num);
                System.out.print(expr);  
                logger.log(Level.INFO, expr);
                System.out.println();
                String expr1 = String.format("%f - ��������� ���������� ��������", number1);
                System.out.print(expr1);  
                logger.log(Level.INFO, expr1);
            }
            else {
                System.out.println("����������� ������� ��������");
                logger.log(Level.INFO, "Operation is not supported");
            }
            System.out.println();
    
            System.out.print("�� ������ ��������� ��� ���� �������� (yes/no): ");
            
            isCont = iScanner.nextLine();
            if  (!operation.equals("return")){
                number1 = stack.peek();
            }

        }
    } catch(IOException ex) {
        ex.printStackTrace();
    }
    //iScanner.close();
 
}
}