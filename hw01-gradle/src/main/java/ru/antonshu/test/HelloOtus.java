package ru.antonshu.test;

import com.google.common.base.Optional;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class HelloOtus {

    public static void main(String[] args) {
        Optional<Integer> number = Optional.fromNullable(null);
        System.out.println("Nullable number: " + number.or(1));

        System.out.println("is standard HashCode equals? " + (new Anton(3).hashCode() == new Anton(3).hashCode()));
        System.out.println("is override HashCode equals? " + (new GuavaAnton(3).hashCode() == new GuavaAnton(3).hashCode()));
    }

    private static class Anton {
        int age;

        public Anton(int age) {
            this.age = age;
        }
    }

    private static class GuavaAnton {
        int age;

        public GuavaAnton(int age) {
            this.age = age;
        }

        @Override
        public int hashCode() {
            HashFunction hash = Hashing.sha256();
            HashCode hashCode = hash.newHasher()
                    .putInt(this.age)
                    .hash();

            return hashCode.asInt();
        }
    }
}
