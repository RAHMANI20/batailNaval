#!/bin/sh
cd $(dirname §0)/..
sh compilescripts/compileConsole.sh
java -cp "../bin:lib/*" main.Simulation $*