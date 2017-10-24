import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendingMachineTest {

    @Test
    public void WhenANickelIsInsertedIntoTheVendingMachineItAddSFiveCentsToItsCoinBox(){

        VendingMachine vending_machine = new VendingMachine();
        vending_machine.addCoin(VendingMachine.Coin.NICKEL);
        assertEquals(5, vending_machine.getCoinBox());
    }

    @Test
    public void WhenADimeIsInsertedIntoTheVendingMachineItAddsTenCentsToItsCoinBox(){

        VendingMachine vending_machine = new VendingMachine();
        vending_machine.addCoin(VendingMachine.Coin.DIME);
        assertEquals(10, vending_machine.getCoinBox());
    }

    @Test
    public void WhenAQuarterIsInsertedIntoTheVendingMachineItAddsTwentyFiveCentsToItsCoinBox(){

        VendingMachine vending_machine = new VendingMachine();
        vending_machine.addCoin(VendingMachine.Coin.QUARTER);
        assertEquals(25, vending_machine.getCoinBox());
    }
}
