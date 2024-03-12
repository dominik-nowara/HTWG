import numpy as np

num = [12, 24, 22, 18, 18, 36, 15, 29, 41, 17]
median = np.median(num)
print(median)
num.sort()
median = np.median(num)
print(median)
mittelwert = np.mean(num)
quantil = np.quantile(num, 0.25)

print(num)
print(f"Mittelwert: {mittelwert}")
print(f"Quantil: {quantil}")


num = [1, 1, 2, 2, 3, 3, 3, 3, 4, 4, 7]
mittelwert2 = np.mean(num)
std = np.std(num)

print(num)
print(f"Mittelwert: {mittelwert2}")
print(f"Standardabweichung: {std}")