package com.rogue.datastructures;

import com.google.common.base.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Collection of generic tests for dynamic set implementations.
 * 
 * @author R. Matt McCann
 */
public abstract class DynamicSetTest {
    private static final Logger logger = Logger.getLogger("DynamicSetTest");
    
    private final DynamicSet<Integer> target;
    
    protected DynamicSetTest(DynamicSet<Integer> target) {
        this.target = target;
    }
    
    @After
    public void afterEachTest() {
        target.empty();
    }
    
    @Test
    public void testDelete() {
        // Possible states:
        // itemIsInSet, itemIsInSetMultipleTimes
        try {
            logger.info("Try deleting a label that exists in the set only once...");
            logger.info("Inserting 0...");
            target.insert(0);
            logger.info("Searching for 0...");
            Optional<? extends Node<Integer>> node = target.search(0);
            assertTrue("Node exists in set", node.isPresent());
            logger.info("Deleting 0...");
            target.delete(node.get());
            logger.info("Searching for 0...");
            node = target.search(0);
            assertFalse("Node exists in set", node.isPresent());
            
            // Try deleting an object in the set more than once
            logger.info("Try deleting a label that exists in the set more than once...");
            logger.info("Inserting 0...");
            target.insert(0);
            logger.info("Inserting 0...");
            target.insert(0);
            logger.info("Searching for 0...");
            node = target.search(0);
            assertTrue("Node exists in set", node.isPresent());
            logger.info("Deleting node...");
            target.delete(node.get());
            logger.info("Searching for 0...");
            node = target.search(0);
            assertTrue("Node exists in set", node.isPresent());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception occurred", ex);
            fail("Exception occurred!");
        }
    }
    
    @Test
    public void testEmpty() {
        // Possible states: 
        // isEmpty, isNotEmpty      
        try {
            logger.info("Try emptying an already already set...");
            target.empty();
            assertTrue("Set is empty", target.isEmpty());
            
            logger.info("Try emptying a populated set...");
            logger.info("Inserting 0...");
            target.insert(0);
            assertFalse("Set is not empty", target.isEmpty());
            target.empty();
            assertTrue("Set is empty", target.isEmpty());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception occurred", ex);
            fail("Exception occurred!");
        }
    }
    
    @Test
    public void testInsert() {
        // Possible states:
        // setDoesNotAlreadyContainElement, setContainsElementAlready
        try {
            // Try inserting an element not already in the set
            Optional<? extends Node<Integer>> node = target.search(0);
            assertFalse("Label exists in set", node.isPresent());
            target.insert(0);
            node = target.search(0);
            assertTrue("Label exists in set", node.isPresent());
            
            // Try inserting an element already in the set
            target.insert(0);
            node = target.search(0);
            assertTrue("Label exists in set", node.isPresent());
            target.insert(0);
            node = target.search(0);
            assertTrue("Label exists in set", node.isPresent());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception occurred", ex);
            fail("Exception occurred!");
        }
    }
    
    @Test
    public void testMaximum() {
        // Possible states:
        // multipleCopiesOfMax, maxInsertedFirst, maxInsertedLast,
        // removeCurrentMax
        try {
            // Try retrieving the max when there are multiple copies of the max
            target.insert(0);
            target.insert(5);
            target.insert(10);
            target.insert(10);
            target.insert(1);
            target.insert(6);
            target.insert(2);
            Node<Integer> max = target.maximum();
            assertEquals("Max is 10", 10, (int) max.getLabel());
            target.empty();
            
            // Try retrieving the max when it is inserted first
            target.insert(10);
            target.insert(8);
            target.insert(6);
            target.insert(5);
            target.insert(0);
            max = target.maximum();
            assertEquals("Max is 10", 10, (int) max.getLabel());
            target.empty();
            
            // Try retrieving the max when the max is inserted last
            target.insert(0);
            target.insert(5);
            target.insert(6);
            target.insert(8);
            target.insert(10);
            max = target.maximum();
            assertEquals("Max is 10", 10, (int) max.getLabel());
            target.empty();
            
            // Try removing the max
            target.insert(0);
            target.insert(10);
            target.insert(5);
            target.insert(8);
            target.insert(2);
            max = target.maximum();
            assertEquals("Max is 10", 10, (int) max.getLabel());
            target.delete(max);
            max = target.maximum();
            assertEquals("Max is 8", 8, (int) max.getLabel());
            target.empty();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception occurred", ex);
            fail("Exception occurred!");
        }
    }
    
    @Test
    public void testMinimum() {
        // Possible states:
        // multipleCopiesOfMin, minInsertedFirst, minInsertedLast,
        // removeCurrentMin
        try {
            // Test retrieving the min when there are multiple copies
            target.insert(0);
            target.insert(5);
            target.insert(10);
            target.insert(-1);
            target.insert(-1);
            Node<Integer> min = target.minimum();
            assertEquals("Min is -1", -1, (int) min.getLabel());
            target.empty();
            
            // Test retrieving the min when its inserted first
            target.insert(-1);
            target.insert(0);
            target.insert(5);
            target.insert(7);
            target.insert(10);
            min = target.minimum();
            assertEquals("Min is -1", -1, (int) min.getLabel());
            target.empty();
            
            // Test retrieving the min when its inserted last
            target.insert(10);
            target.insert(7);
            target.insert(5);
            target.insert(0);
            target.insert(-1);
            min = target.minimum();
            assertEquals("Min is -1", -1, (int) min.getLabel());
            target.empty();
            
            // Test retrieving the min when after removing the initial min
            target.insert(10);
            target.insert(-1);
            target.insert(0);
            target.insert(5);
            target.insert(7);
            min = target.minimum();
            assertEquals("Min is -1", -1, (int) min.getLabel());
            target.delete(min);
            min = target.minimum();
            assertEquals("Min is 0", 0, (int) min.getLabel());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception occurred", ex);
            fail("Exception occurred!");
        }
    }
    
    @Test
    public void testPredecessor() {
        // Possible states:
        // nodeIsMin, setOnlyHasOneNode, nodeHasPredecessor
        try {
            // Try retrieving the predecessor when the node is the minimum
            target.insert(0);
            target.insert(10);
            target.insert(7);
            target.insert(5);
            target.insert(1);
            Node<Integer> node = target.search(0).get();
            Optional<? extends Node<Integer>> predecessor = target.predecessor(node);
            assertFalse("Predecessor exists", predecessor.isPresent());
            target.empty();
            
            // Try retrieving the predecessor when there is only one node
            target.insert(0);
            node = target.search(0).get();
            predecessor = target.predecessor(node);
            assertFalse("Predecessor exists", predecessor.isPresent());
            target.empty();
            
            // Try retrieving the predecessor when it exists
            target.insert(0);
            target.insert(-1);
            target.insert(5);
            target.insert(7);
            target.insert(10);
            node = target.search(0).get();
            predecessor = target.predecessor(node);
            assertTrue("Predecessor exists", predecessor.isPresent());
            assertEquals("Predecessor is -1", -1, (int) predecessor.get().getLabel());
            target.empty();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception occurred", ex);
            fail("Exception occurred!");
        }
    }
    
    @Test
    public void testSearch() {
        throw new UnsupportedOperationException("TODO!");
    }
    
    @Test
    public void testSuccessor() {
        // Possible states:
        // nodeIsMax, setOnlyHasOneNode, nodeHasSuccessor
        try {
            // Try retrieving the successor when the node is the max
            target.insert(0);
            target.insert(10);
            target.insert(3);
            target.insert(5);
            target.insert(9);
            Node<Integer> node = target.search(10).get();
            Optional<? extends Node<Integer>> successor = target.successor(node);
            assertFalse("Successor exists", successor.isPresent());
            target.empty();
            
            // Try retrieving the successor when there is only one node
            target.insert(0);
            node = target.search(0).get();
            successor = target.successor(node);
            assertFalse("Successor exists", successor.isPresent());
            target.empty();
            
            // Try retrieving the successor when there is one
            target.insert(0);
            target.insert(6);
            target.insert(-1);
            target.insert(8);
            target.insert(2);
            node = target.search(6).get();
            successor = target.successor(node);
            assertTrue("Successor exists", successor.isPresent());
            assertEquals("Successor is 8", 8, (int) successor.get().getLabel());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception occurred", ex);
            fail("Exception occurred!");
        }
    }
}
