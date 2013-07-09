package com.rogue.datastructures.trees;

import com.rogue.datastructures.DynamicSetTest;
import com.rogue.datastructures.trees.RedBlackNode.Color;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author R. Matt McCann
 */
public class RedBlackTreeTest extends DynamicSetTest {
    private static Comparator<Integer> comparator =
        new Comparator<Integer>() {
            @Override
            public int compare(Integer obj1, Integer obj2) {
                if (obj1 < obj2) return -1;
                else if (obj1 == obj2) return 0;
                else return 1;
            }
        };
    
    private static final Logger logger = Logger.getLogger("RedBlackTreeTest");
    
    public RedBlackTreeTest() {
        super(new RedBlackTree(comparator));
    }
    
    @Test
    public void testMaintainRedBlackStateAfterInsert() {
        // Initial state:
        //                   11B
        //        2R                    14B
        //    1B       7B                      15R
        //          5R    8R    
        //    z=>4R
        //
        // After maintenance:
        //                   7B
        //           2R              11R
        //      1B       5B       8B       14B
        //             4R                       15R
        try {
            // Set up the tree
            RedBlackTree tree = new RedBlackTree(comparator);
            RedBlackNode<Integer> eleven = new RedBlackNode();
            eleven.setLabel(11);
            eleven.setColor(Color.BLACK);
            eleven.setParent(tree.getSentinel());
            tree.setRoot(eleven);
            RedBlackNode<Integer> two = new RedBlackNode();
            two.setLabel(2);
            two.setColor(Color.RED);
            two.setParent(eleven);
            eleven.setLeftChild(two);
            RedBlackNode<Integer> fourteen = new RedBlackNode();
            fourteen.setLabel(14);
            fourteen.setColor(Color.BLACK);
            fourteen.setParent(eleven);
            eleven.setRightChild(fourteen);
            RedBlackNode<Integer> one = new RedBlackNode();
            one.setLabel(1);
            one.setColor(Color.BLACK);
            one.setParent(two);
            two.setLeftChild(one);
            RedBlackNode<Integer> seven = new RedBlackNode();
            seven.setLabel(7);
            seven.setColor(Color.BLACK);
            seven.setParent(two);
            two.setRightChild(seven);
            RedBlackNode<Integer> fifteen = new RedBlackNode();
            fifteen.setLabel(15);
            fifteen.setColor(Color.RED);
            fifteen.setParent(fourteen);
            fourteen.setRightChild(fifteen);
            RedBlackNode<Integer> five = new RedBlackNode();
            five.setLabel(5);
            five.setColor(Color.RED);
            five.setParent(seven);
            seven.setLeftChild(five);
            RedBlackNode<Integer> eight = new RedBlackNode();
            eight.setLabel(8);
            eight.setColor(Color.RED);
            eight.setParent(seven);
            seven.setRightChild(eight);
            RedBlackNode<Integer> four = new RedBlackNode();
            four.setLabel(4);
            four.setColor(Color.RED);
            four.setParent(five);
            five.setLeftChild(four);
            
            // Execute the method
            tree.maintainRedBlackStateAfterInsert(four);
            
            // Check the results
            assertEquals("7 is the new root", 7, tree.getRoot().getLabel());
            assertEquals("7 is black", Color.BLACK, seven.getColor());
            assertEquals("7's parent is sentinel", tree.getSentinel(), seven.getParent());
            assertEquals("2 is 7's left child", 2, (int) seven.getLeftChild().getLabel());
            assertEquals("11 is 7's right child", 11, (int) seven.getRightChild().getLabel());
            //assertEquals("2 is red", Color.RED, )
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception occurred!", ex);
            throw new RuntimeException(ex);
        }
    }
    
    @Test
    public void testRotateLeft() {
        /**
         * Before Rotation:  
         *        0
         *     1     2
         *          3 4
         * After Rotation:
         *        2
         *     0     4 
         *    1 3  
         */
        try {
            RedBlackTree tree = new RedBlackTree(comparator);
            RedBlackNode zero = new RedBlackNode();
            zero.setLabel(0);
            RedBlackNode one = new RedBlackNode();
            one.setLabel(1);
            RedBlackNode two = new RedBlackNode();
            two.setLabel(2);
            RedBlackNode three = new RedBlackNode();
            three.setLabel(3);
            RedBlackNode four = new RedBlackNode();
            four.setLabel(4);
            two.setLeftChild(three);
            three.setParent(two);
            two.setRightChild(four);
            four.setParent(two);
            zero.setLeftChild(one);
            one.setParent(zero);
            zero.setRightChild(two);
            two.setParent(zero);
            zero.setParent(tree.getSentinel());
            tree.setRoot(zero);

            tree.rotateLeft(zero);

            assertEquals("Left child of 2 is 0", 0, two.getLeftChild().getLabel());
            assertEquals("Rigth child of 2 is 4", 4, two.getRightChild().getLabel());
            assertEquals("Left child of 0 is 1", 1, zero.getLeftChild().getLabel());
            assertEquals("Right child of 0 is 3", 3, zero.getRightChild().getLabel());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception occurred!", ex);
            throw new RuntimeException(ex);
        }
    }
    
    @Test
    public void testRotateRight() {
        /**
         * Before Rotation:
         *        2
         *     0     4
         *    1 3
         * After Rotation:
         *        0
         *     1     2
         *          3 4
         */
        try {
            RedBlackTree tree = new RedBlackTree(comparator);
            RedBlackNode zero = new RedBlackNode();
            zero.setLabel(0);
            RedBlackNode one = new RedBlackNode();
            one.setLabel(1);
            RedBlackNode two = new RedBlackNode();
            two.setLabel(2);
            RedBlackNode three = new RedBlackNode();
            three.setLabel(3);
            RedBlackNode four = new RedBlackNode();
            four.setLabel(4);
            zero.setLeftChild(one);
            zero.setRightChild(three);
            two.setLeftChild(zero);
            two.setRightChild(four);
            two.setParent(tree.getSentinel());
            tree.setRoot(two);

            tree.rotateRight(two);

            assertEquals("Left child of zero is 1", 1, zero.getLeftChild().getLabel());
            assertEquals("Right child of zero is 2", 2, zero.getRightChild().getLabel());
            assertEquals("Left child of two is 3", 3, two.getLeftChild().getLabel());
            assertEquals("Right child of two is 4", 4, two.getRightChild().getLabel());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception occurred!", ex);
            throw new RuntimeException(ex);
        }
    }
    
    @Test
    public void testTransplant() {
        // States:
        // transplantedNodeIsRoot, transplantedNodeIsLeftChild,
        // transplantedNodeIsRightChild
        try {
            logger.info("Testing transplanting the root...");
            RedBlackTree tree = new RedBlackTree(comparator);
            RedBlackNode zero = new RedBlackNode();
            zero.setLabel(0);
            RedBlackNode one = new RedBlackNode();
            one.setLabel(1);
            tree.setRoot(zero);
            zero.setParent(tree.getSentinel());
            tree.transplant(zero, one);
            assertEquals("Root is one", one, tree.getRoot());
            assertEquals("One's parent is sentinel", tree.getSentinel(), one.getParent());
            
            logger.info("Testing transplanting a left child...");
            RedBlackNode two = new RedBlackNode();
            two.setLabel(2);
            zero.setLeftChild(one);
            one.setParent(zero);
            tree.transplant(one,two);
            assertEquals("Zero's left child is two", two, zero.getLeftChild());
            assertEquals("Two's parent is zero", zero, two.getParent());
            
            logger.info("Testing transplanting a right child...");
            zero.setRightChild(one);
            one.setParent(zero);
            tree.transplant(one, two);
            assertEquals("Zero's righ child is two", two, zero.getRightChild());
            assertEquals("Two's parent is zero", zero, two.getParent());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception occurred!", ex);
            throw new RuntimeException(ex);
        }
    }
}
