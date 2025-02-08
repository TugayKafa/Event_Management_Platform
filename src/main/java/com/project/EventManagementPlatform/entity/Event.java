package com.project.EventManagementPlatform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int sessions; // This is not the original idea

    private int maxParticipants;

    @ManyToOne
    private Place place;

    private String organizer; // stores organizer username;

    private Set<String> participants; //stores participants usernames;

    public Event(String name, int sessions, int maxParticipants, Place place, String organizer) {
        this.name = name;
        this.sessions = sessions;
        this.maxParticipants = maxParticipants;
        this.place = place;
        this.organizer = organizer;
    }

    //@ElementCollection
    //private List<String> materials = new ArrayList<>();

}
