package de.bitb.astroskop.model


import de.bitb.astroskop.model.converter.HouseConverter
import de.bitb.astroskop.model.converter.RulerConverter
import de.bitb.astroskop.model.converter.ZodiacConverter
import de.bitb.astroskop.model.enums.House
import de.bitb.astroskop.model.enums.Ruler
import de.bitb.astroskop.model.enums.Zodiac
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import lombok.Getter
import lombok.Setter

@Getter
@Setter
@Entity
class Circumstance {
    @Id
    var id: Long = 0

    var uuid: String? = null
        set(uuid) {
            field = this.uuid
        }
    var creationTimestamp: Long = 0
        set(creationTimestamp) {
            field = this.creationTimestamp
        }

    @Convert(converter = ZodiacConverter::class, dbType = Int::class)
    var zodiac: Zodiac? = null
        set(zodiac) {
            field = this.zodiac
        }
    @Convert(converter = HouseConverter::class, dbType = Int::class)
    var house: House? = null
        set(house) {
            field = this.house
        }
    @Convert(converter = RulerConverter::class, dbType = Int::class)
    var ruler: Ruler? = null
        set(ruler) {
            field = this.ruler
        }

}
