import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class About extends JFrame{
	private JLabel a;
	private JButton back;
	
	
	public About()
	{
		String txt = "<html><center><H2>��� ����</H2><br>" +
                "���� ���� � ������ �������� �� ������������ �����.<br> ����� ������ ���� ����� �� ����������� - ������, " +
                "�� ����� - ���������.<br> ��� ������, ������ ������� � �����!<br>" +
                "����������:<br>" +
                "������ �����, ������, �����, ���� ��� ����������� �� ���������<br>" +
                "������ escape - ����� � ���� � �������� ���� + ���������� �������� ���������.<br>"+
                "��� ������� �������� ��������� ����� - shift(���������) �  alt(���������)<br>"+
                "<H3>�����������: ������� ��������</H3></center></html>";
		setLayout(new FlowLayout());
		a = new JLabel(txt);
		add(a);
		back = new JButton("����� ");
		add(back);
		back.addActionListener(new Menu());
	}
	
	
	

}
