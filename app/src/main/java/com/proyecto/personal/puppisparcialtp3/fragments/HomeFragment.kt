package com.proyecto.personal.puppisparcialtp3.fragments

import android.app.SearchManager
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CursorAdapter
import android.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.personal.puppisparcialtp3.R
import com.proyecto.personal.puppisparcialtp3.adapters.FilterAdapter
import com.proyecto.personal.puppisparcialtp3.adapters.PetListAdapter
import com.proyecto.personal.puppisparcialtp3.databinding.FragmentHomeBinding
import com.proyecto.personal.puppisparcialtp3.entities.AgeRange
import com.proyecto.personal.puppisparcialtp3.entities.Gender
import com.proyecto.personal.puppisparcialtp3.entities.Location
import com.proyecto.personal.puppisparcialtp3.entities.Pet
import com.proyecto.personal.puppisparcialtp3.listeners.OnViewItemClickedListener
import com.proyecto.personal.puppisparcialtp3.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), OnViewItemClickedListener {

    lateinit var vista: View

    lateinit var recPets : RecyclerView
    lateinit var filterAdapter : FilterAdapter
    private var filters : MutableList<String> = mutableListOf()
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var petListAdapter: PetListAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private var locationSelected:Boolean = false
    private var genderSelected:Boolean = false
    private var ageSelected:Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
      //  homeViewModel.onCreate()

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val vista: View = binding.root

        recPets = vista.findViewById(R.id.fragmentHomeRecPets)

        val boton = vista.findViewById<Button>(R.id.boton_prueba_agregar)
        boton.setOnClickListener {
            homeViewModel.newPet()
        }

        filterAdapter =  FilterAdapter(mutableListOf())
        initRecyclerView()
        homeViewModel.filters.observe(viewLifecycleOwner, Observer {

            if (it != null) {
                filterAdapter.updateData(it)
            }
        })

        intiSearchBar()

        return vista
    }


    override fun onStart() {
        super.onStart()


        val emptyList = mutableListOf<Pet>()

        homeViewModel.createPet()


        homeViewModel.pets.observe(this, Observer {
            if (it != null) {
                petListAdapter.updateData(it)
            }
        })


        requireActivity()

        recPets.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        petListAdapter = PetListAdapter(emptyList, this)

        recPets.layoutManager = linearLayoutManager
        recPets.adapter = petListAdapter


        val filterBtn = binding.filterBtn
        val cleanFilters =binding.cleanFilters

        createPopMenu()




        cleanFilters.setOnClickListener{
            homeViewModel.clearList()
            locationSelected = false
             genderSelected = false
            ageSelected = false
            petListAdapter.restoreList()
        }






    }
    override fun onViewItemDetail(pet: Pet) {
     //   val action = Fragment3Directions.actionFragment3ToViewItem(contacto)
      //  this.findNavController().navigate(action)
        //findNavController().navigate(action)
        //Snackbar.make(vista,pet.name, Snackbar.LENGTH_SHORT).show()
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
                        homeViewModel.addFilter(location.name)
                        petListAdapter.filterCategory(location.name)
                        locationSelected = true
                        true

                    }
                }
            }
                if (genderSelected == false) {
                    val genderSubMenu = popupMenu.menu.addSubMenu("Genero")

                    for (gender in Gender.values()) {
                        val menuItem =  genderSubMenu.add(gender.gender)
                        menuItem.setOnMenuItemClickListener {
                            homeViewModel.addFilter(gender.gender)
                            petListAdapter.filterCategory(gender.gender)
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
                            homeViewModel.addFilter(ageRange.ageRange)
                            petListAdapter.filterCategory(ageRange.ageRange)
                            ageSelected = true
                            true
                        }
                }


                }



//                popupMenu.setOnMenuItemClickListener {
//                    homeViewModel.addFilter(it.title.toString())
//                    true
//                }
                popupMenu.show()
            }

        }

    private fun filterText(query: String?) {
        if (query.isNullOrBlank()) {
           homeViewModel.resetOriginalList()

            return
        }
        petListAdapter.filterBreed(query)

    }


    private fun intiSearchBar(){
        val searchView = binding.searchView

        val sugerencias = arrayOf("Opción 1", "Opción 2", "Opción 3","hola", "chau", "martes")
        // val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, sugerencias)
        val cursor = MatrixCursor(arrayOf(BaseColumns._ID, "sugerencia"))
        for ((index, sugerencia) in sugerencias.withIndex()) {
            cursor.addRow(arrayOf(index, sugerencia))
        }

        // Configurar el CursorAdapter para las sugerencias
        val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
        val to = intArrayOf(R.id.text1)
        var cursorAdapter = SimpleCursorAdapter(context, R.layout.search_item, null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER)

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

                    sugerencias.forEachIndexed { index, suggestion ->
                        if (suggestion.contains(newText, true))
                            cursor.addRow(arrayOf(index, suggestion))
                    }
                }

                cursorAdapter.changeCursor(cursor)

                return true
            }
        })

    }

    }

