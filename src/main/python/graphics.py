from sys import stdin
import matplotlib.pyplot as plt
import matplotlib.animation as animation
import re 

i = 0
generations = []
averages = []
minimums = []
fig, ax = plt.subplots()
plt.xlabel('Generation')
plt.ylabel('Performance')

ax.plot(generations, averages, label = 'Average Performance', color = 'blue')
ax.plot(generations, minimums, label = 'Minimum Performance', color = 'red')
ax.legend()

for line in stdin:
    args = re.split(r" ",line)
    generations.append(i)
    averages.append(float(args[0]))
    minimums.append(float(args[1]))
    i = i + 1
    ax.plot(generations, averages, label = 'Average', color = 'blue')
    ax.plot(generations, minimums, label = 'Minimum Performance', color = 'red')
    plt.pause(0.02)

plt.show()
print('done')