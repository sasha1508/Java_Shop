/**
 * main
 */
public class Shop {

    public static void main(String[] args) {

        Toy xxx = Toy.Put("1 2 Конструктор");
        System.out.println(xxx.id);
        System.out.println(xxx.frequency);
        System.out.println(xxx.name);
    }
}


/**
 * InnerShop
 */
class Toy {

    String id;
    String name;
    int frequency;

    public Toy(String id, String name, int frequency){
        this.id = id;
        this.name = name;
        this.frequency = frequency;
    }

    public static Toy Put(String parameters){
        String id = parameters.substring(0, parameters.indexOf(' ')); 
        parameters =   parameters.substring(parameters.indexOf(' ') + 1);
        int frequency = Integer.parseInt(parameters.substring(0, parameters.indexOf(' ')));
        parameters =   parameters.substring(parameters.indexOf(' ') + 1);
        String name = parameters;

        return new Toy(id, name, frequency);
    }
}