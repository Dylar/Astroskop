package de.bitb.astroskop.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Construction {

    @Id
    protected long dbId;

    protected int serverId;
    protected String start;
    protected String end;
    protected String district;
    protected String street;
    protected String streetNumber;
    protected String size;

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Construction && serverId == ((Construction) obj).getServerId();
    }

    @Override
    public int hashCode() {
        return serverId;
    }
}
