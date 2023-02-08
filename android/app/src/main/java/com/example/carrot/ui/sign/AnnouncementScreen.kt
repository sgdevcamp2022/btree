package com.example.carrot.ui.sign

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carrot.ui.theme.Carrot
import com.example.carrot.ui.theme.CarrotTheme
import com.example.carrot.ui.theme.Grey160
import com.example.carrot.ui.theme.Grey210

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AnnouncementScreen(
    navigateToLogin:() -> Unit,
    navigateToSignup:() -> Unit
){
    Scaffold(
        content =  {
            AnnouncementContent(
                navigateToLogin = navigateToLogin,
                navigateToSignup = navigateToSignup
            )
        }
    )
}

@Composable
fun AnnouncementContent(
    navigateToLogin:() -> Unit,
    navigateToSignup:() -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "당신 근처의 당근 마켓",
                color = Color.Black,
                fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "중고 거래부터 동네 정보까지,", color = Color.Black)
            Text(text = "지금 내 동네를 선택하고 시작해보세요!", color = Color.Black)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Carrot
                ),
                shape = MaterialTheme.shapes.extraSmall,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "시작하기", fontWeight = FontWeight.Bold)
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "이미 계정이 있나요?", color = Grey160)
                TextButton(onClick = {
                    navigateToLogin()
                }) {
                    Text(text = "로그인", color = Carrot)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

    }
}

@Preview("login btn test")
@Composable
fun loginBtnPreview(){
    CarrotTheme {
        Surface {
            AnnouncementScreen({}, {})
        }
    }
}