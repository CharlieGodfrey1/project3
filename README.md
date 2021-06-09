# Project-Taurus

This is the README for the final project of the NSAC cohort 11. The aim of the project was to create an entire web application from scratch including backend, frontend logic as well as a CI/CD pipeline with the intention of testing and deploying the application into the cloud.

# Contents
1. Resources
2. Brief
3. Plan
4. Project Management
5. Software stack
6. Future goals

# 1. Resources
- [Presentation](https://docs.google.com/presentation/d/16NKg8SW3qEdGMSq5RcbgI7QcXcaR4bgiEKYvSFjKwls/edit#slide=id.g1f87997393_0_782)
- [Jira Board](https://team-1612863346985.atlassian.net/jira/software/projects/TAUR/boards/5)

# 2. Brief

The brief of the project was to create a web application to tackle three scenarios. These included: Scenario 1 - A suspect has been detained, the client needs to know background of the suspect i.e Biographical information, associates, financial transactions and whereabouts. Scenario 2 - An incident has occured, the client needs to know who was in the area at a particular point. Finally scenario 3 - A suspect flees the scene and an officer reported a vehicles number plate. The client needs to know who the suspect was and where else have they been.

# 3. Plan

We began the project starting with scenario three. This required us to sipher through a MySql database to analyse data regarding a suspects vehicle registration plate. Through this the application returned biographical data on the suspect, as well as all the locations the suspect had been based on ANPR cameras. Upon completion of this scenario we moved onto the second task, this required us to determine who had been on a particular street at a particular time. We decided to further our searches by utilising longitude and latitude to generate a set radius around the chosen street. This provided us with a more detailed dataset to work with and a more functional application. 

In the frontend we began by creating a wireframe for scenario three. We also generated entity-relationship diagrams(ERDs) in the backend to better understand the dataset to allow us to better plan out the Spring API. These can be seen as below.

# Scenario 3 Wireframe

![s3wireframe](https://user-images.githubusercontent.com/78798264/119544328-81284000-bd89-11eb-8401-10457ae736cf.PNG)

# Vehicle ERD (Scenario three)

![VehicleERD](https://user-images.githubusercontent.com/78798264/119541574-78823a80-bd86-11eb-9294-7e3ff1ca019e.png)

# Transaction ERD (Scenario two)
![transactionERD](https://user-images.githubusercontent.com/78798264/119543956-242c8a00-bd89-11eb-8ab1-ed4de1e49454.png)


# 4. Project Management

We utilised Jira software and modelled it as an Agile scrum board. The Jira board utilises agile sprint methodologies to complete the various user stories as created at the beginning of the project. Below is an example of a User-story.

![CaptureTr3](https://user-images.githubusercontent.com/78798264/119539662-5be50300-bd84-11eb-988c-dc52372b0535.PNG)

This project comprised of two one week sprints. We attempted to reduce dependencies within the same sprint. Below is an example of a sprint.

![SprintExampleFinalproject](https://user-images.githubusercontent.com/78798264/119540210-17a63280-bd85-11eb-83e4-50cc699a15fe.PNG)

# 5. Software stack

Our DevOps CI/CD pipeline software stack as below:

![DevOpsFPdatastack](https://user-images.githubusercontent.com/78798264/119540374-491efe00-bd85-11eb-8840-8877f03100cf.PNG)

Our backend software stack as below:

![BESOFTWARE](https://user-images.githubusercontent.com/78798264/119541528-6902f180-bd86-11eb-809f-4b15d5837a2b.PNG)

Our testing and overall coverage report. The coverage generated a solid score of ~80%. One of the main things we found we could do to further enhance our coverage is to include more getters and setters in the tests, although visually this would be more satisfactory we felt it would be more important to ensure the application worked in the most efficient manner.

![testing](https://user-images.githubusercontent.com/78798264/119543234-58ec1180-bd88-11eb-9a1e-f3cdcc2ea6a1.PNG)

Our frontend software stack as below:

![FEFPsoftwarestack](https://user-images.githubusercontent.com/78798264/119540933-e9752280-bd85-11eb-98fb-54acd6cbaa86.PNG)

# 6. Future goals

Throughout the project there were various ideas and planned implementations that couldn't quite be achieved due to time constraints, as such a few stretch goals are as follows:
- Addition of the final scenario (scenario one)
- Get the transaction data to render onto the map for scenario two
- Addition of mobile call record data in the data-transfer object for scenario two
- Alter our visual frontend circle vector logic to ensure the searched street is the centre point (Currently based on earliest timeframe)
- Provide method to grab longitude and latitude of searched street even if no data is present. Aids as above.
- Utilise microservices
- Improve MySQL query times
- Orchestration with K8

# Authors

Project by Imogen Davey, Charles Godfrey, Aimee Holdsworth, Sean Leonard, Thomas Marsh

