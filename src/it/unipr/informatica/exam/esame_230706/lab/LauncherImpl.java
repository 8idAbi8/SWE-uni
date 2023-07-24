package it.unipr.informatica.exam.esame_230706.lab;

public class LauncherImpl implements Launcher {

	private final Object mutexMain = new Object();
	private final Object mutexN = new Object();

	private final int N = 10;
	private final int K = Main.K;

	private int attivitaConcluse = 0;
	private int counterN = 0;
	private int attivitaCorrente = 0;

	InnerTask[] tasks = new InnerTask[K];

	public void createActivity(int K) {
		for (int i = 0; i < K; i++) {
			tasks[i] = new InnerTask(i);

			launch(tasks[i]);
		}

		// main thread aspetta che tutte le attivita vengano eseguite per poi terminare
		synchronized (mutexMain) {
			try {
				mutexMain.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void launch(Task task) {

		// eseguo il codice in un thread, in modo che launch ritorni immediatamente
		new Thread(() -> {

			synchronized (mutexN) {
				counterN++;

				if (counterN >= N) {
					for (int i = 0; i < K; i++) {
						mutexN.notify();	
					}

					counterN -= N;
				}

				while (counterN < N && attivitaCorrente < K) {
					try {
						mutexN.wait();						

						new Thread(tasks[attivitaCorrente++]).start();

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}


	private class InnerTask implements Task {
		private int id;

		public InnerTask(int id) {
			this.id = id;
		}

		@Override
		public void run() {

			System.out.println("Attivita': " + id);

			long rand = (long) Math.floor(Math.random() * 51 + 100);

			try {
				Thread.sleep(rand);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			attivitaConcluse++;

			synchronized (mutexMain) {
				if (attivitaConcluse == (K - 1)) {
					mutexMain.notify();
				}
			}
		}
	}
}
