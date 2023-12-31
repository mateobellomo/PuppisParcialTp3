package com.proyecto.personal.puppisparcialtp3.data

import com.proyecto.personal.puppisparcialtp3.data.model.DogsModel
import com.proyecto.personal.puppisparcialtp3.data.network.DogsService
import com.proyecto.personal.puppisparcialtp3.domain.Pet
import com.proyecto.personal.puppisparcialtp3.utils.Gender
import com.proyecto.personal.puppisparcialtp3.utils.Location
import javax.inject.Inject


class DogsRepository @Inject constructor(private val service: DogsService) {


    suspend fun getDogsFromApi(): DogsModel {
        val response: DogsModel = service.getDogsImage()
        return response //.map { it.toDomain()}
    }

    suspend fun getAllBreeds(): List<Pair<String, List<String>>>? {
        val response: List<Pair<String, List<String>>>? = service.getDogsBreeds()
        return response //.map { it.toDomain()}
    }

    suspend fun getSpecificBreedImages(breed: String, imgNumber: Int): DogsModel {
        val response: DogsModel = service.getSpecificBreedImages(breed, imgNumber)
        return response //.map { it.toDomain()}
    }

    suspend fun createDogs(): List<Pet> {
        val beagleImage = getSpecificBreedImages("beagle", 3)
        val shibaImage = getSpecificBreedImages("shiba", 3)
        val shibaImage2 = getSpecificBreedImages("shiba", 3)
        val shibaImage3 = getSpecificBreedImages("shiba", 3)
        val akitaImage = getSpecificBreedImages("akita", 3)
        val mastiffImage = getSpecificBreedImages("mastiff", 3)
        val pugImage = getSpecificBreedImages("pug", 3)
        val labradorImage = getSpecificBreedImages("labrador", 3)
        val labradorImage2 = getSpecificBreedImages("labrador", 3)
        val weimaranerImage = getSpecificBreedImages("weimaraner", 3)
        val weimaranerImage2 = getSpecificBreedImages("weimaraner", 3)

        val listPet: MutableList<Pet> = ArrayList()

        listPet.add(
            Pet(
                Pet.nextId(),
                "Kenzi",
                10,
                "beagle",
                "",
                Gender.MALE,
                "Soy un perrito amigable que busca un hogar y me gustaria ser adoptado para comenzar nuevas aventuras, me encontraron en la calle pero estoy sano, tengo todas las vacunas excepto la antiparasitaria, y me llevo bien con gatitos y otros perritos, sean cachorros o mayores. ",
                "30 gr",
                Location.BUENOS_AIRES,
                "Agustin",
                photo = beagleImage.dogsImage,
                false,
                isFavorite = false,
                ownerNumber = "1132756493",
                ownerImageUrl = "https://scontent.faep14-2.fna.fbcdn.net/v/t1.6435-9/118873612_2890788714482355_7579068685010299484_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=dd63ad&_nc_eui2=AeFBxTBLpHqsHlCPoSotctsPQh_uY71grdtCH-5jvWCt26k_VlyFa-tUqag4scElrTU&_nc_ohc=4yEdZojRGNgAX9HzK0H&_nc_ht=scontent.faep14-2.fna&oh=00_AfAoJ9y-xrjmcuMbtWT9NwLVN-d2KH7BgTwuON8-fzyJEg&oe=65748333"
            )
        )
        listPet.add(
            Pet(
                Pet.nextId(),
                "Skye",
                10,
                "shiba",
                "",
                Gender.FEMALE,
                "Nada",
                "30 kg",
                Location.BUENOS_AIRES,
                "Agustin",
                photo = shibaImage.dogsImage,
                false,
                isFavorite = false,
                ownerNumber = "47562922",
                ownerImageUrl = "https://petapixel.com/assets/uploads/2019/02/download-1-800x800.jpeg"
            )
        )
        listPet.add(
            Pet(
                Pet.nextId(),
                "Spark",
                10,
                "shiba",
                "",
                Gender.MALE,
                "Nada",
                "30 kgs",
                Location.BUENOS_AIRES,
                "Agustin",
                photo = shibaImage2.dogsImage,
                false,
                isFavorite = false,
                ownerNumber = "5739229",
                ownerImageUrl = "https://media.licdn.com/dms/image/D4D08AQE0CXu4hnoe7g/croft-frontend-shrinkToFit1024/0/1646754728586?e=2147483647&v=beta&t=ADkOVwOwmP-4rCH4y0g2_OBFlsszl01TpQPhCgt5SSc"
            )
        )
        listPet.add(
            Pet(
                Pet.nextId(),
                "Camila",
                10,
                "shiba",
                "",
                Gender.MALE,
                "Nada",
                "30.0",
                Location.BUENOS_AIRES,
                "Agustin",
                photo = shibaImage3.dogsImage,
                false,
                isFavorite = false,
                ownerNumber = "1333333",
                ownerImageUrl = "https://petapixel.com/assets/uploads/2019/02/download-2-800x800.jpeg"
            )
        )
        listPet.add(
            Pet(
                Pet.nextId(),
                "Charmander",
                1,
                "mastiff",
                "",
                Gender.MALE,
                "Nada",
                "30.0",
                Location.JUJUY,
                "Agustin",
                photo = mastiffImage.dogsImage,
                false,
                isFavorite = false,
                ownerNumber = "34449292",
                ownerImageUrl = "https://petapixel.com/assets/uploads/2019/02/download-3-800x800.jpeg"
            )
        )
        listPet.add(
            Pet(
                Pet.nextId(),
                "Kubon",
                11,
                "pug",
                "",
                Gender.FEMALE,
                "Nada",
                "30.0",
                Location.CORDOBA,
                "Agustin",
                photo = pugImage.dogsImage,
                false,
                isFavorite = false,
                ownerNumber = "76948483",
                ownerImageUrl = "https://petapixel.com/assets/uploads/2019/02/download-4-800x800.jpeg"
            )
        )
        listPet.add(
            Pet(
                Pet.nextId(),
                "Vulpix",
                12,
                "weimaraner",
                "",
                Gender.FEMALE,
                "Nada",
                "30.0",
                Location.CORDOBA,
                "Javier",
                photo = weimaranerImage.dogsImage,
                false,
                isFavorite = false,
                ownerNumber = "93838338",
                ownerImageUrl = "https://petapixel.com/assets/uploads/2019/02/download-800x800.jpeg"
            )
        )
        listPet.add(
            Pet(
                Pet.nextId(),
                "Eevee",
                18,
                "weimaraner",
                "",
                Gender.FEMALE,
                "Nada",
                "30.0",
                Location.CORDOBA,
                "Rodrigo",
                photo = weimaranerImage2.dogsImage,
                false,
                isFavorite = false,
                ownerNumber = "138349",
                ownerImageUrl = "https://petapixel.com/assets/uploads/2019/02/download-4-800x800.jpeg"
            )
        )
        listPet.add(
            Pet(
                Pet.nextId(),
                "BigBoy",
                20,
                "labrador",
                "",
                Gender.MALE,
                "Nada",
                "10.20",
                Location.CORDOBA,
                "Alexander",
                photo = labradorImage.dogsImage,
                false,
                isFavorite = false,
                ownerNumber = "99383838",
                ownerImageUrl = "https://petapixel.com/assets/uploads/2019/02/download-4-800x800.jpeg"
            )
        )
        listPet.add(
            Pet(
                Pet.nextId(),
                "Dora",
                1,
                "labrador",
                "",
                Gender.FEMALE,
                "Nada",
                "30.0",
                Location.CORDOBA,
                "Martina",
                photo = labradorImage2.dogsImage,
                false,
                isFavorite = false,
                ownerNumber = "93838383",
                ownerImageUrl = "https://petapixel.com/assets/uploads/2019/02/download-2-800x800.jpeg"
            )
        )
        listPet.add(
            Pet(
                Pet.nextId(),
                "Tommy",
                3,
                "akita",
                "",
                Gender.MALE,
                "Nada",
                "12.56",
                Location.CHACO,
                "Daniel",
                photo = akitaImage.dogsImage,
                false,
                isFavorite = false,
                ownerNumber = "11327566",
                ownerImageUrl = "https://petapixel.com/assets/uploads/2019/02/download-2-800x800.jpeg"
            )
        )
        return listPet
    }
}