import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendingMachineTest {

    VendingMachine vending_machine;
    @Before
    public void SetUp(){
        vending_machine = new VendingMachine();
    }

    public void FillCoinBox(){

        //put some coins in the coin_box
        for(int x = 0; x < 6; x++)
            vending_machine.addCoin(VendingMachine.Coin.QUARTER);
        for(int x = 0; x < 4; x++)
            vending_machine.addCoin(VendingMachine.Coin.DIME);
        for(int x = 0; x < 2; x++)
            vending_machine.addCoin(VendingMachine.Coin.NICKEL);

        vending_machine.addProduct("chips", 4);
        vending_machine.selectProduct("chips");
        vending_machine.selectProduct("chips");
        vending_machine.selectProduct("chips");
        vending_machine.selectProduct("chips");

    }

    @Test
    public void WhenANickelIsInsertedIntoTheVendingMachineItAddSFiveCentsToItsCurrentCoins(){
        SetUp();
        vending_machine.addCoin(VendingMachine.Coin.NICKEL);
        assertEquals(0.05, vending_machine.getCurrentCoins());
    }

    @Test
    public void WhenADimeIsInsertedIntoTheVendingMachineItAddsTenCentsToItsCurrentCoins(){
        SetUp();
        vending_machine.addCoin(VendingMachine.Coin.DIME);
        assertEquals(0.10, vending_machine.getCurrentCoins());
    }

    @Test
    public void WhenAQuarterIsInsertedIntoTheVendingMachineItAddsTwentyFiveCentsToItsCurrentCoins(){
        SetUp();
        vending_machine.addCoin(VendingMachine.Coin.QUARTER);
        assertEquals(0.25, vending_machine.getCurrentCoins());
    }

    @Test
    public void WhenColaIsSelectedButSoldOutTheSoldOutMessageAndInsertCoinAndCurrentCoinsDisplayOneAfterAnother(){
        SetUp();
        FillCoinBox();

        vending_machine.selectProduct("Cola");
        assertEquals("SOLD OUT", vending_machine.getTextDisplay());
        vending_machine.selectProduct("cola");
        assertEquals("INSERT COIN", vending_machine.getTextDisplay());
        vending_machine.addCoin(VendingMachine.Coin.NICKEL);  //sets display to current_coins
        vending_machine.selectProduct("cola");;
        vending_machine.selectProduct("cola");;
        assertEquals("0.05", vending_machine.getTextDisplay());
    }

    @Test
    public void WhenColaIsSelectedButCurrentCoinsIsInsufficientPriceAndInsertCoinsAndCurrentCoinsDisplayAfterEachOther(){
        SetUp();
        FillCoinBox();


        vending_machine.addProduct("Cola", 3);
        vending_machine.selectProduct("Cola");
        assertEquals("PRICE $1.00", vending_machine.getTextDisplay());
        vending_machine.selectProduct("cola");
        assertEquals("INSERT COIN", vending_machine.getTextDisplay());
        vending_machine.addCoin(VendingMachine.Coin.QUARTER);
        assertEquals("$0.25", vending_machine.getTextDisplay());
        vending_machine.selectProduct("cola");
        assertEquals("PRICE " + vending_machine.COLAPRICE, vending_machine.getTextDisplay());
    }

    @Test
    public void WhenColaIsSelectedAndAvailableAndCurrenCoinsIsGreatEnoughColaIsDispensedAndThankYouIsDisplayedFollowedByInsertCoinThenPrice(){
        SetUp();
        FillCoinBox();


        for(int x = 5; x > 0; x--)
            vending_machine.addCoin(VendingMachine.Coin.QUARTER);

        vending_machine.addProduct("Cola", 5);

        vending_machine.selectProduct("cola");
        assertEquals("THANK YOU", vending_machine.getTextDisplay());
        vending_machine.selectProduct("cola");
        assertEquals("INSERT COIN", vending_machine.getTextDisplay());
        vending_machine.selectProduct(("cola"));
        assertEquals("PRICE $1.00", vending_machine.getTextDisplay());
    }

    @Test
    public void WhenColaIsDispensedPropperChangeIsPutInCoinReturn(){
        SetUp();
        for(int x = 5; x > 0; x--)
            vending_machine.addCoin(VendingMachine.Coin.QUARTER);

        vending_machine.addProduct("cola", 1);
        vending_machine.selectProduct("cola");
        assertEquals(0.25, vending_machine.getCoinReturn());
    }

    @Test
    public void WhenReturnCoinsButtonIsPressedCurrentCoinsAreMovedToTheCoinReturn(){
        SetUp();

        vending_machine.addCoin(VendingMachine.Coin.QUARTER);
        vending_machine.returnCoins();

        assertEquals(0.25, vending_machine.getCoinReturn());
    }

    @Test
    public void WhenCoinBoxDoesNotContainEnoughMoneyExactChangeMessageIsDisplayed(){
        SetUp();
        FillCoinBox();

        vending_machine.addProduct("Cola", 5);
        vending_machine.selectProduct("cola");
        vending_machine.selectProduct("cola");
        assertEquals("EXACT CHANGE ONLY", vending_machine.getTextDisplay());

        for(int x = 0; x < 5; x++)
            vending_machine.addCoin(VendingMachine.Coin.QUARTER);

        vending_machine.selectProduct("cola");
        vending_machine.selectProduct("cola");
        assertEquals("INSERT COIN", vending_machine.getTextDisplay());
    }
}
