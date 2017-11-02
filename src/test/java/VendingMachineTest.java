
import org.junit.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendingMachineTest {

    VendingMachine vending_machine;
    @Before
    public void SetUp(){
        vending_machine = new VendingMachine();
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

        vending_machine.fillCoinBox(6, 4, 2);
        vending_machine.addCoin(VendingMachine.Coin.NICKEL);

        vending_machine.selectProduct("Cola");
        assertEquals("SOLD OUT", vending_machine.getTextDisplay());
        vending_machine.selectProduct("cola");
        assertEquals("INSERT COIN", vending_machine.getTextDisplay());
        vending_machine.selectProduct("cola");
        assertEquals("$0.05", vending_machine.getTextDisplay());
    }

    @Test
    public void WhenColaIsSelectedButCurrentCoinsIsInsufficientPriceAndInsertCoinsAndCurrentCoinsDisplayAfterEachOther(){
        SetUp();

        vending_machine.fillCoinBox(6, 4, 2);
        vending_machine.addCoin(VendingMachine.Coin.QUARTER);

        vending_machine.addProduct("Cola", 3);
        vending_machine.selectProduct("Cola");
        assertEquals("PRICE $1.00", vending_machine.getTextDisplay());
        vending_machine.selectProduct("cola");
        assertEquals("INSERT COIN", vending_machine.getTextDisplay());
        vending_machine.addCoin(VendingMachine.Coin.QUARTER);
        assertEquals("$0.50", vending_machine.getTextDisplay());
        vending_machine.selectProduct("cola");
        assertEquals("PRICE $1.00", vending_machine.getTextDisplay());
    }

    @Test
    public void WhenColaIsSelectedAndAvailableAndCurrenCoinsIsGreatEnoughColaIsDispensedAndThankYouIsDisplayedFollowedByInsertCoinThenPrice(){
        SetUp();

        vending_machine.fillCoinBox(6, 4, 2);


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

        vending_machine.addProduct("Cola", 5);
        vending_machine.selectProduct("cola");
        vending_machine.selectProduct("cola");
        assertEquals("EXACT CHANGE ONLY", vending_machine.getTextDisplay());

        vending_machine.fillCoinBox(6, 4, 2);
        for(int x = 0; x < 5; x++)
            vending_machine.addCoin(VendingMachine.Coin.QUARTER);

        vending_machine.selectProduct("cola");
        vending_machine.selectProduct("cola");
        assertEquals("INSERT COIN", vending_machine.getTextDisplay());
    }

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("junitfaq.SimpleTest");
    }
}
