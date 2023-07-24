
/**
 * @author idabi8 (github)
 * 
 * @since Jun 6, 2023
 *
 * This project is licensed under the MIT license.
 *
 * The MIT License
 * Copyright © 2023 Idlir Abilaliaj
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package it.unipr.informatica.exam.esame_230606.es2.versioniCorrette;

import it.unipr.informatica.exam.esame_230606.es2.A;

public class AImpl3 implements A {

	/* in base alle specifiche del esercizio è megilo usare AImpl.class 
	 * come oggetto di sincronizzazione */
	private final Object mutex = new Object();

	@Override
	public Thread m1(Object o) {

		Thread t = new Thread(() -> {
			synchronized (mutex) {
				m2(o);
			}			
		});
		t.start();
		
		return t;
	}

	private void m2(Object o) {
		System.out.println(Thread.currentThread().getName()  + ", m2(o): " + o);
	}
}
