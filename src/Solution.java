import java.util.ArrayList;


public class Solution {

    public boolean canBeEqualTo24(int[] nums) {

        int[][] priority = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}};
        char[] op = {'+', '-', '*', '/'};
        int[] numsq = new int[4];
        for (int i1 = 0; i1 < 4; i1++) {
            for (int i2 = 0; i2 < 4; i2++) {
                for (int i3 = 0; i3 < 4; i3++) {
                    for (int i4 = 0; i4 < 4; i4++) {
                        if (i1 == i2 || i1 == i3 || i1 == i4 || i2==i3 || i2==i4 || i3==i4) continue;
                        numsq[0] = nums[i1];
                        numsq[1] = nums[i2];
                        numsq[2] = nums[i3];
                        numsq[3] = nums[i4];

                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                for (int k = 0; k < 4; k++) {
                                    char[] exp_op = {op[i], op[j], op[k]};
                                    for (int l = 0; l < 6; l++) {
                                        if (checkExpression(numsq, exp_op, priority[l])) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    private static boolean checkExpression(int[] nums, char[] op, int[] priority) {
        double a,b,c = 0;
        boolean divisionByZero = false;
        char operation;
        ArrayList<Double> rend_nums = new ArrayList<>();
        ArrayList<Integer> rend_prior = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            rend_nums.add(Double.valueOf(nums[i]));
        }
        for (int i = 0; i < priority.length; i++) {
            rend_prior.add(Integer.valueOf(priority[i]));
        }

        for (int i = 0; i < 3; i++) {
            a = rend_nums.get(rend_prior.get(i));
            b = rend_nums.get(rend_prior.get(i)+1);
            operation = op[priority[i]];
            if (operation == '+') {
                c = a+b;
            }
            if (operation == '-') {
                c = a - b;
            }
            if (operation == '*') {
                c = a * b;
            }
            if (operation == '/') {
                if (b == 0) divisionByZero = true;
                else {
                    c = a / b;
                }
            }

            if (divisionByZero) {
                return false;
            }

            rend_nums.remove(rend_prior.get(i)+1);
            rend_nums.set(rend_prior.get(i), c);

            for (int j = i; j < 3; j++) {
                if (rend_prior.get(j) > rend_prior.get(i)) {
                    rend_prior.set(j, rend_prior.get(j)-1);
                }
            }

        }

        if (Math.abs(rend_nums.get(0)-24) < 0.0000000001) {
            return true;
        } else {
            return false;
        }
    }
}
