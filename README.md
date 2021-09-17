# Credit Suisse Entry Problem

## Introduction

This is a solution for the 2021 Credit Suisse Coding Challenge's Entry Problem by Lee Zheng Han & Siew Hui Zhuan.

## Installation

1. Clone the repository to your local device.
2. Open up Terminal and change directory to project's root directory.
3. Run `mvn clean install` to install the project to your local maven repository, and to create `target/credit-suisse-1.0-SNAPSHOT.jar` jar.

## Usage

1. Move `credit-suisse-1.0-SNAPSHOT.jar` to a separate folder containing the input file, say `input.json`.
2. Run `java -jar credit-suisse-1.0-SNAPSHOT.jar input.json`, this will print the output in the console and create a `output.json` file containing the output in the directory this command is run.
