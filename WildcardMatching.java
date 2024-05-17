// Time Complexity : O(m*n), 
// Space Complexity :  O(m*n) 
// Did this code successfully run on Leetcode : yes
//Approach : incrementally check if source can be made using pattern string.

class Solution {
    public boolean isMatch(String s, String p) {
        int m = p.length();
        int n = s.length();

        boolean[][] mat = new boolean[m+1][n+1]; 
        mat[0][0] = true;
        for(int i = 1 ; i <= m; i++){
            if(p.charAt(i-1) == '*' ){
                mat[i][0] = mat[i-1][0];
            }
        }
        
        for(int i = 1 ; i <= m; i++){
            for(int j = 1 ; j <= n; j++){
                if(p.charAt(i-1) == '*' ){
                    mat[i][j] = mat[i][j-1] || mat[i-1][j];
                }
                else if(p.charAt(i-1) == s.charAt(j-1) || p.charAt(i-1) == '?'){
                    //diag up
                    mat[i][j] = mat[i-1][j-1];
                }
                else{
                    mat[i][j] = false;
                }
            }
        }
        
        return mat[m][n];
    }
}

//Approach 2 - Two pointers
// Time Complexity : Worst case : O(m*n), Average Case : nlogm - proven for wildcard matching by Carl Barton
// Space Complexity :  O(1) 
// Did this code successfully run on Leetcode : yes
//Approach : Use two pointers iterate over both the strings, at first do not match the *, if it does not work then go back and match it.

class Solution {
    public boolean isMatch(String s, String p) {
        int m = p.length();
        int n = s.length();

        int pp = 0;
        int sp = 0;

        int sStar = -1;
        int pStar = -1;

        while(sp < n){
            if(pp < m && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')){
                sp++;
                pp++;
            }
            else if(pp < m && p.charAt(pp) == '*'){
                sStar = sp;
                pStar = pp;
                pp++; // 0 case - dont match it
            }
            else if(pStar == -1){ // no previous * encountered
                return false;
            }
            else{ // mismatch
                sStar++;
                sp = sStar;
                pp = pStar+1; // 1 case - Go back and match it
            }
        }
        // if(sp < n) return false;
        while(pp < m){
            if(p.charAt(pp) != '*') return false;
            pp++;
        }
        return true;
    }
}