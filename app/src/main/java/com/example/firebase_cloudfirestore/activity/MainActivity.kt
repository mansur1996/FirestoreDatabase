package com.example.firebase_cloudfirestore.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.firebase_cloudfirestore.adapter.UserAdapter
import com.example.firebase_cloudfirestore.databinding.ActivityMainBinding
import com.example.firebase_cloudfirestore.model.User
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseFirestore : FirebaseFirestore
    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var list : ArrayList<User>
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        //Firestore
        firebaseFirestore = FirebaseFirestore.getInstance()
        list = ArrayList()

        showProgressBar()
        readUsersList()

        binding.bntSave.setOnClickListener {
            saveUser()
        }


    }

    private fun saveUser() {
        showProgressBar()
        val name = binding.etName.text.toString().trim()
        val age = binding.etAge.text.toString().trim().toInt()

        val user = User(name, age)

        firebaseFirestore.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference->
                Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show()
                clearEditTexts()
                list.add(user)
                hideProgressBar()
                userAdapter.notifyItemInserted(list.size)
            }
            .addOnFailureListener { exception->
                Toast.makeText(this, "Error $exception", Toast.LENGTH_SHORT).show()
            }
    }

    private fun readUsersList() {
        firebaseFirestore.collection("users")
            .get()
            .addOnCompleteListener {
                if(it.isSuccessful){
                    val result = it.result

                    result.forEach { queryDocumentSnapshot ->
                        val user = queryDocumentSnapshot.toObject(User::class.java)
                        list.add(user)
                    }

                    refreshAdapter(list)

                }else{
                    Log.w(TAG, "Error getting documents.", it.exception)
                }
            }
    }

    private fun refreshAdapter(list : ArrayList<User>) {
        userAdapter = UserAdapter(list)
        hideProgressBar()
        binding.rvUsers.adapter = userAdapter

    }

    private fun clearEditTexts() {
        binding.etName.setText("")
        binding.etAge.setText("")
    }

    private fun showProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressBar.visibility = View.GONE
    }
}