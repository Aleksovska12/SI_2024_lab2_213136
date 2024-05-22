package mk.ukim.finki;
import java.util.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.*;


/* F && X && X -> item.getPrice() < =300 , item.getDiscount() = anything, item.getBarcode().charAt(0)=anything

T && F && X ->item.getPrice() >=300 , item.getDiscount() <=0 , item.getBarcode().chatAt(0)=anything

T && T && T -> item.getPrice() >= 300, item.getDiscount() > 0 , item.getBarcode().charAt(0) == '0'

/*
 */
class SILab2Test {
    @Test
    public void EveryBranchMethod(){
        //first test case
        RuntimeException ex;
        List<Item> items = null;
        ex = assertThrows(RuntimeException.class,()->SILab2.checkCart(items,10));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));
        //second test case
        List<Item> items1= Arrays.asList(new Item("Kniga", "K1", 300, 20.0f));
        ex = assertThrows(RuntimeException.class,()->SILab2.checkCart(items1,15));
        assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));
        //third test case
        List<Item> items2= Arrays.asList(new Item("Trkalo", null, 278, 30.0f));
        ex = assertThrows(RuntimeException.class,()->SILab2.checkCart(items2,20));
        assertTrue(ex.getMessage().contains("No barcode!"));
        //fourth test case
        List<Item> items3 = Arrays.asList(new Item("Penkalo","0333",30,0.0f));
        assertTrue(SILab2.checkCart(items3,53));
        //fifth test case
        List<Item> items4 = Arrays.asList(new Item("","0426",900,1.0f));
        assertFalse(SILab2.checkCart(items4,60));
        //sixt test case
        List<Item> items5 = Arrays.asList(new Item("Mouse","2679",500,26.7f));
        assertFalse(SILab2.checkCart(items5,150));
    }


    @Test
    public void MultipleCondition(){

        RuntimeException ex;


        // Multiple condition tests
        // F && X && X: item.getPrice() <= 300
        List<Item> items3 = Arrays.asList(new Item("Item", "123456", 300, 0.1f));
        boolean result = SILab2.checkCart(items3, 270); // Price should be 300 * 0.1 = 30, sum = 270
        assertTrue(result); // Payment 270 is enough for the sum 270

        // T && F && X: item.getPrice() > 300, item.getDiscount() <= 0
        List<Item> items4 = Arrays.asList(new Item("Item", "123456", 350, 0));
        result = SILab2.checkCart(items4, 350); // Price should be 350, sum = 350
        assertTrue(result); // Payment 350 is enough for the sum 350

        // T && T && F: item.getPrice() > 300, item.getDiscount() > 0, item.getBarcode().charAt(0) != '0'
        List<Item> items5 = Arrays.asList(new Item("Item", "123456", 350, 0.2f));
        result = SILab2.checkCart(items5, 280); // Price should be 350 * 0.2 = 70, sum = 280
        assertTrue(result); // Payment 280 is enough for the sum 280

        // T && T && T: item.getPrice() > 300, item.getDiscount() > 0, item.getBarcode().charAt(0) == '0'
        List<Item> items6 = Arrays.asList(new Item("Item", "012345", 350, 0.2f));
        result = SILab2.checkCart(items6, 250); // Price should be 350 * 0.2 = 70, sum = 280 - 30 = 250
        assertTrue(result); // Payment 250 is enough for the sum 250



    }


}