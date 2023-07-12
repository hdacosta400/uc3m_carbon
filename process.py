from subprocess import Popen, PIPE
import psutil

# process = Popen(['ps', '-eo' ,'pid,args'], stdout=PIPE, stderr=PIPE)
# stdout, notused = process.communicate()
# c = 5
# for line in stdout.splitlines():
#     line = line.decode()
#     print(line)
#     if c == 0:
#         break 
#     c -=1

cpu_times = []
for proc in psutil.process_iter():
    try:
        # cpu_times.append((proc.name(), sum(proc.cpu_times()[:2])))
    except:
        continue
print(cpu_times)
