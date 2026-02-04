package com.cokimutai.hoteliapp.activity.dashboard

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cokimutai.hoteliapp.R
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults // For customizing colors
import androidx.compose.material3.Icon
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource


@Composable
@Preview
fun HotBottomBar() {
    val bottomMenuItemsList = prepareBottomMenu()
    val context = LocalContext.current
    var selectedItem by remember { mutableStateOf("Home") }
    NavigationBar(
        containerColor = colorResource(R.color.grey)
        ) {
        bottomMenuItemsList.forEach { bottomMenuItem ->
            NavigationBarItem(
                selected = (selectedItem == bottomMenuItem.Lable),
                onClick =
                    {
                        selectedItem = bottomMenuItem.Lable
                        if (bottomMenuItem.Lable == "Cart") {
                            //context.startActivity(Intent(context, CartActivity::class.java))
                        } else {
                            Toast.makeText(context,bottomMenuItem.Lable,Toast.LENGTH_SHORT).show()
                        }
                    },
                icon = {
                    Icon(
                        painter = bottomMenuItem.icon,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .size(20.dp)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(id = R.color.orange),
                    unselectedIconColor = colorResource(id = R.color.darkBrown)
                )
            )
        }
    }
}

data class BottomMenuItem(
    val Lable: String,
    val icon: Painter
)

@Composable
fun prepareBottomMenu(): List<BottomMenuItem> {
    val bottomMenuItemList = arrayListOf<BottomMenuItem>()
    bottomMenuItemList.add(BottomMenuItem(Lable = "Home", icon = painterResource(R.drawable.btn_1)))
    bottomMenuItemList.add(BottomMenuItem(Lable = "Cart", icon = painterResource(R.drawable.btn_2)))
    bottomMenuItemList.add(
        BottomMenuItem(
            Lable = "Favorite",
            icon = painterResource(R.drawable.btn_3)
        )
    )
    bottomMenuItemList.add(
        BottomMenuItem(
            Lable = "Order",
            icon = painterResource(R.drawable.btn_4)
        )
    )
    bottomMenuItemList.add(
        BottomMenuItem(
            Lable = "Profile",
            icon = painterResource(R.drawable.btn_5)
        )
    )
    return bottomMenuItemList

}

