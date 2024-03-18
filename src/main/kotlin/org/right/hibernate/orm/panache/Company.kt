package org.right.hibernate.orm.panache

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import jakarta.persistence.Cacheable
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
@Cacheable
class Company : PanacheEntity {
    companion object: PanacheCompanion<Company>

    @Column
    var name: String = ""

    @Column
    var naceCode: String = ""

    @Column
    var grossValueAdded: Float = 0f

    @Column
    var emissionScope1: Float = 0f

    @Column
    var emissionScope2: Float = 0f

    @Column
    var emissionScope3: Float = 0f

    constructor()

    constructor(name: String, naceCode: String, grossValueAdded: Float, emissionScope1: Float, emissionScope2: Float, emissionScope3: Float) {
        this.name = name
        this.naceCode = naceCode
        this.grossValueAdded = grossValueAdded
        this.emissionScope1 = emissionScope1
        this.emissionScope2 = emissionScope2
        this.emissionScope3 = emissionScope3
    }

    fun isValid(): Boolean {
        if (this.name.isBlank() || this.naceCode.isBlank())
            return false
        if (this.grossValueAdded < 0 || this.emissionScope1 < 0 || this.emissionScope2 < 0 || this.emissionScope3 < 0 )
            return false

        val regex = Regex("[A-U]([0-9][0-9]?)?(.[0-9][0-9]?)?(.[0-9][0-9]?)?")
        return regex.matches(this.naceCode)
    }
}