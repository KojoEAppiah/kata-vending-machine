import org.junit.Before;
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
    public void WhenColaIsSelectedButSoldOutTheSoldOutMessageIsDisplayed(){
        SetUp();

        vending_machine.selectCola();
        assertEquals("SOLD OUT", vending_machine.getTextDisplay());
    }

    @Test
    public void WhenColaIsSelectedButCurrentCoinsIsInsufficientPriceAndInsertCoinsAndCurrentCoinsDisplayAfterEachOther(){
        SetUp();

        vending_machine.addCola(1);
        vending_machine.selectCola();
        assertEquals("PRICE " + vending_machine.COLAPRICE, vending_machine.getTextDisplay());
        vending_machine.selectCola();
        assertEquals("INSERT COIN", vending_machine.getTextDisplay());
        vending_machine.addCoin(VendingMachine.Coin.QUARTER);
        assertEquals("0.25", vending_machine.getTextDisplay());
        vending_machine.selectCola();
        assertEquals("PRICE " + vending_machine.COLAPRICE, vending_machine.getTextDisplay());
    }

    @Test
    public void WhenColaIsSelectedAndAvailableAndCurrenCoinsIsGreatEnoughColaIsDispensedAndThankYouIsDisplayed(){
        SetUp();
        for(int x = 5; x > 0; x--)
            vending_machine.addCoin(VendingMachine.Coin.QUARTER);

        vending_machine.addCola(1);

        vending_machine.selectCola();
        assertEquals(0, vending_machine.getColaCount());
        assertEquals("THANK YOU", vending_machine.getTextDisplay());
    }

    @Test
    public void WhenColaIsDispensedPropperChangeIsPutInCoinReturn(){
        SetUp();
        for(int x = 5; x > 0; x--)
            vending_machine.addCoin(VendingMachine.Coin.QUARTER);

        vending_machine.addCola(1);
        vending_machine.selectCola();
        assertEquals(0.25, vending_machine.getCoinReturn());
    }

    @Test
    public void WhenReturnCoinsButtonIsPressedCurrentCoinsAreMovedToTheCoinReturn(){
        SetUp();

        vending_machine.addCoin(VendingMachine.Coin.QUARTER);
        vending_machine.returnCoins();

        assertEquals(0.25, vending_machine.getCoinReturn());
    }
}
