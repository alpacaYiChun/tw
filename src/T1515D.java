import java.util.*;

public class T1515D {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testcases = scanner.nextInt();
        for(int t=0;t<testcases;t++) {
            int n = scanner.nextInt();
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            HashMap<Integer, int[]> map = new HashMap<>();
            for(int i=0;i<n;i++) {
                int c = scanner.nextInt();
                int idx = i<l ? 0:1;
                if(!map.containsKey(c)) {
                    map.put(c, new int[]{0,0});
                }
                map.get(c)[idx]++;
            }
            List<Integer> left = new ArrayList<>();
            List<Integer> right = new ArrayList<>();
            int count = 0;
            for(Map.Entry<Integer, int[]> entry : map.entrySet()) {
                int ll = entry.getValue()[0];
                int rr = entry.getValue()[1];
                if(ll<rr) {
                    right.add(rr-ll);
                } else if(ll>rr) {
                    left.add(ll-rr);
                }
            }
            int leftSum = left.stream().reduce(0, Integer::sum);
            int rightSum = right.stream().reduce(0, Integer::sum);
            if(leftSum == rightSum) {
                count = leftSum;
            } else {
                var more = left;
                var less = right;
                int lessSide = rightSum;

                if(leftSum < rightSum) {
                    less = left;
                    more = right;
                    lessSide = leftSum;
                }

                int oddCount = (int)more.stream().filter(x -> x%2==1).count();
                if(oddCount >= lessSide) {
                    int linger = oddCount - lessSide;
                    count = (leftSum + rightSum - linger) / 2 + linger;

                } else {
                    int rich = lessSide - oddCount;
                    if(rich %2 == 1) {
                        count =  (leftSum+rightSum) /2 + 1;
                    } else {
                        count = (leftSum + rightSum) / 2;
                    }
                }
            }

            System.out.println(count);
        }
    }
}
