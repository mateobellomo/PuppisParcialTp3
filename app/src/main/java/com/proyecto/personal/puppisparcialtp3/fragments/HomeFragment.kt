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
import android.widget.CursorAdapter
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
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
import com.proyecto.personal.puppisparcialtp3.listeners.OnFilterClickedListener
import com.proyecto.personal.puppisparcialtp3.listeners.OnViewItemClickedListener
import com.proyecto.personal.puppisparcialtp3.viewModels.HomeViewModel
import com.proyecto.personal.puppisparcialtp3.viewModels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), OnViewItemClickedListener, OnFavoritesClickListener,
    OnFilterClickedListener {


    private lateinit var recPets: RecyclerView
    private lateinit var filterAdapter: FilterAdapter
    private var filters: MutableList<String> = mutableListOf()
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var petListAdapter: PetListAdapter
    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val binding get() = _binding!!
    private var locationSelected: Boolean = false
    private var genderSelected: Boolean = false
    private var ageSelected: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val vista: View = binding.root

        recPets = vista.findViewById(R.id.fragmentHomeRecPets)

        filterAdapter = FilterAdapter(mutableListOf(), this)

        initRecyclerView()

        homeViewModel.filters.observe(viewLifecycleOwner, Observer {

            if (it != null) {
                filterAdapter.updateData(it)
            }
        })
        return vista
    }


    override fun onStart() {
        super.onStart()

        recPets.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        petListAdapter = PetListAdapter(mutableListOf(), this, this)
        recPets.layoutManager = linearLayoutManager
        recPets.adapter = petListAdapter

        val cleanFilters = binding.cleanFilters

        createPopMenu()


        cleanFilters.setOnClickListener {
            homeViewModel.clearList()
            locationSelected = false
            genderSelected = false
            ageSelected = false
            petListAdapter.clearFilterList()
        }

        sharedViewModel.pets.observe(this, Observer {
            if (!it.isNullOrEmpty() && sharedViewModel.dogBreedSuggestions.value != null) {
                initSearchBar()
            }
        })

        sharedViewModel.pets.observe(this, Observer {
            if (it != null) {
                val nonAdoptedPets: List<Pet> = it.filter { !it.isAdopted }
                petListAdapter.updateData(nonAdoptedPets)
                binding.homeProgressBar.visibility = View.GONE
            }
        })
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

            if (!locationSelected) {
                val locationSubMenu = popupMenu.menu.addSubMenu("Location")
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
            if (!genderSelected) {
                val genderSubMenu = popupMenu.menu.addSubMenu("Gender")

                for (gender in Gender.values()) {
                    val menuItem = genderSubMenu.add(gender.name)
                    menuItem.setOnMenuItemClickListener {
                        homeViewModel.addFilter(gender.name)
                        petListAdapter.filterCategory(gender.name)
                        genderSelected = true
                        true
                    }
                }
            }
            if (!ageSelected) {
                val ageSubMenu = popupMenu.menu.addSubMenu("Age")

                for (ageRange in AgeRange.values()) {
                    val menuItem = ageSubMenu.add(ageRange.ageRange)
                    menuItem.setOnMenuItemClickListener {
                        homeViewModel.addFilter(ageRange.ageRange)
                        petListAdapter.filterCategory(ageRange.ageRange)
                        ageSelected = true
                        true
                    }
                }
            }


            popupMenu.show()

        }

    }

    private fun filterText(query: String?) {
        petListAdapter.filterBreed(query)
    }

    private fun initSearchBar() {
        val searchView = binding.searchView

        val suggestions = sharedViewModel.dogBreedSuggestions.value?.toTypedArray()
        val dogBreeds = sharedViewModel.availableBreed()
        val cursor = MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))

        dogBreeds.forEachIndexed { index, suggestion ->
            val suggestionAvailable = dogBreeds.contains(suggestion)
            cursor.addRow(arrayOf(index, suggestion))

            if (!suggestionAvailable) {
                cursor.setNotificationUri(
                    context?.contentResolver,
                    Uri.parse("content://com.example.provider/suggestions")
                )
            }
        }

        // Configurar el CursorAdapter para las sugerencias
        val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
        val to = intArrayOf(R.id.text1)
        var cursorAdapter = SimpleCursorAdapter(
            context,
            R.layout.search_item,
            null,
            from,
            to,
            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        )

        cursorAdapter.viewBinder = SimpleCursorAdapter.ViewBinder { view, cursor, columnIndex ->
            if (columnIndex == cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1)) {
                val suggestion = cursor.getString(columnIndex)

                val suggestionAvailable = dogBreeds.contains(suggestion)

                // Personaliza el color del texto según la disponibilidad
                val textView = view as TextView
                val spannableString = SpannableString(suggestion)

                if (!suggestionAvailable) {
                    val notAvailableText = SpannableString(" (Not available at the moment)")
                    notAvailableText.setSpan(
                        ForegroundColorSpan(Color.BLACK),
                        0,
                        notAvailableText.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )

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
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterText(newText)

                val cursor =
                    MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))
                newText.let {

                    if (suggestions != null) {
                        suggestions.forEachIndexed { index, suggestion ->
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
        Toast.makeText(context, "We have taken note", Toast.LENGTH_SHORT).show()
    }

    override fun onViewItemDetail(pet: Pet) {
        val action = HomeFragmentDirections.actionNavigationHomeToPetFileFragment(pet.id)
        findNavController().navigate(action)
    }

    override fun onFilterClick(filter: String) {
        homeViewModel.deleteFilter(filter)
        petListAdapter.deleteFilter(filter)

        when (filter) {
            "FEMALE" -> genderSelected = false
            "MALE" -> genderSelected = false
            "Puppy" -> ageSelected = false
            "Teen" -> ageSelected = false
            "Adult" -> ageSelected = false
            "Senior" -> ageSelected = false
            else -> locationSelected = false
        }
    }
}

