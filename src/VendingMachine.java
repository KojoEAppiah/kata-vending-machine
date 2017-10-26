public class VendingMachine {

    final double COLAPRICE, CHIPPRICE, CANDYPRICE;

    private double coin_box;
    private double coin_return;
    private double current_coins;
    private int cola_count;
    private int chip_count;
    private int candy_count;

    private String text_display;

    public static enum Coin {
        NICKEL, DIME, QUARTER
    }

    VendingMachine() {

        this.COLAPRICE = 1.00;
        this.CHIPPRICE = 0.50;
        this.CANDYPRICE = 0.60;

        this.coin_box = 0.00;
        this.coin_return = 0.00;
        this.current_coins = 0.00;

        this.cola_count = 0;
        this.chip_count = 0;
        this.candy_count = 0;

        this.text_display = "INSERT COIN";
    }

    public String getTextDisplay(){
        return this.text_display;
    }

    public double getCoinBox() {
        return this.coin_box;
    }

    public double getCoinReturn(){
        return this.coin_return;
    }

    public double getCurrentCoins() {
        return this.current_coins;
    }

    public int getColaCount() {
        return this.cola_count;
    }

    public int getChipCount() {
        return this.chip_count;
    }

    public int getCandyCount() {
        return this.candy_count;
    }

    public int addCola(int colas){
        this.cola_count += colas;
        return this.cola_count;
    }

    public int addChips(int chips){
        this.chip_count += chips;
        return this.chip_count;
    }

    public int addCandy(int candies){
        this.candy_count += candies;
        return this.candy_count;
    }

    void addCoin(Coin coin) {
        switch (coin) {
            case NICKEL:
                this.current_coins += 0.05;
                break;
            case DIME:
                this.current_coins += 0.10;
                break;
            case QUARTER:
                this.current_coins += 0.25;
                break;
        };

        this.text_display = String.valueOf(this.current_coins);
    }

    public double returnCoins(){
        this.coin_return += this.current_coins;
        this.current_coins = 0;
        return this.coin_return;
    }

    public void selectCola() {
        if (this.cola_count > 0) {
            if(this.current_coins > this.COLAPRICE) {
                this.cola_count--;
                this.text_display = "THANK YOU";

                this.coin_box += this.COLAPRICE;
                this.coin_return = this.current_coins - this.COLAPRICE;
                this.current_coins = 0;
            }
            else {

                if(this.current_coins > 0) {
                    if (this.text_display.equals("PRICE " + this.COLAPRICE))
                        this.text_display = String.valueOf(this.current_coins);
                    else if (this.text_display.equals(String.valueOf(this.current_coins)))
                        this.text_display = "PRICE " + this.COLAPRICE;
                }
                else {
                    if (this.text_display.equals("INSERT COIN"))
                        this.text_display = "PRICE " + this.COLAPRICE;
                    else
                        this.text_display = "INSERT COIN";
                }
            }
        }
        else {
            this.text_display = "SOLD OUT";
        }

    }

}
