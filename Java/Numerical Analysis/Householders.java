import java.util.*;
public class Householders {
  
  public static double[][][] householder(int n, double[][][] matrix) {
    double a, rsq, prod = 0, q = 0;
    double[] v = new double[n];
    double[] u = new double[n];
    double[] z = new double[n];
      
    //step 1
    for(int k = 0; k < n - 2; k++) {
      
      //step 2
      for(int j = k + 1; j < n; j++)
        q += Math.pow(matrix[j][k][k], 2);
      
      //step 3
      if(matrix[k+1][k][k] == 0)
        a = -1 * (Math.sqrt(q));
      else
        a = -1 * (Math.sqrt(q) * matrix[k + 1][k][k] / Math.abs(matrix[k+1][k][k]));
      
      
      //step 4
      rsq = Math.pow(a, 2) - (a * matrix[k + 1][k][k]);
      
      //step 5
      v[k + 1] = matrix[k + 1][k][k] - a;
      for(int j = k + 2; j < n; j++)
        v[j] = matrix[j][k][k];
      
      //step 6
      for(int j = k; j < n; j++) {
        double temp = 0;
        for(int i = k + 1; i < n; i++)
          temp += matrix[j][i][k] * v[i];
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
          matrix[j][l][k+1] = matrix[j][l][k] - (v[l] * z[j]) - (v[j] * z[l]);
          matrix[l][j][k+1] = matrix[j][l][k+1];
        }
        //step 11
        matrix[l][l][k+1] = matrix[l][l][k] - 2 * (v[l] * z[l]);
      }
      
      //setp 12
      matrix[n - 1][n - 1][k+1] = matrix[n - 1][n - 1][k] - 2 * (v[n - 1] * z[n - 1]);
      
      //step 13
      for(int j = k + 2; j < n; j++)
        matrix[k][j][k+1] = matrix[j][k][k+1] = 0;
      
      //step 14
      matrix[k + 1][k][k+1] = matrix[k + 1][k][k] - (v[k + 1] * z[k]);
      matrix[k][k + 1][k+1] = matrix[k + 1][k][k+1];
      
    }
    //step 15
    return matrix;
  }
  
  public static void main(String args[]) {
    double matrix[][][] = new double[4][4][4];
    /*
     {4, 1, -2, 2},
      {1, 2, 0, 1},
      {-2, 0, 3, -2},
      {2, 1, -2, -1}
     */
    matrix[0][0][0] = 4;
    matrix[1][0][0] = 1;
    matrix[2][0][0] = -2;
    matrix[3][0][0] = 2;
    
    matrix[0][1][0] = 1;
    matrix[1][1][0] = 2;
    matrix[2][1][0] = 0;
    matrix[3][1][0] = 1;
    
    matrix[0][2][0] = -2;
    matrix[1][2][0] = 0;
    matrix[2][2][0] = 3;
    matrix[3][2][0] = -2;
    
    matrix[0][3][0] = 2;
    matrix[1][3][0] = 1;
    matrix[2][3][0] = -2;
    matrix[3][3][0] = -1;
    
    matrix = householder(matrix.length, matrix);
    for (int i=0; i< matrix.length ; i++) {
      for (int j=0; j < matrix[0].length ; j++) {
        System.out.printf("%.4f", matrix[i][j][0]);
        System.out.print(" | ");
      }
      System.out.println();
    }
    System.out.println("----------------------------------------");
    for (int i=0; i< matrix.length ; i++) {
      for (int j=0; j < matrix[0].length ; j++) {
        System.out.printf("%.4f", matrix[i][j][1]);
        System.out.print(" | ");
      }
      System.out.println();
    }
    System.out.println("----------------------------------------");
    for (int i=0; i< matrix.length ; i++) {
      for (int j=0; j < matrix[0].length ; j++) {
        System.out.printf("%.4f", matrix[i][j][2]);
        System.out.print(" | ");
      }
      System.out.println();
    }
    System.out.println("----------------------------------------");
    for (int i=0; i< matrix.length ; i++) {
      for (int j=0; j < matrix[0].length ; j++) {
        System.out.printf("%.4f", matrix[i][j][3]);
        System.out.print(" | ");
      }
      System.out.println();
    }
  }
  
}