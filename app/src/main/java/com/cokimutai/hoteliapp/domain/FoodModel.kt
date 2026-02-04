package com.cokimutai.hoteliapp.domain

import java.io.Serializable

data class FoodModel(
    var BestFood: Boolean = false,
    var Calorie: Int = 0,
    var CategoryId: String = "",
    var Description: String = "",
    var Id: Int = 0,
    var ImagePath: String = "",
    var LocationId: Int = 0,
    var Price: Double = 0.0,
    var PriceId: Int = 0,
    var Star: Double = 0.0,
    var TimeId: Int = 0,
    var TimeValue: Int = 0,
    var Title: String = "",
    var numberInCart: Int =0
) : Serializable