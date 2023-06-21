#!/bin/sh

sudo powermetrics -i 1000 --samplers cpu_power -a --hide-cpu-duty-cycle --show-usage-summary --show-extra-power-info & sleep 5; kill $!