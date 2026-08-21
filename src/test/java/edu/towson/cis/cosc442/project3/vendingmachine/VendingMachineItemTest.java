package edu.towson.cis.cosc442.project3.vendingmachine;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.towson.cis.cosc442.project3.vendingmachine.VendingMachineException;
import edu.towson.cis.cosc442.project3.vendingmachine.VendingMachineItem;

public class VendingMachineItemTest {

    @Test
    public void testGetName() throws VendingMachineException {
        VendingMachineItem item = new VendingMachineItem("Chips", 1.50);

        assertEquals("Chips", item.getName());
    }
    @Test
    public void testGetPrice() throws VendingMachineException {
        VendingMachineItem item = new VendingMachineItem("Chips", 1.50);

        assertEquals(1.50, item.getPrice(), 0.001);
    }
}