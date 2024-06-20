package com.github.inncontrol.profiles.domain.model.aggregates;


import com.github.inncontrol.profiles.domain.model.commands.CreateProfileCommand;
import com.github.inncontrol.profiles.domain.model.valueobjects.EmailAddress;
import com.github.inncontrol.profiles.domain.model.valueobjects.PersonName;
import com.github.inncontrol.profiles.domain.model.valueobjects.UserId;
import com.github.inncontrol.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;

@Entity
public class Profile extends AuditableAbstractAggregateRoot<Profile> {
    @Embedded
    private PersonName name;

    @Embedded
    private EmailAddress email;



    @Embedded
    private UserId userId;

    public Profile(String firstName, String lastName, String email) {
        this.name = new PersonName(firstName, lastName);
        this.email = new EmailAddress(email);

    }

    public Profile(CreateProfileCommand command) {
        this.name = new PersonName(command.firstName(), command.lastName());
        this.email = new EmailAddress(command.email());
    }
    public Profile() {

    }

    public void updateName(String firstName, String lastName) {
        this.name = new PersonName(firstName, lastName);
    }

    public void updateEmail(String email) {
        this.email = new EmailAddress(email);
    }



    public String getFullName() { return name.getFullName(); }

    public String getEmailAddress() { return email.address(); }

}
