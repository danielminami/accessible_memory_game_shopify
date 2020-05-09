package com.minamid.accessiblememorygame

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.minamid.accessiblememorygame.model.Image
import com.minamid.accessiblememorygame.model.Product
import com.minamid.accessiblememorygame.ui.game.GameViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameViewModelDuplicateAndShuflleCardsUnitTest {

    lateinit var mViewModel: GameViewModel
    lateinit var instrumentationContext: Context
    lateinit var mockProductList: ArrayList<Product>
    lateinit var imageList: List<Image>
    var numOfMatches: Int = 0
    var matchSize: Int = 0

    @Before
    fun setup() {
        mViewModel = GameViewModel()
        mockProductList = ArrayList()
        instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
    }

    @Test
    fun multiplyArraySizeByMinShouldBeTrue() {
        numOfMatches = 10
        matchSize = 2
        for (x in 0 until numOfMatches) {
            val product = Product()
            product.title = "Test$x"
            product.image = Image()
            mockProductList.add(product)
        }

        val method = mViewModel.javaClass.getDeclaredMethod("duplicateAndShuffleCards", List::class.java, Int::class.java, Int::class.java)
        method.isAccessible = true
        imageList = method.invoke(mViewModel, mockProductList, numOfMatches, matchSize) as List<Image>

        Assert.assertEquals(true, (numOfMatches * matchSize) == imageList.size)
    }

    @Test
    fun multiplyArraySizeByMaxShouldBeTrue() {
        numOfMatches = 14
        matchSize = 4
        for (x in 0 until numOfMatches) {
            val product = Product()
            product.title = "Test$x"
            product.image = Image()
            mockProductList.add(product)
        }

        val method = mViewModel.javaClass.getDeclaredMethod("duplicateAndShuffleCards", List::class.java, Int::class.java, Int::class.java)
        method.isAccessible = true
        imageList = method.invoke(mViewModel, mockProductList, numOfMatches, matchSize) as List<Image>

        Assert.assertEquals(true, (numOfMatches * matchSize) == imageList.size)
    }

}