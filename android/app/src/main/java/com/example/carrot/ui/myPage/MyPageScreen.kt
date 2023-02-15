package com.example.carrot.ui.myPage

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carrot.MyPage
import com.example.carrot.model.SampleData
import com.example.carrot.ui.component.CategoryIconBtn
import com.example.carrot.ui.component.NotificationIconBtn
import com.example.carrot.ui.component.SearchIconBtn
import com.example.carrot.ui.component.SettingIconBtn
import com.example.carrot.ui.theme.Grey160
import com.example.carrot.ui.theme.Grey210
import com.example.carrot.ui.theme.Grey230
import com.example.carrot.ui.theme.Grey240

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MypageTopAppBar(){
    TopAppBar(
        title = {},
        actions = {
            SettingIconBtn()
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageScreen(
    myPageScreenViewModel: MyPageScreenViewModel = MyPageScreenViewModel()
) {
    Scaffold(
        topBar = { MypageTopAppBar() },
        content = {
            Column(
                modifier = Modifier
                    .padding(top = 60.dp, bottom = 70.dp)
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                MyPageCategoryList(
                    myPageScreenViewModel = myPageScreenViewModel
                )
            }
        }
    )
}

@Composable
fun MyPageCategoryList(
    myPageScreenViewModel: MyPageScreenViewModel
){
    val context = LocalContext.current
    LaunchedEffect(Unit){
        myPageScreenViewModel.setNickname(context)
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
    ) {
        MyProfile(myPageScreenViewModel.nickname.value)
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Grey230
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "나의 거래", fontWeight = FontWeight.Bold)
        MyPageCategory(icon = Icons.Outlined.FavoriteBorder, text = "관심목록")
        MyPageCategory(icon = Icons.Outlined.Receipt, text = "판매내역")
        MyPageCategory(icon = Icons.Outlined.ShoppingBag, text = "구매내역")
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Grey230
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "기타", fontWeight = FontWeight.Bold)
        MyPageCategory(icon = Icons.Outlined.Place, text = "내 동네 설정")
        MyPageCategory(icon = Icons.Outlined.LocationSearching, text = "동네 인증하기")
        MyPageCategory(icon = Icons.Outlined.Sell, text = "알림 키워드 설정")
    }
}

@Composable
fun MyProfile(
    myNickname: String
){
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .size(width = 30.dp, height = 30.dp),
                painter = painterResource(id = SampleData.sampleUser[0].profileImage),
                contentDescription = "my profile image"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = myNickname,
                fontWeight = FontWeight.Black
            )
        }
        Button(
            shape = MaterialTheme.shapes.extraSmall,
            colors = ButtonDefaults.buttonColors(
                containerColor = Grey240,
                contentColor = Color.Black
            ),
            contentPadding = PaddingValues(1.dp),
            modifier = Modifier.size(70.dp, 30.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "프로필 보기", fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun MyPageCategory(
    icon: ImageVector,
    text: String
){
    Row(
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text
        )
    }
}