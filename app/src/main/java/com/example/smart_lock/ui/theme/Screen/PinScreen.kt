package com.example.smart_lock.ui.theme.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import android.widget.Toast
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smart_lock.R
import com.example.smart_lock.ui.theme.viewModel.AuthViewModel


@Composable
fun PinScreen(navController: NavController){
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        ){
        Button(
            onClick = {
                      navController.navigate("setPinScreen")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.blue50)
            ),
            modifier = Modifier
                .width(221.dp)
                .height(49.dp)
                .background(
                    color = colorResource(R.color.blue50),
                    shape = RoundedCornerShape(size = 10.dp)
                )
        ) {
            Text(
                text = stringResource(id = R.string.set_pin_code),
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                navController.navigate("enterPinScreen")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.blue50)
            ),
            modifier = Modifier
                .width(221.dp)
                .height(49.dp)
                .background(
                    color = colorResource(R.color.blue50),
                    shape = RoundedCornerShape(size = 10.dp)
                )
        ) {
            Text(
                text = stringResource(id = R.string.enter_pin_code),
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun SetPinCodeScreen(
    authViewModel: AuthViewModel,
    navController: NavController

){

    var pin by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {
        Text(stringResource(id = R.string.set_pin_code),
            style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(36.dp))

        TextField(value = pin,
            onValueChange = {newPin ->
                pin = newPin
                authViewModel.pin = newPin

            },
            label = { Text(text = "Set pin code ") },
            placeholder = { Text(text = "Enter pin code to set") })

        Spacer(modifier = Modifier.height(36.dp))
        Button(
            onClick = {
                if(pin.length == 4){
                    authViewModel.sendPinToFirebase()
                    Toast.makeText(context,"Set pin is success",Toast.LENGTH_SHORT).show()
                   navController.popBackStack()
                }else{
                   Toast.makeText(context,"Set pin is failed",Toast.LENGTH_SHORT).show()
                }

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.blue50)
            ),
            modifier = Modifier
                .width(221.dp)
                .height(49.dp)
                .background(
                    color = colorResource(R.color.blue50),
                    shape = RoundedCornerShape(size = 10.dp)
                )
        ) {
            Text(
                text = stringResource(id = R.string.ok),
                style = MaterialTheme.typography.titleSmall,
                color = Color.White
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun EnterPinCodeScreen(
    authViewModel: AuthViewModel,
    navController: NavController
){
    var pin by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current


    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.enter_pin_code),
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(36.dp))
        TextField(value = pin,
            onValueChange = { newPin ->
                pin = newPin
                authViewModel.pin = newPin
            },
            label = { Text(text = "Enter pin code ") },
            placeholder = { Text(text = "Enter pin code to open door") }
        )
        Spacer(modifier = Modifier.height(36.dp))
        Button(
            onClick = {
                authViewModel.checkPinOnFirebase(pin){isPinCorrect ->
                    if(isPinCorrect){
                       // authViewModel.
                        Toast.makeText(context,"Pin is correct",Toast.LENGTH_SHORT).show()
                        authViewModel.sendOpenAndCloseSignalsWithDelay(10000)
                    }else{
                        Toast.makeText(context,"Incorrect PIN, please try again",Toast.LENGTH_SHORT).show()
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.blue50)
            ),
            modifier = Modifier
                .width(221.dp)
                .height(49.dp)
                .background(
                    color = colorResource(R.color.blue50),
                    shape = RoundedCornerShape(size = 10.dp)
                )
        ) {
            Text(
                text = stringResource(id = R.string.verify),
                style = MaterialTheme.typography.titleSmall,
                color = Color.White
            )
        }
    }

}