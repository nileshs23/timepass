abstract class Car {
    static {
        System.out.println("1");
    }

    public Car(String name) {
        super();
        System.out.println("2");
    }

    {
        System.out.println("3");
    }

}