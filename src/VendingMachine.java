public class VendingMachine {

    public int coin_box;
    public int current_coins;

    public static enum Coin {
        NICKEL, DIME, QUARTER
    }

    VendingMachine(){
        this.coin_box = 0;
        this.current_coins = 0;
    }

    public int getCoinBox(){
        return this.coin_box;
    }

    public int getCurrent_coins(){
        return this.current_coins;
    }

    void addCoin(Coin coin){
        switch (coin){
            case NICKEL:
                this.coin_box += 5;
                this.current_coins += 5;
                break;
            case DIME:
                this.coin_box += 10;
                this.current_coins += 10;
                break;
        };
    }
}
