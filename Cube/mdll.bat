@echo off
if exist %1.o del %1.o
if exist %2.dll del %2.dll
g++ -c %1.cpp
g++ -shared -o %2.dll %1.cpp
if exist %1.o del %1.o