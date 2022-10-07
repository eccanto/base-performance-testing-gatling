FROM openjdk:8-jdk-alpine

LABEL Author="Erik Ccanto <ccanto.erik@gmail.com>"

# Working directory for gatling.
WORKDIR /opt/gatling

# Gating version.
ARG GATLING_VERSION

# Install gatling.
RUN apk add --update wget bash libc6-compat &&                                                                         \
  mkdir -p /tmp/downloads &&                                                                                           \
  wget -q -O /tmp/downloads/gatling-${GATLING_VERSION}.zip https://repo1.maven.org/maven2/io/gatling/highcharts/gatling-charts-highcharts-bundle/${GATLING_VERSION}/gatling-charts-highcharts-bundle-${GATLING_VERSION}-bundle.zip && \
  mkdir -p /tmp/archive && cd /tmp/archive &&                                                                          \
  unzip /tmp/downloads/gatling-${GATLING_VERSION}.zip &&                                                               \
  mv /tmp/archive/gatling-charts-highcharts-bundle-${GATLING_VERSION}/* /opt/gatling/ &&                               \
  rm -rf /tmp/*

# Set directories below to be mountable from host.
VOLUME [ "/opt/gatling/conf", "/opt/gatling/results", "/opt/gatling/user-files" ]

# Set environment variables.
ENV PATH /opt/gatling/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
ENV GATLING_HOME /opt/gatling

ENTRYPOINT [ "gatling.sh" ]
