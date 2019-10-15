
df <- read.delim("input.txt")
m <- data.matrix(df)

n = as.integer(m[1,1])  #����������� ������������
N = as.integer(m[2,1]) #���������� ����� �� ������ ���������, ���� ��� ��������� �� 6
eps = 0.5 #�������� �������������� �������

#����������� � ����������� ������� �� ������� ���������, �� ����� ���� 1, 2 ��� 3
#number_of_classes_each_dimension = floor(runif(n, min = 1, max = 3)) - ��� - num
#��� - int
lo = as.integer(1)
hi = as.integer(4)
number_of_classes_each_dimension = as.integer((hi-lo) * runif(n) + lo)

#����� ����������� [-10, 10] ��� ������� ���������
left_border = -10
right_border = 10
#����������� � ������������ ������� �� ����������
#������� - ���������, ������� - �������������
centres = runif(n*2, min = left_border, max = right_border)
dim(centres) <- c(n, 2)

#��������� ����������� ����� �� �����������
for(i in 1:n){
  centres[i,1:(number_of_classes_each_dimension[i] - 1)] <- sort(centres[i,1:(number_of_classes_each_dimension[i] - 1)])
}

#���������� �����

#�������, ��� ������ - �����, ������� - �� �������� �� ������� ���������
vectors <- rep(0, times = n*N)
dim(vectors) <- c(N, n)

for (i in 1:n) {
  
  counter = 1
  
  if (number_of_classes_each_dimension[i] == 1) {
    number_of_points_in_first_class = N
    for (k in 1:number_of_points_in_first_class) {
      vectors[counter, i] = runif(1, min = left_border, max = right_border)
      counter = counter + 1
    }
  }
  
  if (number_of_classes_each_dimension[i] == 2) {
    number_of_points_in_first_class = N/2
    number_of_points_in_second_class = N/2
    for (k in 1:number_of_points_in_first_class) {
      vectors[counter, i] = runif(1, min = left_border, max = centres[i, 1] + eps)
      counter = counter + 1
    }
    for (k in 1:number_of_points_in_second_class) {
      vectors[counter, i] = runif(1, min = centres[i, 1] - eps, max = right_border)
      counter = counter + 1
    }
  }
  
  if (number_of_classes_each_dimension[i] == 3) {
    number_of_points_in_first_class = N/3
    number_of_points_in_second_class = N/3
    number_of_points_in_third_class = N/3
    for (k in 1:number_of_points_in_first_class) {
      vectors[counter, i] = runif(1, min = left_border, max = centres[i, 1] + eps)
      counter = counter + 1
    }
    for (k in 1:number_of_points_in_second_class) {
      vectors[counter, i] = runif(1, min = centres[i, 1] - eps, max = centres[i, 2] +eps)
      counter = counter + 1
    }
    for (k in 1:number_of_points_in_third_class) {
      vectors[counter, i] = runif(1, min = centres[i, 2] - eps, max = right_border)
      counter = counter + 1
    }
  }
}

#op <- par(bg = "light blue")
#plot(vectors[, 1], vectors[, 2], type = "p", pch = 21, bg = par("bg"), col = "blue", cex = .6,
#     main = 'points x-y', xlab = "x", ylab = "y")
#plot(vectors[, 1], vectors[, 3], type = "p", pch = 21, bg = par("bg"), col = "blue", cex = .6,
#     main = 'points x-z', xlab = "x", ylab = "z")

#����� ���������� ������ � ���� ������
setwd("C:/R/programs")
df1 <- as.data.frame(t(vectors))
write.csv(t(df1), file = "VectorsData.csv", row.names = FALSE)
df2 <- as.data.frame(number_of_classes_each_dimension)
write.csv(t(df2), file = "dimensions.csv", row.names = FALSE)
