public class Shopper {

    private String name;
    private boolean receiveMonthlyNewsletter;
    private int itemsPurchased;

    public Shopper(String name)
    {
        this.name = name;
    }

    public Shopper(String name, boolean receiveMonthlyNewsletter, int itemsPurchased) {
        this.name = name;
        this.receiveMonthlyNewsletter = receiveMonthlyNewsletter;
        this.itemsPurchased = itemsPurchased;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isReceiveMonthlyNewsletter() {
        return receiveMonthlyNewsletter;
    }

    public void setReceiveMonthlyNewsletter(boolean receiveMonthlyNewsletter) {
        this.receiveMonthlyNewsletter = receiveMonthlyNewsletter;
    }

    public int getItemsPurchased() {
        return itemsPurchased;
    }

    public void setItemsPurchased(int itemsPurchased) {
        this.itemsPurchased = itemsPurchased;
    }
}
