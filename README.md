# Spring REST Server for TriVox Health
## Written by Jeff Lee

## Project:
Created as a quick project to exemplify Java and Web service skills. Example is to take an inventory from an odd car dealership, and be able to fetch average costs and cars.

## How to run:
1. java -jar ShadyDealershipBuild.war
2. curl localhost:8080/vehicles/(REQUEST)

## Requests:

Replace (REQUEST) above with:

### all
   To get all vehicles sorted by price ascending

### type
   To get the average cost of each vehicle type, sorted price ascending

### brand
   To get the average cost of each vehicle brand, sorted price ascending

### engine
   To get the average cost of each engine type, sorted price ascending

### color
   To get the average cost of each engine color, sorted price ascending