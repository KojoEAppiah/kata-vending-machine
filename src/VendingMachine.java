public class VendingMachine {

    final double COLAPRICE, CHIPPRICE, CANDYPRICE;

    private int coin_box;
    private int current_coins;
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

        this.coin_box = 0;
        this.current_coins = 0;
        this.cola_count = 5;
        this.chip_count = 5;
        this.candy_count = 5;

        this.text_display = "INSERT COIN";
    }

    public String displayText(){
        return this.text_display;
    }

    public int getCoinBox() {
        return this.coin_box;
    }

    public int getCurrent_coins() {
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

    void addCoin(Coin coin) {
        switch (coin) {
            case NICKEL:
                this.coin_box += 5;
                this.current_coins += 5;
                break;
            case DIME:
                this.coin_box += 10;
                this.current_coins += 10;
                break;
            case QUARTER:
                this.coin_box += 25;
                this.current_coins += 25;
                break;
        }
        ;
    }

    void selectCola() {
        if (this.cola_count > 0) {
            if(current_coins > this.COLAPRICE) {
                this.cola_count--;
                this.text_display = "THANK YOU";
            }
        } else {
            this.text_display = "SOLD OUT";
        }

    }

}
