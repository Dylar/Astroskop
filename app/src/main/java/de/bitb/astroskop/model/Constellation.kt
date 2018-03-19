package de.bitb.astroskop.model

import java.io.Serializable

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

@Entity
@Getter
@Setter
class Constellation : Serializable {

    @Id
    var id: Long = 0

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

    val isHouse: Boolean
        get() = this.ruler == null || Ruler.NONE == this.ruler

    companion object {

        fun create(zodiac: Zodiac, house: House, ruler: Ruler): Constellation {
            val constellation = Constellation()
            constellation.zodiac = zodiac
            constellation.house = house
            constellation.ruler = ruler
            return constellation
        }
    }
}
