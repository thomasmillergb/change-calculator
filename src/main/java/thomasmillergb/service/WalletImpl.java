package thomasmillergb.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class WalletImpl implements Wallet {


    private List<Long> coins;

    public WalletImpl() {
        this.coins = new LinkedList<>();
    }

    @Override
    public void load(final String filename) throws IOException {

        File file = new File(filename);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null)
            coins.add(Long.valueOf(st));
        coins.sort(Long::compare);
    }


    @Override
    public Long getBalance() {
        return coins.stream().mapToLong(value -> value).sum();
    }

    @Override
    public Collection<Long> peek() {
        return coins;
    }

    @Override
    public Long spend(final Long amount) {

        if (amount > getBalance()) {
            throw new RuntimeException("Not enough balance");
        }
        long subtotal = 0L;

        List<Long> newWallet = new LinkedList<>();

        boolean subtotaldone = false;
        for (Long coin : coins) {
            subtotal += coin;
            if (subtotal >= amount) {
                if (!subtotaldone) {
                    newWallet.add(subtotal - amount);
                    subtotaldone = true;
                } else {
                    newWallet.add(coin);
                }
            }
        }
        coins = newWallet;
        return getBalance();

    }
}
