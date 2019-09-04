package com.automation.mobile.app;

class A {
	int a;

	public void showA() {
		System.out.println("aa");
	}
}

class B extends A {
	int c;

	public void showc() {
		System.out.println("bb");
	}
}

class C<T> {

}

public class Demo {

	public static void click(A t) {

		if (t instanceof B) {
			B b = (B) t;
			b.showc();
		} else {
			A a = (A) t;
			a.showA();
		}

	}

	public static void main(String[] args) {

		click(new A());
		click(new B());
	}

}
