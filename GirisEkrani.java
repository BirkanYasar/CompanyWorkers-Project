import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.DropMode;
import java.awt.ComponentOrientation;
import java.awt.Component;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.awt.Rectangle;

public class GirisEkrani extends JFrame {
	
	//CalisanIslemler islemler = new CalisanIslemler();

	ConnectionAttempt ca1 = new ConnectionAttempt();
	
	private JPanel contentPane;
	private JTextField kullanici_alani_adi;
	private JPasswordField parola_alani;
	private JLabel mesaj_yazisi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GirisEkrani frame = new GirisEkrani();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GirisEkrani() {
		setBounds(new Rectangle(400, 200, 0, 0));
		setTitle("Giriş Ekranı");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		kullanici_alani_adi = new JTextField();
		kullanici_alani_adi.setBounds(237, 81, 173, 19);
		contentPane.add(kullanici_alani_adi);
		kullanici_alani_adi.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Kullanıcı Adı:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(116, 80, 111, 20);
		contentPane.add(lblNewLabel);
		
		parola_alani = new JPasswordField();
		parola_alani.setBounds(237, 137, 173, 19);
		contentPane.add(parola_alani);
		
		JLabel lblNewLabel_1 = new JLabel("Parola:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(116, 136, 111, 20);
		contentPane.add(lblNewLabel_1);
		
		mesaj_yazisi = new JLabel("");
		mesaj_yazisi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mesaj_yazisi.setForeground(new Color(128, 0, 0));
		mesaj_yazisi.setBounds(116, 229, 374, 28);
		contentPane.add(mesaj_yazisi);
		
		JButton GirisYap_butonu = new JButton("GiriŞ Yap");
		GirisYap_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mesaj_yazisi.setText("");
				
				String kullaniciAdi = kullanici_alani_adi.getText();
				String parola = new String (parola_alani.getPassword());
				
				if((kullaniciAdi == ca1.getUsername()) && (parola == ca1.getPassword())) {
					mesaj_yazisi.setText("Giriş Başarılı..");
				}else {
					mesaj_yazisi.setText("Giriş Başarısız... Lütfen Tekrar deneyiniz...");
				}
				
				/*mesaj_yazisi.setText("");
				String kullaniciAdi = kullanici_alani_adi.getText();
				String parola = new String (parola_alani.getPassword());
				
				boolean girisBasarili = islemler.girisYap(kullaniciAdi, parola);
				
				if(girisBasarili) {
					mesaj_yazisi.setText("Giriş Başarılı...");
					
					
				}else {
					mesaj_yazisi.setText("Giriş Başarısız... Lütfen Tekrar deneyiniz...");
				}*/
				
			}
		});
		GirisYap_butonu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GirisYap_butonu.setBounds(251, 279, 111, 28);
		contentPane.add(GirisYap_butonu);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
