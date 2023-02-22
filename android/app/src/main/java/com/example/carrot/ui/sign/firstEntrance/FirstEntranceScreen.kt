package com.example.carrot.ui.sign.firstEntrance

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carrot.AuthenticateViewModel
import com.example.carrot.ui.component.BackIconBtn
import com.example.carrot.ui.theme.Black33
import com.example.carrot.ui.theme.Black80
import com.example.carrot.ui.theme.Carrot
import com.example.carrot.ui.theme.Grey210
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstEntranceScreen(
    firstEntranceScreenViewModel: FirstEntranceScreenViewModel = FirstEntranceScreenViewModel(),
    authenticateViewModel: AuthenticateViewModel,
    navigateToHome: () -> Unit,
    onBack: () -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    BackIconBtn(
                        color = Black80,
                        onBack = onBack
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        content = {
            FSContents(
                firstEntranceScreenViewModel = firstEntranceScreenViewModel,
                authenticate = authenticateViewModel.authenticate
            )
            if (authenticateViewModel.authenticated.value){
                navigateToHome()
            }
        }
    )
}

@Composable
fun FSContents(
    firstEntranceScreenViewModel: FirstEntranceScreenViewModel,
    authenticate: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 60.dp, horizontal = 16.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            GuidTexture()
            Spacer(modifier = Modifier.height(12.dp))
            AuthInfo(firstEntranceScreenViewModel = firstEntranceScreenViewModel)
            Spacer(modifier = Modifier.height(12.dp))
            ContinueBtn(
                firstEntranceScreenViewModel = firstEntranceScreenViewModel,
                authenticate = authenticate
            )
        }
    }
}

@Composable
fun GuidTexture(){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "처음이군요!",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text(
            text = "닉네임을 입력해주세요.",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "닉네임은 다른 사람에게 보이는 당신의 이름입니다.",
            fontSize = 12.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthInfo(
    firstEntranceScreenViewModel: FirstEntranceScreenViewModel
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            value = firstEntranceScreenViewModel.nickname.value,
            onValueChange = {
                firstEntranceScreenViewModel.setNickname(it)
            },
            placeholder = { Text(text = "닉네임을 입력해주세요", color = Grey210) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Black33
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun ContinueBtn(
    firstEntranceScreenViewModel: FirstEntranceScreenViewModel,
    authenticate: () -> Unit
){
    val context = LocalContext.current

    Button(
        onClick = {
            GlobalScope.launch {
                firstEntranceScreenViewModel.changeNicknameRequest(
                    context = context,
                    authenticate = authenticate
                )
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Carrot
        ),
        shape = MaterialTheme.shapes.extraSmall,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        enabled = (firstEntranceScreenViewModel.nickname.value != "")
    ) {
        Text(text = "당근 사용하기", fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}