#!/bin/sh
find ./src/org/ -name "*.java" | xargs wc -l | sort -n
