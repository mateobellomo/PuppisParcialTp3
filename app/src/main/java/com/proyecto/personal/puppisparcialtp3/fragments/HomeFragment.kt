package com.proyecto.personal.puppisparcialtp3.fragments

import android.app.SearchManager
import android.database.MatrixCursor
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.BaseColumns
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CursorAdapter
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.personal.puppisparcialtp3.R
import com.proyecto.personal.puppisparcialtp3.adapters.FilterAdapter
import com.proyecto.personal.puppisparcialtp3.adapters.PetListAdapter
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentHomeBinding
import com.proyecto.personal.puppisparcialtp3.utils.AgeRange
import com.proyecto.personal.puppisparcialtp3.utils.Gender
import com.proyecto.personal.puppisparcialtp3.utils.Location
import com.proyecto.personal.puppisparcialtp3.domain.Pet
import com.proyecto.personal.puppisparcialtp3.listeners.OnFavoritesClickListener
import com.proyecto.personal.puppisparcialtp3.listeners.OnViewItemClickedListener
import com.proyecto.personal.puppisparcialtp3.viewModels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), OnViewItemClickedListener, OnFavoritesClickListener {

    lateinit var vista: View

    lateinit var recPets : RecyclerView
    lateinit var filterAdapter : FilterAdapter
    private var filters : MutableList<String> = mutableListOf()
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var petListAdapter: PetListAdapter
    private var _binding: FragmentHomeBinding? = null
    private val sharedViewModel : SharedViewModel by activityViewModels()
    private val binding get() = _binding!!
    private var locationSelected:Boolean = false
    private var genderSelected:Boolean = false
    private var ageSelected:Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
      //  sharedViewModel.onCreate()

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val vista: View = binding.root

        recPets = vista.findViewById(R.id.fragmentHomeRecPets)

        val boton = vista.findViewById<Button>(R.id.boton_prueba_agregar)
        boton.setOnClickListener {
            sharedViewModel.newPet()


        }

        filterAdapter =  FilterAdapter(mutableListOf())
        initRecyclerView()
        sharedViewModel.filters.observe(viewLifecycleOwner, Observer {

            if (it != null) {
                filterAdapter.updateData(it)
            }
        })



        return vista
    }


    override fun onStart() {
        super.onStart()


        val emptyList = mutableListOf<Pet>()




        sharedViewModel.pets.observe(this, Observer {
            if (it != null) {
                petListAdapter.updateData(it)
            }
        })


        requireActivity()

        recPets.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        petListAdapter = PetListAdapter(emptyList, this,this )

        recPets.layoutManager = linearLayoutManager
        recPets.adapter = petListAdapter


        val filterBtn = binding.filterBtn
        val cleanFilters =binding.cleanFilters

        createPopMenu()




        cleanFilters.setOnClickListener{
            sharedViewModel.clearList()
            locationSelected = false
             genderSelected = false
            ageSelected = false
            petListAdapter.restoreList()
        }

        sharedViewModel.dogBreedSugestions.observe(this, Observer {
            if (!it.isNullOrEmpty()){
                intiSearchBar()
            }

        })




    }
    override fun onViewItemDetail(pet: Pet) {
     //  val action = Fragment3Directions.actionFragment3ToViewItem(contacto)
       // this.findNavController().navigate(action)
       // findNavController().navigate(action)
       // Snackbar.make(vista,pet.name, Snackbar.LENGTH_SHORT).show()
        Toast.makeText(context,"todavia no implementado!!!", Toast.LENGTH_SHORT).show()

    }

    private fun initRecyclerView() {
        binding.rvFilter.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvFilter.adapter = filterAdapter

    }


    private fun createPopMenu() {
        binding.filterBtn.setOnClickListener { view ->
            val popupMenu =
                PopupMenu(context, view) // "view" es el elemento que desencadena el menú emergente
            popupMenu.menuInflater.inflate(R.menu.pop_menu, popupMenu.menu)

            if (locationSelected == false) {
                val locationSubMenu = popupMenu.menu.addSubMenu("Ubicacion")
                for (location in Location.values()) {
                    val menuItem = locationSubMenu.add(location.name)
                    menuItem.setOnMenuItemClickListener {
                        sharedViewModel.addFilter(location.name)
                        petListAdapter.filterCategory(location.name)
                        locationSelected = true
                        true

                    }
                }
            }
                if (genderSelected == false) {
                    val genderSubMenu = popupMenu.menu.addSubMenu("Genero")

                    for (gender in Gender.values()) {
                        val menuItem =  genderSubMenu.add(gender.name)
                        menuItem.setOnMenuItemClickListener {
                            sharedViewModel.addFilter(gender.name)
                            petListAdapter.filterCategory(gender.name)
                            genderSelected = true
                            true
                    }
                }
                }
                if (ageSelected == false) {
                    val ageSubMenu = popupMenu.menu.addSubMenu("Edad")

                    for (ageRange in AgeRange.values()) {
                        val menuItem =  ageSubMenu.add(ageRange.ageRange)
                        menuItem.setOnMenuItemClickListener {
                            sharedViewModel.addFilter(ageRange.ageRange)
                            petListAdapter.filterCategory(ageRange.ageRange)
                            ageSelected = true
                            true
                        }
                }


                }



//                popupMenu.setOnMenuItemClickListener {
//                    sharedViewModel.addFilter(it.title.toString())
//                    true
//                }
                popupMenu.show()
            }

        }

    private fun filterText(query: String?) {
        if (query.isNullOrBlank()) {
           sharedViewModel.resetOriginalList()

            return
        }
        petListAdapter.filterBreed(query)

    }


    private fun intiSearchBar(){
        val searchView = binding.searchView

        val sugerencias = sharedViewModel.dogBreedSugestions.value?.toTypedArray()
        val dogBreeds = sharedViewModel.availablesBreed()
        sharedViewModel.listPet
        val cursor = MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))

        dogBreeds.forEachIndexed { index, suggestion ->
            val suggestionAvailable = dogBreeds.contains(suggestion)
            cursor.addRow(arrayOf(index, suggestion))

            // Personaliza el color o el estilo de la sugerencia según su disponibilidad
            if (!suggestionAvailable) {
                cursor.setNotificationUri(context?.contentResolver, Uri.parse("content://com.example.provider/suggestions"))
            }
        }

        // Configurar el CursorAdapter para las sugerencias
        val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
        val to = intArrayOf(R.id.text1)
        var cursorAdapter = SimpleCursorAdapter(context, R.layout.search_item, null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER)


        cursorAdapter.viewBinder = SimpleCursorAdapter.ViewBinder { view, cursor, columnIndex ->
            if (columnIndex == cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1)) {
                val suggestion = cursor.getString(columnIndex)

                // Suponiendo que dogBreeds es la lista de razas de perros actual
                val suggestionAvailable = dogBreeds.contains(suggestion)

                // Personaliza el color del texto según la disponibilidad
                val textView = view as TextView
                val spannableString = SpannableString(suggestion)

                if (!suggestionAvailable) {
                    val notAvailableText = SpannableString(" (not available at the moment)")
                    notAvailableText.setSpan(ForegroundColorSpan(Color.BLACK), 0, notAvailableText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

                    val finalText = TextUtils.concat(spannableString, notAvailableText)
                    textView.text = finalText
                } else {
                    textView.text = spannableString
                }

                return@ViewBinder true
            }

            return@ViewBinder false
        }


         searchView.suggestionsAdapter = cursorAdapter
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterText(newText)

                val cursor =
                    MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))
                newText.let {

                    if (sugerencias != null) {
                        sugerencias.forEachIndexed { index, suggestion ->
                            if (suggestion.contains(newText, true))
                                cursor.addRow(arrayOf(index, suggestion))
                        }
                    }
                }

                cursorAdapter.changeCursor(cursor)

                return true
            }
        })

    }

    override fun onFavoritesClick(pet: Pet) {
        sharedViewModel.onFavoritesClick(pet)
        Toast.makeText(context,"Hemos tomado nota!",Toast.LENGTH_SHORT).show()
    }

}

