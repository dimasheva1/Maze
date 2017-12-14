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
		String txt = "<html><center><H2>Про игру</H2><br>" +
                "Цель игры — пройти лабиринт за определенное время.<br> Успел пройти пока время не закончилось - победа, " +
                "не успел - поражение.<br> Все просто, выбрал уровень и играй!<br>" +
                "Управление:<br>" +
                "Кнопки вверх, вправо, влево, вниз для перемещения по лабиринту<br>" +
                "Кнопка escape - выход в меню с игрового поля + сохранение текущего лабиринта.<br>"+
                "При желании меняется громкость звука - shift(увеличить) и  alt(уменьшить)<br>"+
                "<H3>Разработчик: Дмитрий Шевченко</H3></center></html>";
		setLayout(new FlowLayout());
		a = new JLabel(txt);
		add(a);
		back = new JButton("Назад ");
		add(back);
		back.addActionListener(new Menu());
	}
	
	
	

}
