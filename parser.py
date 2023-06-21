'''
Test script for parsing terminal output
'''
import os, subprocess
import shlex

def parse_power_cmd():
    # cmd = "ioreg -n AppleSmartBatteryManager -r -l | grep -iE '(Amp|Watt|Volt)' "
    cmd = 'sudo ./powermetrics.sh'
    out = subprocess.check_output(cmd, shell=True).decode()
    for row in out.split('\n'):
        if "CPU Power" in row:
            watt_str = row.split(':')[1]
            watts = int(watt_str.split(' ')[1]) / 1000
            print('what are watts:', watts)
            return watts
        
# import cpuinfo
# def detect_cpu_model() -> str:
#     cpu_info = cpuinfo.get_cpu_info()
#     if cpu_info:
#         cpu_model_detected = cpu_info.get("brand_raw", "")
#         return cpu_model_detected
#     return None

# parse_power_cmd()
if __name__ == "__main__":
    os.system('sudo ./powermetrics.sh')
    # parse_power_cmd()
    # print(detect_cpu_model())
