package scisrc.mobiledev.ecommercelayout.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import scisrc.mobiledev.ecommercelayout.R
import scisrc.mobiledev.ecommercelayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ตั้งค่า Toolbar ให้เป็น ActionBar ของ Activity
        setSupportActionBar(binding.toolbar)

        // กำหนด DrawerLayout
        drawerLayout = binding.drawerLayout

        // สร้าง ActionBarDrawerToggle เพื่อผูก Drawer กับ Toolbar
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // ตั้งค่า NavigationView เมื่อผู้ใช้เลือกเมนู
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            val fragment = when (menuItem.itemId) {
                R.id.nav_home -> HomeFragment.newInstance(menuItem.itemId)
                R.id.nav_products -> ProductListFragment()
                R.id.nav_cart -> CartFragment()
                R.id.nav_profile -> ProfileFragment()
                R.id.nav_favorites -> FavoritesFragment()
                else -> HomeFragment.newInstance(R.id.nav_home)
            }

            replaceFragment(fragment, menuItem.itemId)
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // โหลด HomeFragment เป็น Fragment เริ่มต้น
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment.newInstance(R.id.nav_home), R.id.nav_home)
            binding.navView.setCheckedItem(R.id.nav_home)
        }
    }

    private fun replaceFragment(fragment: Fragment, tag: Int) {
        val fragmentManager = supportFragmentManager
        val existingFragment = fragmentManager.findFragmentByTag(tag.toString())

        if (existingFragment == null) {
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, tag.toString())
                .commit()
        }
    }

    // Inflate Options Menu (ปุ่มด้านขวาของ Toolbar)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // จัดการคลิก Options Menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                true
            }
            R.id.action_notification -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
