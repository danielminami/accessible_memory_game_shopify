package com.minamid.accessiblememorygame

import android.arch.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.minamid.accessiblememorygame.ui.GameViewModel
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.minamid.accessiblememorygame.model.MemoryCard

@RunWith(AndroidJUnit4::class)
class GameViewModelTurnAllCardsFacedDownUnitTest {

    lateinit var mViewModel: GameViewModel
    lateinit var instrumentationContext: Context
    lateinit var mListOfMemoryCard: ArrayList<MutableLiveData<MemoryCard>>
    lateinit var memoryCard: MemoryCard
    lateinit var memoryCardLiveData: MutableLiveData<MemoryCard>

    @Before
    fun setup() {
        mViewModel = GameViewModel()
        mListOfMemoryCard = ArrayList()
//        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
        instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        for (x in 0 until 9) {
            memoryCardLiveData = MutableLiveData<MemoryCard>()
            memoryCard = MemoryCard(instrumentationContext)
            memoryCard.isRevealed = true
            memoryCardLiveData.postValue(memoryCard)
            mListOfMemoryCard.add(memoryCardLiveData)
            mViewModel.cardListLiveData.add(memoryCardLiveData)
        }
    }

    @Test
    fun allCardsShouldBeTurnedFaceDown() {

        var isAllCardsFacedDown = true

        val method = mViewModel.javaClass.getDeclaredMethod("turnAllCardsFacedDown", List::class.java)
        method.isAccessible = true
        method.invoke(mViewModel, mListOfMemoryCard)

        for ((index, value) in mListOfMemoryCard.withIndex()) {
            mViewModel.cardListLiveData.get(index).observeForever {
                if (it!!.isRevealed) isAllCardsFacedDown = false
            }
        }
        Assert.assertEquals(true, isAllCardsFacedDown)
    }


}