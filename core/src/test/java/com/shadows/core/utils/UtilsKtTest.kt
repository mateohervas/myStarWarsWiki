package com.shadows.core.utils
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UtilsKtTest {

    @Test
    fun `given non null string transform to inches `() {
        val centimeters = "100"
        val inches = centimeters.toInches()
        assert(inches == "39.37")
    }
    @Test
    fun `given  null string return 0 `() {
        val centimeters = null
        val inches = centimeters.toInches()
        assert(inches == "0")
    }
}