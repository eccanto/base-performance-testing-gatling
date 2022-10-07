#!/usr/bin/env bash

set -euo pipefail

PROJECT_DIR=.

GREEN="32"
BOLDGREEN="\e[1;${GREEN}m"
ENDCOLOR="\e[0m"

# Scala static code checkers
echo -e "${BOLDGREEN}> running WartRemover...${ENDCOLOR}"
