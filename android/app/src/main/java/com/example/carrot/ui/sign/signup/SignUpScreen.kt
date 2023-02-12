package com.example.carrot.ui.sign.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carrot.ui.component.BackIconBtn
import com.example.carrot.ui.theme.Black33
import com.example.carrot.ui.theme.Black80
import com.example.carrot.ui.theme.Carrot
import com.example.carrot.ui.theme.Grey210
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen(
    signUpScreenViewModel: SignUpScreenViewModel = SignUpScreenViewModel(),
    navigateToLoginScreen: () -> Unit,
    onBack: () -> Unit
) {
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
            SignUpContents(signUpScreenViewModel)
            if (signUpScreenViewModel.dialogToggle.value){
                SignUpDialog(
                    navigateToLoginScreen = navigateToLoginScreen,
                    dialogToggle = signUpScreenViewModel.setDialogToggle,
                    title = signUpScreenViewModel.dialogTitle.value,
                    content = signUpScreenViewModel.dialogContent.value
                )
            }
        }
    )
}

@Composable
fun SignUpContents(
    signUpScreenViewModel: SignUpScreenViewModel
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
            AuthInfo(signUpScreenViewModel = signUpScreenViewModel)
            Spacer(modifier = Modifier.height(12.dp))
            SignUpBtn(signUpScreenViewModel = signUpScreenViewModel)
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
            text = "안녕하세요!",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text(
            text = "이메일로 가입해주세요.",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "이메일은 안전하게 보관되어 이웃들에게 공개되지 않아요.",
            fontSize = 12.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthInfo(
    signUpScreenViewModel: SignUpScreenViewModel
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            value = signUpScreenViewModel.email.value,
            onValueChange = {
                signUpScreenViewModel.setEmail(it)
            },
            placeholder = { Text(text = "이메일을 입력해주세요", color = Grey210) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Black33
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            value = signUpScreenViewModel.password.value,
            onValueChange = {
                signUpScreenViewModel.setPassword(it)
            },
            placeholder = { Text(text = "비밀번호를 입력해주세요", color = Grey210) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Black33
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation()
        )
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun SignUpBtn(
    signUpScreenViewModel: SignUpScreenViewModel
){
    Button(
        onClick = {
            GlobalScope.launch {
                signUpScreenViewModel.signupReguest()
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Carrot
        ),
        shape = MaterialTheme.shapes.extraSmall,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        enabled = (signUpScreenViewModel.email.value != "" && signUpScreenViewModel.password.value != "")
    ) {
        Text(text = "회원가입", fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpDialog(
    navigateToLoginScreen: () -> Unit,
    dialogToggle: () -> Unit,
    title: String,
    content: String
){
    AlertDialog(
        onDismissRequest = { dialogToggle() },
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize(),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "회원가입 $title",
                    color = Black33,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = content,
                    color = Black33,
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    if (title == "성공"){
                        TextButton(
                            onClick = { dialogToggle() }
                        ) {
                            Text(text = "취소", color = Carrot)
                        }
                        TextButton(
                            onClick = { navigateToLoginScreen() }
                        ) {
                            Text(text = "확인", color = Carrot)
                        }
                    } else {
                        TextButton(
                            onClick = { dialogToggle() }
                        ) {
                            Text(text = "확인", color = Carrot)
                        }
                    }
                }
            }
        }
    }
}