public class SimpleGreeting {
    private String message;

    public SimpleGreeting(String message) {
        this.message = message;
    }

    public void greet() {
        System.out.println("Hello, " + message + "!");
    }

    public static void main(String[] args) {
        SimpleGreeting greeting = new SimpleGreeting("World");
        greeting.greet();
    }
     public int add(int a, int b){
        int result =0;
        result =a+b;
        return result;
    }
}