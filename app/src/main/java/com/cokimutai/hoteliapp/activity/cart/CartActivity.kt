package com.cokimutai.hoteliapp.activity.cart

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.cokimutai.hoteliapp.R
import com.cokimutai.hoteliapp.activity.BaseActivity
import com.cokimutai.hoteliapp.domain.FoodModel
import com.cokimutai.hoteliapp.helper.ManagmentCart

class CartActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CartScreen(ManagmentCart(this), onBackClick = { finish() }) }
    }
}

@Composable
fun CartScreen(
    managementCart: ManagmentCart = ManagmentCart(LocalContext.current),
    onBackClick: () -> Unit
) {

    val cartItems =
        remember { mutableStateListOf<FoodModel>().apply { addAll(managementCart.getListCart()) } }
    val tax = remember { mutableStateOf(0.0) }

    calculateorCart(managementCart, tax)

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
    {
        item {
            ConstraintLayout(modifier = Modifier.padding(top = 36.dp)) {
                val (backBtn, carTxt) = createRefs()
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(carTxt) { centerTo(parent) },
                    text = "Your cart",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                Image(painter = painterResource(R.drawable.back_grey),
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(backBtn) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                        }
                        .clickable { onBackClick() }
                )

            }
        }
        if (cartItems.isEmpty()) {
            item {
                Text(
                    text = "Cart Is Empty",
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        } else {
            items(cartItems, key = { it.Title }) { item ->
                CartItem(
                    cartItems = cartItems,
                    item = item,
                    managmentCart = managementCart,
                    onItemChange = {
                        calculateorCart(
                            managementCart,
                            tax
                        )
                        cartItems.clear()
                        cartItems.addAll(managementCart.getListCart())
                    })

            }
            item {
                Text(
                    text = "Order Summary",
                    color = colorResource(R.color.darkPurple),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
            item {
                CartSummary(
                    itemTotal = managementCart.getTotalFee(),
                    tax = tax.value,
                    delivery = 10.0
                )
            }

            item {
                Text(
                    text = "Information",
                    color = colorResource(R.color.darkPurple),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )

            }
            item {
                DeliveryInfoBox()
            }

        }
    }

}


fun calculateorCart(managmentCart: ManagmentCart, tax: MutableState<Double>) {
    val percentTax = 0.02
    tax.value = Math.round((managmentCart.getTotalFee() * percentTax) * 100) / 100.0
}
