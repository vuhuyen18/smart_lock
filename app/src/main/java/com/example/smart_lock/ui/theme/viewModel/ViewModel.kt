package com.example.smart_lock.ui.theme.viewModel

import android.content.ComponentCallbacks
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import javax.inject.Inject

//class LoginWelcomeViewModel: ViewModel(){
//    var email by mutableStateOf("")
//    var passWord by mutableStateOf("")
//
//}
//
//class SignUpViewModel: ViewModel(){
//    var newEmail by mutableStateOf("")
//    var newPass1 by mutableStateOf("")
//}

class AuthViewModel @Inject constructor() : ViewModel(){


     var email by mutableStateOf("")
    var passWord by mutableStateOf("")

    private val auth = FirebaseAuth.getInstance()

     var currentUser by mutableStateOf<FirebaseUser?>(auth.currentUser)

    fun signIn(navController: NavController,
               callbacks: (Boolean)-> Unit) {
        auth.signInWithEmailAndPassword(email,passWord)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    currentUser = auth.currentUser
                    currentUser?.getIdToken(true)?.addOnCompleteListener { tokenTask ->
                    if(tokenTask.isSuccessful){
                        val idToken = tokenTask.result?.token
                        Log.d("SignIn","Sign in success. Token: $idToken")
                        callbacks(true)
                    }else{
                        callbacks(false)
                        Log.e("SignIn, Lay token that bai", task.exception.toString())
                    }}
                    callbacks(true)
                }else{
                    callbacks(false)
                }
            }
    }

    fun signUp(onSignUpComplete:(Boolean)->Unit) {
        auth.createUserWithEmailAndPassword(email,passWord)
            .addOnCompleteListener { task->
                if(task.isSuccessful){
                    currentUser = auth.currentUser
                    onSignUpComplete(true)

                }else{
                    onSignUpComplete(false)
                }
            }

    }

    fun logOut(){
        auth.signOut()
        currentUser = null
    }

    fun mToast(context: Context){
        Toast.makeText(context,"Sign up is success",Toast.LENGTH_LONG).show()
    }

    //Set pin code
    var pin by mutableStateOf("")
    private var isPinSet by mutableStateOf(false)

    fun sendPinToFirebase(){
            currentUser?.uid?.let { userID ->
                Log.d("FirebaseDebug", "UserID: $userID,Pin: $pin")
                val database = FirebaseDatabase.getInstance("")
                val myRef = database.getReference("users").child(userID)
                myRef.child("pinCode").setValue(pin).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("NewTag", "Data sent successfully")
                        isPinSet = true
                    }else{
                        Log.e("NewTag", "Error sending data: ${task.exception}")
                        task.exception?.printStackTrace()
                    }
                }
            }
    }

    fun checkPinOnFirebase(pinToCheck:String, onPinCheckResult:(Boolean) -> Unit){
        currentUser?.uid?.let { userID ->
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("users").child(userID).child("pinCode")

            myRef.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val storedPin = dataSnapshot.getValue(String::class.java)
                    val isPinCorrect = storedPin == pinToCheck
                    onPinCheckResult(isPinCorrect)

                    //send signal to opendoor if code pin is true
                    if (isPinCorrect){
                        sendDoorSignalToFirebase("open")


                    }else{
                        sendDoorSignalToFirebase("close")
                    }
                }

                //Solve error
                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e("FirebaseDebug","Error checking pin: $databaseError")
                    onPinCheckResult(false)
                }
            })
        }
    }


    private val handler = Handler(Looper.getMainLooper())
    private fun sendDoorSignalToFirebaseWithDelay(signal: String, delayMillis: Long) {
        currentUser?.uid?.let { userID ->
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("doorControl").child(userID).child("signal")

            myRef.setValue(signal).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("FirebaseDebug", "Door signal sent successfully")

                    // Gửi tín hiệu "close" sau khoảng thời gian delay
                    handler.postDelayed({
                        sendDoorSignalToFirebase("close")
                    }, delayMillis)
                } else {
                    Log.e("FirebaseDebug", "Error sending door signal:${task.exception}")
                }
            }
        }
    }


    fun sendOpenAndCloseSignalsWithDelay(delayMillis: Long) {
        sendDoorSignalToFirebaseWithDelay("open", 1000)
    }
    private  fun sendDoorSignalToFirebase(signal: String){
        currentUser?.uid?.let { userID ->
            val database = FirebaseDatabase.getInstance("")
            val myRef = database.getReference("doorControl").child(userID).child("signal")
            myRef.setValue(signal).addOnCompleteListener { task->
                if (task.isSuccessful){
                    Log.d("FirebaseDebug","Door signal sent successfully")
                }else {
                    Log.e("FirebaseDebug","Error sending door signal:${task.exception}")
                }
            }
        }
    }
}

