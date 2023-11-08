package com.proyecto.personal.puppisparcialtp3.data

import com.proyecto.personal.puppisparcialtp3.data.model.DogsModel
import com.proyecto.personal.puppisparcialtp3.data.network.DogsService
import com.proyecto.personal.puppisparcialtp3.domain.Pet
import com.proyecto.personal.puppisparcialtp3.utils.Gender
import com.proyecto.personal.puppisparcialtp3.utils.Location
import javax.inject.Inject


class DogsRepository  @Inject constructor( private val  service: DogsService){



  suspend fun getDogsFromApi(): DogsModel {
   val response: DogsModel =  service.getDogsImage()
   return response //.map { it.toDomain()}
  }

    suspend fun getAllBreeds(): List<Pair<String, List<String>>>? {
        val response: List<Pair<String, List<String>>>? =  service.getDogsBreeds()
        return response //.map { it.toDomain()}
    }

    suspend fun getSpecificBreedImages(breed :String, imgNumber:Int): DogsModel {
        val response: DogsModel =  service.getSpecificBreedImages(breed,imgNumber)
        return response //.map { it.toDomain()}
    }

    suspend fun createDogs() : List<Pet>{
        val beagleImage = getSpecificBreedImages("beagle", 1)
        val shibaImage = getSpecificBreedImages("shiba", 3)
        val akitaImage = getSpecificBreedImages("akita", 2)
        val mastiffImage = getSpecificBreedImages("mastiff", 1)
        val pugImage = getSpecificBreedImages("pug", 1)
        val labradorImage = getSpecificBreedImages("labrador", 3)
        val weimaranerImage = getSpecificBreedImages("weimaraner", 2)

        val listPet: MutableList<Pet> = ArrayList()


        listPet.add(
            Pet(Pet.nextId(),
                "Agustin",
                10,
                "beagle",
                "shiba",
                Gender.MALE,
                "Soy un perrito amigable que busca un hogar y me gustaria ser adoptado para comenzar nuevas aventuras, me encontraron en la calle pero estoy sano, tengo todas las vacunas excepto la antiparasitaria, y me llevo bien con gatitos y otros perritos, sean cachorros o mayores. ",
                "30 gr",
                Location.BUENOS_AIRES,
                "Agustin",
                photo = beagleImage.dogsImage[0],
                false,
                isFavorite = false,
                ownerNumber = 1132756493
            )
        )
        listPet.add(
            Pet(Pet.nextId(),
                "Paola",
                10,
                "cow",
                "shiba",
                Gender.FEMALE,
                "Nada",
                "30 kg",
                Location.BUENOS_AIRES,
                "Agustin",
                photo = shibaImage.dogsImage[0],
                false,
                isFavorite = false,
                ownerNumber = 47562922
            )
        )
        listPet.add(
            Pet(Pet.nextId(),
                "Yanina",
                10,
                "labrador",
                "shiba",
                Gender.MALE,
                "Nada",
                "30 kgs",
                Location.BUENOS_AIRES,
                "Agustin",
                photo = shibaImage.dogsImage[1],
                false,
                isFavorite = false,
                ownerNumber = 5739229
            )
        )
        listPet.add(
            Pet(Pet.nextId(),
                "Camila",
                10,
                "pug",
                "shiba",
                Gender.MALE,
                "Nada",
                "30.0",
                Location.BUENOS_AIRES,
                "Agustin",
                photo = shibaImage.dogsImage[2],
                false,
                isFavorite = true,
                ownerNumber = 1333333
            )
        )
        listPet.add(
            Pet(Pet.nextId(),
                "Fernando",
                1,
                "mastiff",
                "mastiff",
                Gender.MALE,
                "Nada",
                "30.0",
                Location.JUJUY,
                "Agustin",
                photo = mastiffImage.dogsImage[0],
                false,
                isFavorite = true,
                ownerNumber = 34449292
            )
        )
        listPet.add(
            Pet(Pet.nextId(),
                "Raul",
                11,
                "pug",
                "pug",
                Gender.FEMALE,
                "Nada",
                "30.0",
                Location.CORDOBA,
                "Agustin",
                photo = pugImage.dogsImage[0],
                true,
                isFavorite = false,
                ownerNumber = 76948483
            )
        )
        listPet.add(
            Pet(Pet.nextId(),
                "Vulpix",
                12,
                "weimaraner",
                "weimaraner",
                Gender.FEMALE,
                "Nada",
                "30.0",
                Location.CORDOBA,
                "Javier",
                photo = weimaranerImage.dogsImage[0],
                false,
                isFavorite = false,
                ownerNumber = 93838338
            )
        )
        listPet.add(
            Pet(Pet.nextId(),
                "Eevee",
                18,
                "weimaraner",
                "weimaraner",
                Gender.FEMALE,
                "Nada",
                "30.0",
                Location.CORDOBA,
                "Rodrigo",
                photo = weimaranerImage.dogsImage[1],
                false,
                isFavorite = false,
                ownerNumber = 138349
            )
        )
        listPet.add(
            Pet(Pet.nextId(),
                "BigBoy",
                20,
                "labrador",
                "labrador",
                Gender.MALE,
                "Nada",
                "10.20",
                Location.CORDOBA,
                "Alexander",
                photo = labradorImage.dogsImage[0],
                false,
                isFavorite = false,
                ownerNumber = 99383838
            )
        )
        listPet.add(
            Pet(Pet.nextId(),
                "Dora",
                1,
                "labrador",
                "labrador",
                Gender.FEMALE,
                "Nada",
                "30.0",
                Location.CORDOBA,
                "Martina",
                photo = labradorImage.dogsImage[1],
                false,
                isFavorite = false,
                ownerNumber = 93838383
            )
        )
        listPet.add(
            Pet(Pet.nextId(),
                "Tommy",
                3,
                "labrador",
                "labrador",
                Gender.MALE,
                "Nada",
                "12.56",
                Location.CORDOBA,
                "Daniel",
                photo = labradorImage.dogsImage[2],
                false,
                isFavorite = false,
                ownerNumber = 11327566
            )
        )
            return listPet
    }

 }