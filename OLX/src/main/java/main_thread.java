import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.piloto.conSu;

import teste.Captacao;

public class main_thread {

	public static void main(String[] args) throws Exception {

		Thread tCaptacao, tCaptacao2, tAtualiz;

		tCaptacao = new Thread(new Runnable() {

			public void run() {

				try {
					Captacao.executar(2, 2000);
				} catch (ClientProtocolException e) {

				} catch (IOException e) {

				}
			}
		});

		tCaptacao2 = new Thread(new Runnable() {

			public void run() {

				try {
					Captacao.executar(200, 400);
				} catch (ClientProtocolException e) {

				} catch (IOException e) {

				}
			}
		});

		tAtualiz = new Thread(new Runnable() {

			public void run() {

				try {
					conSu.executar();
				} catch (ClientProtocolException e) {

				} catch (IOException e) {

				} catch (Exception e) {

				}
			}
		});
		while (true) {

		/*	if (!tCaptacao2.isAlive()) {
				tCaptacao2.start();
			}
*/
			if (!tCaptacao.isAlive()) {
				tCaptacao.start();
			}

			if (!tAtualiz.isAlive()) {
				tAtualiz.start();
			}

		}

	}
}
