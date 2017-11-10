package io.github.hanjoongcho.com.squareup.retrofit2

import org.junit.Assert.assertEquals
import org.junit.Test

class SimpleServiceTest {

    @Test fun getFirstContributorLoginValueTest() {
        assertEquals("hanjoongcho", getFirstContributorLoginValue("hanjoongcho", "aaf-easyphotomap"))
    }

    @Test fun getLanguageTest() {
        assertEquals("Kotlin", getLanguage("hanjoongcho", "aaf-easyphotomap"))
    }

    @Test fun getUserInfoTest() {
        assertEquals(User("hanjoongcho", "User", "Bulbasaur", "How to survive among piranhas").toString(), getUserInfo("hanjoongcho"))
    }

}
