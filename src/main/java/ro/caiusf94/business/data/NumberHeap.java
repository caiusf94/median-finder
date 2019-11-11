package ro.caiusf94.business.data;

import java.util.PriorityQueue;

/**
 * Data structure holding two priority queues for calculating the median "on the run"
 * Based on an explanation found here: https://stackoverflow.com/a/15319593
 */
public class NumberHeap {

    //Priority queue for numbers less than the current median in a maxheap, i.e median is the maximum, at the root
    private PriorityQueue<Number> maxheap;
    //Priority queue for numbers greater than the current median in a minheap, i.e median is the minimum, at the root
    private PriorityQueue<Number> minheap;


    /**
     * Constructor for a NumberHeap. Initialize minheap and maxheap with appropriate comparators
     */
    public NumberHeap() {
        maxheap = new PriorityQueue<>((i, j) -> i.doubleValue() > j.doubleValue() ? -1 : i == j ? 0 : 1);
        minheap = new PriorityQueue<>((i, j) -> i.doubleValue() > j.doubleValue() ? 1 : i == j ? 0 : -1);
    }


    /**
     * Inserts a {@link Number} object to the appropriate queue and updates the current median.
     *
     * @param number number to be inserted in one of the queues
     */
    public void addNumberToHeap(Number number) {
        insert(number);
        calculateMedian();
    }


    /**
     * Inserts a {@link Number} to a queue to keep the current median updated
     *
     * @param number number to be inserted
     */
    public void insert(Number number) {
        // initialize if empty
        if (isEmpty()) {
            minheap.add(number);
        } else {
            //add to the appropriate queue
            // if number is less than or equal to current median, add to maxheap
            if (Double.compare(number.doubleValue(), calculateMedian()) <= 0) {
                maxheap.add(number);
            }
            // if number is greater than current median, add to minheap
            else {
                minheap.add(number);
            }
        }
        // if any imbalance occurs in the heap sizes
        //i.e, absolute difference of their sizes is greater than one
        rebalanceHeapSizes();
    }


    /**
     * Calculate current median based on values in minheap and maxheap
     *
     * @return median
     */
    public double calculateMedian() {
        //if total size(no. of elements entered) is even, then median is the average of the 2 middle elements
        //i.e, average of the roots of the heaps.
        if (maxheap.size() == minheap.size()) {
            return (maxheap.peek().doubleValue() + minheap.peek().doubleValue()) / 2.0d;
        }
        //else median is middle element, i.e, root of the heap with one element extra
        else if (maxheap.size() > minheap.size()) {
            return maxheap.peek().doubleValue();
        } else {
            return minheap.peek().doubleValue();
        }

    }


    /**
     * Rebalance the heap sizes
     */
    private void rebalanceHeapSizes() {

        //If sizes of heaps differ by 2, then it needs to be rebalanced, since median must be the middle element
        // Rebalancing means moving a node from one heap to the other in order to make them of equal sizes
        if (Math.abs(maxheap.size() - minheap.size()) > 1) {
            //check which one is the culprit and take action by kicking out the root from culprit into victim
            if (maxheap.size() > minheap.size()) {
                minheap.add(maxheap.poll());
            } else {
                maxheap.add(minheap.poll());
            }
        }
    }

    /**
     * Return a message displaying the found median
     *
     * @return
     */
    public String displayMedian() {
        if (isEmpty()) {
            return "No numbers found. Median does not exist.";
        } else {

            return "The median of all numbers is: " + calculateMedian();
        }
    }


    /**
     * Check if there is any input
     *
     * @return true if there is no median to calculate, false otherwise
     */
    private boolean isEmpty() {
        return maxheap.size() == 0 && minheap.size() == 0;
    }

}