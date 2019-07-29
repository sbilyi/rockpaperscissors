from collections import Counter
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.ticker import PercentFormatter



ROCK = [28, 35, 28, 31, 20, 26, 24, 19, 26, 11, 15, 25, 17, 18, 6, 33, 23, 22, 9, 22, 17, 13, 36, 7, 34, 32, 13, 12, 31, 18, 18, 19, 0, 4, 32, 34, 25, 21, 20, 23, 24, 17, 27, 31, 15, 34, 21, 6, 29, 22, 34, 7, 23, 16, 24, 38, 8, 34, 24, 24, 9, 20, 6, 28, 6, 26, 9, 21, 28, 0, 6, 24, 1, 12, 17, 20, 12, 37, 17, 32, 28, 10, 25, 22, 5, 12, 27, 2, 22, 16, 30, 36, 19, 1, 4, 4, 27, 37, 9, 22, 19]
PAPER = [56, 48, 60, 32, 34, 14, 52, 54, 7, 14, 15, 3, 3, 22, 22, 14, 23, 23, 56, 48, 3, 22, 40, 16, 4, 30, 11, 52, 52, 31, 58, 32, 15, 32, 16, 25, 21, 22, 1, 59, 44, 3, 59, 9, 16, 34, 39, 50, 44, 30, 34, 48, 59, 21, 53, 38, 26, 57, 5, 0, 4, 6, 45, 42, 5, 20, 29, 24, 57, 56, 54, 35, 40, 56, 11, 16, 7, 15, 34, 1, 7, 56, 1, 2, 35, 40, 0, 23, 45, 54, 17, 21, 14, 16, 37, 44, 31, 57, 9, 46, 14]
SCISSORS = [0, 35, 1, 4, 25, 20, 31, 15, 20, 33, 9, 32, 25, 38, 27, 11, 1, 6, 25, 18, 11, 30, 24, 30, 21, 29, 35, 28, 13, 33, 4, 21, 2, 16, 8, 6, 15, 26, 18, 23, 17, 8, 22, 29, 10, 22, 32, 30, 24, 25, 21, 23, 34, 37, 28, 9, 26, 35, 36, 6, 13, 20, 28, 2, 30, 19, 23, 14, 36, 12, 37, 2, 17, 21, 15, 15, 17, 33, 21, 17, 4, 4, 8, 5, 24, 35, 24, 23, 36, 25, 30, 19, 20, 17, 11, 27, 1, 11, 15, 29, 3]



def compare(r, p, s):
    if r >= p and r >= s:
        return 'ROCK'
    elif p>= r and p>= s:
        return 'PAPER'
    elif s>=r and s>= p:
        return 'SCISSORS'

data = []

for i in range(100):
    data.append(compare(ROCK[i], PAPER[i], SCISSORS[i]))


print(data)
print(Counter(data).keys()) # equals to list(set(words))
print(Counter(data).values()) # counts the elements' frequency



plt.hist(data, weights=np.ones(len(data)) / len(data))

plt.gca().yaxis.set_major_formatter(PercentFormatter(1))
plt.show()