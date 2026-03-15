import java.util.ArrayList;
import java.util.List;

class Fancy {
    private List<Long> seq;
    private long mul;
    private long add;
    private final int MOD = 1_000_000_007;

    public Fancy() {
        seq = new ArrayList<>();
        mul = 1;
        add = 0;
    }
    
    public void append(int val) {
        // We need to insert v0 such that (v0 * mul + add) % MOD = val
        // v0 = (val - add) * modInverse(mul) % MOD
        
        long v0 = val - add;
        // Handle negative modulo results in Java
        v0 = (v0 % MOD + MOD) % MOD; 
        
        v0 = (v0 * modInverse(mul, MOD)) % MOD;
        seq.add(v0);
    }
    
    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }
    
    public void multAll(int m) {
        mul = (mul * m) % MOD;
        add = (add * m) % MOD;
    }
    
    public int getIndex(int idx) {
        if (idx >= seq.size()) {
            return -1;
        }
        
        long v0 = seq.get(idx);
        long result = (v0 * mul + add) % MOD;
        return (int) result;
    }
    
    // Fast exponentiation to calculate (base^exp) % MOD
    private long power(long base, long exp, int mod) {
        long res = 1;
        base = base % mod;
        while (exp > 0) {
            if ((exp % 2) == 1) {
                res = (res * base) % mod;
            }
            exp = exp >> 1; // Divide by 2
            base = (base * base) % mod;
        }
        return res;
    }
    
    // Fermat's Little Theorem for modular inverse
    private long modInverse(long n, int mod) {
        return power(n, mod - 2, mod);
    }
}