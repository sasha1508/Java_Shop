/**
 * main
 */
import java.util.Comparator;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Shop {

    public static void main(String[] args) {
        clearScreen();

        PriorityQueue<Toy> queue = new PriorityQueue<>(new ToyComparator());   //очередь с приоритетом
        ArrayDeque<String> queue1 = new ArrayDeque<String>();  //общая очередь
        
        Scanner in = new Scanner(System.in, "866");
        System.out.print("1 - Введи через пробел вес и название игрушки (пример: \"2 Конструктор\"): ");
        String toy1String = "1 " + in.nextLine();
        System.out.print("2 - Введи через пробел вес и название игрушки (пример: \"2 Конструктор\"): ");
        String toy2String = "2 " + in.nextLine();
        System.out.print("3 - Введи через пробел вес и название игрушки (пример: \"2 Конструктор\"): ");
        String toy3String = "3 " + in.nextLine();
        in.close();

        Toy toy1 = Toy.Put(toy1String);
        Toy toy2 = Toy.Put(toy2String);
        Toy toy3 = Toy.Put(toy3String);

        queue.add(toy1);
        queue.add(toy2);
        queue.add(toy3);

        String[] array1 = { toy1.id, toy1.frequency};
        String[] array2 = { toy2.id, toy2.frequency};
        String[] array3 = { toy3.id, toy3.frequency};

        for (int i = 0; i < 10; i++) {
            String toyID = Toy.Get(array1, array2, array3);
            queue1.add(toyID);
        }

        String saveString = "";
        for (int i = 0; i < 10; i++) {
            String queue1String = queue1.pollFirst();
            saveString += Toy.PrintFromId(queue1String, queue) + "\r\n";
            //Toy.Save(queue1String;
            //System.out.print(saveString);
        }
        Toy.Save(saveString);
    }

    // Очистка консоли:
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

class ToyComparator implements Comparator<Toy> {

    @Override
    public int compare(Toy o1, Toy o2) {
        if (o1 == o2) {
            return 0;
        }
        if (o1 == null) {
            return -1; // o1 < o2
        }
        if (o2 == null) {
            return 1; // o1 > o2
        }
        int s = Integer.parseInt(o1.id) - Integer.parseInt(o2.id);
        if (s != 0) {
            return s;
        }
        return o1.id.compareTo(o2.id);
    }
}

/**
 * InnerShop
 */
class Toy {

    String id;
    String name;
    String frequency;

    public Toy(String id, String name, String frequency){
        this.id = id;
        this.name = name;
        this.frequency = frequency;
    }

    public static Toy Put(String parameters){
        String id = parameters.substring(0, parameters.indexOf(' ')); 
        parameters =   parameters.substring(parameters.indexOf(' ') + 1);
        String frequency = parameters.substring(0, parameters.indexOf(' '));
        parameters =   parameters.substring(parameters.indexOf(' ') + 1);
        String name = parameters;

        return new Toy(id, name, frequency);
    }

    public static String Get(String[] array1, String[] array2, String[] array3) {
        int f1 = Integer.parseInt(array1[1]);
        int f2 = Integer.parseInt(array2[1]);
        int f3 = Integer.parseInt(array3[1]);
        Random rand = new Random();
        int random = rand.nextInt(f1 + f2 + f3);

        if(random >= 0 && random < f1) {return "1";}
        else if(random >= f1 && random < (f1 + f2)) {return "2";}
        else if(random >= (f1 + f2)  && random < (f1 + f2 + f3)) {return "3";}
        return "0";
    }

    public static String PrintFromId(String id, PriorityQueue<Toy> queue){
        String saveString = "";
        for (Toy toy : queue) {
            if(id.equals(toy.id)) {
                saveString = "id: " + id + " - " + toy.name;
                System.out.println(saveString);
            }
        } 
        return saveString;
    }

    public static void Save(String saveString)
    {
        File file = new File("Toys.txt");

        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            out.append(saveString)
                .append("\r\n");

            out.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}