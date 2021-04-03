from sys import stdin
import matplotlib.pyplot as plt
import matplotlib.animation as animation
import re 

i = 0
generations = []
averages = []
minimums = []
maximums = []
varieties = []
fig1, ax1 = plt.subplots()
fig1.canvas.set_window_title('Average, minimum and maximum Player Performance per Generation')
plt.xlabel('Generation')
plt.ylabel('Performance')
fig2, ax2 = plt.subplots()
fig2.canvas.set_window_title('Player variety per generation')
plt.ylabel('Variety')
ax1.plot(generations, averages, label = 'Average Performance', color = 'blue')
ax1.plot(generations, minimums, label = 'Minimum Performance', color = 'red')
ax1.plot(generations, maximums, label = 'Maximum Performance', color = 'green')
ax1.set_title('Average, minimum and maximum Player Performance per Generation')
ax1.legend()
ax2.plot(generations, varieties, label = 'Varieties', color = 'violet')
ax2.set_title('Player variety per generation')
ax2.legend()

for line in stdin:
    args = re.split(r" ",line)
    generations.append(i)
    averages.append(float(args[0]))
    minimums.append(float(args[1]))
    maximums.append(float(args[3]))
    varieties.append(float(args[2]))
    i = i + 1
    ax1.plot(generations, averages, label = 'Average', color = 'blue')
    ax1.plot(generations, minimums, label = 'Minimum Performance', color = 'red')
    ax1.plot(generations, maximums, label = 'Maximum Performance', color = 'green')
    ax2.plot(generations, varieties, label = 'Varieties', color = 'violet')
    plt.pause(0.02)

plt.show()
print('done')