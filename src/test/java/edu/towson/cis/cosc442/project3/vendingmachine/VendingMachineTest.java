package edu.towson.cis.cosc442.project3.vendingmachine;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.towson.cis.cosc442.project3.vendingmachine.VendingMachine;
import edu.towson.cis.cosc442.project3.vendingmachine.VendingMachineException;
import edu.towson.cis.cosc442.project3.vendingmachine.VendingMachineItem;

public class VendingMachineTest {

    @Test
    public void testInitialBalance() {
        VendingMachine machine = new VendingMachine();

        assertEquals(0.0, machine.getBalance(), 0.001);
    }

    @Test
    public void testInsertMoney() throws VendingMachineException {
        VendingMachine machine = new VendingMachine();

        machine.insertMoney(2.00);

        assertEquals(2.00, machine.getBalance(), 0.001);
    }
    @Test
    public void testAddAndRemoveItem() throws VendingMachineException {
        VendingMachine machine = new VendingMachine();
        VendingMachineItem item = new VendingMachineItem("Chips", 1.50);

        machine.addItem(item, "A");

        VendingMachineItem removed = machine.removeItem("A");

        assertEquals("Chips", removed.getName());
        assertEquals(1.50, removed.getPrice(), 0.001);
    }
    @Test(expected = VendingMachineException.class)
    public void testNegativePriceThrowsException() throws VendingMachineException {
        new VendingMachineItem("Chips", -1.00);
    }
    @Test
    public void testAddAndRemoveItemFromB() throws VendingMachineException {
        VendingMachine machine = new VendingMachine();
        VendingMachineItem item = new VendingMachineItem("Soda", 2.00);

        machine.addItem(item, "B");

        VendingMachineItem removed = machine.removeItem("B");

        assertEquals("Soda", removed.getName());
    }
    @Test(expected = VendingMachineException.class)
    public void testInvalidSlotCode() throws VendingMachineException {
        VendingMachine machine = new VendingMachine();
        VendingMachineItem item = new VendingMachineItem("Chips", 1.50);

        machine.addItem(item, "Z");
    }
    @Test(expected = VendingMachineException.class)
    public void testNegativeMoneyThrowsException() throws VendingMachineException {
        VendingMachine machine = new VendingMachine();

        machine.insertMoney(-1.00);
    }
    @Test(expected = VendingMachineException.class)
    public void testAddItemToOccupiedSlot() throws VendingMachineException {
        VendingMachine machine = new VendingMachine();

        VendingMachineItem chips =
            new VendingMachineItem("Chips", 1.50);

        VendingMachineItem soda =
            new VendingMachineItem("Soda", 2.00);

        machine.addItem(chips, "A");

        machine.addItem(soda, "A");
    }
    @Test(expected = VendingMachineException.class)
    public void testRemoveFromEmptySlot() throws VendingMachineException {
        VendingMachine machine = new VendingMachine();

        machine.removeItem("A");
    }
    @Test
    public void testSuccessfulPurchase() throws VendingMachineException {
        VendingMachine machine = new VendingMachine();
        VendingMachineItem item = new VendingMachineItem("Chips", 1.50);

        machine.addItem(item, "A");
        machine.insertMoney(2.00);

        boolean purchased = machine.makePurchase("A");

        assertEquals(true, purchased);
        assertEquals(0.50, machine.getBalance(), 0.001);
    }
    @Test
    public void testPurchaseNotEnoughMoney() throws VendingMachineException {
        VendingMachine machine = new VendingMachine();
        VendingMachineItem item = new VendingMachineItem("Chips", 1.50);

        machine.addItem(item, "A");
        machine.insertMoney(1.00);

        boolean purchased = machine.makePurchase("A");

        assertEquals(false, purchased);
        assertEquals(1.00, machine.getBalance(), 0.001);
    }
    @Test
    public void testReturnChange() throws VendingMachineException {
        VendingMachine machine = new VendingMachine();

        machine.insertMoney(2.00);

        double change = machine.returnChange();

        assertEquals(2.00, change, 0.001);
        assertEquals(0.00, machine.getBalance(), 0.001);
    }
    @Test
    public void testGetItemFromC() throws VendingMachineException {
        VendingMachine machine = new VendingMachine();
        VendingMachineItem item = new VendingMachineItem("Candy", 1.25);

        machine.addItem(item, "C");

        VendingMachineItem result = machine.getItem("C");

        assertEquals("Candy", result.getName());
        assertEquals(1.25, result.getPrice(), 0.001);
    }
    @Test
    public void testAddAndRemoveItemFromD() throws VendingMachineException {
        VendingMachine machine = new VendingMachine();
        VendingMachineItem item = new VendingMachineItem("Water", 1.00);

        machine.addItem(item, "D");

        VendingMachineItem removed = machine.removeItem("D");

        assertEquals("Water", removed.getName());
    }
    @Test
    public void testPurchaseEmptySlot() {
        VendingMachine machine = new VendingMachine();

        boolean purchased = machine.makePurchase("A");

        assertEquals(false, purchased);
    }
    @Test
    public void testZeroPriceAllowed() throws VendingMachineException {
        VendingMachineItem item = new VendingMachineItem("Chips", 0.00);

        assertEquals(0.00, item.getPrice(), 0.001);
    }
    @Test
    public void testInsertZeroMoney() throws VendingMachineException {
        VendingMachine machine = new VendingMachine();

        machine.insertMoney(0.00);

        assertEquals(0.00, machine.getBalance(), 0.001);
    }
    @Test
    public void testPurchaseExactAmount() throws VendingMachineException {
        VendingMachine machine = new VendingMachine();
        VendingMachineItem item = new VendingMachineItem("Chips", 1.50);

        machine.addItem(item, "A");
        machine.insertMoney(1.50);

        boolean purchased = machine.makePurchase("A");

        assertEquals(true, purchased);
        assertEquals(0.00, machine.getBalance(), 0.001);
    }
    @Test
    public void testDifferentSlotsStaySeparate() throws VendingMachineException {
        VendingMachine machine = new VendingMachine();

        VendingMachineItem chips = new VendingMachineItem("Chips", 1.50);
        VendingMachineItem soda = new VendingMachineItem("Soda", 2.00);

        machine.addItem(chips, "A");
        machine.addItem(soda, "B");

        assertEquals("Chips", machine.getItem("A").getName());
        assertEquals("Soda", machine.getItem("B").getName());
    }
    @Test
    public void testSlotsACDStaySeparate() throws VendingMachineException {
        VendingMachine machine = new VendingMachine();

        VendingMachineItem chips = new VendingMachineItem("Chips", 1.50);
        VendingMachineItem candy = new VendingMachineItem("Candy", 1.25);
        VendingMachineItem water = new VendingMachineItem("Water", 1.00);

        machine.addItem(chips, "A");
        machine.addItem(candy, "C");
        machine.addItem(water, "D");

        assertEquals("Chips", machine.getItem("A").getName());
        assertEquals("Candy", machine.getItem("C").getName());
        assertEquals("Water", machine.getItem("D").getName());
    }
}