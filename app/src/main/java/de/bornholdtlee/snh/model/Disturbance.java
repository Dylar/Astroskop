package de.bornholdtlee.snh.model;

import java.util.ArrayList;
import java.util.List;

import de.bornholdtlee.snh.model.converter.StringListConverter;
import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Disturbance {
    @Id
    protected long dbId;

    protected int serverId;

    protected String date;

    protected String start;

    protected String end;

    @Convert(converter = StringListConverter.class, dbType = String.class)
    protected List<String> district = new ArrayList<>();
}
