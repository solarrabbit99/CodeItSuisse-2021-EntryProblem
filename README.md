# Credit Suisse Entry Problem

## Introduction

This is a solution for the [2021 Credit Suisse Coding Challenge's Entry Problem](https://github.com/zhenghanlee/credit-suisse#problem) by Lee Zheng Han & Siew Hui Zhuan.

## Installation

1. Clone the repository to your local device.
2. Open up Terminal and change directory to project's root directory.
3. Run `mvn clean install` to install the project to your local maven repository, and to create `target/credit-suisse-1.0-SNAPSHOT.jar` jar.

## Usage

1. Move `credit-suisse-1.0-SNAPSHOT.jar` to a separate folder containing the input file, say `input.json`.
2. Run `java -jar credit-suisse-1.0-SNAPSHOT.jar input.json`, this will print the output in the console and create a `output.json` file containing the output in the directory this command is run.

## Problem

It is the year 2038 and robots have the right to get paid for the work they do. As an employer of robots, you need to calculate how much a robot gets paid for cleaning your apartment.

How much a robot gets paid depends on when you ask the robot to work. After all, during the day the robot can be a little louder and work a bit faster whilst everyone is out of the house, but at night, you will need to turn on the super quiet mode, which takes more effort! Robots also cost a bit more over weekends, due to higher demand.

Your robot rates calculator needs to consider the following:

- A standard minutely rate for weekdays, and an ‘extra’ rate for weekends.
- When rates switches between day and night rates, for a total of four different rates (weekday/weekend + day/night).
- For every eight hours, the robot needs to take an hour of unpaid break (or part thereof) for planned system maintenance.

Implement an application that can take in an input like the example below, and provide an
output as shown:

```json
{
  "shift": {
    "start": "2038-01-01T20:15:00",
    "end": "2038-01-02T04:15:00"
  },
  "roboRate": {
    "standardDay": {
      "start": "07:00:00",
      "end": "23:00:00",
      "value": 20
    },
    "standardNight": {
      "start": "23:00:00",
      "end": "07:00:00",
      "value": 25
    },
    "extraDay": {
      "start": "07:00:00",
      "end": "23:00:00",
      "value": 30
    },
    "extraNight": {
      "start": "23:00:00",
      "end": "07:00:00",
      "value": 35
    }
  }
}
```

Sample output:

```json
{ "value": 13725 }
```

Additional test cases for the sample rates above:

| Start               | End                 | Expected value |
| ------------------- | ------------------- | -------------- |
| 2038-01-01T20:15:00 | 2038-01-02T08:15:00 | 19650          |
| 2038-01-11T07:00:00 | 2038-01-17T19:00:00 | 202200         |
| 2038-01-01T20:15:00 | 2038-01-02T04:16:00 | 13725          |
| 2038-01-01T20:15:00 | 2038-01-02T05:16:00 | 13760          |

The problem statement has not been completely described - you should provide comments on any other assumptions you have made on top of these two:

1. Shift timestamps will be given in ISO format “yyyy-MM-dd’T’hh:mm:ss”, rates in “hh:mm:ss”.
2. Duration boundaries are [inclusive, exclusive), e.g. a shift from 7 am to 11 pm will fit into a single day rate, without incurring a minute of the night rate.
   Assessors will be considering code-readability, implementation and assumptions including checks/validations that your application performs. You are allowed to use third-party libraries.

## Assumptions

The solution made the following assumptions:

1. `"start"` and `"end"` time for day is direct opposite of its night counterpart.
2. `"start"` and `"end"` time are all perfect hours (e.g. `19:00:00` but not `19:30:00`).
