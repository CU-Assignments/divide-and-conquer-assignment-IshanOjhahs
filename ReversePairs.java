class Solution {
    public int reversePairs(int[] nums) {
        int ans = 0;
        List<Long> res = new ArrayList<>();
        res.add((long) nums[nums.length - 1] * 2);

        for (int i = nums.length - 2; i >= 0; i--) {
            ans += LessThanx(res, nums[i]);
            update(res, (long) nums[i] * 2);
        }

        return ans;
    }

    private int LessThanx(List<Long> res, long val) {
        if (res.get(0) >= val) {
            return 0;
        }

        if (res.get(res.size() - 1) < val) {
            return res.size();
        }

        int lo = 0, hi = res.size() - 1;

        while (lo < hi) {
            int mid = (lo + hi) / 2;

            if (res.get(mid) < val) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo;
    }

    private void update(List<Long> res, long val) {
        int index = Collections.binarySearch(res, val);
        if (index < 0) {
            index = -(index + 1);
        }
        res.add(index, val);
    }
}