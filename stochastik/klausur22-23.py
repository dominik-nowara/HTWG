import numpy as np 
import statistics as st

g = [206, 217, 227, 222, 222, 228, 232]

modal = st.multimode(g)
mean = np.mean(g)

q95 = np.quantile(g, 0.95)
std = np.std(g, ddof=1)


print("Modal: ", modal)
print("Mean: ", mean)
print("95% Quantil: ", q95)
print("Standardabweichung: ", std)