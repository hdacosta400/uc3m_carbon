'''

Runs Intel Power Gadget on Intel Mac

assumes this is installed in Applications folder 

uses github python parser to extract cpu information
'''
import os 
import subprocess
subprocess.call('power_gadget', shell=True)

# from power_gadget import power_gadget

def run_power_gadget(output_dir, filename):

    command = "/Applications/Intel\ Power\ Gadget/PowerLog -duration 30 -resolution 1000 -file {}/{}".format(output_dir, filename)
    os.system(command)

def run_parser(filename):
    parser = "python3 power_gadget/power_gadget.py --power-log-file {}".format(filename)
    os.system(parser)
    # output = subprocess.check_output(parser, cwd='power_gadget')
    # print("what is output:", output)
    
     

if __name__ == "__main__":
    filename = "test.csv"
    output_dir = "gadget_output"
    # run_power_gadget(output_dir, filename) 
    run_parser("{}/{}".format(output_dir, filename))