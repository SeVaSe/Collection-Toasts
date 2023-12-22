package com.example.collectiontoasts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.collectiontoasts.databinding.ActivityCreateToastBinding
import com.example.collectiontoasts.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CreateToastActivity : AppCompatActivity(){
    private lateinit var themeNameEditText: EditText
    private lateinit var toastEditText: EditText
    private lateinit var createButton: Button
    private val toastList = mutableListOf<MyToast>()
    private lateinit var binding: ActivityCreateToastBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_toast)

        themeNameEditText = findViewById(R.id.themeNameEditText)
        toastEditText = findViewById(R.id.toastEditText)
        createButton = findViewById(R.id.createButton)
        binding = ActivityCreateToastBinding.inflate(layoutInflater)


        createButton.setOnClickListener {
            val themeName = themeNameEditText.text.toString()
            val toast = toastEditText.text.toString()

            if (themeName.isNotBlank() && toast.isNotBlank()) {
                // Создаем новый пароль
                val newToast = MyToast(themeName, toast)

                // Добавляем его в список паролей
                toastList.add(newToast)

                // для отправки результата обратно в MainActivity
                finishWithResult(Activity.RESULT_OK, newToast)



            } else {

                    // Обработка ошибки, если поля пустые
                Toast.makeText(this, "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show()



            }
        }

        // В CreateToastActivity
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Для предотвращения возврата в эту активность по кнопке "назад"
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_create_toast -> {
                    // Не переходим, если мы уже находимся в CreateToastActivity
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }








    }


    private fun finishWithResult(resultCode: Int, toast: MyToast ) {
        val resultIntent = Intent()
        resultIntent.putExtra("NEW_TOAST", toast) // Передаем новый тост через Intent
        setResult(resultCode, resultIntent)
        finish()

        Log.d("CreateToastActivity", "New Toast: $toast")
    }

}