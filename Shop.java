/**
 * main
 */
import java.util.Comparator;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Random;

public class Shop {

    public static void main(String[] args) {
        clearScreen();

        PriorityQueue<Toy> queue = new PriorityQueue<>(new ToyComparator());   //очередь с приоритетом
        ArrayDeque<String> queue1 = new ArrayDeque<String>();  //общая очередь
        
        Toy toy1 = Toy.Put("1 2 Конструктор");
        Toy toy2 = Toy.Put("2 2 Робот");
        Toy toy3 = Toy.Put("3 6 Кукла");

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

        for (int i = 0; i < 10; i++) {
            String queue1String = queue1.pollFirst();
            //System.out.println(queue1String);
            Toy.PrintFromId(queue1String, queue);
        }
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
        int random = rand.nextInt(10);
        if(random >= 0 && random < f1) {return "1";}
        else if(random >= f1 && random < (f1 + f2)) {return "2";}
        else if(random >= (f1 + f2)  && random < (f1 + f2 + f3)) {return "3";}

        return "0";
    }

    public static void PrintFromId(String id, PriorityQueue<Toy> queue){
        for (Toy toy : queue) {
            if(id.equals(toy.id)) System.out.println("id: " + id + " - " + toy.name);
        } 
    }
}