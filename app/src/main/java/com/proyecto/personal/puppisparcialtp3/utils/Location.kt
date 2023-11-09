package com.proyecto.personal.puppisparcialtp3.utils

enum class Location(val location: String) {
    BUENOS_AIRES("Buenos Aires"),
    CIUDAD_AUTONOMA_DE_BUENOS_AIRES("Ciudad Autonoma de Buenos Aires"),
    CATAMARCA("Catamarca"),
    CHACO("Chaco"),
    CHUBUT("Chubut"),
    CORDOBA("Cordoba"),
    CORRIENTES("Corrientes"),
    ENTRE_RIOS("Entre Rios"),
    FORMOSA("Formosa"),
    JUJUY("Jujuy"),
    LA_PAMPA("La Pampa"),
    LA_RIOJA("La Rioja"),
    MENDOZA("Mendoza"),
    MISIONES("Misiones"),
    NEUQUEN("Neuquen"),
    RIO_NEGRO("Río Negro"),
    SALTA("Salta"),
    SAN_JUAN("San Juan"),
    SAN_LUIS("San Luis"),
    SANTA_CRUZ("Santa Cruz"),
    SANTA_FE("Santa Fe"),
    SANTIAGO_DEL_ESTERO("Santiago del Estero"),
    TIERRA_DEL_FUEGO("Tierra del Fuego, Antartida e Islas del Atlantico Sur"),
    TUCUMAN("Tucuman");


    companion object {
        fun fromString(value: String): Location {
            return when (value) {
                "Buenos Aires" -> BUENOS_AIRES
                "Ciudad Autonoma de Buenos Aires" -> CIUDAD_AUTONOMA_DE_BUENOS_AIRES
                "Catamarca" -> CATAMARCA
                "Chaco" -> CHACO
                "Chubut" -> CHUBUT
                "Cordoba" -> CORDOBA
                "Corrientes" -> CORRIENTES
                "Entre Rios" -> ENTRE_RIOS
                "Formosa" -> FORMOSA
                "Jujuy" -> JUJUY
                "La Pampa" -> LA_PAMPA
                "La Rioja" -> LA_RIOJA
                "Mendoza" -> MENDOZA
                "Misiones" -> MISIONES
                "Neuquen" -> NEUQUEN
                "Río Negro" -> RIO_NEGRO
                "Salta" -> SALTA
                "San Juan" -> SAN_JUAN
                "San Luis" -> SAN_LUIS
                "Santa Cruz" -> SANTA_CRUZ
                "Santa Fe" -> SANTA_FE
                "Santiago del Estero" -> SANTIAGO_DEL_ESTERO
                "Tierra del Fuego, Antartida e Islas del Atlantico Sur" -> TIERRA_DEL_FUEGO
                else -> TUCUMAN
            }
        }
    }
}