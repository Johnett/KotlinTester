package com.example.kotlintester

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_main.btAddUser
import timber.log.Timber

class MainActivity : AppCompatActivity() {

  lateinit var toolbar: Toolbar
  lateinit var appBar: AppBarLayout
  lateinit var addUser: MaterialButton
  lateinit var checkAddress: TextView

  @SuppressLint("NewApi")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    toolbar = findViewById(R.id.toolBar)
    appBar = findViewById(R.id.appBar)
    addUser = findViewById(R.id.btAddUser)
    val items = (1..5).toMutableList()

    setupUi()

    btAddUser.setOnClickListener {
      val intent = Intent(this, AddUser::class.java)
      startActivity(intent)
    }

    Timber.plant(Timber.DebugTree())

    val keyword = "\\d+".toRegex()
    val numberList = listOf("abc", "123", "def", "456").filter(keyword::matches)
    Timber.v("number_list_to_find ${Person("James", 25).isAdult} ${Person("Justin",
        20).isACorrectPerson}")

    val simpleList = listOf("Jason", "Justin", "Tibin", "Jacob", "Kristen", "John", "Jerin")
    val frequencies = simpleList.onEach { it.first().toUpperCase() }

    Timber.v("print frequency value $frequencies")

    val list1 = listOf("a", "b")
    val list2 = listOf("1", "2", "3", "4")

    Timber.v("minimum_value ${minOf(list1.size, list2.size)}")
    Timber.v("just_check if the control initialized ${this::checkAddress.isInitialized}")
    items.shuffle()
    println("with_list_all_value_inside_item_collections $items")
    items.replaceAll { it * 2 }
    println("with_list_replace_value_with $items")
    items.fill(5)
    println("with_list_full_items $items")

    deadLock()
    stringTemplate()
    printCollectionValues()
    printCollectionValuesWithWhile()
    usingFiltering()

    var b = 1
    println("test___first_try ${checkingType(b)}")
    var c = "Jacob"
    println("test___second_try ${checkingType(c)}")
    var d = true
    println("test___third_try ${checkingType(d)}")
  }

  private fun setupUi() {
    toolbar.run {
      inflateMenu(R.menu.menu_main)
    }
  }

  private fun findSum(first: Int, second: Int): Int {
    return first + second
  }

  data class Person(val name: String, val age: Int) {
    val isAdult get() = age >= 20

    val isACorrectPerson get() = name == "James"
  }

  private fun deadLock() {
    var x: Int
    synchronized(lock = Any()) {
      x = 20
    }
    x = 40
    println("value_of_x $x ")
  }

  /*
  Test
   */
  //This is a comment block

  private fun stringTemplate() {
    var a = 1
    val s1 = "a is $a"
    a = 4
    var s2 = "${s1.replace("is", "was")} now a is $a"
    println("evolution_of_variable_a_  $s2")
  }

  private fun checkingType(obj: Any): Any {
    return when (obj) {
      is String -> "String"
      is Int -> "Integer"
      else -> {
        "unknown type"
      }
    }
  }

  private fun printCollectionValues() {
      val sampleList = listOf("apple","banana","mango","plum","orange")
      for (index in sampleList.indices){
        println("complete_list_value $index value is ${sampleList[index]}")
      }
  }

  private fun printCollectionValuesWithWhile() {
    val sampleList = listOf("apple","banana","mango","plum","orange")
    var index=0
    while (index < sampleList.size){
      println("complete_list_value_while $index value is ${sampleList[index]}")
      index++
    }
  }

  private fun usingFiltering(){
    val sampleList = listOf("apple","banana","mango","plum","orange","avocado","almond")

    sampleList
        .filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.toUpperCase() }
        .forEach{ println("printing_filtered_values_in_an_organized_fashion $it")}
  }

}
