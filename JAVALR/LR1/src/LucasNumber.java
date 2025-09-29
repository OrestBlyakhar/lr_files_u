import java.math.BigInteger;

public class LucasNumber {
    private int index; // Номер числа в послідовності
    private BigInteger value; // Значення числа Люка

    // Конструктор класу: зберігає індекс і обчислює значення
    public LucasNumber(int index){
        this.index = index;
        this.value = calculateLucas(index);
    }

    // Гетери для доступу до полів
    public int  getIndex() {
        return index;
    }

    public BigInteger getValue() {
        return value;
    }

    // Рекурсивна функція для обчислення числа Люка
//    private BigInteger calculateLucas(int n){
//        if (n == 1){
//            return BigInteger.ONE;
//        }
//        if(n == 2){
//            return BigInteger.valueOf(3);
//        }
//        return  calculateLucas(n - 1).add(calculateLucas(n - 2));
//    }

    // Ітеративна функція для обчислення чисел Люка
    private BigInteger calculateLucas(int n){
        if (n == 1) return BigInteger.ONE;
        if (n == 2) return BigInteger.valueOf(3);

        BigInteger prev2 = BigInteger.ONE;         // L(1) = 1
        BigInteger prev1 = BigInteger.valueOf(3);  // L(2) = 3
        BigInteger current = BigInteger.ZERO;

        for (int i = 3; i <= n; i++) {
            current = prev1.add(prev2);  // L(n) = L(n-1) + L(n-2)
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }

    // Перевірка, чи є число повним квадратом
    public boolean isPerfectSquare(){
        BigInteger sqrt = bigIntSqrtFloor(value);
        return sqrt.multiply(sqrt).equals(value);
    }

    //Обчислення цілої частини квадратного кореня для BigInteger
    private static BigInteger bigIntSqrtFloor(BigInteger x){
        BigInteger right = x, left = BigInteger.ZERO, mid;
        while(right.compareTo(left) > 0){
            mid = left.add(right).add(BigInteger.ONE).shiftRight(1);
            if(mid.multiply(mid).compareTo(x) <= 0) {
                left = mid;
            } else {
                right = mid.subtract(BigInteger.ONE);
            }
        }
        return left;
    }
}
