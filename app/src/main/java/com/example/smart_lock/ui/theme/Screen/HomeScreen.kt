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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smart_lock.R


@Composable
fun HomeSmartLock(navController: NavController,
                  onProfileClick:()-> Unit){

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp),
        modifier = Modifier.padding(top = 100.dp).fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.material_symbols_light_door_sensor_outline),
            contentDescription = null,
            contentScale = ContentScale.None,
            modifier = Modifier
                .padding(1.dp)
                .width(210.dp)
                .height(232.dp))

        Text(text = stringResource(id = R.string.open_door),
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black)
        Button(onClick = {
                navController.navigate("pinScreen")
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.white50)
            ),
            modifier = Modifier.width(267.dp)
                .height(98.dp)
                .background(colorResource(id = R.color.white50),
                shape = RoundedCornerShape(size = 50.dp))

        ) {
            Image(painter = painterResource(id = R.drawable.ic_material_symbols_light_lock_open_outline),
                contentDescription =null )
        }
        Row(modifier = Modifier
            .width(309.dp)
            .height(1.dp)
            .background(color = Color.Black)
            .padding(start = 20.dp)){}

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(100.dp),
            modifier = Modifier.padding(top = 120.dp)
            ) {
            Image(painter = painterResource(id = R.drawable.octicon_home_16),
                contentDescription = null)
            Image(painter = painterResource(id = R.drawable.ic_twotone_history) ,
                contentDescription = null )
            Image(painter = painterResource(id = R.drawable.vector_ic),
                contentDescription = null ,
                modifier = Modifier.clickable { onProfileClick.invoke() } )
        }
    }




}