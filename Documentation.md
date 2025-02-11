# E-Learning Platform Documentation

## Table of Contents
1. [Introduction](#introduction)
2. [Project Overview](#project-overview)
3. [User Roles](#user-roles)
4. [Use Cases](#use-cases)
5. [System Architecture](#system-architecture)
6. [Database Schema](#database-schema)
7. [Future Enhancements](#future-enhancements)
8. [Conclusion](#conclusion)

---

## 1. Introduction

The Event Management Platform is a dynamic and user-friendly system designed to simplify the process of organizing, managing, and participating in events. It provides a seamless experience for users to create events, register participants, upload and access event materials, and manage event-related information efficiently.

Built with the Spring 6 Application Development Framework, the platform ensures high performance and scalability. The front-end is powered by Thymeleaf. The backend is structured around models.

By integrating robust features for event creation, material sharing, and participant management, this platform streamlines event organization for both individuals and businesses, making it an essential tool for modern event planning.
---

## 2. Project Overview

## 2.1 Business Needs

Event management has become increasingly complex, requiring efficient tools to streamline planning, organization, and participation. The **Event Management Platform** addresses these challenges by providing:  

- A centralized system for creating, managing, and tracking events.  
- A seamless experience for organizers to handle participant registrations and event logistics.  
- A platform for attendees to discover, register, and access event-related materials.  
- Administrative tools for managing venues, schedules, and event resources efficiently.  

## 2.2 Key Features  

- **User Management**: Registration, authentication, and role-based access control for organizers and participants.  
- **Event Creation and Management**: Organizers can create, edit, and delete events with schedules and locations.  
- **Material Sharing**: Upload and distribute event-related documents, presentations, and other resources.  
- **Participant Registration**: Users can register for events and manage their participation.    

This platform enhances event planning efficiency, improving user experience for both organizers and attendees.
---

## 3. System Features

### 3.1 User Roles  

The system supports the following user roles:  

1. **Anonymous User**:  
   - Can browse events without signing up.  
   - Cannot register for or participate in events.  

2. **Registered User**:  
   - Can register an account and manage personal details.  
   - Can browse and search for events.  

3. **Attendee** (Registered User with participation rights):  
   - Can register for available events.  
   - Can access event materials and receive notifications.  
   - Can unsubscribe from events when needed.  

4. **Organizer**:  
   - Can create, edit, and manage events.  
   - Can upload and distribute event materials.  
   - Can track event participants and manage resources.  

5. **Admin**:  
   - Has access to all events and resources.  
   - Can manage user accounts, roles, and platform-wide settings.  
   - Can add, edit, and remove event venues.  

This role-based structure ensures smooth event management, user engagement, and administrative control over the platform.
---

## 4. Use Cases

### 4.1 Browse Events  
- **Actors**: All users.  
- **Description**: Users can browse available events on the platform.  

### 4.2 Register  
- **Actors**: Unregistered User.  
- **Description**: Unregistered users can register by providing an email, name, and password.  

### 4.3 Login  
- **Actors**: Registered Users (Organizer, Participant).  
- **Description**: Users can log in using their email and password.  

### 4.4 Manage Profile  
- **Actors**: Registered Users, Admin.  
- **Description**: Users can edit their personal data, such as name, email, and preferences.  

### 4.5 Create Event  
- **Actors**: Organizer, Admin.  
- **Description**: Organizers can create new events by providing details such as name, description, date, location, and capacity.  

### 4.6 Edit Event  
- **Actors**: Organizer, Admin.  
- **Description**: Organizers can update event details, such as date, location, or description.  

### 4.7 Delete Event  
- **Actors**: Organizer, Admin.  
- **Description**: Organizers can delete their own events.  

### 4.8 Register for Event  
- **Actors**: Participant.  
- **Description**: Participants can register for events they wish to attend.  

### 4.9 Cancel Registration  
- **Actors**: Participant.  
- **Description**: Participants can cancel their registration for an event.
- 
### 4.10 View Attendee List  
- **Actors**: Organizer, Admin.  
- **Description**: Organizers can see a list of attendees for their events.
- 
### 4.11 Browse Events 
- **Actors**: All users.  
- **Description**: Users can browse events filtered by name, organizer and location.  
---

## 5. System Architecture

The system follows a **3-tier architecture**:
1. **Presentation Layer**:
   - Front-end built using **Thymeleaf**.
   - Handles user interaction and displays data.

2. **Application Layer**:
   - Backend built using **Spring 6** (Spring Boot, Spring MVC, Spring Data JPA).
   - Implements business logic, API endpoints, and data processing.

3. **Data Layer**:
   - **MySQL** database for storing user data, events and places.
   - Uses **Spring Data JPA** for database interactions.

---

## 6. Database Schema

The database schema includes the following tables:
- **Users**: Stores user information (email, password, firstname, lastname, etc.).
- **Events**: Stores events details (name, organizer_id, place_id, maxParticipants, etc.).
- **Place**: Stores places details (name, location, max capacity).
- **Event_materials**: Stores events materials names(event id, material)
- **Event_participants**: Stores many to many mapping between users and events in which they participate

---

## 7. Future Enhancements

1. **Mobile Application**  
   - Develop a mobile app for iOS and Android to provide a seamless event management experience on mobile devices. 

2. **Live Streaming**  
   - Integrate live streaming functionality for virtual or hybrid events, allowing remote participants to join in real-time.  

3. **Advanced Analytics**  
   - Provide deeper insights into event performance, attendee engagement, and ticket sales using data visualization tools.  

4. **Smart Ticketing System**  
   - Implement QR-based smart ticketing for seamless event check-in and security verification.  

---

## 8. Conclusion

The **Event Management Platform** is a comprehensive system designed to help its users to create and join events. It offers a range of features for organizers and participants, ensuring a seamless experience from event discovery to attendance. With its scalable architecture, powered by modern web technologies, the platform is built for future growth and innovation.  

---
