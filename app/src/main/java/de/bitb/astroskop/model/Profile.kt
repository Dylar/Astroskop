package de.bitb.astroskop.model


import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import lombok.Getter
import lombok.Setter

@Getter
@Setter
@Entity
class Profile {
    @Id
    var id: Long = 0

    var uuid: String? = null
        set(uuid) {
            field = this.uuid
        }

    var name: String? = null
        set(name) {
            field = this.name
        }

    var constellations: ToMany<Constellation>? = null
        set(constellations) {
            field = this.constellations
        }

}
