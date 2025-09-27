# AmazonEndToEndFlow

[Short one-liner]
End-to-end automation of the Amazon shopping flow (search → add to cart → checkout simulation). Implemented in Java + Selenium + TestNG + Maven.

## Demo
<img width="2382" height="1262" alt="image" src="https://github.com/user-attachments/assets/9c696f62-edf8-437c-85c0-03082e2023da" />

## Highlights
- Implements Page Object Model (POM) for maintainability.
- Cross-browser ready (Chrome); easily extended.
- TestNG suites with retry and screenshots-on-failure.
- CI with GitHub Actions → runs tests and uploads reports.

## Tech stack
- Java 11+ (specify exact version)
- Maven
- Selenium WebDriver
- TestNG
- WebDriverManager (io.github.bonigarcia)
- Allure reports (optional)
- GitHub Actions for CI

## What this repo proves (resume-friendly)
- Automated full E2E shopping flow including product search, cart, address/payment flow (test mode).
- Captures screenshots and generates detailed reports for each run.
- CI integrated → automatic test runs on push/PR.

## Quick start (run locally)
1. Clone:
```bash
git clone git@github.com:yourUser/AmazonEndToEndFlow.git
cd AmazonEndToEndFlow

## Config Changes
```bash
export AMAZON_USERNAME=...
export AMAZON_PASSWORD=...
mvn clean test -Damazon.username=${AMAZON_USERNAME} -Damazon.password=${AMAZON_PASSWORD}
