package de.bitb.astroskop.model;


import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Profile {
    @Id
    protected long id;

    private String uuid;

    private String name;

    private ToMany<Constellation> constellations;

}
