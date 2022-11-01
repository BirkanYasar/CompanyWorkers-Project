import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JSeparator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EmployeesPanel extends JDialog {

	public DefaultTableModel model = null;
	WorkerTransactions workerTransactions = new WorkerTransactions();
	
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField searchBar;
	private JSeparator separator;

	public static void main(String[] args) {
		try {
			EmployeesPanel dialog = new EmployeesPanel();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void getEmployee() {
		
		model.setRowCount(0);//employee tablosu her başlatıldıgında bosaltılıp alttaki metodlar sayiesinde tekrar doldurulmak gerektiği icin yazıldı(Refresh)
		
		ArrayList<Employee> list = new ArrayList<Employee>();
		
		list = workerTransactions.getEmployeeList();
		
		if(list != null) {//DB den gelen calisan bilgilerini getiren metod null deger donebilir. Bunu kontrol etmek icin bu metodu yazdik
			
			for(Employee emp : list) {
				
				Object[] add = {emp.getId(),emp.getName(),emp.getSurname(),emp.getDepartment(),emp.getSalary()};
				
				model.addRow(add);
			}
			
		}
		
	}
	
	public void dynamicSearch(String src) {
		
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		
		table.setRowSorter(tr);
		
		tr.setRowFilter(RowFilter.regexFilter(src));
		
	}

	public EmployeesPanel() {
		
		setTitle("Employee Table");
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane employeesTable = new JScrollPane();
		employeesTable.setBounds(49, 168, 667, 366);
		contentPanel.add(employeesTable);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		employeesTable.setViewportView(table);	

		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Surname", "Department", "Salary"
			}	
		));
		
		searchBar = new JTextField();
		searchBar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String search = searchBar.getText();//buraya yazilan arama kelimesi degiskene atanacak ve bu degisken arayici metodun icine gonderilerek veriye erisim saglanmaya calisilacak.
				
				dynamicSearch(search);
				
			}
		});
		searchBar.setBounds(49, 32, 667, 20);
		contentPanel.add(searchBar);
		searchBar.setColumns(10);
		
		separator = new JSeparator();
		separator.setBounds(49, 61, 667, 37);
		contentPanel.add(separator);
		
		model = (DefaultTableModel)table.getModel();
		
		getEmployee();
	}
}
