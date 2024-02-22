package com.example.smart_lock.ui.theme.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smart_lock.R

import com.example.smart_lock.ui.theme.viewModel.AuthViewModel
import com.google.android.play.integrity.internal.m


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun SignUp(authViewModel: AuthViewModel,
           navController: NavController){
    Column (
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.padding(top = 80.dp, start = 44.dp)){
        Text(
            text = stringResource(id = R.string.creat_your_account),
            style = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            ,
            color = Color.Black,
           modifier = Modifier
               .width(211.dp)
               .height(150.dp)
        )

        //creat box to fill new ID and pass to sign up
        Spacer(modifier = Modifier.width(12.dp))

        TextField(value = authViewModel.email,
            onValueChange = { authViewModel.email = it },
            label = {Text(text = "Email")},
            placeholder = {Text(text = "Enter your Email")})


        TextField(value = authViewModel.passWord,
            onValueChange = { authViewModel.passWord = it },
            label = {Text(text = "Password")},
            placeholder = {Text(text = "Enter your Password")})

        //Creat check box to agree
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier) {
            var checked by remember {
                mutableStateOf(false)
            }

            Checkbox(
                checked = checked,
                onCheckedChange = { newCheckedState ->
                    checked = newCheckedState
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = Color.Gray,
                    checkmarkColor = Color.White
                )
            )
            Text(text = stringResource(id = R.string.agree_with_policy))


        }

        Button(
            onClick = {
                     authViewModel.signUp {isSuccess ->
                         if (isSuccess){
                        navController.navigate("Login_welcome")
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
                text = stringResource(id = R.string.sign_up),
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    }

}



