package com.example.kotlintester

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class AddUser : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var etName: EditText
    lateinit var etAge: EditText
    lateinit var etAddress: EditText
    lateinit var etDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        toolbar = findViewById(R.id.toolBar)
        etName = findViewById(R.id.etName)
        etAge = findViewById(R.id.etAge)
        etAddress = findViewById(R.id.etAddress)
        etDescription = findViewById(R.id.etDescription)

        setupUi()
    }

    private fun setupUi(){
        toolbar.run {
            inflateMenu(R.menu.menu_adduser)
            setNavigationIcon(R.drawable.ic_action_close)
            setNavigationOnClickListener { finish() }
            setOnMenuItemClickListener {item ->
                when (item.itemId) {
                    R.id.commit -> if(validateFields()) {
                        val retrofit = Retrofit.Builder()
                                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .baseUrl("https://backender.herokuapp.com/")
                                        .build()
                        val postApi = retrofit.create(UserService::class.java)

                        val userDetails = UserParameter(etName.text.trim().toString(),
                                                    etAge.text.trim().toString(),
                                                    etAddress.text.trim().toString(),
                                                    etDescription.text.trim().toString())

                        var userResponse = postApi.addUser(userDetails)
                        System.out.print("testingvalues"+userResponse.toString())

                        Toast.makeText(this@AddUser, "Success", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@AddUser, "Failed", Toast.LENGTH_SHORT).show()
                    }
                }
                return@setOnMenuItemClickListener true
            }
        }
    }

    private fun validateFields():Boolean{
        when {
            etName.text.trim().isEmpty() -> {
                etName.error = getString(R.string.error_name)
                return false
            }
            etAge.text.trim().isEmpty() -> {
                etAge.error = getString(R.string.error_age)
                return false
            }
            etAddress.text.trim().isEmpty() -> {
                etAddress.error = getString(R.string.error_address)
                return false
            }
            etDescription.text.trim().isEmpty() -> {
                etDescription.error = getString(R.string.error_description)
                return false
            }
            else ->{
                return true
            }
        }
    }
}
