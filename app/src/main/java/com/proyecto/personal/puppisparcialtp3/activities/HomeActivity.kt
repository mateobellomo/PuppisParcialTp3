package com.proyecto.personal.puppisparcialtp3.activities

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.navigation.NavigationView
import com.proyecto.personal.puppisparcialtp3.R
import com.proyecto.personal.puppisparcialtp3.databinding.ActivityHomeBinding
import com.proyecto.personal.puppisparcialtp3.domain.Pet
import com.proyecto.personal.puppisparcialtp3.helpers.SharedPref
import com.proyecto.personal.puppisparcialtp3.viewModels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val sharedViewModel : SharedViewModel by viewModels()
    private var nightMode: Boolean = false
    lateinit var badge : BadgeDrawable
    private var counter = 0



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(sharedViewModel.pets.value.isNullOrEmpty()){
            Log.d("mis pets si llama", "llama")
            sharedViewModel.getRepositoryDogs()
        }
        sharedViewModel.onCreate()

        SharedPref.init(applicationContext)
        nightMode = SharedPref.read(SharedPref.DARK_MODE, false)
        if(nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        binding = ActivityHomeBinding.inflate(layoutInflater)
        drawerLayout = binding.drawerLayout
        setContentView(binding.root)

        val navView: NavigationView = binding.navView
        setSupportActionBar(binding.appBar.toolbar)
        val navController = findNavController(R.id.nav_host_fragment_content)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,R.id.navigation_profile, R.id.navigation_config, R.id.navigation_favorites,
                R.id.navigation_adoptions,R.id.navigation_post,R.id.petFileFragment
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val bottomNav = binding.navBottom
        bottomNav.setupWithNavController(navController)
         badge = bottomNav.getOrCreateBadge(R.id.navigation_adoptions)
        badge.isVisible = false
        badge.number = counter

        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            binding.appBar.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )


// Oculta la Toolbar cuando se abre el DrawerLayout
        drawerLayout.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                if (slideOffset > 0.6) {
                    supportActionBar?.hide()
                } else {
                    supportActionBar?.show()
                }
            }

            override fun onDrawerClosed(drawerView: View) {
                supportActionBar?.show()
            }
        })
//        actionBarDrawerToggle.isDrawerIndicatorEnabled = true
//        actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_hamburger)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_hamburger)
        }
        updateDrawerInfo()
        SharedPref.addImageURLChangeListener { newImageUrl ->
            newImageUrl?.let {
                updateHeaderImage(
                    imageUrl = it
                )
            }
        }
        sharedViewModel.pets.observe(this, Observer {
            if (it != null) {
                if(it.isEmpty()){

                }

                val adoptedPets = it.filter { e -> e.isAdopted }
                Log.d("Entro",adoptedPets.size.toString())
                if (adoptedPets.isNotEmpty()) {
                    badge.isVisible = true
                    badge.number = adoptedPets.size

                } else {
                    badge.isVisible = false
                }

            }

        })


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.icono_toolbar -> {


                return true
            }
            // Otros casos si hay más elementos en el menú
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun updateDrawerInfo() {
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val headerView = navigationView.getHeaderView(0)

        val photoUrl = SharedPref.read(SharedPref.IMAGE_URL, "")
        val userName = SharedPref.read(SharedPref.NAME, "")

        if (photoUrl != null) {
            val userNameTxt = headerView.findViewById<TextView>(R.id.avatarName)
            userNameTxt.text = userName
            val userImageView = headerView.findViewById<ImageView>(R.id.imageView)
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_placeholder)
            requestOptions.error(R.drawable.ic_error)
            Glide.with(this)
                .load(photoUrl)
                .apply(requestOptions)
                .thumbnail(0.5f)
                .circleCrop()
                .into(userImageView)
        }
    }

    private fun updateHeaderImage(imageUrl: String) {
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val headerView = navigationView.getHeaderView(0)
        val userImageView = headerView.findViewById<ImageView>(R.id.imageView)
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.ic_placeholder)
        requestOptions.error(R.drawable.ic_error)
        Glide.with(this)
            .load(imageUrl)
            .apply(requestOptions)
            .thumbnail(0.5f)
            .circleCrop()
            .into(userImageView)
    }
}