public class MajorityElement {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 2, 2, 4};
        int result = getMajorityElement(nums);

        if (result != -1) {
            System.out.println("El elemento mayoritario es: " + result);
        } else {
            System.out.println("No hay elemento mayoritario en el arreglo.");
        }
    }

    public static int getMajorityElement(int[] nums) {
        int major = nums[0];
        int counts = 0;

        for (int num : nums) {
            if (counts == 0) {
                major = num;
                counts++;
            } else if (major == num) {
                counts++;
            } else {
                counts--;
            }
        }

        if (isMajority(nums, major)) {
            return major;
        }

        return -1;
    }

    public static boolean isMajority(int[] nums, int candidate) {
        int count = 0;

        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }

        return count > nums.length / 2;
    }
}
