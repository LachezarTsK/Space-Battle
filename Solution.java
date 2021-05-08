package spaceBattle;

import java.util.Stack;

public class Solution {
  public static void main(String[] args) {
    // int[] nums = {1, 5, 3, -6};
    // int[] nums = {1, 5, 3, -6, 7};
    int[] nums = {-1, 10, 3, -6, 7};

    Solution sol = new Solution();
    System.out.println(Arrays.toString(sol.solve(nums)));
  }

  /*
  By the problem design on binarysearch.com, we have to work
  around the given method 'public int solve(int[] nums)'
  so that the code can be run on the website. Even though the name 'solve'
  does not make a lot of sense, it is left as it is, so that the code can
  be run directly on the website, without any modifications.
  */
  public int[] solve(int[] nums) {

    Stack<Integer> rockets = new Stack<Integer>();

    for (int i = 0; i < nums.length; i++) {

      // Add rockets moving from left to right.
      if (nums[i] > 0) {
        rockets.push(nums[i]);
      } else {

        /*
        The rocket moving from the right side to the left, destroys all smaller rockets on its path
        that move from left to right, until it encounters an equal-sized or a bigger rocket
        that moves from left to right.
        */
        while (!rockets.isEmpty() && rockets.peek() > 0 && rockets.peek() < Math.abs(nums[i])) {
          rockets.pop();
        }

        /*
        The rocket moving from the right side to the left, encounters an equal-sized rocket,
        that moves from left to right, and they mutually destroy.
        */
        if (!rockets.isEmpty() && rockets.peek() > 0 && rockets.peek() == Math.abs(nums[i])) {
          rockets.pop();
        }

        /*
        The rocket moving from the right side to the left, does not encounter any other rockets,
        either because the rockets ahead also move from right to left with the same speed,
        or there are no other rockets ahead.
        */
        else if ((!rockets.isEmpty() && rockets.peek() < 0) || rockets.isEmpty()) {
          rockets.push(nums[i]);
        }

        /*
        If neither of the above scenarios takes place, it means that the rocket moving from
        the right side to the left, has encountered a larger rocket that moves from left to right,
        and is therefore destroyed (i.e. not added to stack 'rockets').
        */
      }
    }

    int[] stateOfRockets_afterAllCollisions = new int[rockets.size()];
    int index = rockets.size() - 1;
    while (!rockets.isEmpty()) {
      stateOfRockets_afterAllCollisions[index--] = rockets.pop();
    }
    return stateOfRockets_afterAllCollisions;
  }
}
