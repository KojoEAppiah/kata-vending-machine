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
    public void WhenANickelIsInsertedIntoTheVendingMachineItAddSFiveCentsToItsCoinBox(){
        SetUp();
        vending_machine.addCoin(VendingMachine.Coin.NICKEL);
        assertEquals(5, vending_machine.getCoinBox());
    }

    @Test
    public void WhenADimeIsInsertedIntoTheVendingMachineItAddsTenCentsToItsCoinBox(){
        SetUp();
        vending_machine.addCoin(VendingMachine.Coin.DIME);
        assertEquals(10, vending_machine.getCoinBox());
    }

    @Test
    public void WhenAQuarterIsInsertedIntoTheVendingMachineItAddsTwentyFiveCentsToItsCoinBox(){
        SetUp();
        vending_machine.addCoin(VendingMachine.Coin.QUARTER);
        assertEquals(25, vending_machine.getCoinBox());
    }

    @Test
    public void WhenColaIsSelectedButSoldOutTheSoldOutMessageIsDisplayed(){
        SetUp();
        for(int x = vending_machine.getColaCount(); x > 0; x--)
            vending_machine.selectCola();
        vending_machine.selectCola();
        assertEquals("SOLD OUT", vending_machine.displayText());
    }

    @Test
    public void WhenColaIsSelectedAndAvailableAndCurrenCoinsIsGreatEnoughColaIsDispensedAndThankYouIsDisplayed(){
        SetUp();
        for(int x = 5; x > 0; x++)
            vending_machine.addCoin(VendingMachine.Coin.QUARTER);

        vending_machine.selectCola();
        assertEquals(4, vending_machine.getColaCount());
        assertEquals("THANK YOU", vending_machine.displayText());
    }

}
