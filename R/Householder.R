"A <-  matrix(data = c(12, 10, 4,
                      10, 8, -5,
                      4,-5, 3),
             nrow = 3, 
             ncol = 3)
n <-  nrow(A)"
"A <-  matrix(data = c(12, 10, 4,
                      10, 8, -5,
                      4,-5, 3),
             nrow = 3, 
             ncol = 3)
n <-  nrow(A)"
"A <-  matrix(data = c(8, 0.25, 0.5, 2, -1,
                      0.25, -4, 0, 1, 2,
                      0.5, 0, 5, 0.75, -1,
                      2, 1, 0.75, 5, -0.5,
                      -1, 2, -1, -0.5, 6),
             nrow = 5, 
             ncol = 5)
n <-  nrow(A)"


A <-  matrix(data = c(4, 1, -2, 2,
                      1, 2, 0, 1,
                      -2, 0, 3, -2,
                      2, 1, -2, -1),
             nrow = 4,
             ncol = 4)
n <-  nrow(A)
#Step 1
for (k in 1:(n-2)) {
  
  v <- numeric(n)
  u <- numeric(n)
  z <- numeric(n)
  
  #Step 2
  q = sum(A[(k+1):n,k]^2)
  
  #Step 3
  alpha <- ifelse(test = A[k+1,k] == 0,
                  yes = -sqrt(q),
                  no =  -(sqrt(q) * A[k+1,k]) / (abs(A[k+1,k])))
  
  #Step 4
  rsq <- alpha^2 - (alpha * A[k+1,k])
  
  #Step 5
  v[k+1] <- A[k+1,k] - alpha
  v[(k+2):n] <- A[(k+2):n,k]
  
  #Step 6
  for(j in k:n)
    u[j] <- (1 / rsq) * sum(A[j,(k+1):n]*v[(k+1):n])
  
  #Step 7
  prod <- sum(v[(k+1):n] * u[(k+1):n])
  
  #Step 8
  z[k:n] <- u[k:n] - (prod / (2 * rsq)) * v[k:n]
  
  #Step 9
  for (l in (k+1):(n-1)) {
    #Step 10
    A[(l+1):n,l] <- A[(l+1):n,l] - (v[l] * z[(l+1):n]) - (v[(l+1):n]*z[l])
    A[l,(l+1):n] <- A[(l+1):n,l]
    
    #Step 11
    A[l,l] <- A[l,l] - (2 * (v[l] * z[l]))
  }
  
  #Step 12
  A[n,n] <- A[n,n] - 2 * (v[n] * z[n])
  
  #Step 13
  A[(k+2):n,k] <- 0
  A[k,(k+2):n] <- A[(k+2):n,k]
  
  #Step 14
  A[k+1,k] = A[k+1,k] - (v[k+1] * z[k])
  A[k,k+1] = A[k+1,k]
}

A



poly.neville <- function(x, y, x0) {
  
  n <- length(x)
  q <- matrix(data = 0, n, n)
  q[,1] <- y
  
  for (i in 2:n) {
    for (j in i:n) {
      q[j,i] <- ((x0 - x[j-i+1]) * q[j,i-1] - (x0 - x[j]) * q[j-1,i-1]) / (x[j] - x[j-i+1])
    }
  }
  
  res <- list('Approximated value'=q[n,n], 'Neville iterations table'=q)
  return(res)
}

x <- c(1, 1.3, 1.6, 1.9, 2.2)
y <- c(0.7651977, 0.620086, 0.4554022, 0.2818186, 0.1103623)

poly.neville(x, y, 1.5)




