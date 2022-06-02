package com.affan.citrolymark2.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.affan.citrolymark2.MainActivity
import com.affan.citrolymark2.R
import com.affan.citrolymark2.adapter.AdapterProduk
import com.affan.citrolymark2.adapter.AdapterSlider
import com.affan.citrolymark2.app.ApiConfig
import com.affan.citrolymark2.model.Produk
import com.affan.citrolymark2.model.ResponModel
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    lateinit var vpSlider:ViewPager
    lateinit var rvProduk: RecyclerView
    lateinit var rvProdukTerlaris: RecyclerView
    lateinit var rvSpesialHariIni: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home,container,false)
        init(view)
        getProduk()


        return view
    }

    fun displayProduk(){
        val arrSlider = ArrayList<Int>()
        arrSlider.add(R.drawable.slide1)
        arrSlider.add(R.drawable.slide2)
        arrSlider.add(R.drawable.slide3)

        val adapterSlider = AdapterSlider(arrSlider,activity)
        vpSlider.adapter = adapterSlider

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager2 = LinearLayoutManager(activity)
        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager3 = LinearLayoutManager(activity)
        layoutManager3.orientation = LinearLayoutManager.HORIZONTAL

        rvProduk.adapter = AdapterProduk(listProduk)
        rvProduk.layoutManager = layoutManager

        rvProdukTerlaris.adapter = AdapterProduk(listProduk)
        rvProdukTerlaris.layoutManager = layoutManager2

        rvSpesialHariIni.adapter = AdapterProduk(listProduk)
        rvSpesialHariIni.layoutManager = layoutManager3
    }

    private var listProduk:ArrayList<Produk> = ArrayList()
    fun getProduk(){
        ApiConfig.instanceRetrofit.getProduk().enqueue(object : Callback<ResponModel> {
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val res = response.body()!!
                if(res.success == 1){
                    listProduk = res.produks
                    displayProduk()
                }
            }
        })
    }

    fun init(view: View){
        vpSlider = view.findViewById(R.id.vp_slider)
        rvProduk = view.findViewById(R.id.rv_produk)
        rvProdukTerlaris = view.findViewById(R.id.rv_produkTerlasir)
        rvSpesialHariIni = view.findViewById(R.id.rv_spesialHariIni)
    }

//    val arrProduk: ArrayList<Produk>get(){
//        val arr = ArrayList<Produk>()
//        val p1 = Produk()
//        p1.nama = "Wortel"
//        p1.harga = "Rp 9.000"
//        p1.gambar = R.drawable.sayur_wortel
//
//        val p2 = Produk()
//        p2.nama = "Sayur Bayam"
//        p2.harga = "Rp 5.000"
//        p2.gambar = R.drawable.sayur_bayam
//
//        val p3 = Produk()
//        p3.nama = "Kacang Panjang"
//        p3.harga = "Rp 4.800"
//        p3.gambar = R.drawable.sayur_kacangpanjang
//
//        val p4 = Produk()
//        p4.nama = "Sayur Kol"
//        p4.harga = "Rp 7.200"
//        p4.gambar = R.drawable.sayur_kol
//
//        val p5 = Produk()
//        p5.nama = "Sawi Putih"
//        p5.harga = "Rp 6.000"
//        p5.gambar = R.drawable.sayur_sawi
//
//        val p6 = Produk()
//        p6.nama = "Selada Air"
//        p6.harga = "Rp 6.500"
//        p6.gambar = R.drawable.sayur_seladaair
//
//
//        arr.add(p1)
//        arr.add(p2)
//        arr.add(p3)
//        arr.add(p4)
//        arr.add(p5)
//        arr.add(p6)
//
//        return arr
//    }
//
//    val arrProdukTerlaris: ArrayList<Produk>get(){
//        val arr = ArrayList<Produk>()
//        val p1 = Produk()
//        p1.nama = "Daging Sapi Beef Slice"
//        p1.harga = "Rp 9.000"
//        p1.gambar = R.drawable.daging_sapi_beef_slice
//
//        val p2 = Produk()
//        p2.nama = "Ceker Ayam Kota Premium"
//        p2.harga = "Rp 5.000"
//        p2.gambar = R.drawable.ceker_ayam_kota
//
//        val p3 = Produk()
//        p3.nama = "Ayam Kota Utuh"
//        p3.harga = "Rp 40.800"
//        p3.gambar = R.drawable.ayam_kota_utuh
//
//        val p4 = Produk()
//        p4.nama = "Bawang Putih"
//        p4.harga = "Rp 7.200"
//        p4.gambar = R.drawable.bawang_putih
//
//        val p5 = Produk()
//        p5.nama = "Merica Butir"
//        p5.harga = "Rp 6.000"
//        p5.gambar = R.drawable.merica
//
//        val p6 = Produk()
//        p6.nama = "Paha Bawah Drum Stick Ayam KOta Premium"
//        p6.harga = "Rp 6.500"
//        p6.gambar = R.drawable.ayam_kota_pahabawah
//
//
//        arr.add(p1)
//        arr.add(p2)
//        arr.add(p3)
//        arr.add(p4)
//        arr.add(p5)
//        arr.add(p6)
//
//        return arr
//    }
//
//    val arrSpesialHariIni: ArrayList<Produk>get(){
//        val arr = ArrayList<Produk>()
//        val p1 = Produk()
//        p1.nama = "Bawang Merah"
//        p1.harga = "Rp 9.000"
//        p1.gambar = R.drawable.bawang_merah
//
//        val p2 = Produk()
//        p2.nama = "Sayur Seledri"
//        p2.harga = "Rp 5.000"
//        p2.gambar = R.drawable.sayur_seledri
//
//        val p3 = Produk()
//        p3.nama = "Susu Bayi"
//        p3.harga = "Rp 44.800"
//        p3.gambar = R.drawable.susu_bayi
//
//        val p4 = Produk()
//        p4.nama = "Sabun Lifeboy"
//        p4.harga = "Rp 7.200"
//        p4.gambar = R.drawable.sabun_lifeboy
//
//        val p5 = Produk()
//        p5.nama = "Rokok Marlboro Merah"
//        p5.harga = "Rp 26.000"
//        p5.gambar = R.drawable.rokok_marlboro_merah
//
//        val p6 = Produk()
//        p6.nama = "Sampo Clear Men"
//        p6.harga = "Rp 26.500"
//        p6.gambar = R.drawable.sampo
//
//
//        arr.add(p1)
//        arr.add(p2)
//        arr.add(p3)
//        arr.add(p4)
//        arr.add(p5)
//        arr.add(p6)
//
//        return arr
//    }
}
