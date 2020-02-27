import java.util.*;
public class Test {
  public static ArrayList<double[][]> householder(double[][] A) {
    int m = A.length; // rows
    int n = A[0].length; // cols
    byte sign; 
    double sum, A0,tmp;
    ArrayList<double[][]> QR = new ArrayList<>();
    double[] v = new double[m];
    double[][] Q = identity(m);
    for(int k = 0; k < n; k++) {
      sum = 0;
      sign = 1;
      A0 = A[k][k];
      if(A0 < 0){
        sign = -1;
      }
      for(int i = k; i < m; i++) {
        sum += A[i][k]*A[i][k];
      }
      tmp = sign*Math.sqrt(sum);
      v[k] = tmp + A0;          
      tmp = Math.sqrt(2*(sum + A0*tmp));
      v[k] /= tmp;
      for(int i = k+1; i < m; i++) {
        v[i] = A[i][k]/tmp;
      }                        
      for(int j = 0; j < n; j++) {
        sum = 0;
        for(int i = k; i < m; i++) {
          sum += v[i]*A[i][j];
        }
        for(int i = k; i < m; i++) {
          A[i][j] -= 2*v[i]*sum;
        }
      }            
      for(int j = 0; j < m; j++) {
        sum = 0;
        for(int i = k; i < m; i++) {
          sum += v[i]*Q[i][j];
        }
        for(int i = k; i < m; i++) {
          Q[i][j] -= 2*v[i]*sum;
        }
      }                
    }
    QR.add(transpose(Q));
    QR.add(A);
    return QR;
  }
  
  private static double[][] identity(int m) {
    double a[][] = new double[m][m];
    
    for(int i = 0; i < m; i++) {
      a[i][i] = 1;
    }
    
    return a;
  }
  
  private static double[][] transpose(double[][] q) {
    double[][] temp = new double[q[0].length][q.length];
    for (int i = 0; i < q.length; i++)
      for (int j = 0; j < q[0].length; j++)
      temp[j][i] = q[i][j];
    return temp;
  }
  
  public static void main(String args[]) {
    double matrix[][] ={
      {4, 1, -2, 2},
      {1, 2, 0, 1},
      {-2, 0, 3, -2},
      {2, 1, -2, -1}
    };
    ArrayList<double[][]> QR = householder(matrix);
    matrix = QR.get(1);
    for (int i=0; i< matrix.length ; i++) {
      for (int j=0; j < matrix[0].length ; j++) {
        System.out.printf("%.4f", matrix[i][j]);
        System.out.print(" | ");
      }
      System.out.println();
    }
  }
  
}