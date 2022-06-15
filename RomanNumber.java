public enum RomanNumber {
        I(1),II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10);

        private int number;

        RomanNumber(int num){
            this.number = num;
        }
        /* Оперция получения числового значения */
        public int getNumber() {
            return number;
        }

        /* Оперции приведения типов */
        public static RomanNumber toRoman(int num){
            return values()[num-1];
        }
        public static RomanNumber toRoman(String str){
            return RomanNumber.valueOf(str);
        }

    /* Математические операции */
        public static RomanNumber sum(RomanNumber lhs, RomanNumber rhs){
            return toRoman(lhs.getNumber() + rhs.getNumber());
        }
        public static RomanNumber sub(RomanNumber lhs, RomanNumber rhs){
            return toRoman(lhs.getNumber() - rhs.getNumber());
        }
        public static RomanNumber mult(RomanNumber lhs, RomanNumber rhs){
            return toRoman(lhs.getNumber() * rhs.getNumber());
        }
        public static RomanNumber div(RomanNumber lhs, RomanNumber rhs){
            return toRoman(lhs.getNumber() / rhs.getNumber());
        }
    }

