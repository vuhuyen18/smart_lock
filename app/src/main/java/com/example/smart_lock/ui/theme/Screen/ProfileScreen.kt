package com.example.smart_lock.ui.theme.Screen

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smart_lock.R


@Preview(showSystemUi =true)
@Composable
fun Profile(){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp),
        modifier = Modifier.padding(top = 100.dp)) {
        Image(
            painter = painterResource(id = R.drawable.vector),
            contentDescription = null
        )
        Text(text = "ID:874598237")

        Row (verticalAlignment = Alignment.CenterVertically){
            Icon(
                painter = painterResource(id = R.drawable.baseline_settings_24),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Setting")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_email_24),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Support")
        }

        Button(
            onClick = { /*TODO*/ },
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
                text = stringResource(id = R.string.log_out),
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    }
}