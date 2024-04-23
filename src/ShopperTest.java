public class ShopperTest {

    public static void main(String[] args)
    {
        Shopper[] customers = new Shopper[3000];
        customers[0] = new Shopper("Jane Doe", true, 5);
        customers[1] = new LoyaltyShopper("Maria Gonzales", true, 100, 1000);
        // code omitted here fills in information for remaining customers
        customers[2998] = new Shopper("Bob Bob");
        customers[2999] = new LoyaltyShopper("Juan Rodriguez", false, 1, 50);

        for (Shopper customer : customers)
        {
            String customerInfo = customer.toString();
            System.out.println(customerInfo);
        }

        // Print out all of the shopper information.
        //You may assume that toString is defined for both Shopper and LoyaltyShopper
    }
}
