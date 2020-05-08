package com.minamid.accessiblememorygame

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.minamid.accessiblememorygame.ui.game.GameViewModel
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import android.arch.lifecycle.Observer

@RunWith(AndroidJUnit4::class)
class GameViewModelIsScreenLockedUnitTest {

    lateinit var mViewModel: GameViewModel

    @Before
    fun setup() {
        mViewModel = GameViewModel()
    }

    @Test
    fun onCheckScreenLockLiveDateShouldBeTrue() {
        mViewModel.getIsScreenLock().value = true
        mViewModel.getIsScreenLock().observeForever(Observer {
            Assert.assertEquals(true, it)
        })
    }

    @Test
    fun onCheckScreenLockLiveDateShouldBeFalse() {
        mViewModel.getIsScreenLock().value = false
        mViewModel.getIsScreenLock().observeForever(Observer {
            Assert.assertEquals(false, it)
        })
    }

}