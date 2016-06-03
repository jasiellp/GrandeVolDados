import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.grande.volume.dados.olx.atualizacao.Atualiar;

import teste.Captacao;

public class main_thread {

	public static void main(String[] args) throws Exception {

		Thread tCaptacao, tCaptacao2, tAtualiz,tAtualiz2;

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
					Atualiar.executar(40000,80000);
				} catch (ClientProtocolException e) {

				} catch (IOException e) {

				} catch (Exception e) {

				}
			}
		});
		
		
		tAtualiz2 = new Thread(new Runnable() {

			public void run() {

				try {
					Atualiar.executar(80000,120000);
				} catch (ClientProtocolException e) {

				} catch (IOException e) {

				} catch (Exception e) {

				}
			}
		});
		while (true) {

			if (!tCaptacao2.isAlive()) {
				tCaptacao2.start();
			}
			
			if (!tCaptacao.isAlive()) {
				tCaptacao.start();
			}

			

			if (!tAtualiz.isAlive()) {
				tAtualiz.start();
			}

			
			if (!tAtualiz2.isAlive()) {
				tAtualiz2.start();
			}

		}

	}
}
