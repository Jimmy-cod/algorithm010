package Week04;

public class LC74_search_2d_matrix {
    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix == null || matrix.length ==0){
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0, right = m*n-1;
        int mid=0;
        while (left<=right){
            mid=left+(right-left)/2;
            // int r = mid/rows;
            // int c = mid%rows;
            int midVal = matrix[mid/n][mid%n];
            if (target == midVal){
                return true;
            }
            else if(target > midVal){
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return false;
    }


    //O(mlogn)
    public boolean searchMatrix_2(int[][] matrix, int target) {
        //1.get row and col from matrix
        //2.check targe with end of element for each row
        //3.if small than the element, binary search in the row
        if (matrix == null || matrix.length ==0 || matrix[0].length == 0){
            return false;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int r=0;r<rows;r++){
            if (target==matrix[r][cols-1]){
                return true;
            }
            if (target<matrix[r][cols-1]){
                //binary search here
                int left = 0, right = cols-1;
                int mid=0;
                while (left<=right){
                    mid=left+(right-left)/2;
                    if (target == matrix[r][mid]){
                        return true;
                    }
                    else if(target > matrix[r][mid]){
                        left = mid + 1;
                    }
                    else {
                        right = mid - 1;
                    }
                }
                return false;
            }
        }
        return false;
    }
}
