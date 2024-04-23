public class LoyaltyShopper extends Shopper {

    private int loyaltyPoints;

    public LoyaltyShopper(String name, boolean receiveMonthlyNewsletter, int itemsPurchased, int loyaltyPoints) {
        super(name, receiveMonthlyNewsletter, itemsPurchased);
        this.loyaltyPoints = loyaltyPoints;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        System.out.println("Set loyalty points to " + loyaltyPoints + " for shopper " + this.getName());
        this.loyaltyPoints = loyaltyPoints;
    }
}
