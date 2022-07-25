# Test task

# Credentials 
Credentials are expected to be in a file with the following structure:

login = "test"
password = "test"

There are two ways to link this file:
- name it credentials.conf and place it directly to the resources directory (src/main/resources);
- set path to the file into environment variable CREDENTIALS. 

# Report
For report creation vanilla TestNG is used.

A report will be created after a test run with Maven as a file emailable-report.html (target/surefire-reports/). 

Screenshots of failed tests will be created in target/surefire-reports/failure_screenshots directory.
