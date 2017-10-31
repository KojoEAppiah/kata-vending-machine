import java.text.DecimalFormat;

public class VendingMachine {

    final double COLAPRICE, CHIPPRICE, CANDYPRICE;
    final int NICKEL, DIME, QUARTER;

    private int coin_box[];
    private double coin_box_value;
    private int coin_return_coins[];
    private double coin_return_value;
    private int current_coins[];
    private double current_coins_value;
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

        this.NICKEL = 0;
        this.DIME = 1;
        this.QUARTER = 2;

        this.coin_box = new int[3];
        this.coin_box_value = 0.00;
        for(int i = 0; i < 3; i++)
            this.coin_box[i] = 0;

        this.current_coins = new int[3];
        this.current_coins_value = 0.00;
        for(int i = 0; i < 3; i++)
            this.current_coins[i] = 0;

        this.coin_return_coins = new int[3];
        this.coin_return_value = 0.00;
        for(int i = 0; i < 3; i++)
            this.coin_return_coins[i] = 0;

        this.cola_count = 0;
        this.chip_count = 0;
        this.candy_count = 0;

        this.text_display = "INSERT COIN";
    }

    public String getTextDisplay(){
        return this.text_display;
    }

    public double getCoinBox() {
        return this.coin_box_value;
    }

    public double getCoinReturn(){
        return this.coin_return_value;
    }

    public double getCurrentCoins() {
        return this.current_coins_value;
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

    public int addProduct(String product, int ammount){

        switch(product.toLowerCase()){

            case "cola":
                cola_count += ammount;
                return cola_count;

            case "chips":
                chip_count += ammount;
                return chip_count;

            case "candy":
                candy_count += ammount;
                return candy_count;
        }

        return 0;
    }

    void addCoin(Coin coin) {
        switch (coin) {
            case NICKEL:
                this.current_coins_value += 0.05;
                this.current_coins[this.NICKEL]++;
                break;
            case DIME:
                this.current_coins_value += 0.10;
                this.current_coins[this.DIME]++;
                break;
            case QUARTER:
                this.current_coins_value += 0.25;
                this.current_coins[this.QUARTER]++;
                break;
            default:                   //for pennies - straight to the coin return
                this.coin_return_value += 0.01;
        };

        DecimalFormat formatter = new DecimalFormat("$0.00");
        this.text_display = formatter.format(this.current_coins_value);
    }

    public double returnCoins(){
        this.coin_return_value += this.current_coins_value;
        this.current_coins_value = 0;

        for(int i = 0; i < 3; i++) {
            this.coin_return_coins[i] = this.current_coins[i];
            this.current_coins[i] = 0;
        }
        return this.coin_return_value;
    }


    public void selectProduct(String product) {

        double product_price = 0.00;
        int count = 0;

        DecimalFormat formatter = new DecimalFormat("$0.00");

        switch(product.toLowerCase()){

            case "cola":
                product_price = this.COLAPRICE;
                count = cola_count;
                break;

            case "chips":
                product_price = this.CHIPPRICE;
                count = chip_count;
                break;

            case "candy":
                product_price = this.CANDYPRICE;
                count = candy_count;
                break;
        }

        if (count > 0) {
            if (this.current_coins_value >= product_price) {

                this.text_display = "THANK YOU";

                makeChange(product_price);

                for(int i = 0; i < 3; i++) {
                    this.coin_box[i] += this.current_coins[i];
                    this.current_coins[i] = 0;
                }

                this.coin_box_value += product_price;
                this.coin_return_value += this.current_coins_value - product_price;
                this.current_coins_value = 0;
            } else {

                if (this.current_coins_value > 0) {

                    if (this.text_display.equals("PRICE " + formatter.format(product_price)))
                        this.text_display = "INSERT COIN";
                    else if (this.text_display.equals(formatter.format(this.current_coins_value))) {
                        this.text_display = "PRICE " + formatter.format(product_price);
                    }
                } else {
                    if (this.text_display.equals("INSERT COIN") || this.text_display.equals("EXACT CHANGE ONLY"))
                        this.text_display = "PRICE " + formatter.format(product_price);
                    else {
                        if (canMakeChange())
                            this.text_display = "INSERT COIN";
                        else
                            this.text_display = "EXACT CHANGE ONLY";
                    }
                }
            }
        }

        else {
            if(this.text_display.equals("SOLD OUT")) {

                if (canMakeChange())
                    this.text_display = "INSERT COIN";
                else
                    this.text_display = "EXACT CHANGE ONLY";
            }
            else if(this.text_display.equals("INSERT COIN"))
                this.text_display = formatter.format(this.current_coins_value);
            else
                this.text_display = "SOLD OUT";
        }

    }

    public void makeChange(double price) {

        int value = 0;
        double difference = this.current_coins_value - price;

        while(difference > 0) {

            if(difference >= 0.25) {
                this.current_coins[QUARTER]--;
                this.coin_return_coins[QUARTER]++;
                difference -= 0.25;
                this.current_coins_value -= 0.25;
                this.coin_return_value += 0.25;
            }
            else if(difference >= 0.10) {
                this.current_coins[DIME]--;
                this.coin_return_coins[DIME]++;
                difference -= 0.10;
                this.current_coins_value -= 0.10;
                this.coin_return_value += 0.10;
            }
            else {
                this.current_coins[NICKEL]--;
                this.coin_return_coins[NICKEL]++;
                difference -= 0.05;
                this.current_coins_value -= 0.05;
                this.coin_return_value += 0.05;
            }
        }
    }


    public boolean canMakeChange(){

        if(this.coin_box[QUARTER] >= 3 && this.coin_box[DIME] >= 2 && this.coin_box[NICKEL] > 0)
            return true;

        return false;
    }
}
