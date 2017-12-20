package de.bitb.astroskop.model;


import java.util.List;

import de.bitb.astroskop.model.converter.IntegerListConverter;
import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class PowerGraph {

    @Id
    protected long dbId;

    @Convert(converter = IntegerListConverter.class, dbType = String.class)
    private List<Integer> stress;
    @Convert(converter = IntegerListConverter.class, dbType = String.class)
    private List<Integer> importe;
    @Convert(converter = IntegerListConverter.class, dbType = String.class)
    private List<Integer> generated;
}
