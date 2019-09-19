public class Program {

    public static boolean hasOne(int n) {
        int a = n % 100000;
        a = a / 10000;
        int b = n % 10000;
        b = b / 1000;
        int c = n % 1000;
        c = c / 100;
        int d = n % 100;
        d = d / 10;
        int e = n % 10;

        if (n % 10 == 1) {
            return true;
        } else if (n / 10 == 1) {
            return true;
        } else if (n > 1000) {
            n = n % 1000;
            if (n / 100 == 1) {
                return true;
            }
        } else if (n > 100) {
            n = n % 100;
            if (n % 10 == 1) {
                return true;
            } else if (n / 10 == 1) {
                return true;
            }
        }
        return false;
    }

    public static boolean dividesSelf(int n) {
        if (n > 100) {
            int c = n % 1000;
            c = c / 100;
            int d = n % 100;
            d = d / 10;
            int e = n % 10;
            if (n % c == 0) {
                if (n % d == 0) {
                    if (n % e == 0) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        if (n > 10) {
            int d = n % 100;
            d = d / 10;
            int e = n % 10;
            if (n % d == 0) {
                if (n % e == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 213;
        dividesSelf(n);
        hasOne(n);
    }
}
