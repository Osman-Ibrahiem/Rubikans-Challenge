package com.rubikans.challenge.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.rubikans.challenge.R
import com.rubikans.challenge.core.theme.ThemeUtils
import com.rubikans.challenge.core.theme.ToggleThemeCheckBox
import com.rubikans.challenge.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        private const val DELAY_TO_APPLY_THEME = 1000L
    }

    private lateinit var binding: ActivityMainBinding
    private val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navigationHostFragment) as NavHostFragment
        navHostFragment.navController
    }

    private val activityScope = CoroutineScope(Dispatchers.Main)

    @Inject
    lateinit var themeUtils: ThemeUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBarWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        menu.findItem(R.id.menu_toggle_theme).apply {
            val actionView = this.actionView
            if (actionView is ToggleThemeCheckBox) {
                actionView.isChecked = themeUtils.isDarkTheme(this@MainActivity)
                actionView.setOnCheckedChangeListener { _, isChecked ->
                    activityScope.launch {
                        themeUtils.setNightMode(isChecked)
                        delay(DELAY_TO_APPLY_THEME)
                    }
                }
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) return navController.navigateUp()
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}