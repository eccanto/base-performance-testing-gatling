[![code style: prettier](https://img.shields.io/badge/code_style-prettier-ff69b4.svg?style=flat-square)](https://github.com/prettier/prettier)

# Performance testing using Gatling (Java and Scala)

# Table of contents

* [Overview](#overview)
  * [Scenario](#scenario)
  * [System](#system)
* [Examples](#examples)
* [Appendix](#appendix)
* [License](#license)

# Overview

[Gatling](https://github.com/gatling/gatling) is a performance testing tool used for load, stress testing and user
behavior simulation. Performance testing is the general name for tests that check how the system behaves and performs.
Software performance testing examines responsiveness, stability, scalability, reliability, speed, and resource usage
of your software and infrastructure.

For performance testing Gatling presents test results in a offline report. One of the significant features of Gatling is its
[well-documented code source](https://gatling.io/docs/gatling/).

# Scenario

In these examples, you will run a login and obtain users information using
[JSON Web Token](https://www.rfc-editor.org/rfc/rfc7519) Authentication. The tested application will be a REST API
server mock defined in this repository:
[base-mockoon-api-rest-server-mock](https://github.com/eccanto/base-mockoon-api-rest-server-mock).

## System

We'll begin by creating a controller container and several worker containers. There are certain prerequisites that we
have to perform on all these workers. These include installing Gatling on all workers and setting up the scenario
(`Java` or `Scala` file). To achieve a consistent result, we should install the same version of Gatling on all workers,
with the same configuration on each one.

# Examples

Available examples (branches):
- [Load testing using Gatling (Scala)](https://github.com/eccanto/base-gatling-performance-testing/tree/feature/load-testing-scala)
- [Load testing using Gatling (Java)](https://github.com/eccanto/base-gatling-performance-testing/tree/feature/load-testing-java)
- [Stress testing using Gatling (Scala)](https://github.com/eccanto/base-gatling-performance-testing/tree/feature/stress-testing-scala)
- [Stress testing using Gatling (Java)](https://github.com/eccanto/base-gatling-performance-testing/tree/feature/stress-testing-java)

# Appendix

## TODO

# License

[MIT](./LICENSE)
