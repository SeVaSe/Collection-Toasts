package com.example.collectiontoasts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collectiontoasts.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toastAdapter: ToastAdapter
    private val toastList = mutableListOf<MyToast>()

    private val CREATE_TOAST_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_home -> {
                    // Не переходим, если мы уже находимся в MainActivity
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_create_toast -> {
                    startCreateToastActivity()
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }







        toastAdapter = ToastAdapter(toastList) { toast ->
            val builder = AlertDialog.Builder(this)
            builder.apply {
                setTitle(toast.toastName)
                setMessage("Описание: ${toast.toastDesc}")
                setPositiveButton("Удалить") { _, _ ->
                    toastList.remove(toast)
                    toastAdapter.notifyDataSetChanged()
                }
                setNegativeButton("Отмена") { dialog, _ ->
                    dialog.dismiss()
                }
            }
            val dialog = builder.create()
            dialog.show()
        }

        binding.toastRecyclerView.adapter = toastAdapter
        binding.toastRecyclerView.layoutManager = LinearLayoutManager(this)

        loadToastData()
    }

    private fun loadToastData() {
        toastList.clear()
        val toast1 = MyToast("За дружбу!", "Пусть наша дружба будет такой же яркой и теплой, как это веселое застолье!")
        val toast2 = MyToast("За любовь!", "Пусть любовь будет заполнена смехом, счастьем и нежностью каждый день!")
        val toast3 = MyToast("За успех!", "Пусть ваш путь к успеху будет наполнен трудом и радостью побед!")
        val toast4 = MyToast("За здоровье!", "Пусть каждый новый день приносит здоровье и бодрость!")
        val toast5 = MyToast("За новые свершения!", "Пусть каждый шаг к новым свершениям будет полон энтузиазма и уверенности!")
        val toast6 = MyToast("За счастье!", "Пусть счастье будет вашим верным спутником, освещая ваш путь вперед!")
        val toast7 = MyToast("За приключения!", "Пусть ваши приключения будут захватывающими и незабываемыми!")
        val toast8 = MyToast("За мечты!", "Пусть ваши мечты всегда вдохновляют вас на новые свершения!")
        val toast9 = MyToast("За оптимизм!", "Пусть ваш оптимизм разгоняет тучи и наполняет жизнь яркими красками!")
        val toast10 = MyToast("За настоящий момент!", "Пусть каждый момент вашей жизни будет наполнен радостью и смехом!")
        val toast11 = MyToast("За улыбки!", "Пусть ваши улыбки радуют окружающих и согревают сердца!")
        val toast12 = MyToast("За творчество!", "Пусть ваше творчество вдохновляет и приносит радость!")
        val toast13 = MyToast("За веру!", "Пусть вера в ваши силы помогает вам преодолевать любые препятствия!")
        val toast14 = MyToast("За удачу!", "Пусть удача сопутствует вам на каждом шагу!")

        toastList.addAll(listOf(toast1, toast2, toast3, toast4, toast5, toast6, toast7, toast8, toast9, toast10, toast11, toast12, toast13, toast14))
        toastAdapter.notifyDataSetChanged()

    }

    private fun startCreateToastActivity() {
        val createToastIntent = Intent(this, CreateToastActivity::class.java)
        startActivityForResult(createToastIntent, CREATE_TOAST_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_TOAST_REQUEST && resultCode == Activity.RESULT_OK) {
            val newToast: MyToast? = data?.getParcelableExtra("NEW_TOAST")

            newToast?.let {
                toastList.add(it)
                toastAdapter.notifyDataSetChanged()
            }
        }
    }


}















