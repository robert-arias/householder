import java.util.*;
public class Householder {
  
  public static double[][] householder(int n, double[][] matrix) {
    //step 1
    for(int k = 0; k < n - 2; k++) {
      
      double a, rsq, prod = 0, q = 0;
      double[] v = new double[n];
      double[] u = new double[n];
      double[] z = new double[n];
      
      //step 2
      for(int j = k + 1; j < n; j++)
        q += Math.pow(matrix[j][k], 2);
      
      //step 3
      if(matrix[k+1][k] == 0)
        a = -1 * (Math.sqrt(q));
      else
        a = -1 * ((Math.sqrt(q) * matrix[k + 1][k]) / Math.abs(matrix[k+1][k]));
      
      
      //step 4
      rsq = Math.pow(a, 2) - (a * matrix[k + 1][k]);
      
      //step 5
      v[k + 1] = matrix[k + 1][k] - a;
      for(int j = k + 2; j < n; j++)
        v[j] = matrix[j][k];
      
      //step 6
      for(int j = k; j < n; j++) {
        double temp = 0;
        for(int i = k + 1; i < n; i++)
          temp += matrix[j][i] * v[i];
        u[j] = (1 / rsq) * temp;
      }
      
      //step 7
      for(int i = k + 1; i < n; i++)
        prod += v[i] * u[i];
      
      //step 8
      for(int j = k; j < n; j++) {
        z[j] = u[j] - ((prod / (2 * rsq)) * v[j]);
      }
      
      //step 9
      for(int l = k + 1; l < n - 1; l++) {
        //step 10
        for(int j = l + 1; j < n; j++) {
          matrix[j][l] = matrix[j][l] - (v[l] * z[j]) - (v[j] * z[l]);
          matrix[l][j] = matrix[j][l];
        }
        //step 11
        matrix[l][l] = matrix[l][l] - (2 * (v[l] * z[l]));
      }
      
      //setp 12
      matrix[n - 1][n - 1] = matrix[n - 1][n - 1] - 2 * (v[n - 1] * z[n - 1]);
      
      //step 13
      for(int j = k + 2; j < n; j++)
        matrix[k][j] = matrix[j][k] = 0;
      
      //step 14
      matrix[k + 1][k] = matrix[k + 1][k] - (v[k + 1] * z[k]);
      matrix[k][k + 1] = matrix[k + 1][k];
      
    }
    //step 15
    return matrix;
  }
  
  public static void main(String args[]) {
    double matrix[][] ={
      {8, 0.25, 0.5, 2, -1},
      {0.25, -4, 0, 1, 2},
      {0.5, 0, 5, 0.75, -1},
      {2, 1, 0.75, 5, -0.5},
      {-1, 2, -1, -0.5, 6}
    };
    matrix = householder(matrix.length, matrix);
    for (int i=0; i< matrix.length ; i++) {
      for (int j=0; j < matrix[0].length ; j++) {
        System.out.printf("%.4f", matrix[i][j]);
        System.out.print(" | ");
      }
      System.out.println();
    }
  }
  
}