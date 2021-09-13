# Social_influencer

[![Website](https://img.shields.io/badge/View-website-blue)](https:talenalexander.com/industry-news/10-influencers-who-have-an-active-presence-on-social-media/)

Android application for Advertiser to make a deal with top-rated influencers to publish their content on social-media-platforms and do the campaigning.

## Contents
- [Submission or project name](#submission-or-project-name)
  - [Contents](#contents)
  - [Short description](#short-description)
    - [The idea](#the-idea)
    - [Summary](#summary)
  - [The architecture](#the-architecture)
  - [Long description](#long-description)
  - [Project roadmap](#project-roadmap)
  - [Getting Started](#getting-started)
  - [Compatible devices](#compatible-devices)
  - [Project screenshots](#project-screenshots)
  - [Privacy](#privacy)
  - [Acknowledgments](#acknowledgments)
  
## Short description
Advertiser explors and selects the influencer who is doing tremendous in marketing point of view.He makes deal with influencer on marketing/campaigning his products by fixing budget.

Influencer gets the request in the form of notification and if he likes the deal, he accepts and post it in social media platforms and send an acknowledgement to the advertiser.

### The idea
Creating platform for the advertisers and influencers to interact and publish their products on social-media platforms and get profited

### Summary

Developed UI/UX and functionality for Advertiser & Influencer application
  SignIn,Register & User Profile Management
  In Advertiser & Influencer Register screen, we need to upload an image of advertiser in which integrated HMS Core kit Media i.e., CameraEngine and uploading the image to server.
  
Advertiser Screen:
  Home          - Displaying the advertisements,Brands and Top rated influencers.
  Explore       - Filter the list of influencers based on the category.(All, technology, beauty etc)
                - After opting the influencer, we can view his details which is designed in a 3 Tab format
                    1.  Details of the particular campaign
                    2.  Details of the Influencer and his exposure in market till now.
                    3.  Applying a request to the influencer by providing details of the brand, setting the reward,price of the campaign which goes out to the influencer in the form of notification
  Notification - Displays the post which the influencer accepts the request and published tha campaign in socialmedia
  Profile      - Displays the Profile of the advertiser who logged in

Influencer Screen:
  - This is basically a one user login
  - Displays the number of Campaign Requests received,Posts published,marketing platforms,Rewards earned, Views and Posts List.
  - When influencer receives a notification from the advertiser, it gets listed in the notification page which opens when influencer clicks on the notification icon in his toolbar.
  - So based on the Campaign request, hw may accept and post the brand in his social media and send the acknowledged message in the form of notification to the advertiser.


## The architecture

![Alt text](https://github.com/saisree369/social_influencer/blob/main/app/src/main/res/drawable/si_flowchart.png?raw=true "Flow")

 1.The advertiser sends the campaign request to the influencer selected.

2. Influencer checks the details of the product that needs to be campaigned.

3. Influencer accepts the request and post it on social media platforms.

## Long Description

[More detail is available here](https://github.com/saisree369/social_influencer/blob/main/docs/Social%20Advertiser_documentation.docx)

## Project roadmap

The project currently does the following things

- Feature 1
- Feature 2
- Feature 3

### Feature-1 : 

Registering of Advertiser & Influencer and their connection flow.

![Alt text](https://github.com/saisree369/social_influencer/blob/main/app/src/main/res/drawable/feature_1.png?raw=true "Connection")

### Feature-2 : 

Advertiser Selecting Influencer and Advertiser Campaign Dashboard

![Alt text](https://github.com/saisree369/social_influencer/blob/main/app/src/main/res/drawable/feature_2.png?raw=true "Advertiser Flow")

### Feature-3 : 

Influencer Accepting Campaign Request and Advertiser Campaign Dashboard

![Alt text](https://github.com/saisree369/social_influencer/blob/main/app/src/main/res/drawable/feature_3.png?raw=true "Influencer Flow")

## Getting Started

You can clone the repo directly and build it in android studio.

The url's which is used for fetching data is generic. You can opt for your own favourite data.

## Compatible devices 
- All android phones with SDK version >= 28

- Huawei phones with kirin 980 or later & EMUI 10.0 or later is required as Camera Engine dependenncy is included

## Project Screenshots

[Please find all screen screenshots of the project](https://github.com/saisree369/social_influencer/blob/main/docs/images/)

## Privacy

[Privacy Statement](https://docs.google.com/document/d/18X1ZqY-QR5Bd8IuWTGHp52gI5PMvVoePyDFXTS3PO4w/edit)

## Acknowledgements

- Based on [Call For Code's README template](https://github.com/Call-for-Code/Project-Sample/blob/main/README.md)


  
  
