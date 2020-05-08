package com.minamid.accessiblememorygame

import android.arch.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.minamid.accessiblememorygame.ui.game.GameViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameViewModelIsWinnerUnitTestUnitTest {

    lateinit var mViewModel: GameViewModel

    @Before
    fun setup() {
        mViewModel = GameViewModel()
    }

    @Test
    fun onCheckWinnerLiveDateShouldBeTrue() {
        mViewModel.getIsWinnerLiveData().value = true
        mViewModel.getIsWinnerLiveData().observeForever(Observer {
            assertEquals(true, it)
        })
    }

    @Test
    fun onCheckWinnerLiveDateShouldBeFalse() {
        mViewModel.getIsWinnerLiveData().value = false
        mViewModel.getIsWinnerLiveData().observeForever(Observer {
            assertEquals(false, it)
        })
    }

}