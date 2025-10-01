class Solution {
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] product = new int[m + n];

        // Multiply each digit and add to product array
        for (int i = m - 1; i >= 0; i--) {
            int d1 = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int d2 = num2.charAt(j) - '0';
                int sum = d1 * d2 + product[i + j + 1];

                product[i + j + 1] = sum % 10;
                product[i + j] += sum / 10;
            }
        }

        // Convert product array to string, skipping leading zeros
        StringBuilder sb = new StringBuilder();
        for (int p : product) {
            if (sb.length() == 0 && p == 0) continue;
            sb.append(p);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}
