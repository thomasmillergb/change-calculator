package thomasmillergb.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class WalletTest {


    private Wallet underTest;


    @BeforeEach
    void setup() {
        underTest = new WalletImpl();
    }

    @Test
    void loadingWalletSingleCoin() throws IOException {

        underTest.load("/Users/thomasmiller/dev/bo/src/test/resources/cointstest1.txt");
        assertEquals(1, underTest.getBalance());

    }

    @Test
    void loadingWalletMultipleCoins() throws IOException {
        underTest.load("/Users/thomasmiller/dev/bo/src/test/resources/cointstest2.txt");
        assertEquals(118, underTest.getBalance());
    }


    @Test
    void spend100CoinsFrom200() throws IOException {
        underTest.load("/Users/thomasmiller/dev/bo/src/test/resources/cointsspendtest.txt");

        assertEquals(200, underTest.getBalance());
        assertEquals(100, underTest.spend(100L));
        assertEquals(100, underTest.getBalance());

        assertEquals(Collections.singletonList(100L), underTest.peek());


    }
    @Test
    void spend60CoinsFrom4LotsOf20() throws IOException {
        underTest.load("/Users/thomasmiller/dev/bo/src/test/resources/cointsspendtest2.txt");

        assertEquals(80, underTest.getBalance());
        assertEquals(20, underTest.spend(60L));
        assertEquals(20, underTest.getBalance());
        //Todo if change is zero do not add to coins
        assertEquals(Collections.singletonList(20L), underTest.peek());


    }

}