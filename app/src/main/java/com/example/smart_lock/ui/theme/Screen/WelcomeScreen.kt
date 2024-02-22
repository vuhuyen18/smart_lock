package com.example.smart_lock.ui.theme.Screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import android.widget.Toast

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smart_lock.R
import com.example.smart_lock.ui.theme.viewModel.AuthViewModel
import androidx.navigation.compose.rememberNavController
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginWelcome(
    authViewModel: AuthViewModel,
    navController: NavController,
    onSignUpClick: () -> Unit,
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(top = 40.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = stringResource(id = R.string.welcome_to_smart_lock),
            style = TextStyle(
                fontSize = 48.sp,
                fontFamily = FontFamily(Font(R.font.inter_extrabold)),
                fontWeight = FontWeight(400),
                color = Color.Black
            ),
            modifier = Modifier
                .width(304.dp)
                .height(132.dp)

        )

        Image(
            painter = painterResource(id = R.drawable.material_symbols_lock_outline),
            contentDescription = "image description",
            contentScale = ContentScale.None,
            modifier = Modifier
                .padding(1.dp)
                .width(198.dp)
                .height(174.dp)
        )
        Modifier
            .width(271.dp)
            .height(1.dp)
            .background(Color.Black)

        //Creat a box to field ID to login

        TextField(value = authViewModel.email,
            onValueChange = { authViewModel.email = it },
            label = { Text(text = "Email") },
            placeholder = { Text(text = "Enter your Email") })

        //Creat a box to field Pass to login
//        var passWord by remember {
//            mutableStateOf(TextFieldValue(""))
//        }
        TextField(value = authViewModel.passWord,
            onValueChange = { authViewModel.passWord = it },
            label = { Text(text = "Password") },
            placeholder = { Text(text = "Enter your password") })

        //
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.you_dont_have_account),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.inter_extrabold)),
                    color = Color.Black
                )
            )
            Text(
                text = stringResource(id = R.string.sign_up),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.inter_extrabold)),
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.blue50)
                ),
                modifier = Modifier.clickable { onSignUpClick.invoke() }
            )

        }

        Button(
            onClick = {
                authViewModel.signIn(navController) { isSuccess ->
                    if (isSuccess) {
                        navController.navigate("Home_screen")
                    } else {
                Toast.makeText(context,"Sign in is failed",Toast.LENGTH_SHORT).show()
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
                    text = stringResource(id = R.string.sign_in),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }
            }


    }

