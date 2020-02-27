import java.util.*;
public class HouseholderA {
  
  public static double[][] householder(int n, double[][] matrix) {
    //step 1
    for(int k = 0; k < n - 1; k++) {
      
      double a, rsq, prod = 0, q = 0;
      double[] v = new double[n];
      double[] u = new double[n];
      double[] z = new double[n];
      double[] y = new double[n];
      
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
      for(int j = 0; j < n; j++) {
        double temp = 0;
        for(int i = k + 1; i < n; i++)
          temp += matrix[j][i] * v[i];
        u[j] = (1 / rsq) * temp;
        
        temp = 0;
        for(int i = k + 1; i < n; i++)
          temp += matrix[i][j] * v[i];
        y[j] = (1 / rsq) * temp;
      }
      
      //step 7
      for(int i = k + 1; i < n; i++)
        prod += v[i] * u[i];
      
      //step 8
      for(int j = 0; j < n; j++) {
        z[j] = u[j] - (prod / rsq) * v[j];
      }
      
      //step 9
      for(int l = k + 1; l < n; l++) {
        //step 10
        for(int j = 0; j < k; j++) {
          matrix[j][l] = matrix[j][l] - (z[j] * v[l]);
          matrix[l][j] = matrix[l][j] - (y[j] * v[l]);
        }
        //step 11
        for(int j = k + 1; j < n; j++)
          matrix[j][l] = matrix[j][l] - (z[j] * v[l]) - (y[l] * v[j]);
      }
      
    }
    //step 15
    return matrix;
  }
  
  public static void main(String args[]) {
    double matrix[][] ={
      {2, -1, 3},
      {2, 0, 1},
      {-2, 1, 4}
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