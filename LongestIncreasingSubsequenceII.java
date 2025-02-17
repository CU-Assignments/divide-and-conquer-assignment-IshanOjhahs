class Solution {
    public int lengthOfLIS(int[] nums, int k) {
        SegmentTree st = new SegmentTree(new int[100001]);

        int ans = 0;

        for(int i : nums){
            int val = st.query(i - k, i-1);
            st.update(i, val+1);
            ans = Math.max(val+1, ans);
        }

        return ans;
        
    }
}

class SegmentTree {

    int tree[];
    int arr[];

    SegmentTree(int ar[]) {
        arr = ar;
        tree = new int[4 * arr.length];
        build(1, 0, arr.length - 1);
    }
    
    int query(int l, int r) {
        return query(1, 0, arr.length - 1, l, r);
    }

    void update(int pos, int val) {
        update(1, 0, arr.length - 1, pos, val);
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            int left = node * 2;
            int right = node * 2 + 1;
            build(left, start, mid);
            build(right, mid + 1, end);
            tree[node] = Math.max(tree[left], tree[right]);
        }
    }


    private void update(int node, int start, int end, int pos, int val) {
        if (start == end) {
            arr[start] = val;
            tree[node] = val;
        } else {
            int mid = (start + end) / 2;
            if (start <= pos && pos <= mid) {
                update(node * 2, start, mid, pos, val);
            } else {
                update(node * 2 + 1, mid + 1, end, pos, val);
            }
            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }
    }

    private int query(int node, int start, int end, int l, int r) {
        if (end < l || r < start)
            return Integer.MIN_VALUE;

        if (start == end) {
            return tree[node];
        } else if (l <= start && end <= r) {
            return tree[node];
        } else {
            int mid = (start + end) / 2;
            int left = query(node * 2, start, mid, l, r);
            int right = query(node * 2 + 1, mid + 1, end, l, r);

            return Math.max(left, right);
        }
    }



}