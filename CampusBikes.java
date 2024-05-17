// Time Complexity : m*n 
// Space Complexity :  m*n
// Did this code successfully run on Leetcode : yes
//approach : Find range of distances by computing for each worker and bike pair and then do bucket sort and assign the bikes.


class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if(workers == null || workers.length == 0 || bikes == null || bikes.length == 0){
            return new int[0];
        }

        int n = workers.length;
        int m = bikes.length;
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for(int i  = 0; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                int dist = manhattanDist(workers[i], bikes[j]);
                if(!map.containsKey(dist)){
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[]{i,j});

                min = Math.min(min, dist);
                max = Math.max(max, dist);
            }
        }
        // System.out.println(map);
        int [] result = new int[n];
        boolean[] assignedW = new boolean[n];
        boolean[] occupiedB = new boolean[m];
        int count = 0;
        for(int d = min ; d <= max ; d++){
            List<int[]> li = map.get(d);
            if(li != null){
                for(int[] wb : li){
                    int w = wb[0];
                    int b = wb[1];

                    if(!assignedW[w] && !occupiedB[b]){
                        assignedW[w] = true;
                        occupiedB[b] = true;
                        result[w] = b;
                        count++;
                        if(count == m){
                            return result;
                        }
                        
                    }
                }
            }
        }

        return result;
    }

    private int manhattanDist(int[] w, int[] b){
        return Math.abs(w[0] - b[0]) + Math.abs(w[1] - b[1]);
    }
}