import java.util.*;
//import java.io.FileWriter;
import java.io.IOException;
import java.text.*;
import java.util.logging.*;

public class HomeWork {
    //public 
    public static void main(String[] args) throws ParseException, IOException{
        // 1. Пусть дан LinkedList с несколькими элементами. Реализуйте метод, который вернёет «перевёрнутый» список.
        List<Integer> list = new LinkedList<Integer>();
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            list.add(rand.nextInt(15));
        }
        System.out.println("Первоначальный список:");
        System.out.println(list.toString());
        System.out.println("Перевернутый список");
        ReversePrint(list);
        System.out.println(list.toString());
        /* 2. Реализуйте очередь с помощью LinkedList со следующими методами:
        • enqueue() — помещает элемент в конец очереди,
        • dequeue() — возвращает первый элемент из очереди и удаляет его,
        • first() — возвращает первый элемент из очереди, не удаляя. */
        MyQueue(list);
        // 3. В калькулятор добавьте возможность отменить последнюю операцию.
        Calculator();
        
    }

    public static List<Integer> ReversePrint(List<Integer> list) {
        Collections.reverse(list);
        return list;
    }

    public static void MyQueue(List<Integer> list) {
        enqueue(list); // помещает элемент в конец очереди,
        System.out.println(list.toString());

        int num = dequeue(list); //возвращает первый элемент из очереди и удаляет его,
        System.out.printf("%d - первый элемент очереди, удален из списка", num);
        System.out.println();
        System.out.println(list.toString());

        int num2 = first(list); //возвращает первый элемент из очереди, не удаляя.
        System.out.printf("%d - первый элемент очереди, не удален из списка", num2);
        System.out.println();
        System.out.println(list.toString());
    }
    public static List<Integer> enqueue(List<Integer> list) {
        Scanner iScanner = new Scanner(System.in);
        System.out.println("Введите число,которое необходимо поместить в конец списка: ");
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
                System.out.print("Введите 1-ое число: ");
                String str1 = iScanner.nextLine();
                number1 = Double.parseDouble(str1);
                System.out.print("Введите 2-ое число: ");
                String str2 = iScanner.nextLine();
                number2 = Double.parseDouble(str2);
                System.out.print("Введите операцию (+, -, *, /): ");
                operation = iScanner.nextLine(); 
            }
            else{  
                if (stack.size() == 1) {
                    System.out.print("Введите операцию (+, -, *, /): ");
                    operation = iScanner.nextLine(); 
                }
                else {
                    System.out.print("Введите операцию (+, -, *, /): ");
                    System.out.println("Для отмены предыдущей операции, введите return: ");   
                    operation = iScanner.nextLine(); 
                } 
                if (!operation.equals("return"))  {    
                    System.out.print("Введите число: ");
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
                String expr = String.format("%f - результат последней операции", num);
                System.out.print(expr);  
                logger.log(Level.INFO, expr);
                System.out.println();
                String expr1 = String.format("%f - результат предыдущей операции", number1);
                System.out.print(expr1);  
                logger.log(Level.INFO, expr1);
            }
            else {
                System.out.println("Неправильно введена операция");
                logger.log(Level.INFO, "Operation is not supported");
            }
            System.out.println();
    
            System.out.print("Вы хотите выполнить еще одну операцию (yes/no): ");
            
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