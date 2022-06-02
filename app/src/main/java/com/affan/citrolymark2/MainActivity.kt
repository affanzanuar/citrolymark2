package com.affan.citrolymark2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.affan.citrolymark2.activity.LoginActivity
import com.affan.citrolymark2.activity.MasukActivity
import com.affan.citrolymark2.fragment.AkunFragment
import com.affan.citrolymark2.fragment.HomeFragment
import com.affan.citrolymark2.fragment.InboxFragment
import com.affan.citrolymark2.fragment.TransaksiFragment
import com.affan.citrolymark2.helper.SharedPref
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val fragmentHome: Fragment = HomeFragment()
    private var fragmentTransaksi: Fragment = TransaksiFragment()
    private var fragmentInbox: Fragment = InboxFragment()
    private var fragmentAkun: Fragment = AkunFragment()
    private val fm: FragmentManager = supportFragmentManager
    private var active: Fragment = fragmentHome

    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    private var statusLogin = false

    private lateinit var s:SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav_view.background = null

        s = SharedPref(this)

        setUpBottomNav()
    }

    fun setUpBottomNav(){
        fm.beginTransaction().add(R.id.container, fragmentHome).show(fragmentHome).commit()
        fm.beginTransaction().add(R.id.container, fragmentTransaksi).hide(fragmentTransaksi).commit()
        fm.beginTransaction().add(R.id.container, fragmentInbox).hide(fragmentInbox).commit()
        fm.beginTransaction().add(R.id.container, fragmentAkun).hide(fragmentAkun).commit()

        bottomNavigationView = findViewById(R.id.nav_view)
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navigation_home->{
                    callFragment(0, fragmentHome)
                }
                R.id.navigation_transaksi->{
                    callFragment(1, fragmentTransaksi)
                }
                R.id.navigation_inbox->{
                    callFragment(3, fragmentInbox)
                }
                R.id.navigation_akun->{
                    if (s.getStatusLogin()) {
                        callFragment(4, fragmentAkun)
                    }else{
                        startActivity(Intent(this, MasukActivity::class.java))
                    }

                }
            }

            false
        }
    }

    fun callFragment(int:Int, fragment: Fragment){
        menuItem = menu.getItem(int)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}