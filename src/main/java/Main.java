public class Main {
    public static void main( String[] args ) {
        Player attacker = new Player("warrior", 2);
        attacker = attacker.putArmor(new Item(0,0,3,10,25));
        System.out.println(attacker.getPerformance());
    }
}
