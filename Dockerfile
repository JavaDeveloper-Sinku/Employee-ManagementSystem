FROM ubuntu:latest
LABEL authors="sinkusingh"

ENTRYPOINT ["top", "-b"]